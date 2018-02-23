package br.com.sidtmcafe.controller;

import br.com.sidtmcafe.componentes.AlertMensagem;
import br.com.sidtmcafe.componentes.Tarefa;
import br.com.sidtmcafe.componentes.Variaveis;
import br.com.sidtmcafe.service.*;
import br.com.sidtmcafe.interfaces.FormularioModelo;
import br.com.sidtmcafe.model.dao.*;
import br.com.sidtmcafe.model.vo.*;
import br.com.sidtmcafe.view.ViewCadastroEmpresa;
import com.jfoenix.controls.*;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.application.Platform;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.util.Pair;

import java.net.URL;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.ResourceBundle;

import static br.com.sidtmcafe.interfaces.Constants.DTFORMAT_DATA;
import static br.com.sidtmcafe.interfaces.Constants.DTFORMAT_DATAHORA;
import static br.com.sidtmcafe.interfaces.Constants.DTFORMAT_HORA;

public class ControllerCadastroEmpresa extends Variaveis implements Initializable, FormularioModelo {

    public AnchorPane painelViewCadastroEmpresa;
    public TitledPane tpnCadastroEmpresa;
    public JFXTextField txtPesquisa;
    public JFXTreeTableView<TabEmpresaVO> ttvEmpresa;
    public JFXComboBox cboFiltroPesquisa;
    public Label lblRegistrosLocalizados;
    public TitledPane tpnDadosCadastrais;
    public JFXComboBox cboClassificacaoJuridica;
    public JFXTextField txtCNPJ;
    public JFXTextField txtIE;
    public JFXComboBox<SisSituacaoSistemaVO> cboSituacaoSistema;
    public JFXTextField txtRazao;
    public JFXTextField txtFantasia;
    public JFXCheckBox chkIsCliente;
    public JFXCheckBox chkIsFornecedor;
    public JFXCheckBox chkIsTransportadora;
    public Label lblDataCadastro;
    public Label lblDataCadastroDiff;
    public Label lblDataAtualizacao;
    public Label lblDataAtualizacaoDiff;
    public JFXListView<TabEnderecoVO> listEndereco;
    public JFXTextField txtEndCEP;
    public JFXTextField txtEndLogradouro;
    public JFXTextField txtEndNumero;
    public JFXTextField txtEndComplemento;
    public JFXTextField txtEndBairro;
    public JFXComboBox<SisUFVO> cboEndUF;
    public JFXComboBox<SisMunicipioVO> cboEndMunicipio;
    public JFXTextField txtEndPontoReferencia;
    public JFXListView listAtividadePrincipal;
    public JFXListView listAtividadeSecundaria;
    public Label lblDataAbertura;
    public Label lblDataAberturaDiff;
    public Label lblNaturezaJuridica;
    public JFXTreeTableView ttvDetalheReceita;
    public JFXTabPane tpnContatoPrazosCondicoes;
    public JFXListView<TabEmailHomePageVO> listHomePage;
    public JFXListView<TabEmailHomePageVO> listEmail;
    public JFXListView<TabTelefoneVO> listTelefone;
    public JFXListView<TabContatoVO> listContatoNome;
    public JFXListView<TabEmailHomePageVO> listContatoHomePage;
    public JFXListView<TabEmailHomePageVO> listContatoEmail;
    public JFXListView<TabTelefoneVO> listContatoTelefone;
    public JFXComboBox cboPrazoTipoPagto;
    public JFXTextField txtPrazoDias;
    public JFXTextField txtPrazoLimite;
    public JFXCheckBox chkPrazoNfe;
    public JFXCheckBox chkPrazoRecibo;


    FormatadorDeDados formatCNPJ_CPF, formatIE;

    @Override
    public void fechar() {

    }

    @Override
    public void preencherObjetos() {
        listaTarefas = new ArrayList<>();
        criarTabelas();
        carregaListas();
        preencherCombos();
        preencherTabelas();

        new Tarefa().tarefaAbreCadastroEmpresa(this, listaTarefas);

        PersonalizarCampos.fieldMaxLen(painelViewCadastroEmpresa);
        PersonalizarCampos.maskFields(painelViewCadastroEmpresa);

        formatCNPJ_CPF = new FormatadorDeDados();
        formatCNPJ_CPF.maskField(txtCNPJ, FormatadorDeDados.gerarMascara("cnpj", 0, "#"));
        formatIE = new FormatadorDeDados();
        formatIE.maskField(txtIE, FormatadorDeDados.gerarMascara("ie", 0, "#"));
        txtPesquisa.requestFocus();
    }

    @Override
    public void fatorarObjetos() {
        FormatadorDeDados.fatorarColunaCheckBox(colunaIsCliente);
        FormatadorDeDados.fatorarColunaCheckBox(colunaIsFornecedor);
        FormatadorDeDados.fatorarColunaCheckBox(colunaIsTransportadora);
    }

    @Override
    public void escutarTeclas() {

        ttvEmpresa.getSelectionModel().selectedItemProperty().addListener((ov, o, n) -> {
            if (n == null) return;
            setTtvEmpresaVO(n.getValue());
            exibirDadosEmpresa();
        });

        ControllerPrincipal.ctrlPrincipal.tabPaneViewPrincipal.getSelectionModel().selectedItemProperty().addListener((ov, o, n) -> {
            if (ControllerPrincipal.ctrlPrincipal.tabPaneViewPrincipal.getTabs().size() <= 0) return;
            if (n.getText().equals(ViewCadastroEmpresa.getTituloJanela())) {
                ControllerPrincipal.ctrlPrincipal.painelViewPrincipal.addEventFilter(KeyEvent.KEY_RELEASED, event -> {
                    switch (event.getCode()) {
                        case F1:
                            if (!getStatusBarFormulario().contains(event.getCode().toString())) break;
                            setStatusFormulario("Incluir");
                            setTtvEmpresaVO(new TabEmpresaVO());
                            break;
                        case F2:
                            if (!getStatusBarFormulario().contains(event.getCode().toString())) break;
                            if (!validarDadosCadastrais()) break;

                            salvarEmpresa();
                            setTtvEmpresaVO(new TabEmpresaDAO().getEmpresaVO(getTtvEmpresaVO().getId()));
                            empresaVOObservableList.set(indexObservableEmpresa, getTtvEmpresaVO());

                            setStatusFormulario("Pesquisa");
                            carregarPesquisaEmpresas(txtPesquisa.getText());
                            break;
                        case F3:
                            if (!getStatusBarFormulario().contains(event.getCode().toString())) break;
                            switch (getStatusFormulario().toLowerCase()) {
                                case "incluir":
                                    if (new AlertMensagem("Cancelar inclusão", USUARIO_LOGADO_APELIDO
                                            + ", deseja cancelar inclusão no cadastro de empresa?",
                                            "ic_cadastro_empresas_white_24dp.png").getRetornoAlert_YES_NO().get() == ButtonType.NO)
                                        return;
                                    PersonalizarCampos.clearField((AnchorPane) tpnDadosCadastrais.getContent());
                                    break;
                                case "editar":
                                    if (new AlertMensagem("Cancelar edição", USUARIO_LOGADO_APELIDO
                                            + ", deseja cancelar edição do cadastro de empresa?",
                                            "ic_cadastro_empresas_white_24dp.png").getRetornoAlert_YES_NO().get() == ButtonType.NO)
                                        return;
                                    setTtvEmpresaVO(new TabEmpresaDAO().getEmpresaVO(getTtvEmpresaVO().getId()));
                                    exibirDadosEmpresa();
                                    break;
                            }
                            setStatusFormulario("Pesquisa");
                            break;
                        case F4:
                            if ((!getStatusBarFormulario().contains(event.getCode().toString())) || (getTtvEmpresaVO() == null))
                                break;
                            indexObservableEmpresa = empresaVOObservableList.indexOf(getTtvEmpresaVO());
                            setStatusFormulario("Editar");
                            break;
                        case F5:
                            if (!getStatusBarFormulario().contains(event.getCode().toString())) break;
                            if (!validarDadosCadastrais()) break;

                            salvarEmpresa();
                            setTtvEmpresaVO(new TabEmpresaDAO().getEmpresaVO(getTtvEmpresaVO().getId()));
                            empresaVOObservableList.set(indexObservableEmpresa, getTtvEmpresaVO());

                            setStatusFormulario("Pesquisa");
                            carregarPesquisaEmpresas(txtPesquisa.getText());
                            exibirDadosEmpresa();
                            break;
                        case F6:
                            if (getStatusFormulario().toLowerCase().equals("pesquisa") || (!event.isShiftDown())) break;
                            keyShiftF6();
                            break;
                        case F7:
                            if (!getStatusBarFormulario().contains(event.getCode().toString())) break;
                            txtPesquisa.requestFocus();
                            break;
                        case F8:
                            if (!getStatusBarFormulario().contains(event.getCode().toString())) break;
                            cboFiltroPesquisa.requestFocus();
                            break;
                        case F12:
                            if (!getStatusBarFormulario().contains(event.getCode().toString())) break;
                            ControllerPrincipal.ctrlPrincipal.tabPaneViewPrincipal.getTabs().remove(ControllerPrincipal.ctrlPrincipal.tabPaneViewPrincipal.getSelectionModel().getSelectedItem());
                            break;
                        case HELP:
                            if (getStatusFormulario().toLowerCase().equals("pesquisa")) return;
                            keyInsert();
                            break;
                        case DELETE:
                            if (getStatusFormulario().toLowerCase().equals("pesquisa")) return;
                            keyDelete();
                            break;
                    }
                });
            }
        });

        txtPesquisa.textProperty().addListener((ov, o, n) -> {
            carregarPesquisaEmpresas(n);
        });

        txtPesquisa.addEventHandler(KeyEvent.KEY_RELEASED, event -> {
            if (event.getCode() == KeyCode.ENTER) {
                ttvEmpresa.requestFocus();
                ttvEmpresa.getSelectionModel().select(0);
                ttvEmpresa.getFocusModel().focus(0);
            }
        });

        cboClassificacaoJuridica.getSelectionModel().selectedIndexProperty().addListener((ov, o, n) -> {
            if (n.intValue() == 1) {
                txtIE.setPromptText("IE");
                txtRazao.setPromptText("Razão");
                txtFantasia.setPromptText("Fantasia");
                txtCNPJ.setPromptText("C.N.P.J.");
                formatCNPJ_CPF.setMascara("cnpj");
                txtCNPJ.setText(FormatadorDeDados.getCampoFormatado(txtCNPJ.getText(), "cnpj"));
            } else {
                txtIE.setPromptText("RG");
                txtRazao.setPromptText("Nome");
                txtFantasia.setPromptText("Apelido");
                txtCNPJ.setPromptText("C.P.F.");
                formatCNPJ_CPF.setMascara("cpf");
                txtCNPJ.setText(FormatadorDeDados.getCampoFormatado(txtCNPJ.getText(), "cpf"));
            }
        });

        txtCNPJ.addEventHandler(KeyEvent.KEY_RELEASED, event -> {
            if ((event.getCode() == KeyCode.ENTER)) {
                String valCnpj = txtCNPJ.getText().replaceAll("[\\-/. \\[\\]]", "");
                int idTipBusca = cboClassificacaoJuridica.getSelectionModel().getSelectedIndex();
                String tipBusca = "C.N.P.J.";
                if (idTipBusca == 0) tipBusca = "C.P.F.";

                if ((valCnpj.length() != 11 && valCnpj.length() != 14) & (!ValidadorDeDados.isCnpjCpfValido(valCnpj))) {
                    new AlertMensagem("Dado inválido!", USUARIO_LOGADO_APELIDO + ", o " + tipBusca + ": " + txtCNPJ.getText() + " é inválido!",
                            "ic_web_service_err_white_24dp").getRetornoAlert_OK();
                    txtCNPJ.requestFocus();
                    txtCNPJ.selectAll();
                }

                if (idTipBusca == 1) {
                    listaTarefas = new ArrayList<>();
                    listaTarefas.add(new Pair("pesquisa cnpj", "Pesquisando C.N.P.J: [" + txtCNPJ.getText() + "]"));

                    WsCnpjReceitaWsVO wsCnpjReceitaWsVO = new Tarefa().tarefaWsCnpjReceitaWs(listaTarefas);

                    if (wsCnpjReceitaWsVO == null) {
                        txtCNPJ.requestFocus();
                        txtCNPJ.selectAll();
                    } else {
                        exibirRetorno_WsCnpjReceitaWs(wsCnpjReceitaWsVO);
                        //ttvEmpresaVO = new TabEmpresaDAO().getEmpresaVO(wsCnpjReceitaWsVO, ttvEmpresaVO);
                    }
                    //exibirDadosEmpresa();
                    listEndereco.getSelectionModel().select(0);
                    //exibirDadosEndereco();
                }
            }
        });

        listEndereco.getSelectionModel().selectedIndexProperty().addListener((ov, o, n) -> {
            if (!getStatusFormulario().toLowerCase().equals("pesquisa"))
                if ((o.intValue() >= 0) && (n.intValue() != o.intValue()) && (n.intValue() >= 0))
                    try {
                        guardarEndereco(o.intValue());
                    } catch (Exception ex) {
                        if (!(ex instanceof IndexOutOfBoundsException))
                            ex.printStackTrace();
                    }
            if (n == null || n.intValue() < 0) return;
            exibirDadosEndereco();
        });

        txtEndCEP.addEventHandler(KeyEvent.KEY_RELEASED, event -> {
            if (event.getCode() == KeyCode.ENTER) {
                String valCep = txtEndCEP.getText().replaceAll("[\\-/. \\[\\]]", "");
                if (valCep.length() != 8) {
                    return;
                }
                listaTarefas = new ArrayList<>();
                listaTarefas.add(new Pair("pesquisa cep", "Pesquisando C.E.P.: [" + txtEndCEP.getText() + "]"));

                WsCepPostmonVO wsCepPostmonVO = new Tarefa().tarefaWsCepPostmon(listaTarefas);
                if (wsCepPostmonVO == null) {
                    txtEndCEP.requestFocus();
                    txtEndCEP.selectAll();
                } else {
                    exibirRetorno_WsCepPostmon(wsCepPostmonVO);
                    txtEndNumero.requestFocus();
                }
            }
        });

        cboEndUF.getSelectionModel().selectedItemProperty().addListener((ov, o, n) -> {
            if (n == null) return;
            carregarPesquisaMunicipios(n.getId());
            formatIE.setMascara("ie" + n.getSigla());
            txtIE.setText(FormatadorDeDados.getCampoFormatado(txtIE.getText(), "ie" + n.getSigla()));
        });

        listContatoNome.getSelectionModel().selectedIndexProperty().addListener((ov, o, n) -> {
            if (n == null || n.intValue() < 0) return;
            exiberDadosContato();
        });


    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        preencherObjetos();
        fatorarObjetos();
        escutarTeclas();
        setStatusFormulario("Pesquisa");
        Platform.runLater(() -> {
            painelViewCadastroEmpresa.fireEvent(ExecutaComandoTecladoMouse.pressTecla(KeyCode.F7));
        });
    }

    int indexObservableEmpresa = 0;
    TabEmpresaVO ttvEmpresaVO;
    List<TabEnderecoVO> ttvEnderecoVO;
    List<TabEmailHomePageVO> ttvEmailHomePageVO;
    List<TabTelefoneVO> ttvTelefoneVO;
    TabContatoVO ttvContatoVO;
    List<TabEmailHomePageVO> ttvContatoEmailHomePageVO;
    List<TabTelefoneVO> ttvContatoTelefoneVO;
    List<TabEmpresa_DetalheReceitaFederalVO> ttvDetalheReceitaFederalVO;

    List<Pair> listaTarefas;

    ObservableList<TabEmpresaVO> empresaVOObservableList;
    FilteredList<TabEmpresaVO> empresaVOFilteredList;
    ObservableList<SisMunicipioVO> municipioVOObservableList;
    FilteredList<SisMunicipioVO> municipioVOFilteredList;
    ObservableList<TabEmailHomePageVO> emailHomePageVOObservableList;
    FilteredList<TabEmailHomePageVO> emailVOFilteredList;
    FilteredList<TabEmailHomePageVO> homePageVOFilteredList;
    ObservableList<TabEmailHomePageVO> contatoEmailHomePageVOObservableList;
    FilteredList<TabEmailHomePageVO> contatoEmailVOFilteredList;
    FilteredList<TabEmailHomePageVO> contatoHomePageVOFilteredList;
    List<TabContatoVO> contatoVOList;
    List<SisTipoEnderecoVO> tipoEnderecoVOList;
    List<SisTelefoneOperadoraVO> telefoneOperadoraVOList;
    List<TabCargoVO> cargoVOList;
    List<SisMunicipioVO> municipioVOList;
    ObservableList<TabEmpresa_DetalheReceitaFederalVO> empresa_detalheReceitaFederalVOObservableList;
    FilteredList<TabEmpresa_DetalheReceitaFederalVO> atividadePrincipal_detalheReceitaFederalVOFilteredList;
    FilteredList<TabEmpresa_DetalheReceitaFederalVO> atividadeSecundaria_detalheReceitaFederalVOFilteredList;
    FilteredList<TabEmpresa_DetalheReceitaFederalVO> qsa_detalheReceitaFederalVOFilteredList;

    JFXTreeTableColumn<TabEmpresaVO, Integer> colunaId;
    JFXTreeTableColumn<TabEmpresaVO, String> colunaCnpj;
    JFXTreeTableColumn<TabEmpresaVO, String> colunaIe;
    JFXTreeTableColumn<TabEmpresaVO, String> colunaRazao;
    JFXTreeTableColumn<TabEmpresaVO, String> colunaFantasia;
    JFXTreeTableColumn<TabEmpresaVO, String> colunaEndereco;
    JFXTreeTableColumn<TabEmpresaVO, String> colunaEndLogradouro;
    JFXTreeTableColumn<TabEmpresaVO, String> colunaEndNumero;
    JFXTreeTableColumn<TabEmpresaVO, String> colunaEndComplemento;
    JFXTreeTableColumn<TabEmpresaVO, String> colunaEndBairro;
    JFXTreeTableColumn<TabEmpresaVO, String> colunaEndUFMunicipio;
    JFXTreeTableColumn<TabEmpresaVO, Boolean> colunaIsCliente;
    JFXTreeTableColumn<TabEmpresaVO, Boolean> colunaIsFornecedor;
    JFXTreeTableColumn<TabEmpresaVO, Boolean> colunaIsTransportadora;
    JFXTreeTableColumn<TabEmpresa_DetalheReceitaFederalVO, String> colunaQsaKey;
    JFXTreeTableColumn<TabEmpresa_DetalheReceitaFederalVO, String> colunaQsaValue;

    int qtdRegistrosLocalizados = 0;
    String statusFormulario, statusBarFormulario;

    static String STATUSBARPESQUISA = "[F1-Novo]  [F3-Excluir]  [F4-Editar]  [F7-Pesquisar]  [F8-Filtro pesquisa]  [F12-Sair]  ";
    static String STATUSBAREDITAR = "[F3-Cancelar edição]  [F5-Atualizar]  ";
    static String STATUSBARINCLUIR = "[F2-Incluir]  [F3-Cancelar inclusão]  ";

    public TabEmpresaVO getTtvEmpresaVO() {
        return ttvEmpresaVO;
    }

    public void setTtvEmpresaVO(TabEmpresaVO ttvEmpresaVO) {
        if (ttvEmpresaVO == null)
            ttvEmpresaVO = new TabEmpresaVO();
        this.ttvEmpresaVO = ttvEmpresaVO;

        setTtvEnderecoVO(getTtvEmpresaVO().getEnderecoVOList());
        setTtvEmailHomePageVO(getTtvEmpresaVO().getEmailHomePageVOList());
        setTtvTelefoneVO(getTtvEmpresaVO().getTelefoneVOList());
        setTtvDetalheReceitaFederalVO(getTtvEmpresaVO().getDetalheReceitaFederalVOList());
    }

    public List<TabEnderecoVO> getTtvEnderecoVO() {
        return ttvEnderecoVO;
    }

    public void setTtvEnderecoVO(List<TabEnderecoVO> ttvEnderecoVO) {
        if (ttvEnderecoVO == null)
            ttvEnderecoVO = new ArrayList<>();
        this.ttvEnderecoVO = ttvEnderecoVO;
    }

    public List<TabEmailHomePageVO> getTtvEmailHomePageVO() {
        return ttvEmailHomePageVO;
    }

    public void setTtvEmailHomePageVO(List<TabEmailHomePageVO> ttvEmailHomePageVO) {
        if (ttvEmailHomePageVO == null)
            ttvEmailHomePageVO = new ArrayList<>();
        this.ttvEmailHomePageVO = ttvEmailHomePageVO;
    }

    public List<TabTelefoneVO> getTtvTelefoneVO() {
        return ttvTelefoneVO;
    }

    public void setTtvTelefoneVO(List<TabTelefoneVO> ttvTelefoneVO) {
        if (ttvTelefoneVO == null)
            ttvTelefoneVO = new ArrayList<>();
        this.ttvTelefoneVO = ttvTelefoneVO;
    }

    public TabContatoVO getTtvContatoVO() {
        return ttvContatoVO;
    }

    public void setTtvContatoVO(TabContatoVO ttvContatoVO) {
        if (ttvContatoVO == null)
            ttvContatoVO = new TabContatoVO();
        this.ttvContatoVO = ttvContatoVO;

        setTtvContatoEmailHomePageVO(ttvContatoVO.getEmailHomePageVOList());
        setTtvContatoTelefoneVO(ttvContatoVO.getTelefoneVOList());
    }

    public List<TabEmailHomePageVO> getTtvContatoEmailHomePageVO() {
        return ttvContatoEmailHomePageVO;
    }

    public void setTtvContatoEmailHomePageVO(List<TabEmailHomePageVO> ttvContatoEmailHomePageVO) {
        if (ttvContatoEmailHomePageVO == null)
            ttvContatoEmailHomePageVO = new ArrayList<>();
        this.ttvContatoEmailHomePageVO = ttvContatoEmailHomePageVO;
    }

    public List<TabTelefoneVO> getTtvContatoTelefoneVO() {
        return ttvContatoTelefoneVO;
    }

    public void setTtvContatoTelefoneVO(List<TabTelefoneVO> ttvContatoTelefoneVO) {
        if (ttvContatoTelefoneVO == null)
            ttvContatoTelefoneVO = new ArrayList<>();
        this.ttvContatoTelefoneVO = ttvContatoTelefoneVO;
    }

    public List<TabEmpresa_DetalheReceitaFederalVO> getTtvDetalheReceitaFederalVO() {
        return ttvDetalheReceitaFederalVO;
    }

    public void setTtvDetalheReceitaFederalVO(List<TabEmpresa_DetalheReceitaFederalVO> ttvDetalheReceitaFederalVO) {
        this.ttvDetalheReceitaFederalVO = ttvDetalheReceitaFederalVO;
    }

    public int getQtdRegistrosLocalizados() {
        return qtdRegistrosLocalizados;
    }

    public void setQtdRegistrosLocalizados(int qtdRegistrosLocalizados) {
        this.qtdRegistrosLocalizados = qtdRegistrosLocalizados;
        atualizaLblRegistrosLocalizados();
    }

    public String getStatusFormulario() {
        return statusFormulario;
    }

    public void setStatusFormulario(String statusFormulario) {
        this.statusFormulario = statusFormulario;
        atualizaLblRegistrosLocalizados();
        setStatusBarFormulario(statusFormulario);
    }

    public String getStatusBarFormulario() {
        return statusBarFormulario;
    }

    public void setStatusBarFormulario(String statusFormulario) {
        if (statusFormulario.toLowerCase().contains("incluir")) {
            PersonalizarCampos.desabilitaCampos((AnchorPane) tpnCadastroEmpresa.getContent(), true);
            PersonalizarCampos.desabilitaCampos((AnchorPane) tpnDadosCadastrais.getContent(), false);
            PersonalizarCampos.clearField((AnchorPane) tpnDadosCadastrais.getContent());
            cboClassificacaoJuridica.requestFocus();
            cboClassificacaoJuridica.getSelectionModel().select(0);
            cboClassificacaoJuridica.getSelectionModel().select(1);
            this.statusBarFormulario = STATUSBARINCLUIR;
        } else if (statusFormulario.toLowerCase().contains("editar")) {
            PersonalizarCampos.desabilitaCampos((AnchorPane) tpnCadastroEmpresa.getContent(), true);
            PersonalizarCampos.desabilitaCampos((AnchorPane) tpnDadosCadastrais.getContent(), false);
            txtCNPJ.requestFocus();
            this.statusBarFormulario = STATUSBAREDITAR;
        } else if (statusFormulario.toLowerCase().contains("pesquisa")) {
            PersonalizarCampos.desabilitaCampos((AnchorPane) tpnCadastroEmpresa.getContent(), false);
            PersonalizarCampos.desabilitaCampos((AnchorPane) tpnDadosCadastrais.getContent(), true);
            txtPesquisa.requestFocus();
            this.statusBarFormulario = STATUSBARPESQUISA;
        }
        ControllerPrincipal.ctrlPrincipal.atualizarTeclasStatusBar(statusBarFormulario);
    }

    void atualizaLblRegistrosLocalizados() {
        lblRegistrosLocalizados.setText("[" + getStatusFormulario() + "]  " + String.valueOf(getQtdRegistrosLocalizados()) + " registro(s) localizado(s).");
    }

    void criarTabelas() {
        listaTarefas.add(new Pair("criarTabelaEmpresa", "criando tabela empresas"));
        criarTabelaQsaReceita();
    }

    void carregaListas() {
        //listaTarefas.add(new Pair("carregarTodosMunicipios", "carregando listas de municipios"));
        listaTarefas.add(new Pair("carregarTabCargo", "carregando lista cargos"));
        listaTarefas.add(new Pair("carregarSisTipoEndereco", "carregando lista tipo endereço"));
        listaTarefas.add(new Pair("carregarSisTelefoneOperadora", "carregando lista operadoras telefone"));
        listaTarefas.add(new Pair("carregarListaEmpresa", "carregando lista de empresas"));
    }

    void preencherCombos() {
        listaTarefas.add(new Pair("preencherCboEndUF", "preenchendo dados UF"));
        listaTarefas.add(new Pair("preencherCboSituacaoSistema", "preenchendo situações do sistema"));
        listaTarefas.add(new Pair("preencherCboFiltroPesquisa", "preenchendo filtros pesquisa"));
        listaTarefas.add(new Pair("preencherCboClassificacaoJuridica", "preenchendo classificações jurídicas"));
    }

    void preencherTabelas() {
        listaTarefas.add(new Pair("preencherTabelaEmpresa", "preenchendo tabela empresa"));
    }

    public void criarTabelaEmpresa() {
        try {
            Label lblId = new Label("id");
            lblId.setPrefWidth(30);
            colunaId = new JFXTreeTableColumn<TabEmpresaVO, Integer>();
            colunaId.setGraphic(lblId);
            colunaId.setPrefWidth(30);
            colunaId.setStyle("-fx-alignment: center-right;");
            colunaId.setCellValueFactory(param -> param.getValue().getValue().idProperty().asObject());

            Label lblCnpj = new Label("C.N.P.J / C.P.F.");
            lblCnpj.setPrefWidth(120);
            colunaCnpj = new JFXTreeTableColumn<TabEmpresaVO, String>();
            colunaCnpj.setGraphic(lblCnpj);
            colunaCnpj.setPrefWidth(120);
            colunaCnpj.setStyle("-fx-alignment: center-right;");
            colunaCnpj.setCellValueFactory(param -> {
                if (param.getValue().getValue().isPessoaJuridicaProperty().get() == 0)
                    return new SimpleStringProperty(FormatadorDeDados.getCampoFormatado(param.getValue().getValue().cnpjProperty().getValue(), "cpf"));
                return new SimpleStringProperty(FormatadorDeDados.getCampoFormatado(param.getValue().getValue().cnpjProperty().getValue(), "cnpj"));
            });

            Label lblIe = new Label(("IE / RG"));
            lblIe.setPrefWidth(75);
            colunaIe = new JFXTreeTableColumn<TabEmpresaVO, String>();
            colunaIe.setGraphic(lblIe);
            colunaIe.setPrefWidth(75);
            colunaIe.setStyle("-fx-alignment: center-right;");
            colunaIe.setCellValueFactory(param -> {
                try {
                    if (param.getValue().getValue().getEnderecoVOList().get(0).logradouroProperty().get() != "")
                        return new SimpleStringProperty(FormatadorDeDados.getCampoFormatado(param.getValue().getValue().ieProperty().getValue(), "ie" + param.getValue().getValue().getEnderecoVOList().get(0).getUfVO().getSigla()));
                    return param.getValue().getValue().ieProperty();
                } catch (Exception ex) {
                    return param.getValue().getValue().ieProperty();
                }
            });

            Label lblRazao = new Label("Razão / Nome");
            lblRazao.setPrefWidth(200);
            colunaRazao = new JFXTreeTableColumn<TabEmpresaVO, String>();
            colunaRazao.setGraphic(lblRazao);
            colunaRazao.setPrefWidth(200);
            colunaRazao.setCellValueFactory(param -> param.getValue().getValue().razaoProperty());

            Label lblFantasia = new Label("Fantasia / Apelido");
            lblFantasia.setPrefWidth(110);
            colunaFantasia = new JFXTreeTableColumn<TabEmpresaVO, String>();
            colunaFantasia.setGraphic(lblFantasia);
            colunaFantasia.setPrefWidth(110);
            colunaFantasia.setCellValueFactory(param -> param.getValue().getValue().fantasiaProperty());

            colunaEndereco = new JFXTreeTableColumn<TabEmpresaVO, String>("Endereço");
            colunaEndereco.setStyle("-fx-alignment: center;");

            Label lblEndLogradouro = new Label("Logradouro");
            lblEndLogradouro.setPrefWidth(140);
            colunaEndLogradouro = new JFXTreeTableColumn<TabEmpresaVO, String>();
            colunaEndLogradouro.setGraphic(lblEndLogradouro);
            colunaEndLogradouro.setPrefWidth(140);
            colunaEndLogradouro.setCellValueFactory(param -> {
                if (param.getValue().getValue().getEnderecoVOList().size() > 0)
                    if (param.getValue().getValue().getEnderecoVOList().get(0).logradouroProperty() != null)
                        return param.getValue().getValue().getEnderecoVOList().get(0).logradouroProperty();
                return new SimpleStringProperty("");
            });

            Label lblEndNumero = new Label("Número");
            lblEndNumero.setPrefWidth(50);
            colunaEndNumero = new JFXTreeTableColumn<TabEmpresaVO, String>();
            colunaEndNumero.setGraphic(lblEndNumero);
            colunaEndNumero.setPrefWidth(50);
            colunaEndNumero.setStyle("-fx-alignment: center-right;");
            colunaEndNumero.setCellValueFactory(param -> {
                if (param.getValue().getValue().getEnderecoVOList().size() > 0)
                    if (param.getValue().getValue().getEnderecoVOList().get(0).numeroProperty() != null)
                        return param.getValue().getValue().getEnderecoVOList().get(0).numeroProperty();
                return new SimpleStringProperty("");
            });

            Label lblEndComplemento = new Label("Complemento");
            lblEndComplemento.setPrefWidth(150);
            colunaEndComplemento = new JFXTreeTableColumn<TabEmpresaVO, String>();
            colunaEndComplemento.setGraphic(lblEndComplemento);
            colunaEndComplemento.setPrefWidth(150);
            colunaEndComplemento.setCellValueFactory(param -> {
                if (param.getValue().getValue().getEnderecoVOList().size() > 0)
                    if (param.getValue().getValue().getEnderecoVOList().get(0).complementoProperty() != null)
                        return param.getValue().getValue().getEnderecoVOList().get(0).complementoProperty();
                return new SimpleStringProperty("");
            });

            Label lblEndBairro = new Label("Bairro");
            lblEndBairro.setPrefWidth(85);
            colunaEndBairro = new JFXTreeTableColumn<TabEmpresaVO, String>();
            colunaEndBairro.setGraphic(lblEndBairro);
            colunaEndBairro.setPrefWidth(85);
            colunaEndBairro.setCellValueFactory(param -> {
                if (param.getValue().getValue().getEnderecoVOList().size() > 0)
                    if (param.getValue().getValue().getEnderecoVOList().get(0).bairroProperty() != null)
                        return param.getValue().getValue().getEnderecoVOList().get(0).bairroProperty();
                return new SimpleStringProperty("");
            });

            Label lblEndUFMunicipio = new Label("UF - Cidade");
            lblEndUFMunicipio.setPrefWidth(75);
            colunaEndUFMunicipio = new JFXTreeTableColumn<TabEmpresaVO, String>();
            colunaEndUFMunicipio.setGraphic(lblEndUFMunicipio);
            colunaEndUFMunicipio.setPrefWidth(75);
            colunaEndUFMunicipio.setCellValueFactory(param -> {
                if (param.getValue().getValue().getEnderecoVOList().size() > 0)
                    if (param.getValue().getValue().getEnderecoVOList().get(0) != null)
                        return new SimpleStringProperty(
                                param.getValue().getValue().getEnderecoVOList().get(0).getUfVO().siglaProperty() + " - " +
                                        param.getValue().getValue().getEnderecoVOList().get(0).getMunicipioVO().descricaoProperty());
                return new SimpleStringProperty("");
            });

            colunaEndereco.getColumns().addAll(colunaEndLogradouro, colunaEndNumero,
                    colunaEndComplemento, colunaEndBairro);

            VBox vBoxIsCliente = new VBox();
            Label lblImgIsCliente = new Label();
            lblImgIsCliente.getStyleClass().add("lbl_ico_cliente");
            lblImgIsCliente.setPrefSize(24, 24);
            Label lblIsCliente = new Label("Cliente");
            vBoxIsCliente.setAlignment(Pos.CENTER);
            vBoxIsCliente.getChildren().addAll(lblImgIsCliente, lblIsCliente);
            colunaIsCliente = new JFXTreeTableColumn<TabEmpresaVO, Boolean>();
            colunaIsCliente.setPrefWidth(65);
            colunaIsCliente.setGraphic(vBoxIsCliente);
            colunaIsCliente.setCellValueFactory(param -> {
                if (param.getValue().getValue().getIsCliente() == 0) return new SimpleBooleanProperty(false);
                else return new SimpleBooleanProperty(true);
            });

            VBox vBoxIsFornecedor = new VBox();
            Label lblImgIsFornecedor = new Label();
            lblImgIsFornecedor.getStyleClass().add("lbl_ico_fornecedor");
            lblImgIsFornecedor.setPrefSize(24, 24);
            Label lblIsFornecedor = new Label("Forn.");
            vBoxIsFornecedor.setAlignment(Pos.CENTER);
            vBoxIsFornecedor.getChildren().addAll(lblImgIsFornecedor, lblIsFornecedor);
            colunaIsFornecedor = new JFXTreeTableColumn<TabEmpresaVO, Boolean>();
            colunaIsFornecedor.setPrefWidth(65);
            colunaIsFornecedor.setGraphic(vBoxIsFornecedor);
            colunaIsFornecedor.setCellValueFactory(param -> {
                if (param.getValue().getValue().getIsFornecedor() == 0) return new SimpleBooleanProperty(false);
                else return new SimpleBooleanProperty(true);
            });

            VBox vBoxIsTransportadora = new VBox();
            Label lblImgIsTransportadora = new Label();
            lblImgIsTransportadora.getStyleClass().add("lbl_ico_transportadora");
            lblImgIsTransportadora.setPrefSize(24, 24);
            Label lblIsTransportadora = new Label("Transp.");
            vBoxIsTransportadora.setAlignment(Pos.CENTER);
            vBoxIsTransportadora.getChildren().addAll(lblImgIsTransportadora, lblIsTransportadora);
            colunaIsTransportadora = new JFXTreeTableColumn<TabEmpresaVO, Boolean>();
            colunaIsTransportadora.setPrefWidth(65);
            colunaIsTransportadora.setGraphic(vBoxIsTransportadora);
            colunaIsTransportadora.setCellValueFactory(param -> {
                if (param.getValue().getValue().getIsTransportadora() == 0) return new SimpleBooleanProperty(false);
                else return new SimpleBooleanProperty(true);
            });

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    void criarTabelaQsaReceita() {
        try {
            Label lblQsaKey = new Label("Item");
            lblQsaKey.setPrefWidth(100);
            colunaQsaKey = new JFXTreeTableColumn<TabEmpresa_DetalheReceitaFederalVO, String>();
            colunaQsaKey.setGraphic(lblQsaKey);
            colunaQsaKey.setPrefWidth(100);
            colunaQsaKey.setStyle("-fx-alignment: center-right;");
            colunaQsaKey.setCellValueFactory(param -> {
                return param.getValue().getValue().str_keyProperty();
            });

            Label lblQsaValue = new Label("Detalhe");
            lblQsaValue.setPrefWidth(250);
            colunaQsaValue = new JFXTreeTableColumn<TabEmpresa_DetalheReceitaFederalVO, String>();
            colunaQsaValue.setGraphic(lblQsaValue);
            colunaQsaValue.setPrefWidth(250);
            colunaQsaValue.setStyle("-fx-alignment: center-right;");
            colunaQsaValue.setCellValueFactory(param -> {
                return param.getValue().getValue().str_valueProperty();
            });
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void carregarSisTipoEndereco() {
        tipoEnderecoVOList = new ArrayList<SisTipoEnderecoVO>(new SisTipoEnderecoDAO().getTipoEnderecoVOList());
    }

    public void carregarSisTelefoneOperadora() {
        telefoneOperadoraVOList = new ArrayList<SisTelefoneOperadoraVO>(new SisTelefoneOperadoraDAO().getTelefoneOperadoraVOList());
    }

    public void carregarTabCargo() {
        cargoVOList = new ArrayList<TabCargoVO>(new TabCargoDAO().getCargoVOList());
    }

    public void carregarTodosMunicipios() {
        municipioVOObservableList = FXCollections.observableArrayList(new SisMunicipioDAO().getMunicipioVOList());
    }

    void carregarEmailHomePage() {
        emailHomePageVOObservableList = FXCollections.observableArrayList(getTtvEmailHomePageVO());

        homePageVOFilteredList = new FilteredList<TabEmailHomePageVO>(emailHomePageVOObservableList, homePage -> true);
        homePageVOFilteredList.setPredicate(homePage -> homePage.getIsEmail() == 0);
        preencherHomePageEmpresa();

        emailVOFilteredList = new FilteredList<TabEmailHomePageVO>(emailHomePageVOObservableList, email -> true);
        emailVOFilteredList.setPredicate(email -> email.getIsEmail() == 1);
        preencherEmailEmpresa();
    }

    public void carregarListaEmpresa() {
        empresaVOObservableList = FXCollections.observableArrayList(new TabEmpresaDAO().getEmpresaVOList());
    }

    public void preencherCboEndUF() {
        cboEndUF.getItems().clear();
        cboEndUF.getItems().add(new SisUFVO());
        cboEndUF.getItems().addAll(new SisUFDAO().getUfVOList());
        cboEndUF.getSelectionModel().select(1);
    }

    public void preencherCboSituacaoSistema() {
        cboSituacaoSistema.getItems().clear();
        cboSituacaoSistema.getItems().setAll(new SisSituacaoSistemaDAO().getSituacaoSistemaVOList());
        cboSituacaoSistema.getSelectionModel().select(0);
    }

    public void preencherCboFiltroPesquisa() {
        cboFiltroPesquisa.getItems().clear();
        cboFiltroPesquisa.getItems().add(0, "");
        cboFiltroPesquisa.getItems().add(1, "Clientes");
        cboFiltroPesquisa.getItems().add(2, "Fornecedores");
        cboFiltroPesquisa.getItems().add(3, "Transportadoras");
        cboFiltroPesquisa.getSelectionModel().select(0);

    }

    public void preencherCboClassificacaoJuridica() {
        cboClassificacaoJuridica.getItems().clear();
        cboClassificacaoJuridica.getItems().add(0, "FÍSICA");
        cboClassificacaoJuridica.getItems().add(1, "JURÍDICA");
        cboClassificacaoJuridica.getSelectionModel().select(0);
    }

    public void preencherTabelaEmpresa() {
        try {
            if (empresaVOFilteredList == null)
                carregarPesquisaEmpresas(txtPesquisa.getText());
            setQtdRegistrosLocalizados(empresaVOFilteredList.size());
            final TreeItem<TabEmpresaVO> root = new RecursiveTreeItem<TabEmpresaVO>(empresaVOFilteredList, RecursiveTreeObject::getChildren);
            ttvEmpresa.getColumns().setAll(colunaId, colunaCnpj, colunaIe, colunaRazao, colunaFantasia,
                    colunaEndereco, colunaIsCliente, colunaIsFornecedor, colunaIsTransportadora);
            ttvEmpresa.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
            ttvEmpresa.setRoot(root);
            ttvEmpresa.setShowRoot(false);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    void preencherTabelaQsa() {
        final TreeItem<TabEmpresa_DetalheReceitaFederalVO> root = new RecursiveTreeItem<TabEmpresa_DetalheReceitaFederalVO>
                (qsa_detalheReceitaFederalVOFilteredList, RecursiveTreeObject::getChildren);
        ttvDetalheReceita.getColumns().setAll(colunaQsaKey, colunaQsaValue);
        ttvDetalheReceita.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        ttvDetalheReceita.setRoot(root);
        ttvDetalheReceita.setShowRoot(false);
    }

    void preencherCboEndMunicipio() {
        cboEndMunicipio.getItems().clear();
        if (municipioVOList != null)
            cboEndMunicipio.getItems().setAll(municipioVOList);
        cboEndMunicipio.getSelectionModel().select(0);
    }

    void carregarPesquisaEmpresas(String strPesq) {
        String busca = strPesq.toLowerCase().trim();
        int filtro = cboFiltroPesquisa.getSelectionModel().getSelectedIndex();

        empresaVOFilteredList = new FilteredList<TabEmpresaVO>(empresaVOObservableList, empresa -> true);
        empresaVOFilteredList.setPredicate(empresa -> {
            if (filtro > 0) {
                switch (filtro) {
                    case 1:
                        if (empresa.getIsCliente() == 0) return false;
                        break;
                    case 2:
                        if (empresa.getIsFornecedor() == 0) return false;
                        break;
                    case 3:
                        if (empresa.getIsTransportadora() == 0) return false;
                        break;
                }
            }
            if (busca.length() <= 0) return true;

            if (empresa.getCnpj().toLowerCase().contains(busca)) return true;
            if (empresa.getIe().toLowerCase().contains(busca)) return true;
            if (empresa.getRazao().toLowerCase().contains(busca)) return true;
            if (empresa.getFantasia().toLowerCase().contains(busca)) return true;

            return false;
        });
        preencherTabelaEmpresa();
    }

    void carregarPesquisaMunicipios(int Uf_id) {
        //municipioVOFilteredList = new FilteredList<SisMunicipioVO>(municipioVOObservableList, municipio -> true);
        //municipioVOFilteredList.setPredicate(municipio -> municipio.getUfVO().getSigla().equals(siglaUF));
        municipioVOList = new SisMunicipioDAO().getMunicipioVOList(Uf_id);
        preencherCboEndMunicipio();
    }

    void carregarContatoEmailHomePage() {
        contatoEmailHomePageVOObservableList = FXCollections.observableArrayList(getTtvContatoEmailHomePageVO());

        contatoHomePageVOFilteredList = new FilteredList<TabEmailHomePageVO>(contatoEmailHomePageVOObservableList, contHomePage -> true);
        contatoHomePageVOFilteredList.setPredicate(contHomePage -> contHomePage.getIsEmail() == 0);
        preencherContatoHomePage();

        contatoEmailVOFilteredList = new FilteredList<TabEmailHomePageVO>(contatoEmailHomePageVOObservableList, contEmail -> true);
        contatoEmailVOFilteredList.setPredicate(contEmail -> contEmail.getIsEmail() == 1);
        preencherContatoEmail();

    }

    void preencherListaEnderecoEmpresa() {
        listEndereco.getItems().clear();
        if (getTtvEnderecoVO() != null)
            listEndereco.getItems().setAll(getTtvEnderecoVO());
        listEndereco.getSelectionModel().select(0);
    }

    void preencherListaTelefoneEmpresa() {
        listTelefone.getItems().clear();
        if (getTtvTelefoneVO() != null)
            listTelefone.getItems().setAll(getTtvTelefoneVO());
    }

    void preencherListaTelefoneContato() {
        listContatoTelefone.getItems().clear();
        if (getTtvContatoTelefoneVO() != null)
            listContatoTelefone.getItems().setAll(getTtvContatoTelefoneVO());
    }

    void preencherEmailEmpresa() {
        listEmail.getItems().clear();
        if (emailVOFilteredList != null)
            listEmail.getItems().setAll(emailVOFilteredList);
    }

    void preencherHomePageEmpresa() {
        listHomePage.getItems().clear();
        if (homePageVOFilteredList != null)
            listHomePage.getItems().setAll(homePageVOFilteredList);
    }

    void preencherContatoEmail() {
        listContatoEmail.getItems().clear();
        if (contatoEmailVOFilteredList != null)
            listContatoEmail.getItems().setAll(contatoEmailVOFilteredList);
    }

    void preencherContatoHomePage() {
        listContatoHomePage.getItems().clear();
        if (contatoHomePageVOFilteredList != null)
            listContatoHomePage.getItems().setAll(contatoHomePageVOFilteredList);
    }

    void preencherListaContatoEmpresa() {
        listContatoNome.getItems().clear();
        if (getTtvEmpresaVO().getContatoVOList() != null)
            listContatoNome.getItems().setAll(getTtvEmpresaVO().getContatoVOList());
        exiberDadosContato();
    }

    void preencherListaDetalhesReceita() {
        listAtividadePrincipal.getItems().clear();
        listAtividadeSecundaria.getItems().clear();

        empresa_detalheReceitaFederalVOObservableList = FXCollections.observableArrayList(getTtvDetalheReceitaFederalVO());
        atividadePrincipal_detalheReceitaFederalVOFilteredList = new FilteredList<TabEmpresa_DetalheReceitaFederalVO>(empresa_detalheReceitaFederalVOObservableList, principal -> true);
        atividadePrincipal_detalheReceitaFederalVOFilteredList.setPredicate(principal -> {
            if (principal.getIsAtividadePrincipal() == 1) return true;
            return false;
        });
        listAtividadePrincipal.getItems().setAll(atividadePrincipal_detalheReceitaFederalVOFilteredList);

        atividadeSecundaria_detalheReceitaFederalVOFilteredList = new FilteredList<TabEmpresa_DetalheReceitaFederalVO>(empresa_detalheReceitaFederalVOObservableList, principal -> true);
        atividadeSecundaria_detalheReceitaFederalVOFilteredList.setPredicate(principal -> {
            if (principal.getIsAtividadePrincipal() == 0) return true;
            return false;
        });
        listAtividadeSecundaria.getItems().setAll(atividadeSecundaria_detalheReceitaFederalVOFilteredList);

        qsa_detalheReceitaFederalVOFilteredList = new FilteredList<TabEmpresa_DetalheReceitaFederalVO>(empresa_detalheReceitaFederalVOObservableList, principal -> true);
        qsa_detalheReceitaFederalVOFilteredList.setPredicate(principal -> {
            if (principal.getIsAtividadePrincipal() == 2) return true;
            return false;
        });
        preencherTabelaQsa();
    }

    void exibirDadosEmpresa() {
        if (getTtvEmpresaVO() == null) return;
        cboClassificacaoJuridica.getSelectionModel().select(getTtvEmpresaVO().getIsPessoaJuridica());
        String tipFormat = "cnpj";
        if (cboClassificacaoJuridica.getSelectionModel().getSelectedIndex() == 0)
            tipFormat = "cpf";
        txtCNPJ.setText(FormatadorDeDados.getCampoFormatado(getTtvEmpresaVO().getCnpj(), tipFormat));
        txtIE.setText(getTtvEmpresaVO().getIe());
        cboSituacaoSistema.getSelectionModel().select(getTtvEmpresaVO().getSituacaoSistemaVO());
        txtRazao.setText(getTtvEmpresaVO().getRazao());
        txtFantasia.setText(getTtvEmpresaVO().getFantasia());
        chkIsCliente.setSelected(getTtvEmpresaVO().getIsCliente() == 1);
        chkIsFornecedor.setSelected(getTtvEmpresaVO().getIsFornecedor() == 1);
        chkIsTransportadora.setSelected(getTtvEmpresaVO().getIsTransportadora() == 1);

        lblNaturezaJuridica.setText("Natureza Júridica: " + getTtvEmpresaVO().getNaturezaJuridica());
        lblDataAbertura.setText("data abertura: ");
        lblDataAberturaDiff.setText("tempo de abertura: ");
        if (getTtvEmpresaVO().getDataAbertura() != null) {
            lblDataAbertura.setText("data abertura: " + getTtvEmpresaVO().getDataAbertura().toLocalDateTime().format(DTFORMAT_DATA));
            lblDataAberturaDiff.setText("tempo de abertura: " + DatasTrabalhadas.getStrIntervaloDatas(getTtvEmpresaVO().getDataAbertura().toLocalDateTime(), null));
        }

        lblDataCadastro.setText("data cadastro: " + getTtvEmpresaVO().getDataCadastro().toLocalDateTime().format(DTFORMAT_DATAHORA) + " [" + getTtvEmpresaVO().getUsuarioCadastroVO().getApelido() + "]");
        lblDataCadastroDiff.setText("tempo de cadastro: " + DatasTrabalhadas.getStrIntervaloDatas(getTtvEmpresaVO().getDataCadastro().toLocalDateTime(), null));
        lblDataAtualizacao.setText("");
        lblDataAtualizacaoDiff.setText("");
        if (getTtvEmpresaVO().getDataAtualizacao() != null) {
            lblDataAtualizacao.setText("data atualização: " + getTtvEmpresaVO().getDataAtualizacao().toLocalDateTime().format(DTFORMAT_DATAHORA) + " [" + getTtvEmpresaVO().getUsuarioAtualizacaoVO().getApelido() + "]");
            lblDataAtualizacaoDiff.setText("tempo de atualização: " + DatasTrabalhadas.getStrIntervaloDatas(getTtvEmpresaVO().getDataAtualizacao().toLocalDateTime(), null));
        }

        preencherListaEnderecoEmpresa();
        preencherListaTelefoneEmpresa();
        carregarEmailHomePage();
        preencherListaContatoEmpresa();
        preencherListaDetalhesReceita();
    }

    void exibirDadosEndereco() {
        int index = listEndereco.getSelectionModel().getSelectedIndex();
        if (index < 0)
            index = 0;
        TabEnderecoVO enderecoVO = listEndereco.getItems().get(index);
        txtEndCEP.setText(FormatadorDeDados.getCampoFormatado(enderecoVO.getCep(), "cep"));
        txtEndLogradouro.setText(enderecoVO.getLogradouro());
        txtEndNumero.setText(enderecoVO.getNumero());
        txtEndComplemento.setText(enderecoVO.getComplemento());
        txtEndBairro.setText(enderecoVO.getBairro());
        txtEndPontoReferencia.setText(enderecoVO.getPontoReferencia());
        cboEndUF.getSelectionModel().select(enderecoVO.getUfVO().getId());
        cboEndMunicipio.getSelectionModel().select(enderecoVO.getMunicipioVO());
    }

    void exiberDadosContato() {
        if (listContatoNome.getItems().size() <= 0) {
            listContatoHomePage.getItems().clear();
            listContatoEmail.getItems().clear();
            listContatoTelefone.getItems().clear();
        } else {
            int index = listContatoNome.getSelectionModel().getSelectedIndex();
            if (index < 0) index = 0;
            try {
                setTtvContatoVO(getTtvEmpresaVO().getContatoVOList().get(index));
                carregarContatoEmailHomePage();
                preencherListaTelefoneContato();
            } catch (Exception ex) {
                if (!(ex instanceof IndexOutOfBoundsException))
                    ex.printStackTrace();
            }
        }
    }

    TabEmpresaVO guardarEmpresa(String end_ids, String tel_ids, String cont_ids, String emailHome_ids) {
        TabEmpresaVO emp = getTtvEmpresaVO();
        emp.setIsPessoaJuridica(cboClassificacaoJuridica.getSelectionModel().getSelectedIndex());
        emp.setCnpj(txtCNPJ.getText().replaceAll("[\\-/. \\[\\]]", ""));
        emp.setIe(txtIE.getText().replaceAll("[\\-/. \\[\\]]", ""));
        emp.setRazao(txtRazao.getText());
        emp.setFantasia(txtFantasia.getText());
        int isCli = 0;
        int isFor = 0;
        int isTransp = 0;
        if (chkIsCliente.isSelected()) isCli = 1;
        if (chkIsFornecedor.isSelected()) isFor = 1;
        if (chkIsTransportadora.isSelected()) isTransp = 1;
        emp.setIsCliente(isCli);
        emp.setIsFornecedor(isFor);
        emp.setIsTransportadora(isTransp);
        emp.setEndereco_ids(end_ids);
        emp.setTelefone_ids(tel_ids);
        emp.setContato_ids(cont_ids);
        emp.setEmailHomePage_ids(emailHome_ids);
        emp.setUsuarioCadastro_id(Integer.parseInt(USUARIO_LOGADO_ID));
        emp.setUsuarioAtualizacao_id(Integer.parseInt(USUARIO_LOGADO_ID));
        emp.setSituacaoSistema_id(((SisSituacaoSistemaVO) cboSituacaoSistema.getSelectionModel().getSelectedItem()).getId());

        LocalDate dtTemp = LocalDate.parse(lblDataAbertura.getText().substring(15), DTFORMAT_DATA);
        LocalDateTime dtAbertura = LocalDateTime.of(dtTemp.getYear(), dtTemp.getMonth(), dtTemp.getDayOfMonth(), 0, 0, 0);
        emp.setDataAbertura(Timestamp.valueOf(dtAbertura));

        emp.setNaturezaJuridica(lblNaturezaJuridica.getText().substring(19));
        return emp;
    }

    void guardarEndereco(int index) {
        if (index < 0) return;
        TabEnderecoVO endVO = getTtvEnderecoVO().get(index);
        endVO.setTipoEnderecoVO(listEndereco.getItems().get(index).getTipoEnderecoVO());
        endVO.setTipoEndereco_id(endVO.getTipoEnderecoVO().getId());
        endVO.setCep(txtEndCEP.getText().replaceAll("[\\-/. \\[\\]]", ""));
        endVO.setLogradouro(txtEndLogradouro.getText());
        endVO.setNumero(txtEndNumero.getText());
        endVO.setComplemento(txtEndComplemento.getText());
        endVO.setBairro(txtEndBairro.getText());
        endVO.setUf_id(cboEndUF.getSelectionModel().getSelectedItem().getId());
        endVO.setUfVO(cboEndUF.getSelectionModel().getSelectedItem());
        endVO.setMunicipio_id(cboEndMunicipio.getSelectionModel().getSelectedItem().getId());
        endVO.setMunicipioVO(cboEndMunicipio.getSelectionModel().getSelectedItem());
        endVO.setPontoReferencia(txtEndPontoReferencia.getText());
        getTtvEnderecoVO().set(index, endVO);
        listEndereco.getItems().set(index, endVO);
    }

    TabEnderecoVO addEndereco() {
        int tipEnd = 1;
        try {
            if (getTtvEnderecoVO().get(0).getTipoEndereco_id() == 1) {
                if (!validarEnd()) {
                    new AlertMensagem("Endereço invalido",
                            USUARIO_LOGADO_APELIDO + ", para adicionar endereço 1˚ informe endereço principal valido",
                            "ic_endereco_add_white_24dp.png").getRetornoAlert_OK();
                    return null;
                } else {
                    List<SisTipoEnderecoVO> list = getTipoEnderecoDisponivel();
                    if (list.size() <= 0) {
                        new AlertMensagem("Endereço não disponivél",
                                USUARIO_LOGADO_APELIDO + ", a empresa " + txtRazao.getText()
                                        + " não tem disponibilidade de endereço!\nAtualize algum endereço já existente!",
                                "ic_endereco_add_white_24dp.png").getRetornoAlert_OK();
                        return null;
                    }
                    Object o = new AlertMensagem("Adicionar dados [endereço]",
                            USUARIO_LOGADO_APELIDO + ", selecione o tipo endereço",
                            "ic_endereco_add_white_24dp.png").getRetornoAlert_ComboBox(list).get();
                    if (o == null) return null;
                    tipEnd = ((SisTipoEnderecoVO) o).getId();
                }
                txtEndCEP.requestFocus();
            }
        } catch (Exception ex) {
            if (!(ex instanceof IndexOutOfBoundsException)) {
                ex.printStackTrace();
                return null;
            }
        } finally {
            TabEnderecoVO newEndereco = new TabEnderecoVO();
            newEndereco.setId(0);
            newEndereco.setTipoEndereco_id(tipEnd);
            newEndereco.setTipoEnderecoVO(new SisTipoEnderecoDAO().getTipoEnderecoVO(tipEnd));
            newEndereco.setUf_id(3);
            newEndereco.setUfVO(new SisUFDAO().getUfVO(newEndereco.getUf_id()));
            newEndereco.setMunicipio_id(112);
            newEndereco.setMunicipioVO(new SisMunicipioDAO().getMunicipioVO(newEndereco.getMunicipio_id()));
            return newEndereco;
        }

    }

    List<SisTipoEnderecoVO> getTipoEnderecoDisponivel() {
        List<SisTipoEnderecoVO> endDisponiveis = new ArrayList<>();
        for (SisTipoEnderecoVO tipEnd : tipoEnderecoVOList) {
            int exite = 0;
            for (int i = 0; i < listEndereco.getItems().size(); i++) {
                if (tipEnd.getDescricao().equals(listEndereco.getItems().get(i).getTipoEnderecoVO().getDescricao()))
                    exite = 1;
            }
            if (exite == 0) endDisponiveis.add(tipEnd);
        }
        return endDisponiveis;
    }

    TabEmailHomePageVO addEmailHomePage(boolean isEmpresa, boolean isEmail) {
        String empresa = "a empresa: " + txtRazao.getText();
        if (!isEmpresa) empresa = "o contato: " + listContatoNome.getSelectionModel().getSelectedItem().toString();
        String tipDado = "email";
        String ico = "ic_web_email_white_24dp.png";
        if (!isEmail) {
            tipDado = "home page";
            ico = "ic_web_home_page_white_24dp.png";
        }
        String strEmailHomePage;
        try {
            strEmailHomePage = new AlertMensagem("Adicionar dados [" + tipDado + "]",
                    USUARIO_LOGADO_APELIDO + ", qual " + tipDado + " a ser adicionada para " + empresa + " ?",
                    ico).getRetornoAlert_TextField(FormatadorDeDados.gerarMascara("", 80, "?"), "").get();
        } catch (Exception ex) {
            if (!(ex instanceof NoSuchElementException))
                ex.printStackTrace();
            return null;
        }
        if (strEmailHomePage == null) return null;
        if ((isEmail) && (!strEmailHomePage.contains("@"))) {
            new AlertMensagem("Dados inválidos", USUARIO_LOGADO_APELIDO
                    + ", o email informado é inválido!", "ic_msg_alerta_triangulo_white_24dp.png").getRetornoAlert_OK();
            return null;
        }
        TabEmailHomePageVO emailHomePageVO = new TabEmailHomePageVO();
        emailHomePageVO.setId(0);
        emailHomePageVO.setDescricao(strEmailHomePage);
        int email = 0;
        if (isEmail) email = 1;
        emailHomePageVO.setIsEmail(email);

        return emailHomePageVO;
    }

    TabEmailHomePageVO editEmailHomePage(boolean isEmpresa, TabEmailHomePageVO emailHomePageVO) {
        String empresa = "a empresa: " + txtRazao.getText();
        if (!isEmpresa) empresa = "o contato: " + listContatoNome.getSelectionModel().getSelectedItem().toString();
        String tipDado = "email";
        String ico = "ic_web_email_white_24dp.png";
        if (!(emailHomePageVO.getIsEmail() == 1)) {
            tipDado = "home page";
            ico = "ic_web_home_page_white_24dp.png";
        }
        String strEmailHomePage;
        try {
            strEmailHomePage = new AlertMensagem("Editar informações [" + tipDado + "]",
                    USUARIO_LOGADO_APELIDO + ", qual alteração será feita no " + tipDado + " d" + empresa + " ?",
                    ico).getRetornoAlert_TextField(FormatadorDeDados.gerarMascara("", 80, "?"),
                    emailHomePageVO.getDescricao()).get();
        } catch (Exception ex) {
            if (!(ex instanceof NoSuchElementException))
                ex.printStackTrace();
            return null;
        }
        if (strEmailHomePage == null) return emailHomePageVO;
        if ((emailHomePageVO.getIsEmail() == 1) && (!strEmailHomePage.contains("@"))) {
            new AlertMensagem("Dados inválidos", USUARIO_LOGADO_APELIDO
                    + ", o email informado é inválido!", "ic_msg_alerta_triangulo_white_24dp.png").getRetornoAlert_OK();
            return emailHomePageVO;
        }
        emailHomePageVO.setDescricao(strEmailHomePage);

        return emailHomePageVO;
    }

    TabTelefoneVO addTelefone(boolean isEmpresa) {
        String empresa = "a empresa: " + txtRazao.getText();
        if (!isEmpresa) empresa = "o contato: " + listContatoNome.getSelectionModel().getSelectedItem().toString();

        Pair<String, Object> pairTelefone;
        AlertMensagem alertMensagem = new AlertMensagem();
        alertMensagem.setPromptTextField("telefone");
        alertMensagem.setPromptCombo("Operadora");
        alertMensagem.setCabecalho("Adicionar dados [telefone]");
        alertMensagem.setPromptText(USUARIO_LOGADO_APELIDO + ", qual telefone a ser adicionado para " + empresa + " ?");
        alertMensagem.setStrIco("ic_telefone_white_24dp.png");
        try {
            pairTelefone = alertMensagem.getRetornoAlert_TextFieldEComboBox(telefoneOperadoraVOList,
                    FormatadorDeDados.gerarMascara("", 9, "#"), "").get();
        } catch (Exception ex) {
            if (!(ex instanceof NoSuchElementException))
                ex.printStackTrace();
            return null;
        }
        if (pairTelefone == null) return null;
        if (pairTelefone.getKey().toString().length() < 8 || pairTelefone.getKey().toString().length() > 9) {
            new AlertMensagem("Dados inválidos", USUARIO_LOGADO_APELIDO
                    + ", o número de telefone informado é inválido!", "ic_msg_alerta_triangulo_white_24dp.png").getRetornoAlert_OK();
            return null;
        }
        TabTelefoneVO telefoneVO = new TabTelefoneVO();
        telefoneVO.setId(0);
        telefoneVO.setDescricao(pairTelefone.getKey().toString());
        telefoneVO.setTelefoneOperadoraVO((SisTelefoneOperadoraVO) pairTelefone.getValue());
        telefoneVO.setTelefoneOperadora_id(telefoneVO.getTelefoneOperadoraVO().getId());

        return telefoneVO;
    }

    TabTelefoneVO editTelefone(boolean isEmpresa, TabTelefoneVO telefone) {
        String empresa = "a empresa: " + txtRazao.getText();
        if (!isEmpresa) empresa = "o contato: " + listContatoNome.getSelectionModel().getSelectedItem().toString();

        Pair<String, Object> pairTelefone;
        AlertMensagem alertMensagem = new AlertMensagem();
        alertMensagem.setPromptTextField("telefone");
        alertMensagem.setPromptCombo("Operadora");
        alertMensagem.setCabecalho("Editar informações [telefone]");
        alertMensagem.setPromptText(USUARIO_LOGADO_APELIDO + ", qual alteração será feita no telefone d" + empresa + " ?");
        alertMensagem.setStrIco("ic_telefone_white_24dp.png");
        try {
            pairTelefone = alertMensagem.getRetornoAlert_TextFieldEComboBox(telefoneOperadoraVOList,
                    FormatadorDeDados.gerarMascara("", 9, "#"), telefone.getDescricao()).get();
        } catch (Exception ex) {
            if (!(ex instanceof NoSuchElementException))
                ex.printStackTrace();
            return null;
        }
        if (pairTelefone == null) return telefone;
        if (pairTelefone.getKey().toString().length() < 8 || pairTelefone.getKey().toString().length() > 9) {
            new AlertMensagem("Dados inválidos", USUARIO_LOGADO_APELIDO
                    + ", o número de telefone informado é inválido!", "ic_msg_alerta_triangulo_white_24dp.png").getRetornoAlert_OK();
            return telefone;
        }
        telefone.setDescricao(pairTelefone.getKey().toString());
        telefone.setTelefoneOperadoraVO((SisTelefoneOperadoraVO) pairTelefone.getValue());
        telefone.setTelefoneOperadora_id(telefone.getTelefoneOperadoraVO().getId());

        return telefone;
    }

    TabContatoVO addContato() {
        Pair<String, Object> pairContato;
        AlertMensagem alertMensagem = new AlertMensagem();
        alertMensagem.setPromptTextField("Contato");
        alertMensagem.setPromptCombo("Cargo");
        alertMensagem.setCabecalho("Adicionar dados [contato]");
        alertMensagem.setPromptText(USUARIO_LOGADO_APELIDO + ", qual o contato a ser adicionado para a empresa "
                + txtRazao.getText() + "?");
        alertMensagem.setStrIco("ic_contato_add_white_24dp.png");
        try {
            pairContato = alertMensagem.getRetornoAlert_TextFieldEComboBox(cargoVOList,
                    FormatadorDeDados.gerarMascara("", 80, "@"), "").get();
        } catch (Exception ex) {
            if (!(ex instanceof NoSuchElementException)) {
                ex.printStackTrace();
            }
            return null;
        }
        if (pairContato == null) return null;
        TabContatoVO contatoVO = new TabContatoVO();
        contatoVO.setId(0);
        contatoVO.setDescricao(pairContato.getKey().toString());
        contatoVO.setCargoVO((TabCargoVO) pairContato.getValue());
        contatoVO.setCargo_id(contatoVO.getCargoVO().getId());
        contatoVO.setTelefone_ids("");
        contatoVO.setTelefoneVOList(new ArrayList<TabTelefoneVO>());
        contatoVO.setEmailHomePage_ids("");
        contatoVO.setEmailHomePageVOList(new ArrayList<TabEmailHomePageVO>());
        return contatoVO;
    }

    TabContatoVO editContato(TabContatoVO contato) {
        Pair<String, Object> pairContato;
        AlertMensagem alertMensagem = new AlertMensagem();
        alertMensagem.setPromptTextField("Contato");
        alertMensagem.setPromptCombo("Cargo");
        alertMensagem.setCabecalho("Editar informações [contato]");
        alertMensagem.setPromptText(USUARIO_LOGADO_APELIDO + ", qual alteração será feita no contato da empresa "
                + txtRazao.getText() + "?");
        alertMensagem.setStrIco("ic_contato_add_white_24dp.png");
        try {
            pairContato = alertMensagem.getRetornoAlert_TextFieldEComboBox(cargoVOList,
                    FormatadorDeDados.gerarMascara("", 80, "@"),
                    contato.getDescricao()).get();
        } catch (Exception ex) {
            if (!(ex instanceof NoSuchElementException)) {
                ex.printStackTrace();
            }
            return null;
        }
        if (pairContato == null) return contato;
        contato.setDescricao(pairContato.getKey().toString());
        contato.setCargoVO((TabCargoVO) pairContato.getValue());
        contato.setCargo_id(contato.getId());

        return contato;
    }

    boolean validarDadosCadastrais() {
        if (!validarDadosEmpresa()) return false;

        if (!validarEnd()) return false;

        return true;
    }

    boolean validarDadosEmpresa() {
        boolean result = true;
        String valCnpj = txtCNPJ.getText().replaceAll("[\\-/. \\[\\]]", "");
        if ((valCnpj.length() != 11 && valCnpj.length() != 14) & (!ValidadorDeDados.isCnpjCpfValido(valCnpj))) {
            txtCNPJ.requestFocus();
            result = false;
        }
        if (txtRazao.getText().length() == 0 & result == true) {
            txtRazao.requestFocus();
            result = false;
        }
        if (txtFantasia.getText().length() == 0 & result == true) {
            txtFantasia.requestFocus();
            result = false;
        }

        int tipEmpresa = 0;
        if (chkIsCliente.isSelected()) tipEmpresa++;
        if (chkIsFornecedor.isSelected()) tipEmpresa++;
        if (chkIsTransportadora.isSelected()) tipEmpresa++;

        if (tipEmpresa == 0 & result == true) {
            chkIsCliente.requestFocus();
            result = false;
        }

        if (!result)
            new AlertMensagem("Dados inválido!",
                    USUARIO_LOGADO_APELIDO + ", precisa de dados válidos para empresa",
                    "ic_dados_invalidos_white_24dp.png").getRetornoAlert_OK();

        return result;
    }

    boolean validarEnd() {
        boolean result = true;
        if (getTtvEnderecoVO().size() == 0) {
            getTtvEnderecoVO().add(addEndereco());
            listEndereco.getItems().add(getTtvEnderecoVO().get(0));
        }
        listEndereco.getSelectionModel().select(0);
        if (txtEndCEP.getText().replaceAll("[\\-/. \\[\\]]", "").length() != 8 || txtEndCEP.getText().equals("")) {
            txtEndCEP.requestFocus();
            result = false;
        }
        if (txtEndLogradouro.getText().length() == 0 & result == true) {
            txtEndLogradouro.requestFocus();
            result = false;
        }
        if (txtEndNumero.getText().length() == 0 & result == true) {
            txtEndNumero.requestFocus();
            result = false;
        }
        if (txtEndBairro.getText().length() == 0 & result == true) {
            txtEndBairro.requestFocus();
            result = false;
        }
        if (!result)
            new AlertMensagem("Endereço inválido!",
                    USUARIO_LOGADO_APELIDO + ", precisa endereço válido para empresa",
                    "ic_endereco_invalido_white_24dp.png").getRetornoAlert_OK();
        return result;
    }

    void exibirRetorno_WsCnpjReceitaWs(WsCnpjReceitaWsVO receitaWsVO) {
        cboClassificacaoJuridica.getSelectionModel().select(1);
        txtCNPJ.setText(receitaWsVO.getCnpj());
        txtRazao.setText(receitaWsVO.getNome());
        txtFantasia.setText(receitaWsVO.getFantasia());

        lblNaturezaJuridica.setText("Natureza Júridica: " + receitaWsVO.getNaturezaJuridica());
        lblDataAbertura.setText("data abertura: ");
        lblDataAberturaDiff.setText("tempo de abertura: ");
        if (receitaWsVO.getAbertura() != null) {
            LocalDate dtTemp = LocalDate.parse(receitaWsVO.getAbertura(), DTFORMAT_DATA);
            LocalDateTime dtAbertura = LocalDateTime.of(dtTemp.getYear(), dtTemp.getMonth(), dtTemp.getDayOfMonth(), 0, 0, 0);

            lblDataAbertura.setText("data abertura: " + dtAbertura.format(DTFORMAT_DATA));
            lblDataAberturaDiff.setText("tempo de abertura: " + DatasTrabalhadas.getStrIntervaloDatas(dtAbertura, null));
        }

        if (getTtvEnderecoVO().size() == 0) {
            TabEnderecoVO end = addEndereco();
            getTtvEnderecoVO().add(end);
            listEndereco.getItems().add(end);
        }
        listEndereco.getSelectionModel().select(0);
        String valCep = receitaWsVO.getCep().replaceAll("[\\-/. \\[\\]]", "");
        txtEndCEP.setText(FormatadorDeDados.getCampoFormatado(valCep, "cep"));
        txtEndLogradouro.setText(receitaWsVO.getLogradouro());
        txtEndNumero.setText(receitaWsVO.getNumero());
        txtEndComplemento.setText(receitaWsVO.getComplemento());
        txtEndBairro.setText(receitaWsVO.getBairro());
        if (receitaWsVO.getUf().equals("")) receitaWsVO.setUf("AM");
        cboEndUF.getSelectionModel().select(new SisUFDAO().getUfVO(receitaWsVO.getUf()));
        if (receitaWsVO.getMunicipio().equals("")) receitaWsVO.setMunicipio("MANAUS");
        cboEndMunicipio.getSelectionModel().select(new SisMunicipioDAO().getMunicipioVO(receitaWsVO.getMunicipio()));
        txtEndPontoReferencia.setText("");
        guardarEndereco(listEndereco.getSelectionModel().getSelectedIndex());

        if (receitaWsVO.getTelefone() != "") {
            List<String> telefonesReceitaWsVOList = new ArrayList<>();
            for (String strCodTelefone : receitaWsVO.getTelefone().split(" / ")) {
                strCodTelefone = strCodTelefone.substring(strCodTelefone.length() - 10).replaceAll("[\\-/. \\[\\]]", "");
                if (strCodTelefone.length() == 8 & Integer.parseInt(strCodTelefone.substring(0, 1)) >= 8)
                    strCodTelefone = "9" + strCodTelefone;
                telefonesReceitaWsVOList.add(strCodTelefone);
            }
            for (int i = 0; i < telefonesReceitaWsVOList.size(); i++) {
                TabTelefoneVO tel = new TabTelefoneVO();
                tel.setDescricao(telefonesReceitaWsVOList.get(i));
                tel.setTelefoneOperadora_id(2);
                tel.setTelefoneOperadoraVO(new SisTelefoneOperadoraDAO().getTelefoneOperadoraVO(2));
                if (getTtvTelefoneVO().size() > i) {
                    tel.setId(((TabTelefoneVO) getTtvTelefoneVO().get(i)).getId());
                    getTtvTelefoneVO().set(i, tel);
                } else {
                    tel.setId(0);
                    getTtvTelefoneVO().add(tel);
                }
            }
            listTelefone.getItems().setAll(getTtvTelefoneVO());
        }

        if (receitaWsVO.getEmail() != "") {
            String emails = receitaWsVO.getEmail().replaceAll("@", "");
            int qtdEmails = receitaWsVO.getEmail().length() - emails.length();

            for (int i = 0; i < qtdEmails; i++) {
                TabEmailHomePageVO email = new TabEmailHomePageVO();
                email.setDescricao(receitaWsVO.getEmail());
                email.setIsEmail(1);
                if (listEmail.getItems().size() > i) {
                    int index = getTtvEmailHomePageVO().indexOf(listEmail.getItems().get(i));
                    email.setId(getTtvEmailHomePageVO().get(index).getId());
                    getTtvEmailHomePageVO().set(index, email);
                } else {
                    email.setId(0);
                    getTtvEmailHomePageVO().add(email);
                }
                listEmail.getItems().clear();
                for (TabEmailHomePageVO mail : getTtvEmailHomePageVO()) {
                    if (mail.getIsEmail() == 1)
                        listEmail.getItems().add(mail);
                }
            }
        }

        if (receitaWsVO.getAtividadePrincipal().size() > 0)
            for (Pair<String, String> pair : receitaWsVO.getAtividadePrincipal()) {
                TabEmpresa_DetalheReceitaFederalVO atividadePrincipal = new TabEmpresa_DetalheReceitaFederalVO();
                atividadePrincipal.setId(0);
                atividadePrincipal.setEmpresa_id(0);
                atividadePrincipal.setIsAtividadePrincipal(1);
                atividadePrincipal.setStr_key(pair.getKey());
                atividadePrincipal.setStr_value(pair.getValue());
                getTtvDetalheReceitaFederalVO().add(atividadePrincipal);
            }

        if (receitaWsVO.getAtividadesSecundarias().size() > 0)
            for (Pair<String, String> pair : receitaWsVO.getAtividadesSecundarias()) {
                TabEmpresa_DetalheReceitaFederalVO atividadeSecundaria = new TabEmpresa_DetalheReceitaFederalVO();
                atividadeSecundaria.setEmpresa_id(0);
                atividadeSecundaria.setId(0);
                atividadeSecundaria.setIsAtividadePrincipal(0);
                atividadeSecundaria.setStr_key(pair.getKey());
                atividadeSecundaria.setStr_value(pair.getValue());
                getTtvDetalheReceitaFederalVO().add(atividadeSecundaria);
            }

        if (receitaWsVO.getQsa().size() > 0)
            for (Pair<String, String> pair : receitaWsVO.getQsa()) {
                TabEmpresa_DetalheReceitaFederalVO qsa = new TabEmpresa_DetalheReceitaFederalVO();
                qsa.setEmpresa_id(0);
                qsa.setId(0);
                qsa.setIsAtividadePrincipal(2);
                qsa.setStr_key(pair.getKey());
                qsa.setStr_value(pair.getValue());
                getTtvDetalheReceitaFederalVO().add(qsa);
            }

        preencherListaDetalhesReceita();
    }

    void exibirRetorno_WsCepPostmon(WsCepPostmonVO cepPostmonVO) {
        String valCep = cepPostmonVO.getCep().replaceAll("[\\-/. \\[\\]]", "");
        txtEndCEP.setText(FormatadorDeDados.getCampoFormatado(valCep, "cep"));
        txtEndLogradouro.setText(cepPostmonVO.getLogradouro());
        txtEndNumero.setText("");
        txtEndComplemento.setText("");
        txtEndBairro.setText(cepPostmonVO.getBairro());
        if (cepPostmonVO.getEstado_sigla().equals("")) cepPostmonVO.setEstado_sigla("AM");
        cboEndUF.getSelectionModel().select(new SisUFDAO().getUfVO(cepPostmonVO.getEstado_sigla()));
        if (cepPostmonVO.getCidade().equals("")) cepPostmonVO.setCidade("MANAUS");
        cboEndMunicipio.getSelectionModel().select(new SisMunicipioDAO().getMunicipioVO(cepPostmonVO.getCidade()));
        txtEndPontoReferencia.setText("");
        guardarEndereco(listEndereco.getSelectionModel().getSelectedIndex());
    }

    void keyInsert() {
        if (listEndereco.isFocused()) {
            guardarEndereco(listEndereco.getSelectionModel().getSelectedIndex());
            TabEnderecoVO endereco;
            if ((endereco = addEndereco()) == null) return;
            getTtvEnderecoVO().add(endereco);
            listEndereco.getItems().add(endereco);
            listEndereco.getSelectionModel().selectLast();
        }
        if (listHomePage.isFocused()) {
            TabEmailHomePageVO homePage;
            if ((homePage = addEmailHomePage(true, false)) == null) return;
            getTtvEmailHomePageVO().add(homePage);
            listHomePage.getItems().add(homePage);
            listHomePage.getSelectionModel().selectLast();
        }
        if (listEmail.isFocused()) {
            TabEmailHomePageVO email;
            if ((email = addEmailHomePage(true, true)) == null) return;
            getTtvEmailHomePageVO().add(email);
            listEmail.getItems().add(email);
            listEmail.getSelectionModel().selectLast();
        }
        if (listTelefone.isFocused()) {
            TabTelefoneVO telefone;
            if ((telefone = addTelefone(true)) == null) return;
            getTtvTelefoneVO().add(telefone);
            listTelefone.getItems().add(telefone);
            listTelefone.getSelectionModel().selectLast();
        }
        if (listContatoNome.isFocused()) {
            TabContatoVO contato;
            if ((contato = addContato()) == null) return;
            getTtvEmpresaVO().getContatoVOList().add(contato);
            listContatoNome.getItems().setAll(getTtvEmpresaVO().getContatoVOList());
            listContatoNome.getSelectionModel().selectLast();
        }
        if (listContatoHomePage.isFocused()) {
            if (listContatoNome.getSelectionModel().getSelectedIndex() < 0)
                listContatoNome.getSelectionModel().select(0);
            TabEmailHomePageVO homePage;
            if ((homePage = addEmailHomePage(false, false)) == null) return;
            getTtvContatoEmailHomePageVO().add(homePage);
            listContatoHomePage.getItems().add(homePage);
            listContatoHomePage.getSelectionModel().selectLast();
        }
        if (listContatoEmail.isFocused()) {
            if (listContatoNome.getSelectionModel().getSelectedIndex() < 0)
                listContatoNome.getSelectionModel().select(0);
            TabEmailHomePageVO email;
            if ((email = addEmailHomePage(false, true)) == null) return;
            getTtvContatoEmailHomePageVO().add(email);
            listContatoEmail.getItems().add(email);
            listContatoEmail.getSelectionModel().selectLast();
        }
        if (listContatoTelefone.isFocused()) {
            if (listContatoNome.getSelectionModel().getSelectedIndex() < 0)
                listContatoNome.getSelectionModel().select(0);
            TabTelefoneVO telefone;
            if ((telefone = addTelefone(false)) == null) return;
            getTtvContatoTelefoneVO().add(telefone);
            listContatoTelefone.getItems().add(telefone);
            listContatoTelefone.getSelectionModel().selectLast();
        }
    }

    void keyDelete() {
        if ((listEndereco.isFocused()) && (listEndereco.getSelectionModel().getSelectedItem().getTipoEnderecoVO().getId() != 1)) {
            getTtvEnderecoVO().remove(listEndereco.getSelectionModel().getSelectedItem());
            listEndereco.getItems().remove(listEndereco.getSelectionModel().getSelectedItem());
        }
        if ((listHomePage.isFocused()) && (listHomePage.getSelectionModel().getSelectedIndex() >= 0)) {
            getTtvEmailHomePageVO().remove(listHomePage.getSelectionModel().getSelectedItem());
            listHomePage.getItems().remove(listHomePage.getSelectionModel().getSelectedItem());
        }
        if ((listEmail.isFocused()) && (listEmail.getSelectionModel().getSelectedIndex() >= 0)) {
            getTtvEmailHomePageVO().remove(listEmail.getSelectionModel().getSelectedItem());
            listEmail.getItems().remove(listEmail.getSelectionModel().getSelectedItem());
        }
        if ((listTelefone.isFocused()) && (listTelefone.getSelectionModel().getSelectedIndex() >= 0)) {
            getTtvTelefoneVO().remove(listTelefone.getSelectionModel().getSelectedItem());
            listTelefone.getItems().remove(listTelefone.getSelectionModel().getSelectedItem());
        }
        if ((listContatoNome.isFocused()) && (listContatoNome.getSelectionModel().getSelectedIndex() >= 0)) {
            getTtvEmpresaVO().getContatoVOList().remove(listContatoNome.getSelectionModel().getSelectedItem());
            listContatoNome.getItems().remove(listContatoNome.getSelectionModel().getSelectedItem());
            listContatoHomePage.getItems().clear();
            listContatoEmail.getItems().clear();
            listContatoTelefone.getItems().clear();
        }
        if ((listContatoHomePage.isFocused()) && (listContatoHomePage.getSelectionModel().getSelectedIndex() >= 0)) {
            getTtvContatoEmailHomePageVO().remove(listContatoHomePage.getSelectionModel().getSelectedItem());
            listContatoHomePage.getItems().remove(listContatoHomePage.getSelectionModel().getSelectedItem());
        }
        if ((listContatoEmail.isFocused()) && (listContatoEmail.getSelectionModel().getSelectedIndex() >= 0)) {
            getTtvContatoEmailHomePageVO().remove(listContatoEmail.getSelectionModel().getSelectedItem());
            listContatoEmail.getItems().remove(listContatoEmail.getSelectionModel().getSelectedItem());
        }
        if ((listContatoTelefone.isFocused()) && (listContatoTelefone.getSelectionModel().getSelectedIndex() >= 0)) {
            getTtvContatoTelefoneVO().remove(listContatoTelefone.getSelectionModel().getSelectedItem());
            listContatoTelefone.getItems().remove(listContatoTelefone.getSelectionModel().getSelectedItem());
        }
    }

    void keyShiftF6() {
        if ((listHomePage.isFocused()) && (listHomePage.getSelectionModel().getSelectedIndex() >= 0)) {
            TabEmailHomePageVO homePage = listHomePage.getSelectionModel().getSelectedItem();
            int index = getTtvEmailHomePageVO().indexOf(homePage);
            homePage = editEmailHomePage(true, homePage);
            getTtvEmailHomePageVO().set(index, homePage);
            listHomePage.getItems().set(listHomePage.getSelectionModel().getSelectedIndex(), homePage);
            listHomePage.getSelectionModel().selectLast();
        }
        if ((listEmail.isFocused()) && (listEmail.getSelectionModel().getSelectedIndex() >= 0)) {
            TabEmailHomePageVO email;
            email = listEmail.getSelectionModel().getSelectedItem();
            int index = getTtvEmailHomePageVO().indexOf(email);
            email = editEmailHomePage(true, email);
            getTtvEmailHomePageVO().set(index, email);
            listEmail.getItems().set(listEmail.getSelectionModel().getSelectedIndex(), email);
            listEmail.getSelectionModel().selectLast();
        }
        if ((listTelefone.isFocused()) && (listTelefone.getSelectionModel().getSelectedIndex() >= 0)) {
            TabTelefoneVO telefone = listTelefone.getSelectionModel().getSelectedItem();
            int index = listTelefone.getSelectionModel().getSelectedIndex();
            telefone = editTelefone(true, telefone);
            getTtvTelefoneVO().set(index, telefone);
            listTelefone.getItems().set(index, telefone);
            listTelefone.getSelectionModel().selectLast();
        }
        if ((listContatoNome.isFocused()) && (listContatoNome.getSelectionModel().getSelectedIndex() >= 0)) {
            TabContatoVO contato = listContatoNome.getSelectionModel().getSelectedItem();
            int index = getTtvEmpresaVO().getContatoVOList().indexOf(contato);
            contato = editContato(contato);
            getTtvEmpresaVO().getContatoVOList().set(index, contato);
            listContatoNome.getItems().setAll(getTtvEmpresaVO().getContatoVOList());
            listContatoNome.getSelectionModel().selectLast();
        }
        if ((listContatoHomePage.isFocused()) && (listContatoHomePage.getSelectionModel().getSelectedIndex() >= 0)) {
            TabEmailHomePageVO homePage = listContatoHomePage.getSelectionModel().getSelectedItem();
            int index = getTtvContatoEmailHomePageVO().indexOf(homePage);
            homePage = editEmailHomePage(false, homePage);
            getTtvEmailHomePageVO().set(index, homePage);
            listContatoHomePage.getItems().set(listContatoHomePage.getSelectionModel().getSelectedIndex(), homePage);
            listContatoHomePage.getSelectionModel().selectLast();
        }
        if ((listContatoEmail.isFocused()) && (listContatoEmail.getSelectionModel().getSelectedIndex() >= 0)) {
            TabEmailHomePageVO email = listContatoEmail.getSelectionModel().getSelectedItem();
            int index = getTtvContatoEmailHomePageVO().indexOf(email);
            email = editEmailHomePage(false, email);
            getTtvContatoEmailHomePageVO().set(index, email);
            listContatoEmail.getItems().set(listContatoEmail.getSelectionModel().getSelectedIndex(), email);
            listContatoEmail.getSelectionModel().selectLast();
        }
        if ((listContatoTelefone.isFocused()) && (listContatoTelefone.getSelectionModel().getSelectedIndex() >= 0)) {
            TabTelefoneVO telefone = listContatoTelefone.getSelectionModel().getSelectedItem();
            int index = getTtvContatoTelefoneVO().indexOf(telefone);
            telefone = editTelefone(false, telefone);
            if ((telefone = addTelefone(false)) == null) return;
            getTtvContatoTelefoneVO().set(index, telefone);
            listContatoTelefone.getItems().set(listContatoTelefone.getSelectionModel().getSelectedIndex(), telefone);
            listContatoTelefone.getSelectionModel().selectLast();
        }
    }

    public void salvarEmpresa() {
        String cont_ids = "";
        if (getTtvEmpresaVO().getContatoVOList().size() > 0) {
            for (TabContatoVO contatoVO : getTtvEmpresaVO().getContatoVOList()) {
                setTtvContatoVO(contatoVO);
                String contTel_ids = "";
                String contEmailHome_ids = "";
                if (getTtvContatoTelefoneVO().size() > 0) {
                    for (TabTelefoneVO contTel : getTtvContatoTelefoneVO()) {
                        int id = 0;
                        if (contTel.getId() == 0) {
                            id = new TabTelefoneDAO().insertTabTelefoneVO(contTel);
                        } else {
                            id = contTel.getId();
                            new TabTelefoneDAO().updateTabTelefoneVO(contTel);
                        }
                        if ((!contTel_ids.equals("")) & id > 0)
                            contTel_ids += ";";
                        contTel_ids += id;
                    }
                    contatoVO.setTelefone_ids(contTel_ids);
                }
                if (getTtvContatoEmailHomePageVO().size() > 0) {
                    for (TabEmailHomePageVO contEmailHome : getTtvContatoEmailHomePageVO()) {
                        int id = 0;
                        if (contEmailHome.getId() == 0) {
                            id = new TabEmailHomePageDAO().insertTabEmailHomaPageVO(contEmailHome);
                        } else {
                            id = contEmailHome.getId();
                            new TabEmailHomePageDAO().updateTabEmailHomaPageVO(contEmailHome);
                        }
                        if ((!contEmailHome_ids.equals("")) & id > 0)
                            contEmailHome_ids += ";";
                        contEmailHome_ids += id;
                    }
                    contatoVO.setEmailHomePage_ids(contEmailHome_ids);
                }
                int id = 0;
                if (contatoVO.getId() == 0) {
                    id = new TabContatoDAO().insertTabContatoVO(contatoVO);
                } else {
                    id = contatoVO.getId();
                    new TabContatoDAO().updateTabContatoVO(contatoVO);
                }
                if ((!cont_ids.equals("")) & id > 0)
                    cont_ids += ";";
                cont_ids += id;
            }
        }
        String tel_ids = "";
        String emailHome_ids = "";
        String end_ids = "";
        if (getTtvTelefoneVO().size() > 0) {
            for (TabTelefoneVO tel : getTtvTelefoneVO()) {
                int id = 0;
                if (tel.getId() == 0) {
                    id = new TabTelefoneDAO().insertTabTelefoneVO(tel);
                } else {
                    id = tel.getId();
                    new TabTelefoneDAO().updateTabTelefoneVO(tel);
                }
                if ((!tel_ids.equals("")) & id > 0)
                    tel_ids += ";";
                tel_ids += id;
            }
        }
        if (getTtvEmailHomePageVO().size() > 0) {
            for (TabEmailHomePageVO emailHome : getTtvEmailHomePageVO()) {
                int id = 0;
                if (emailHome.getId() == 0) {
                    id = new TabEmailHomePageDAO().insertTabEmailHomaPageVO(emailHome);
                } else {
                    id = emailHome.getId();
                    new TabEmailHomePageDAO().updateTabEmailHomaPageVO(emailHome);
                }
                if ((!emailHome_ids.equals("")) & id > 0)
                    emailHome_ids += ";";
                emailHome_ids += id;
            }
        }
        guardarEndereco(listEndereco.getSelectionModel().getSelectedIndex());
        if (getTtvEnderecoVO().size() > 0)
            for (TabEnderecoVO endereco : getTtvEnderecoVO()) {
                int id = 0;
                if (endereco.getId() == 0) {
                    id = new TabEnderecoDAO().insertTabEnderecoVO(endereco);
                } else {
                    id = endereco.getId();
                    new TabEnderecoDAO().updateTabEnderecoVO(endereco);
                }
                if ((!end_ids.equals("")) & id > 0)
                    end_ids += ";";
                end_ids += id;
            }

        setTtvEmpresaVO(guardarEmpresa(end_ids, tel_ids, cont_ids, emailHome_ids));
        int idEmpresa = 0;
        if ((idEmpresa = getTtvEmpresaVO().getId()) == 0) {
            idEmpresa = new TabEmpresaDAO().insertTabEmpresaVO(getTtvEmpresaVO());
        } else {
            new TabEmpresaDAO().updateTabEmpresaVO(getTtvEmpresaVO());
        }

        if (getTtvDetalheReceitaFederalVO().size() > 0)
            for (TabEmpresa_DetalheReceitaFederalVO detReceita : getTtvDetalheReceitaFederalVO())
                if (detReceita.getId() == 0) {
                    detReceita.setEmpresa_id(idEmpresa);
                    new TabEmpresa_DetalheReceitaFederalDAO().insertTabEmpresa_DetalheReceitaFederalVO(detReceita);
                }

    }

}
