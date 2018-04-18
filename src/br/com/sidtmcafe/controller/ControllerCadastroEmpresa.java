package br.com.sidtmcafe.controller;

import br.com.sidtmcafe.componentes.AlertMensagem;
import br.com.sidtmcafe.componentes.Tarefa;
import br.com.sidtmcafe.componentes.Variavel;
import br.com.sidtmcafe.database.ConnectionFactory;
import br.com.sidtmcafe.interfaces.FormularioModelo;
import br.com.sidtmcafe.model.dao.*;
import br.com.sidtmcafe.model.model.TabModel;
import br.com.sidtmcafe.model.vo.*;
import br.com.sidtmcafe.service.*;
import br.com.sidtmcafe.view.ViewCadastroEmpresa;
import com.jfoenix.controls.*;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.EventHandler;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.util.Pair;

import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.ResourceBundle;

import static br.com.sidtmcafe.interfaces.Constants.DTF_DATA;
import static br.com.sidtmcafe.interfaces.Constants.DTF_DATAHORA;

public class ControllerCadastroEmpresa extends Variavel implements Initializable, FormularioModelo {

    public AnchorPane painelViewCadastroEmpresa;
    public TitledPane tpnCadastroEmpresa;
    public JFXTextField txtPesquisa;
    public JFXTreeTableView<TabEmpresaVO> ttvEmpresa;
    public JFXComboBox cboFiltroPesquisa;
    public Label lblRegistrosLocalizados;
    public TitledPane tpnDadoCadastral;
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
    public JFXListView<TabEmpresaReceitaFederalVO> listAtividadePrincipal;
    public JFXListView<TabEmpresaReceitaFederalVO> listAtividadeSecundaria;
    public Label lblDataAbertura;
    public Label lblDataAberturaDiff;
    public Label lblNaturezaJuridica;
    public JFXTreeTableView<TabEmpresaReceitaFederalVO> ttvDetalheReceita;
    public JFXTabPane tpnContatoPrazosCondicoes;
    public JFXListView<TabEmailHomePageVO> listHomePage;
    public JFXListView<TabEmailHomePageVO> listEmail;
    public JFXListView<TabTelefoneVO> listTelefone;
    public JFXListView<TabContatoVO> listContatoNome;
    public JFXListView<TabEmailHomePageVO> listContatoHomePage;
    public JFXListView<TabEmailHomePageVO> listContatoEmail;
    public JFXListView<TabTelefoneVO> listContatoTelefone;

    @Override
    public void fechar() {
        for (int i = 0; i < ControllerPrincipal.ctrlPrincipal.tabPaneViewPrincipal.getTabs().size(); i++)
            if (ControllerPrincipal.ctrlPrincipal.tabPaneViewPrincipal.getTabs().get(i).getText().toLowerCase().equals(tituloTab.toLowerCase())) {
                ControllerPrincipal.ctrlPrincipal.fecharTab(i);
                ControllerPrincipal.ctrlPrincipal.painelViewPrincipal.removeEventHandler(KeyEvent.KEY_PRESSED, eventCadastroEmpresa);
            }
    }

    @Override
    public void criarObjetos() {
        listaTarefas.add(new Pair("criarTabelaEmpresa", "criando tabela empresas"));

    }

    @Override
    public void preencherObjetos() {
        listaTarefas.add(new Pair("preencherCboFiltroPesquisa", "preenchendo filtros pesquisa"));
        listaTarefas.add(new Pair("preencherCboClassificacaoJuridica", "preenchendo classificações jurídicas"));
        listaTarefas.add(new Pair("preencherCboSituacaoSistema", "preenchendo situações do sistema"));
        listaTarefas.add(new Pair("preencherCboEndUF", "preenchendo dados UF"));

        listaTarefas.add(new Pair("carregarTabCargo", "carregando lista cargos"));
        listaTarefas.add(new Pair("carregarSisTipoEndereco", "carregando lista tipo endereço"));
        listaTarefas.add(new Pair("carregarSisTelefoneOperadora", "carregando lista operadoras telefone"));
        listaTarefas.add(new Pair("carregarListaEmpresa", "carregando lista de empresas"));

        listaTarefas.add(new Pair("preencherTabelaEmpresa", "preenchendo tabela empresa"));

        new Tarefa().tarefaAbreCadastroEmpresa(this, listaTarefas);

        PersonalizarCampo.fieldMaxLen(painelViewCadastroEmpresa);
        PersonalizarCampo.maskFields(painelViewCadastroEmpresa);

        formatCNPJ_CPF = new FormatarDado();
        formatCNPJ_CPF.maskField(txtCNPJ, FormatarDado.gerarMascara("cnpj", 0, "#"));
        formatIE = new FormatarDado();
        formatIE.maskField(txtIE, FormatarDado.gerarMascara("ie", 0, "#"));

    }

    @Override
    public void fatorarObjetos() {
        FormatarDado.fatorarColunaCheckBox(TabModel.getColunaIsCliente());
        FormatarDado.fatorarColunaCheckBox(TabModel.getColunaIsFornecedor());
        FormatarDado.fatorarColunaCheckBox(TabModel.getColunaIsTransportadora());
    }

    @Override
    public void escutarTeclas() {

        ttvEmpresa.getSelectionModel().selectedItemProperty().addListener((ov, o, n) -> {
            if (n == null) return;
            setTabEmpresaVO(n.getValue());
            exibirDadosEmpresa();
        });

        ControllerPrincipal.ctrlPrincipal.tabPaneViewPrincipal.getSelectionModel().selectedItemProperty().addListener((ov, o, n) -> {
            if (!(ControllerPrincipal.ctrlPrincipal.getTabAtual().equals(tituloTab)))
                return;
            if ((n != null) & (n != o))
                setStatusBarFormulario(getStatusFormulario());
        });

        eventCadastroEmpresa = new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (!(ControllerPrincipal.ctrlPrincipal.getTabAtual().equals(tituloTab)))
                    return;
                switch (event.getCode()) {
                    case F1:
                        if (!getStatusBarFormulario().contains(event.getCode().toString())) break;
                        TabEnderecoVO enderecoVO = new TabEnderecoVO(1);
                        TabEmpresaVO empresaVO = new TabEmpresaVO();
                        empresaVO.setTabEnderecoVOList(new ArrayList<>());
                        empresaVO.getTabEnderecoVOList().add(enderecoVO);

                        setTabEmpresaVO(empresaVO);
                        setStatusFormulario("Incluir");
                        break;
                    case F2:
                    case F5:
                        if (!getStatusBarFormulario().contains(event.getCode().toString())) break;
                        if (!validarDadosCadastrais()) break;

                        salvarEmpresa();
                        setStatusFormulario("Pesquisa");
                        carregarListaEmpresa();
                        preencherTabelaEmpresa();
                        carregarPesquisaEmpresas(txtPesquisa.getText());
                        txtPesquisa.requestFocus();
                        break;
                    case F3:
                        if (!getStatusBarFormulario().contains(event.getCode().toString())) break;
                        switch (getStatusFormulario().toLowerCase()) {
                            case "incluir":
                                if (new AlertMensagem("Cancelar inclusão", USUARIO_LOGADO_APELIDO
                                        + ", deseja cancelar inclusão no cadastro de empresa?",
                                        "ic_cadastro_empresas_white_24dp.png").getRetornoAlert_YES_NO().get() == ButtonType.NO)
                                    return;
                                PersonalizarCampo.clearField((AnchorPane) tpnDadoCadastral.getContent());
                                break;
                            case "editar":
                                if (new AlertMensagem("Cancelar edição", USUARIO_LOGADO_APELIDO
                                        + ", deseja cancelar edição do cadastro de empresa?",
                                        "ic_cadastro_empresas_white_24dp.png").getRetornoAlert_YES_NO().get() == ButtonType.NO)
                                    return;
                                setTabEmpresaVO(ttvEmpresa.getSelectionModel().getSelectedItem().getValue());
                                break;
                        }
                        setStatusFormulario("Pesquisa");
                        break;
                    case F4:
                        if ((!getStatusBarFormulario().contains(event.getCode().toString())) || (getTabEmpresaVO() == null))
                            break;
                        indexObservableEmpresa = empresaVOObservableList.indexOf(getTabEmpresaVO());
                        setStatusFormulario("Editar");
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
                        fechar();
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
            }
        };

        ControllerPrincipal.ctrlPrincipal.painelViewPrincipal.addEventHandler(KeyEvent.KEY_RELEASED, eventCadastroEmpresa);

        txtPesquisa.textProperty().addListener((ov, o, n) -> {
            carregarPesquisaEmpresas(n);
        });

        cboFiltroPesquisa.getSelectionModel().selectedIndexProperty().addListener((ov, o, n) -> {
            carregarPesquisaEmpresas(txtPesquisa.getText());
        });

        txtPesquisa.addEventHandler(KeyEvent.KEY_PRESSED, event -> {
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
                txtCNPJ.setText(FormatarDado.getCampoFormatado(txtCNPJ.getText(), "cnpj"));
            } else {
                txtIE.setPromptText("RG");
                txtRazao.setPromptText("Nome");
                txtFantasia.setPromptText("Apelido");
                txtCNPJ.setPromptText("C.P.F.");
                formatCNPJ_CPF.setMascara("cpf");
                txtCNPJ.setText(FormatarDado.getCampoFormatado(txtCNPJ.getText(), "cpf"));
            }
        });

        txtCNPJ.addEventHandler(KeyEvent.KEY_PRESSED, event -> {
            if (event.getCode() == KeyCode.ENTER) {
                String cnpjValue = txtCNPJ.getText().replaceAll("[\\-/., \\[\\]\\(\\)]", "");
                if ((cnpjValue.length() != 11 && cnpjValue.length() != 14) || (!ValidadarDado.isCnpjCpfValido(cnpjValue))) {
                    new AlertMensagem("Dado inválido!", USUARIO_LOGADO_APELIDO + ", o "
                            + txtCNPJ.getPromptText() + ": " + txtCNPJ.getText() + " é inválido!",
                            "ic_web_service_err_white_24dp").getRetornoAlert_OK();
                    txtCNPJ.requestFocus();
                    return;
                }
                if ((new TabEmpresaDAO().getTabEmpresaVO_Simples(cnpjValue)) != null) {
                    new AlertMensagem("Empresa duplicada", USUARIO_LOGADO_APELIDO + ", o "
                            + txtCNPJ.getPromptText() + ": " + txtCNPJ.getText() + " já existe no banco de dados!",
                            "ic_web_service_err_white_24dp").getRetornoAlert_OK();
                    txtCNPJ.requestFocus();
                    return;
                }

                if (cboClassificacaoJuridica.getSelectionModel().getSelectedIndex() == 1) {
                    listaTarefas = new ArrayList<>();
                    listaTarefas.add(new Pair("buscarCNPJ", cnpjValue));

                    TabEmpresaVO tabEmpresaVO = new Tarefa().tarefaWsCnpjReceitaWs(listaTarefas);
                    if (tabEmpresaVO == null)
                        return;
                    for (int i = tabEmpresaVO.getTabEnderecoVOList().size(); i < getTabEnderecoVOList().size(); i++) {
                        tabEmpresaVO.getTabEnderecoVOList().add(getTabEnderecoVOList().get(i));
                    }
                    for (TabEmailHomePageVO emailHomePageVO : getTabEmailHomePageVOList())
                        if (!tabEmpresaVO.getTabEmailHomePageVOList().contains(emailHomePageVO))
                            tabEmpresaVO.getTabEmailHomePageVOList().add(emailHomePageVO);

                    for (TabTelefoneVO telefoneVO : getTabTelefoneVOList())
                        if (!tabEmpresaVO.getTabTelefoneVOList().contains(telefoneVO))
                            tabEmpresaVO.getTabTelefoneVOList().add(telefoneVO);

                    if (getStatusFormulario().toLowerCase().equals("editar")) {
                        tabEmpresaVO.setId(getTabEmpresaVO().getId());
                        tabEmpresaVO.setIe(getTabEmpresaVO().getIe());
                        tabEmpresaVO.setSisSituacaoSistema_id(getTabEmpresaVO().getSisSituacaoSistema_id());
                        tabEmpresaVO.setSisSituacaoSistemaVO(getTabEmpresaVO().getSisSituacaoSistemaVO());
                        tabEmpresaVO.setIsLoja(getTabEmpresaVO().isIsLoja());
                        tabEmpresaVO.setIsCliente(getTabEmpresaVO().isIsCliente());
                        tabEmpresaVO.setIsFornecedor(getTabEmpresaVO().isIsFornecedor());
                        tabEmpresaVO.setIsTransportadora(getTabEmpresaVO().isIsTransportadora());
                        tabEmpresaVO.setUsuarioCadastro_id(getTabEmpresaVO().getUsuarioCadastro_id());
                        tabEmpresaVO.setUsuarioCadastroVO(getTabEmpresaVO().getUsuarioCadastroVO());
                        tabEmpresaVO.setDataCadastro(getTabEmpresaVO().getDataCadastro());
                    }

                    setTabEmpresaVO(tabEmpresaVO);
                    exibirDadosEmpresa();
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

        txtEndCEP.addEventHandler(KeyEvent.KEY_PRESSED, event -> {
            if (event.getCode() == KeyCode.ENTER) {
                String cepValue = txtEndCEP.getText().replaceAll("[\\-/., \\[\\]\\(\\)]", "");
                if (cepValue.length() != 8) {
                    new AlertMensagem("Dado inválido!", USUARIO_LOGADO_APELIDO + ", o "
                            + "CEP: " + txtEndCEP.getText() + " é inválido!",
                            "ic_web_service_err_white_24dp").getRetornoAlert_OK();
                    txtEndCEP.requestFocus();
                    return;
                }
                listaTarefas = new ArrayList<>();
                listaTarefas.add(new Pair("buscarCEP", cepValue));

                TabEnderecoVO tabEnderecoVO = new Tarefa().tarefaWsCepPostmon(listaTarefas);
                if (tabEnderecoVO == null)
                    return;
                int idEnd = listEndereco.getSelectionModel().getSelectedIndex();
                tabEnderecoVO.setSisTipoEnderecoVO(new SisTipoEnderecoDAO().getSisTipoEnderecoVO(getTabEnderecoVOList().get(idEnd).getSisTipoEndereco_id()));
                tabEnderecoVO.setSisTipoEndereco_id(tabEnderecoVO.getSisTipoEnderecoVO().getId());
                getTabEnderecoVOList().set(idEnd, tabEnderecoVO);
                exibirDadosEndereco();
                txtEndNumero.requestFocus();
            }
        });

        cboEndUF.getSelectionModel().selectedIndexProperty().addListener((ov, o, n) -> {
            if ((o.intValue() >= 0) && (n.intValue() != o.intValue()) && (n.intValue() >= 0))
                try {
//                    if (listEndereco.getSelectionModel().getSelectedIndex() == 0) {
//                        String uf = cboEndUF.getItems().get(n.intValue()).getSigla();
//                        formatIE.setMascara("ie" + uf);
//                        txtIE.setText(FormatarDado.getCampoFormatado(txtIE.getText(), "ie" + uf));
//                    }
                    String mascUF = "ie" + listEndereco.getItems().get(0).getSisMunicipioVO().getUfVO().getSigla();
                    formatIE.setMascara(mascUF);
                    txtIE.setText(FormatarDado.getCampoFormatado(txtIE.getText(), mascUF));
                } catch (Exception ex) {
//                    formatIE.setMascara("ie");
//                    txtIE.setText(FormatarDado.getCampoFormatado(txtIE.getText(), "ie"));
                    //if (!(ex instanceof IndexOutOfBoundsException))
                    ex.printStackTrace();
                }
            if (n == null || n.intValue() < 0) return;
            sisMunicipioVOList = cboEndUF.getItems().get(n.intValue()).getMunicipioVOList();
            preencherCboEndMunicipio();
        });

        listContatoNome.getSelectionModel().selectedIndexProperty().addListener((ov, o, n) -> {
            if (n == null || n.intValue() < 0) return;
            setTabContatoVO(getTabContatoVOList().get(n.intValue()));
        });
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        listaTarefas = new ArrayList<>();
        criarObjetos();
        preencherObjetos();
        fatorarObjetos();
        escutarTeclas();
        setStatusFormulario("Pesquisa");
        Platform.runLater(() -> {
            ControllerPrincipal.ctrlPrincipal.painelViewPrincipal.fireEvent(ExecutaComandoTecladoMouse.pressTecla(KeyCode.F7));
        });
    }

    int idEnderecoAtual = 0;
    int qtdRegistrosLocalizados = 0;
    int indexObservableEmpresa = 0;
    String tituloTab = ViewCadastroEmpresa.getTituloJanela();
    String statusFormulario, statusBarFormulario;
    EventHandler<KeyEvent> eventCadastroEmpresa;

    static String STATUSBARPESQUISA = "[F1-Novo]  [F3-Excluir]  [F4-Editar]  [F7-Pesquisar]  [F8-Filtro pesquisa]  [F12-Sair]  ";
    static String STATUSBAREDITAR = "[F3-Cancelar edição]  [F5-Atualizar]  ";
    static String STATUSBARINCLUIR = "[F2-Incluir]  [F3-Cancelar inclusão]  ";

    FormatarDado formatCNPJ_CPF, formatIE;
    List<Pair> listaTarefas;
    ObservableList<TabEmpresaVO> empresaVOObservableList;
    FilteredList<TabEmpresaVO> empresaVOFilteredList;
    TabEmpresaVO tabEmpresaVO;
    TabContatoVO tabContatoVO;
    List<TabEnderecoVO> tabEnderecoVOList;
    List<TabEnderecoVO> deletadosTabEnderecoVOList;
    List<TabEmailHomePageVO> tabEmailHomePageVOList;
    List<TabEmailHomePageVO> deletadosTabEmailHomePageVOList;
    List<TabTelefoneVO> tabTelefoneVOList;
    List<TabTelefoneVO> deletadosTabTelefoneVOList;
    List<TabContatoVO> tabContatoVOList;
    List<TabContatoVO> deletadosTabContatoVOList;
    List<TabEmailHomePageVO> tabContatoEmailHomePageVOList;
    List<TabEmailHomePageVO> deletadosTabContatoEmailHomePageVOList;
    List<TabTelefoneVO> tabContatoTelefoneVOList;
    List<TabTelefoneVO> deletadosTabContatoTelefoneVOList;

    List<SisUFVO> sisUFVOList;
    List<SisMunicipioVO> sisMunicipioVOList;
    List<SisSituacaoSistemaVO> sisSituacaoSistemaVOList;
    List<SisCargoVO> sisCargoVOList;
    List<SisTipoEnderecoVO> sisTipoEnderecoVOList;
    List<SisTelefoneOperadoraVO> sisTelefoneOperadoraVOList;

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
        cboClassificacaoJuridica.getSelectionModel().select(1);
    }

    public void preencherCboSituacaoSistema() {
        sisSituacaoSistemaVOList = new ArrayList<SisSituacaoSistemaVO>(new SisSituacaoSistemaDAO().getSisSituacaoSistemaVOList());
        if (sisSituacaoSistemaVOList == null) {
            cboSituacaoSistema.getItems().clear();
            return;
        }
        cboSituacaoSistema.getItems().setAll(new SisSituacaoSistemaDAO().getSisSituacaoSistemaVOList());
        cboSituacaoSistema.getSelectionModel().select(0);
    }

    public void preencherCboEndUF() {
        sisUFVOList = new ArrayList<SisUFVO>(new SisUFDAO().getSisUFVOList_DetMunicipios());
        if (sisUFVOList == null) {
            cboEndUF.getItems().clear();
            return;
        }
        sisMunicipioVOList = new ArrayList<>();
        cboEndUF.getItems().setAll(sisUFVOList);
        cboEndUF.getSelectionModel().select(0);
    }

    public void preencherTabelaEmpresa() {
        try {
            if (empresaVOFilteredList == null)
                carregarPesquisaEmpresas(txtPesquisa.getText());
            setQtdRegistrosLocalizados(empresaVOFilteredList.size());
            final TreeItem<TabEmpresaVO> root = new RecursiveTreeItem<TabEmpresaVO>(empresaVOFilteredList, RecursiveTreeObject::getChildren);
            ttvEmpresa.getColumns().setAll(TabModel.getColunaIdEmpresa(), TabModel.getColunaCnpj(), TabModel.getColunaIe(),
                    TabModel.getColunaRazao(), TabModel.getColunaFantasia(), TabModel.getColunaEndereco(),
                    TabModel.getColunaIsCliente(), TabModel.getColunaIsFornecedor(), TabModel.getColunaIsTransportadora());
            ttvEmpresa.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
            ttvEmpresa.setRoot(root);
            ttvEmpresa.setShowRoot(false);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    void carregarPesquisaEmpresas(String strPesq) {
        String busca = strPesq.toLowerCase().trim();

        empresaVOFilteredList = new FilteredList<TabEmpresaVO>(empresaVOObservableList, empresa -> true);
        int filtro = cboFiltroPesquisa.getSelectionModel().getSelectedIndex();
        empresaVOFilteredList.setPredicate(empresa -> {
            if (filtro > 0) {
                switch (filtro) {
                    case 1:
                        if (!empresa.isIsCliente()) return false;
                        break;
                    case 2:
                        if (!empresa.isIsFornecedor()) return false;
                        break;
                    case 3:
                        if (!empresa.isIsTransportadora()) return false;
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

    public void carregarListaEmpresa() {
        empresaVOObservableList = FXCollections.observableArrayList(new TabEmpresaDAO().getTabEmpresaVOList(false));
    }

    void preencherCboEndMunicipio() {
        if (sisMunicipioVOList == null) {
            cboEndMunicipio.getItems().clear();
            return;
        }
        cboEndMunicipio.getItems().setAll(sisMunicipioVOList);
        cboEndMunicipio.getSelectionModel().select(0);
    }

    public void carregarTabCargo() {
        sisCargoVOList = new ArrayList<SisCargoVO>(new SisCargoDAO().getSisCargoVOList());
    }

    public void carregarSisTipoEndereco() {
        sisTipoEnderecoVOList = new ArrayList<SisTipoEnderecoVO>(new SisTipoEnderecoDAO().getSisTipoEnderecoVOList());
    }

    public void carregarSisTelefoneOperadora() {
        sisTelefoneOperadoraVOList = new ArrayList<SisTelefoneOperadoraVO>(new SisTelefoneOperadoraDAO().getSisTelefoneOperadoraVOList());
    }

    public int getQtdRegistrosLocalizados() {
        return qtdRegistrosLocalizados;
    }

    public void setQtdRegistrosLocalizados(int qtdRegistrosLocalizados) {
        this.qtdRegistrosLocalizados = qtdRegistrosLocalizados;
        atualizaLblQtdRegistroLocalizado();
    }

    public String getStatusFormulario() {
        return statusFormulario;
    }

    public void setStatusFormulario(String statusFormulario) {
        this.statusFormulario = statusFormulario;
        atualizaLblQtdRegistroLocalizado();
        setStatusBarFormulario(statusFormulario);
    }

    public String getStatusBarFormulario() {
        return statusBarFormulario;
    }

    public void setStatusBarFormulario(String statusFormulario) {
        if (statusFormulario.toLowerCase().contains("incluir")) {
            PersonalizarCampo.desabilitaCampos((AnchorPane) tpnCadastroEmpresa.getContent(), true);
            PersonalizarCampo.desabilitaCampos((AnchorPane) tpnDadoCadastral.getContent(), false);
            PersonalizarCampo.clearField((AnchorPane) tpnDadoCadastral.getContent());
            cboClassificacaoJuridica.requestFocus();
            cboClassificacaoJuridica.getSelectionModel().select(0);
            cboClassificacaoJuridica.getSelectionModel().select(1);
            this.statusBarFormulario = STATUSBARINCLUIR;
        } else if (statusFormulario.toLowerCase().contains("editar")) {
            PersonalizarCampo.desabilitaCampos((AnchorPane) tpnCadastroEmpresa.getContent(), true);
            PersonalizarCampo.desabilitaCampos((AnchorPane) tpnDadoCadastral.getContent(), false);
            this.statusBarFormulario = STATUSBAREDITAR;
            txtCNPJ.requestFocus();
        } else if (statusFormulario.toLowerCase().contains("pesquisa")) {
            PersonalizarCampo.desabilitaCampos((AnchorPane) tpnCadastroEmpresa.getContent(), false);
            PersonalizarCampo.desabilitaCampos((AnchorPane) tpnDadoCadastral.getContent(), true);
            this.statusBarFormulario = STATUSBARPESQUISA;
            txtPesquisa.requestFocus();
        }
        ControllerPrincipal.ctrlPrincipal.atualizarTeclasStatusBar(statusBarFormulario);
    }

    void atualizaLblQtdRegistroLocalizado() {
        lblRegistrosLocalizados.setText("[" + getStatusFormulario() + "]  " + String.valueOf(getQtdRegistrosLocalizados()) + " registro(s) localizado(s).");
    }

    public TabEmpresaVO getTabEmpresaVO() {
        return tabEmpresaVO;
    }

    public void setTabEmpresaVO(TabEmpresaVO tabEmpresaVO) {
        if (tabEmpresaVO == null)
            tabEmpresaVO = new TabEmpresaVO();
        this.tabEmpresaVO = tabEmpresaVO;
        setTabEnderecoVOList(getTabEmpresaVO().getTabEnderecoVOList());
        setTabEmailHomePageVOList(getTabEmpresaVO().getTabEmailHomePageVOList());
        setTabTelefoneVOList(getTabEmpresaVO().getTabTelefoneVOList());
        setTabContatoVOList(getTabEmpresaVO().getTabContatoVOList());
        //exibirDadosEmpresa();
    }

    public List<TabEnderecoVO> getTabEnderecoVOList() {
        return tabEnderecoVOList;
    }

    public void setTabEnderecoVOList(List<TabEnderecoVO> tabEnderecoVOList) {
        if (tabEnderecoVOList == null) {
            TabEnderecoVO enderecoVO = new TabEnderecoVO(1);
            tabEnderecoVOList = new ArrayList<TabEnderecoVO>();
            tabEnderecoVOList.add(enderecoVO);
        }
        this.tabEnderecoVOList = tabEnderecoVOList;
        atualizaListaEndereco();
    }

    public List<TabEmailHomePageVO> getTabEmailHomePageVOList() {
        return tabEmailHomePageVOList;
    }

    public void setTabEmailHomePageVOList(List<TabEmailHomePageVO> tabEmailHomePageVOList) {
        if (tabEmailHomePageVOList == null)
            tabEmailHomePageVOList = new ArrayList<TabEmailHomePageVO>();
        this.tabEmailHomePageVOList = tabEmailHomePageVOList;
        atualizaListaEmailHomePage();
    }

    public List<TabTelefoneVO> getTabTelefoneVOList() {
        return tabTelefoneVOList;
    }

    public void setTabTelefoneVOList(List<TabTelefoneVO> tabTelefoneVOList) {
        if (tabTelefoneVOList == null)
            tabTelefoneVOList = new ArrayList<TabTelefoneVO>();
        this.tabTelefoneVOList = tabTelefoneVOList;
        atualizaListaTelefone();
    }

    public List<TabContatoVO> getTabContatoVOList() {
        return tabContatoVOList;
    }

    public void setTabContatoVOList(List<TabContatoVO> tabContatoVOList) {
        if (tabContatoVOList == null) {
            tabContatoVOList = new ArrayList<TabContatoVO>();
            tabContatoEmailHomePageVOList = new ArrayList<>();
            tabContatoTelefoneVOList = new ArrayList<>();
        }
        this.tabContatoVOList = tabContatoVOList;
        atualizaListaContato();
    }

    public List<TabEmailHomePageVO> getTabContatoEmailHomePageVOList() {
        return tabContatoEmailHomePageVOList;
    }

    public void setTabContatoEmailHomePageVOList(List<TabEmailHomePageVO> tabContatoEmailHomePageVOList) {
        if (tabContatoEmailHomePageVOList == null)
            tabContatoEmailHomePageVOList = new ArrayList<TabEmailHomePageVO>();
        this.tabContatoEmailHomePageVOList = tabContatoEmailHomePageVOList;
        atualizaListaContatoEmailHomePage();
    }

    public List<TabTelefoneVO> getTabContatoTelefoneVOList() {
        return tabContatoTelefoneVOList;
    }

    public void setTabContatoTelefoneVOList(List<TabTelefoneVO> tabContatoTelefoneVOList) {
        if (tabContatoTelefoneVOList == null)
            tabContatoTelefoneVOList = new ArrayList<TabTelefoneVO>();
        this.tabContatoTelefoneVOList = tabContatoTelefoneVOList;
        atualizaListaContatoTelefone();
    }

    public TabContatoVO getTabContatoVO() {
        return tabContatoVO;
    }

    public void setTabContatoVO(TabContatoVO tabContatoVO) {
        if (tabContatoVO == null)
            tabContatoVO = new TabContatoVO();
        this.tabContatoVO = tabContatoVO;
        setTabContatoEmailHomePageVOList(getTabContatoVO().getTabEmailHomePageVOList());
        setTabContatoTelefoneVOList(getTabContatoVO().getTabTelefoneVOList());
    }

    void exibirDadosEmpresa() {
        if (getTabEmpresaVO() == null) return;
        cboClassificacaoJuridica.getSelectionModel().select(getTabEmpresaVO().getIsEmpresa());
        String tipFormat = "cnpj";
        if (cboClassificacaoJuridica.getSelectionModel().getSelectedIndex() == 0)
            tipFormat = "cpf";
        txtCNPJ.setText(FormatarDado.getCampoFormatado(getTabEmpresaVO().getCnpj(), tipFormat));
        txtIE.setText(FormatarDado.getCampoFormatado(getTabEmpresaVO().getIe(), "ie" + getTabEnderecoVOList().get(0).getSisMunicipioVO().getUfVO().getSigla()));
        cboSituacaoSistema.getSelectionModel().select(getTabEmpresaVO().getSisSituacaoSistemaVO());
        txtRazao.setText(getTabEmpresaVO().getRazao());
        txtFantasia.setText(getTabEmpresaVO().getFantasia());
        chkIsCliente.setSelected(getTabEmpresaVO().isIsCliente());
        chkIsFornecedor.setSelected(getTabEmpresaVO().isIsFornecedor());
        chkIsTransportadora.setSelected(getTabEmpresaVO().isIsTransportadora());

        lblNaturezaJuridica.setText("Natureza Júridica: " + getTabEmpresaVO().getNaturezaJuridica());
        lblDataAbertura.setText("data abertura: ");
        lblDataAberturaDiff.setText("tempo de abertura: ");
        if (getTabEmpresaVO().getDataAbertura() != null) {
            LocalDate ldAbertura = getTabEmpresaVO().getDataAbertura().toLocalDate();
            lblDataAbertura.setText("data abertura: " + ldAbertura.format(DTF_DATA));
            lblDataAberturaDiff.setText("tempo de abertura: " + DataTrabalhada.getStrIntervaloDatas(ldAbertura, null));
        }

        if (!getStatusFormulario().toLowerCase().equals("incluir")) {
            LocalDateTime ldtCadastro = getTabEmpresaVO().getDataCadastro().toLocalDateTime();
            lblDataCadastro.setText("data cadastro: " + ldtCadastro.format(DTF_DATAHORA) + " [" + getTabEmpresaVO().getUsuarioCadastroVO().getApelido() + "]");
            lblDataCadastroDiff.setText("tempo de cadastro: " + DataTrabalhada.getStrIntervaloDatas(ldtCadastro.toLocalDate(), null));
            lblDataAtualizacao.setText("");
            lblDataAtualizacaoDiff.setText("");
            if (getTabEmpresaVO().getDataAtualizacao() != null) {
                LocalDateTime ldtAtualizacao = getTabEmpresaVO().getDataAtualizacao().toLocalDateTime();
                lblDataAtualizacao.setText("data atualização: " + ldtAtualizacao.format(DTF_DATAHORA) + " [" + getTabEmpresaVO().getUsuarioAtualizacaoVO().getApelido() + "]");
                lblDataAtualizacaoDiff.setText("tempo de atualização: " + DataTrabalhada.getStrIntervaloDatas(ldtAtualizacao.toLocalDate(), null));
            }
        }
        //exibirDadosAdicionais();
        //preencherListaDetalhesReceita();
    }

    void exibirDadosEndereco() {
        if (getTabEnderecoVOList() == null) {
            limparEndereco();
            return;
        }
        idEnderecoAtual = listEndereco.getSelectionModel().getSelectedIndex();
        TabEnderecoVO enderecoVO = getTabEnderecoVOList().get(idEnderecoAtual);
        txtEndCEP.setText(FormatarDado.getCampoFormatado(enderecoVO.getCep(), "cep"));
        txtEndLogradouro.setText(enderecoVO.getLogradouro());
        txtEndNumero.setText(enderecoVO.getNumero());
        txtEndComplemento.setText(enderecoVO.getComplemento());
        txtEndBairro.setText(enderecoVO.getBairro());
        txtEndPontoReferencia.setText(enderecoVO.getPontoReferencia());

        boolean diferente = true;
        cboEndUF.getSelectionModel().selectFirst();
        while (diferente) {
            if (cboEndUF.getSelectionModel().getSelectedItem().getSigla().equals(enderecoVO.getSisMunicipioVO().getUfVO().getSigla())) {
                diferente = false;
            } else {
                cboEndUF.getSelectionModel().selectNext();
            }
        }
        diferente = true;
        cboEndMunicipio.getSelectionModel().selectFirst();
        while (diferente) {
            if (cboEndMunicipio.getSelectionModel().getSelectedItem().getDescricao().equals(enderecoVO.getSisMunicipioVO().getDescricao())) {
                diferente = false;
            } else {
                cboEndMunicipio.getSelectionModel().selectNext();
            }
        }
//        System.out.println("cboMunicipio: id [" + cboEndMunicipio.getSelectionModel().getSelectedItem().getId() +
//                "] descricao [" + cboEndMunicipio.getSelectionModel().getSelectedItem().getDescricao() + "]");

    }

    void atualizaListaEndereco() {
        if (getTabEnderecoVOList() == null) {
            listEndereco.getItems().clear();
            return;
        }
        listEndereco.getItems().setAll(getTabEnderecoVOList());
        listEndereco.getSelectionModel().select(0);
    }

    void atualizaListaEmailHomePage() {
        listHomePage.getItems().clear();
        listEmail.getItems().clear();
        if (getTabEmailHomePageVOList() == null) { // se lista de email e home page da empresa for nula limpa listas de email e home page
            return;
        }
        for (TabEmailHomePageVO emailHomeVO : getTabEmailHomePageVOList()) { // percorre lista de emailHomePage da empresa separando nas listas de email e homePage
            if (emailHomeVO.isIsEmail()) // se isEmail for verdadeiro lanca na lista de email da empresa
                listEmail.getItems().add(emailHomeVO);
            else // se isEmail for falso lanca na lista de homePage da empresa
                listHomePage.getItems().add(emailHomeVO);
        }
    }

    void atualizaListaTelefone() {
        if (getTabTelefoneVOList() == null) {
            listTelefone.getItems().clear();
            return;
        }
        listTelefone.getItems().setAll(getTabTelefoneVOList());
    }

    void atualizaListaContato() {
        if (getTabContatoVOList() == null) {
            listContatoNome.getItems().clear();
            return;
        }
        listContatoNome.getItems().setAll(getTabContatoVOList());
    }

    void atualizaListaContatoEmailHomePage() {
        listContatoHomePage.getItems().clear();
        listContatoEmail.getItems().clear();
        if (getTabContatoEmailHomePageVOList() == null) {
            return;
        }
        getTabContatoVO().setTabEmailHomePageVOList(getTabContatoEmailHomePageVOList());
        for (TabEmailHomePageVO contatoEmailHomeVO : getTabContatoEmailHomePageVOList()) {
            if (contatoEmailHomeVO.isIsEmail())
                listContatoEmail.getItems().add(contatoEmailHomeVO);
            else
                listContatoHomePage.getItems().add(contatoEmailHomeVO);
        }
    }

    void atualizaListaContatoTelefone() {
        if (getTabContatoTelefoneVOList() == null) {
            listContatoTelefone.getItems().clear();
            return;
        }
        getTabContatoVO().setTabTelefoneVOList(getTabContatoTelefoneVOList());
        listContatoTelefone.getItems().setAll(getTabContatoTelefoneVOList());
    }

    void limparEndereco() {
        txtEndCEP.setText("");
        txtEndLogradouro.setText("");
        txtEndNumero.setText("");
        txtEndComplemento.setText("");
        txtEndBairro.setText("");
        txtEndPontoReferencia.setText("");
        cboEndUF.getSelectionModel().select(0);
    }

    boolean validarDadosCadastrais() {
        if (!validarDadosEmpresa()) return false;

        if (!validarDadosEndPrincipal()) return false;

        return true;
    }

    boolean validarDadosEmpresa() {
        boolean result = true;
        String valCnpj = txtCNPJ.getText().replaceAll("[\\-/. \\[\\]]", "");
        if ((valCnpj.length() != 11 && valCnpj.length() != 14) & (!ValidadarDado.isCnpjCpfValido(valCnpj))) {
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
        else result = guardarEmpresa();
        return result;
    }

    boolean validarDadosEndPrincipal() {
        boolean result = true;
        guardarEndereco(idEnderecoAtual);
        if (txtEndCEP.getText().replaceAll("[\\-/. \\[\\]]", "").length() != 8 || txtEndCEP.getText().length() == 0) {
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
        //else result = guardarEndereco(listEndereco.getSelectionModel().getSelectedIndex());
        return result;
    }

    boolean guardarEndereco(int index) {
        try {
            if (index < 0) return false;
            //int idEnd = getTabEnderecoVOList().indexOf(listEndereco.getItems().get(index));
            TabEnderecoVO endAntVO = getTabEnderecoVOList().get(index);
            endAntVO.setSisTipoEnderecoVO(listEndereco.getItems().get(index).getSisTipoEnderecoVO());
            endAntVO.setSisTipoEndereco_id(listEndereco.getItems().get(index).getSisTipoEndereco_id());
            endAntVO.setCep(txtEndCEP.getText().replaceAll("[\\-/. \\[\\]]", ""));
            endAntVO.setLogradouro(txtEndLogradouro.getText());
            endAntVO.setNumero(txtEndNumero.getText());
            endAntVO.setComplemento(txtEndComplemento.getText());
            endAntVO.setBairro(txtEndBairro.getText());
            endAntVO.setPontoReferencia(txtEndPontoReferencia.getText());
            endAntVO.setSisMunicipio_id(cboEndMunicipio.getSelectionModel().getSelectedItem().getId());
            endAntVO.setSisMunicipioVO(new SisMunicipioDAO().getSisMunicipioVO(cboEndMunicipio.getSelectionModel().getSelectedItem().getId()));
            getTabEnderecoVOList().set(index, endAntVO);
            listEndereco.getItems().set(index, endAntVO);
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
        return true;
    }

    boolean guardarEmpresa() {
        try {
            getTabEmpresaVO().setIsEmpresa(cboClassificacaoJuridica.getSelectionModel().getSelectedIndex());
            getTabEmpresaVO().setCnpj(txtCNPJ.getText().replaceAll("[\\-/. \\[\\]]", ""));
            getTabEmpresaVO().setIe(txtIE.getText().replaceAll("[\\-/. \\[\\]]", ""));
            getTabEmpresaVO().setRazao(txtRazao.getText());
            getTabEmpresaVO().setFantasia(txtFantasia.getText());
            getTabEmpresaVO().setIsCliente(chkIsCliente.isSelected());
            getTabEmpresaVO().setIsFornecedor(chkIsFornecedor.isSelected());
            getTabEmpresaVO().setIsTransportadora(chkIsTransportadora.isSelected());
            getTabEmpresaVO().setSisSituacaoSistema_id(cboSituacaoSistema.getSelectionModel().getSelectedItem().getId());
            getTabEmpresaVO().setUsuarioCadastro_id(Integer.parseInt(USUARIO_LOGADO_ID));
            getTabEmpresaVO().setUsuarioAtualizacao_id(Integer.parseInt(USUARIO_LOGADO_ID));

            LocalDate ldAbertura = LocalDate.parse(lblDataAbertura.getText().substring(15, 25), DTF_DATA);
            getTabEmpresaVO().setDataAbertura(Date.valueOf(ldAbertura));

            getTabEmpresaVO().setNaturezaJuridica(lblNaturezaJuridica.getText().substring(19));
        } catch (Exception ex) {
            return false;
        }
        return true;
    }

    void deletaEndereco(TabEnderecoVO enderecoVO) {
        if (enderecoVO.getId() != 0) {
            if (deletadosTabEnderecoVOList == null)
                deletadosTabEnderecoVOList = new ArrayList<>();
            deletadosTabEnderecoVOList.add(enderecoVO);
        }
        getTabEnderecoVOList().remove(enderecoVO);
        atualizaListaEndereco();
    }

    void deletaContato(TabContatoVO contatoVO) {
        if (contatoVO.getId() != 0) {
            if (deletadosTabContatoVOList == null)
                deletadosTabContatoVOList = new ArrayList<>();
            deletadosTabContatoVOList.add(listContatoNome.getSelectionModel().getSelectedItem());
        }
        getTabContatoVOList().remove(listContatoNome.getSelectionModel().getSelectedItem());
        atualizaListaContato();
    }

    void deletaEmailHomePage(boolean isEmpresa, TabEmailHomePageVO emailHomePageVO) {
        if (isEmpresa) {
            if (emailHomePageVO.getId() != 0) {
                if (deletadosTabEmailHomePageVOList == null)
                    deletadosTabEmailHomePageVOList = new ArrayList<>();
                deletadosTabEmailHomePageVOList.add(emailHomePageVO);
            }
            getTabEmailHomePageVOList().remove(emailHomePageVO);
            atualizaListaEmailHomePage();
        } else {
            if (emailHomePageVO.getId() != 0) {
                if (deletadosTabContatoEmailHomePageVOList == null)
                    deletadosTabContatoEmailHomePageVOList = new ArrayList<>();
                deletadosTabContatoEmailHomePageVOList.add(emailHomePageVO);
            }
            getTabContatoVO().getTabEmailHomePageVOList().remove(emailHomePageVO);
            atualizaListaContatoEmailHomePage();
        }
    }

    void deletaTelefone(boolean isEmpresa, TabTelefoneVO telefoneVO) {
        if (isEmpresa) {
            if (telefoneVO.getId() != 0) {
                if (deletadosTabTelefoneVOList == null)
                    deletadosTabTelefoneVOList = new ArrayList<>();
                deletadosTabTelefoneVOList.add(telefoneVO);
            }
            getTabTelefoneVOList().remove(telefoneVO);
            atualizaListaTelefone();
        } else {
            if (telefoneVO.getId() != 0) {
                if (deletadosTabContatoTelefoneVOList == null)
                    deletadosTabContatoTelefoneVOList = new ArrayList<>();
                deletadosTabContatoTelefoneVOList.add(telefoneVO);
            }
            getTabContatoVO().getTabTelefoneVOList().remove(telefoneVO);
            atualizaListaContatoTelefone();
        }
    }


//    void exibirRetorno_WsCnpjReceitaWs(WsCnpjReceitaWsVO receitaWsVO) {
//        cboClassificacaoJuridica.getSelectionModel().select(1);
//        txtCNPJ.setText(receitaWsVO.getCnpj());
//        txtRazao.setText(receitaWsVO.getNome());
//        if (receitaWsVO.getFantasia().equals("")) receitaWsVO.setFantasia("***");
//        txtFantasia.setText(receitaWsVO.getFantasia());
//
//        lblNaturezaJuridica.setText("Natureza Júridica: " + receitaWsVO.getNaturezaJuridica());
//        lblDataAbertura.setText("data abertura: ");
//        lblDataAberturaDiff.setText("tempo de abertura: ");
//        if (receitaWsVO.getAbertura() != null) {
//            LocalDate ldAbertura = LocalDate.parse(receitaWsVO.getAbertura(), DTF_DATA);
//            lblDataAbertura.setText("data abertura: " + ldAbertura.format(DTF_DATA));
//            lblDataAberturaDiff.setText("tempo de abertura: " + DataTrabalhada.getStrIntervaloDatas(ldAbertura, null));
//        }
//
//        if (getTtvEnderecoVO().size() == 0) {
//            getTtvEnderecoVO().add(addEndereco());
//            listEndereco.getItems().setAll(getTtvEnderecoVO());
//        }
//        listEndereco.getSelectionModel().select(0);
//        String valCep = receitaWsVO.getCep().replaceAll("[\\-/. \\[\\]]", "");
//        txtEndCEP.setText(FormatarDado.getCampoFormatado(valCep, "cep"));
//        txtEndLogradouro.setText(receitaWsVO.getLogradouro());
//        txtEndNumero.setText(receitaWsVO.getNumero());
//        if (receitaWsVO.getNumero().equals("")) txtEndNumero.setText("0");
//        txtEndComplemento.setText(receitaWsVO.getComplemento());
//        txtEndBairro.setText(receitaWsVO.getBairro());
//        if (receitaWsVO.getMunicipio().equals("")) {
//            cboEndUF.getSelectionModel().select(0);
//        } else {
//            SisMunicipioVO receitaMunicipio = new SisMunicipioDAO().getMunicipioVO(receitaWsVO.getMunicipio());
//            cboEndUF.getSelectionModel().select(receitaMunicipio.getUfVO());
//            cboEndMunicipio.getSelectionModel().select(receitaMunicipio);
//        }
//        txtEndPontoReferencia.setText("");
//        guardarEndereco(listEndereco.getSelectionModel().getSelectedIndex());
//
//        if (receitaWsVO.getTelefone() != "") {
//            List<String> receitaWsTelefone = new ArrayList<>();
//            for (String strCodTelefone : receitaWsVO.getTelefone().replaceAll("[-]", "").split("/")) {
//                boolean telExiste = false;
//                strCodTelefone = strCodTelefone.substring(strCodTelefone.length() - 9).replaceAll("[\\-/.() \\[\\]]", "");
//                if (strCodTelefone.length() == 8 & Integer.parseInt(strCodTelefone.substring(0, 1)) >= 8)
//                    strCodTelefone = "9" + strCodTelefone;
//                for (int i = 0; i < getTtvTelefoneVO().size(); i++) {
//                    if (getTtvTelefoneVO().get(i).getDescricao().equals(strCodTelefone.replaceAll("[\\-/. \\[\\]]", "")))
//                        telExiste = true;
//                }
//                if (!telExiste)
//                    receitaWsTelefone.add(strCodTelefone);
//            }
//            for (int i = 0; i < receitaWsTelefone.size(); i++) {
//                TabTelefoneVO tel = new TabTelefoneVO();
//                tel.setDescricao(receitaWsTelefone.get(i));
//                tel.setTelefoneOperadora_id(2);
//                tel.setTelefoneOperadoraVO(new SisTelefoneOperadoraDAO().getTelefoneOperadoraVO(2));
//                tel.setId(0);
//                getTtvTelefoneVO().add(tel);
//            }
//            listTelefone.getItems().setAll(getTtvTelefoneVO());
//        }
//
//        if (receitaWsVO.getEmail() != "") {
//            List<String> receitaWsEmail = new ArrayList<>();
//            int qtdEmail = receitaWsVO.getEmail().length() - receitaWsVO.getEmail().replaceAll("@", "").length();
//            String strEmail = null;
//            if (qtdEmail > 1) {
//                System.out.print(qtdEmail + " emails no wsReceitaWsVO\n" + qtdEmail + " emails no wsReceitaWsVO\n" + qtdEmail + " emails no wsReceitaWsVO\n" + qtdEmail + " emails no wsReceitaWsVO\n" + qtdEmail + " emails no wsReceitaWsVO\n" + qtdEmail + " emails no wsReceitaWsVO\n");
//            } else {
//                strEmail = receitaWsVO.getEmail();
//            }
//            boolean emailExiste = false;
//            for (int i = 0; i < listEmail.getItems().size(); i++)
//                if (receitaWsVO.getEmail().toLowerCase().equals(listEmail.getItems().get(i).getDescricao().toLowerCase()))
//                    emailExiste = true;
//            if (!emailExiste)
//                receitaWsEmail.add(strEmail);
//            for (int i = 0; i < receitaWsEmail.size(); i++) {
//                TabEmailHomePageVO email = new TabEmailHomePageVO();
//                email.setId(0);
//                email.setDescricao(receitaWsEmail.get(i));
//                email.setIsEmail(1);
//                getTtvEmailHomePageVO().add(email);
//                listEmail.getItems().add(email);
//            }
//        }
//
//        if (getTtvDetalheReceitaFederalVO() == null)
//            setTtvDetalheReceitaFederalVO(new ArrayList<>());
//        for (TabEmpresaDetalheReceitaFederalVO detalhe : getTtvDetalheReceitaFederalVO())
//            detalhe.setEmpresa_id(0);
//
//
//        if (receitaWsVO.getAtividadePrincipal().size() > 0) {
//            List<Pair<String, String>> receitaWsAtividadePrincipal = new ArrayList<>();
//            for (Pair<String, String> pair : receitaWsVO.getAtividadePrincipal()) {
//                boolean atividadePrincExiste = false;
//                for (int i = 0; i < listAtividadePrincipal.getItems().size(); i++)
//                    if (((TabEmpresaDetalheReceitaFederalVO) listAtividadePrincipal.getItems().get(i)).getStr_key().equals(pair.getKey()))
//                        atividadePrincExiste = true;
//
//                if (!atividadePrincExiste)
//                    receitaWsAtividadePrincipal.add(pair);
//            }
//            for (int i = 0; i < receitaWsAtividadePrincipal.size(); i++) {
//                TabEmpresaDetalheReceitaFederalVO atividadePrincipal = new TabEmpresaDetalheReceitaFederalVO();
//                atividadePrincipal.setId(0);
//                atividadePrincipal.setEmpresa_id(0);
//                atividadePrincipal.setIsAtividadePrincipal(1);
//                atividadePrincipal.setStr_key(receitaWsAtividadePrincipal.get(i).getKey());
//                atividadePrincipal.setStr_value(receitaWsAtividadePrincipal.get(i).getValue());
//                getTtvDetalheReceitaFederalVO().add(atividadePrincipal);
//            }
//        }
//
//        if (receitaWsVO.getAtividadesSecundarias().size() > 0) {
//            List<Pair<String, String>> receitaWsAtividadeSecundaria = new ArrayList<>();
//            for (Pair<String, String> pair : receitaWsVO.getAtividadesSecundarias()) {
//                boolean atividadeSecunExiste = false;
//                for (int i = 0; i < listAtividadeSecundaria.getItems().size(); i++)
//                    if (((TabEmpresaDetalheReceitaFederalVO) listAtividadeSecundaria.getItems().get(i)).getStr_key().equals(pair.getKey()))
//                        atividadeSecunExiste = true;
//                if (!atividadeSecunExiste)
//                    receitaWsAtividadeSecundaria.add(pair);
//            }
//            for (int i = 0; i < receitaWsAtividadeSecundaria.size(); i++) {
//                TabEmpresaDetalheReceitaFederalVO atividadeSecundaria = new TabEmpresaDetalheReceitaFederalVO();
//                atividadeSecundaria.setEmpresa_id(0);
//                atividadeSecundaria.setId(0);
//                atividadeSecundaria.setIsAtividadePrincipal(0);
//                atividadeSecundaria.setStr_key(receitaWsAtividadeSecundaria.get(i).getKey());
//                atividadeSecundaria.setStr_value(receitaWsAtividadeSecundaria.get(i).getValue());
//                getTtvDetalheReceitaFederalVO().add(atividadeSecundaria);
//            }
//
//        }
//
//        if (receitaWsVO.getQsa().size() > 0) {
//            List<Pair<String, String>> receitaWsQsa = new ArrayList<>();
//            for (Pair<String, String> pair : receitaWsVO.getQsa()) {
//                boolean qsaExiste = false;
//                for (int i = 0; i < ttvDetalheReceita.getCurrentItemsCount(); i++)
//                    if (((TabEmpresaDetalheReceitaFederalVO) ttvDetalheReceita.getTreeItem(i).getValue()).getStr_key().equals(pair.getKey()))
//                        qsaExiste = true;
//                if (!qsaExiste)
//                    receitaWsQsa.add(pair);
//            }
//            for (int i = 0; i < receitaWsQsa.size(); i++) {
//                TabEmpresaDetalheReceitaFederalVO qsa = new TabEmpresaDetalheReceitaFederalVO();
//                qsa.setEmpresa_id(0);
//                qsa.setId(0);
//                qsa.setIsAtividadePrincipal(2);
//                qsa.setStr_key(receitaWsQsa.get(i).getKey());
//                qsa.setStr_value(receitaWsQsa.get(i).getValue());
//                getTtvDetalheReceitaFederalVO().add(qsa);
//            }
//        }
//        preencherListaDetalhesReceita();
//    }

    //    int indexObservableEmpresa = 0;
//    TabEmpresaVO ttvEmpresaVO;
//    List<TabEnderecoVO> ttvEnderecoVO;
//    List<TabEmailHomePageVO> ttvEmailHomePageVO;
//    List<TabTelefoneVO> ttvTelefoneVO;
//    TabContatoVO ttvContatoVO;
//    List<TabEmailHomePageVO> ttvContatoEmailHomePageVO;
//    List<TabTelefoneVO> ttvContatoTelefoneVO;
//    List<TabEmpresaDetalheReceitaFederalVO> ttvDetalheReceitaFederalVO;
//
//    ObservableList<TabEmailHomePageVO> emailHomePageVOObservableList;
//    FilteredList<TabEmailHomePageVO> emailVOFilteredList;
//    FilteredList<TabEmailHomePageVO> homePageVOFilteredList;
//    ObservableList<TabEmailHomePageVO> contatoEmailHomePageVOObservableList;
//    FilteredList<TabEmailHomePageVO> contatoEmailVOFilteredList;
//    FilteredList<TabEmailHomePageVO> contatoHomePageVOFilteredList;
//    ObservableList<TabEmpresaDetalheReceitaFederalVO> empresa_detalheReceitaFederalVOObservableList;
//    FilteredList<TabEmpresaDetalheReceitaFederalVO> atividadePrincipal_detalheReceitaFederalVOFilteredList;
//    FilteredList<TabEmpresaDetalheReceitaFederalVO> atividadeSecundaria_detalheReceitaFederalVOFilteredList;
//    FilteredList<TabEmpresaDetalheReceitaFederalVO> qsa_detalheReceitaFederalVOFilteredList;
//
//
//    public TabEmpresaVO getTtvEmpresaVO() {
//        return ttvEmpresaVO;
//    }
//
//    public void setTtvEmpresaVO(TabEmpresaVO ttvEmpresaVO) {
//        if (ttvEmpresaVO == null)
//            ttvEmpresaVO = new TabEmpresaVO();
//        this.ttvEmpresaVO = ttvEmpresaVO;
//
//        setTtvEnderecoVO(getTtvEmpresaVO().getEnderecoVOList());
//        setTtvEmailHomePageVO(getTtvEmpresaVO().getEmailHomePageVOList());
//        setTtvTelefoneVO(getTtvEmpresaVO().getTelefoneVOList());
//        setTtvDetalheReceitaFederalVO(getTtvEmpresaVO().getDetalheReceitaFederalVOList());
//    }
//
//    public List<TabEnderecoVO> getTtvEnderecoVO() {
//        return ttvEnderecoVO;
//    }
//
//    public void setTtvEnderecoVO(List<TabEnderecoVO> ttvEnderecoVO) {
//        if (ttvEnderecoVO == null)
//            ttvEnderecoVO = new ArrayList<>();
//        this.ttvEnderecoVO = ttvEnderecoVO;
//        listEndereco.getItems().setAll(ttvEnderecoVO);
//    }
//
//    public List<TabEmailHomePageVO> getTtvEmailHomePageVO() {
//        return ttvEmailHomePageVO;
//    }
//
//    public void setTtvEmailHomePageVO(List<TabEmailHomePageVO> ttvEmailHomePageVO) {
//        if (ttvEmailHomePageVO == null)
//            ttvEmailHomePageVO = new ArrayList<>();
//        this.ttvEmailHomePageVO = ttvEmailHomePageVO;
//    }
//
//    public List<TabTelefoneVO> getTtvTelefoneVO() {
//        return ttvTelefoneVO;
//    }
//
//    public void setTtvTelefoneVO(List<TabTelefoneVO> ttvTelefoneVO) {
//        if (ttvTelefoneVO == null)
//            ttvTelefoneVO = new ArrayList<>();
//        this.ttvTelefoneVO = ttvTelefoneVO;
//    }
//
//    public TabContatoVO getTtvContatoVO() {
//        return ttvContatoVO;
//    }
//
//    public void setTtvContatoVO(TabContatoVO ttvContatoVO) {
//        if (ttvContatoVO == null)
//            ttvContatoVO = new TabContatoVO();
//        this.ttvContatoVO = ttvContatoVO;
//
//        setTtvContatoEmailHomePageVO(ttvContatoVO.getEmailHomePageVOList());
//        setTtvContatoTelefoneVO(ttvContatoVO.getTelefoneVOList());
//    }
//
//    public List<TabEmailHomePageVO> getTtvContatoEmailHomePageVO() {
//        return ttvContatoEmailHomePageVO;
//    }
//
//    public void setTtvContatoEmailHomePageVO(List<TabEmailHomePageVO> ttvContatoEmailHomePageVO) {
//        if (ttvContatoEmailHomePageVO == null)
//            ttvContatoEmailHomePageVO = new ArrayList<>();
//        this.ttvContatoEmailHomePageVO = ttvContatoEmailHomePageVO;
//    }
//
//    public List<TabTelefoneVO> getTtvContatoTelefoneVO() {
//        return ttvContatoTelefoneVO;
//    }
//
//    public void setTtvContatoTelefoneVO(List<TabTelefoneVO> ttvContatoTelefoneVO) {
//        if (ttvContatoTelefoneVO == null)
//            ttvContatoTelefoneVO = new ArrayList<>();
//        this.ttvContatoTelefoneVO = ttvContatoTelefoneVO;
//    }
//
//    public List<TabEmpresaDetalheReceitaFederalVO> getTtvDetalheReceitaFederalVO() {
//        return ttvDetalheReceitaFederalVO;
//    }
//
//    public void setTtvDetalheReceitaFederalVO(List<TabEmpresaDetalheReceitaFederalVO> ttvDetalheReceitaFederalVO) {
//        this.ttvDetalheReceitaFederalVO = ttvDetalheReceitaFederalVO;
//    }
//
//
//
//
//    void carregarEmailHomePage() {
//        emailHomePageVOObservableList = FXCollections.observableArrayList(getTtvEmailHomePageVO());
//
//        homePageVOFilteredList = new FilteredList<TabEmailHomePageVO>(emailHomePageVOObservableList, homePage -> true);
//        homePageVOFilteredList.setPredicate(homePage -> homePage.getIsEmail() == 0);
//        preencherHomePageEmpresa();
//
//        emailVOFilteredList = new FilteredList<TabEmailHomePageVO>(emailHomePageVOObservableList, email -> true);
//        emailVOFilteredList.setPredicate(email -> email.getIsEmail() == 1);
//        preencherEmailEmpresa();
//    }
//
//
//
//
//    void preencherTabelaQsa() {
//        final TreeItem<TabEmpresaDetalheReceitaFederalVO> root = new RecursiveTreeItem<TabEmpresaDetalheReceitaFederalVO>
//                (qsa_detalheReceitaFederalVOFilteredList, RecursiveTreeObject::getChildren);
//        ttvDetalheReceita.getColumns().setAll(TabModel.getColunaQsaKey(), TabModel.getColunaQsaValue());
//        ttvDetalheReceita.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
//        ttvDetalheReceita.setRoot(root);
//        ttvDetalheReceita.setShowRoot(false);
//    }
//
//
//
//    void carregarContatoEmailHomePage() {
//        contatoEmailHomePageVOObservableList = FXCollections.observableArrayList(getTtvContatoEmailHomePageVO());
//
//        contatoHomePageVOFilteredList = new FilteredList<TabEmailHomePageVO>(contatoEmailHomePageVOObservableList, contHomePage -> true);
//        contatoHomePageVOFilteredList.setPredicate(contHomePage -> contHomePage.getIsEmail() == 0);
//        preencherContatoHomePage();
//
//        contatoEmailVOFilteredList = new FilteredList<TabEmailHomePageVO>(contatoEmailHomePageVOObservableList, contEmail -> true);
//        contatoEmailVOFilteredList.setPredicate(contEmail -> contEmail.getIsEmail() == 1);
//        preencherContatoEmail();
//
//    }
//
//    void preencherListaEnderecoEmpresa() {
//        listEndereco.getItems().clear();
//        limparEndereco();
//        if (getTtvEnderecoVO() != null)
//            listEndereco.getItems().setAll(getTtvEnderecoVO());
//        listEndereco.getSelectionModel().select(0);
//    }
//
//    void preencherListaTelefoneEmpresa() {
//        listTelefone.getItems().clear();
//        if (getTtvTelefoneVO() != null)
//            listTelefone.getItems().setAll(getTtvTelefoneVO());
//    }
//
//    void preencherListaTelefoneContato() {
//        listContatoTelefone.getItems().clear();
//        if (getTtvContatoTelefoneVO() != null)
//            listContatoTelefone.getItems().setAll(getTtvContatoTelefoneVO());
//    }
//
//    void preencherEmailEmpresa() {
//        listEmail.getItems().clear();
//        if (emailVOFilteredList != null)
//            listEmail.getItems().setAll(emailVOFilteredList);
//    }
//
//    void preencherHomePageEmpresa() {
//        listHomePage.getItems().clear();
//        if (homePageVOFilteredList != null)
//            listHomePage.getItems().setAll(homePageVOFilteredList);
//    }
//
//    void preencherContatoEmail() {
//        listContatoEmail.getItems().clear();
//        if (contatoEmailVOFilteredList != null)
//            listContatoEmail.getItems().setAll(contatoEmailVOFilteredList);
//    }
//
//    void preencherContatoHomePage() {
//        listContatoHomePage.getItems().clear();
//        if (contatoHomePageVOFilteredList != null)
//            listContatoHomePage.getItems().setAll(contatoHomePageVOFilteredList);
//    }
//
//    void preencherListaContatoEmpresa() {
//        listContatoNome.getItems().clear();
//        if (getTtvEmpresaVO().getContatoVOList() != null)
//            listContatoNome.getItems().setAll(getTtvEmpresaVO().getContatoVOList());
//        exiberDadosContato();
//    }
//
//    void preencherListaDetalhesReceita() {
//        listAtividadePrincipal.getItems().clear();
//        listAtividadeSecundaria.getItems().clear();
//
//        empresa_detalheReceitaFederalVOObservableList = FXCollections.observableArrayList(getTtvDetalheReceitaFederalVO());
//        atividadePrincipal_detalheReceitaFederalVOFilteredList = new FilteredList<TabEmpresaDetalheReceitaFederalVO>(empresa_detalheReceitaFederalVOObservableList, principal -> true);
//        atividadePrincipal_detalheReceitaFederalVOFilteredList.setPredicate(principal -> {
//            if (principal.getIsAtividadePrincipal() == 1) return true;
//            return false;
//        });
//        listAtividadePrincipal.getItems().setAll(atividadePrincipal_detalheReceitaFederalVOFilteredList);
//
//        atividadeSecundaria_detalheReceitaFederalVOFilteredList = new FilteredList<TabEmpresaDetalheReceitaFederalVO>(empresa_detalheReceitaFederalVOObservableList, principal -> true);
//        atividadeSecundaria_detalheReceitaFederalVOFilteredList.setPredicate(principal -> {
//            if (principal.getIsAtividadePrincipal() == 0) return true;
//            return false;
//        });
//        listAtividadeSecundaria.getItems().setAll(atividadeSecundaria_detalheReceitaFederalVOFilteredList);
//
//        qsa_detalheReceitaFederalVOFilteredList = new FilteredList<TabEmpresaDetalheReceitaFederalVO>(empresa_detalheReceitaFederalVOObservableList, principal -> true);
//        qsa_detalheReceitaFederalVOFilteredList.setPredicate(principal -> {
//            if (principal.getIsAtividadePrincipal() == 2) return true;
//            return false;
//        });
//        preencherTabelaQsa();
//    }
//
//
//    void exibirDadosAdicionais() {
//        preencherListaEnderecoEmpresa();
//        preencherListaTelefoneEmpresa();
//        carregarEmailHomePage();
//        preencherListaContatoEmpresa();
//    }
//
//
//    void exibirDadosEndereco() {
//        int index = listEndereco.getSelectionModel().getSelectedIndex();
//        if (index < 0)
//            index = 0;
//        TabEnderecoVO enderecoVO = listEndereco.getItems().get(index);
//        txtEndCEP.setText(FormatarDado.getCampoFormatado(enderecoVO.getCep(), "cep"));
//        txtEndLogradouro.setText(enderecoVO.getLogradouro());
//        txtEndNumero.setText(enderecoVO.getNumero());
//        txtEndComplemento.setText(enderecoVO.getComplemento());
//        txtEndBairro.setText(enderecoVO.getBairro());
//        txtEndPontoReferencia.setText(enderecoVO.getPontoReferencia());
//        cboEndUF.getSelectionModel().select(enderecoVO.getMunicipioVO().getUfVO());
//        cboEndMunicipio.getSelectionModel().select(enderecoVO.getMunicipioVO());
//    }
//
//    void exiberDadosContato() {
//        if (listContatoNome.getItems().size() <= 0) {
//            listContatoHomePage.getItems().clear();
//            listContatoEmail.getItems().clear();
//            listContatoTelefone.getItems().clear();
//        } else {
//            int index = listContatoNome.getSelectionModel().getSelectedIndex();
//            if (index < 0) index = 0;
//            try {
//                setTtvContatoVO(getTtvEmpresaVO().getContatoVOList().get(index));
//                carregarContatoEmailHomePage();
//                preencherListaTelefoneContato();
//            } catch (Exception ex) {
//                if (!(ex instanceof IndexOutOfBoundsException))
//                    ex.printStackTrace();
//            }
//        }
//    }
//
//    TabEmpresaVO guardarEmpresa() {
//        TabEmpresaVO emp = getTtvEmpresaVO();
//        emp.setIsEmpresa(cboClassificacaoJuridica.getSelectionModel().getSelectedIndex());
//        emp.setCnpj(txtCNPJ.getText().replaceAll("[\\-/. \\[\\]]", ""));
//        emp.setIe(txtIE.getText().replaceAll("[\\-/. \\[\\]]", ""));
//        emp.setRazao(txtRazao.getText());
//        emp.setFantasia(txtFantasia.getText());
//        int isCli = 0;
//        int isFor = 0;
//        int isTransp = 0;
//        if (chkIsCliente.isSelected()) isCli = 1;
//        if (chkIsFornecedor.isSelected()) isFor = 1;
//        if (chkIsTransportadora.isSelected()) isTransp = 1;
//        emp.setUsuarioCadastro_id(Integer.parseInt(USUARIO_LOGADO_ID));
//        emp.setUsuarioAtualizacao_id(Integer.parseInt(USUARIO_LOGADO_ID));
//        emp.setSisSituacaoSistema_id(((SisSituacaoSistemaVO) cboSituacaoSistema.getSelectionModel().getSelectedItem()).getId());
//
//        LocalDate ldAbertura = LocalDate.parse(lblDataAbertura.getText().substring(15, 25), DTF_DATA);
//        emp.setDataAbertura(Date.valueOf(ldAbertura));
//
//        emp.setNaturezaJuridica(lblNaturezaJuridica.getText().substring(19));
//        return emp;
//    }
//
//
//    TabEmailHomePageVO addEmailHomePage(boolean isEmpresa, boolean isEmail) {
//        String empresa = "a empresa: " + txtRazao.getText();
//        if (!isEmpresa) empresa = "o contato: " + listContatoNome.getSelectionModel().getSelectedItem().toString();
//        String tipDado = "email";
//        String ico = "ic_web_email_white_24dp.png";
//        if (!isEmail) {
//            tipDado = "home page";
//            ico = "ic_web_home_page_white_24dp.png";
//        }
//        String strEmailHomePage;
//        try {
//            strEmailHomePage = new AlertMensagem("Adicionar dados [" + tipDado + "]",
//                    USUARIO_LOGADO_APELIDO + ", qual " + tipDado + " a ser adicionada para " + empresa + " ?",
//                    ico).getRetornoAlert_TextField(FormatarDado.gerarMascara("", 80, "?"), "").get();
//        } catch (Exception ex) {
//            if (!(ex instanceof NoSuchElementException))
//                ex.printStackTrace();
//            return null;
//        }
//        if (strEmailHomePage == null) return null;
//        if ((isEmail) && (!strEmailHomePage.contains("@"))) {
//            new AlertMensagem("Dados inválidos", USUARIO_LOGADO_APELIDO
//                    + ", o email informado é inválido!", "ic_msg_alerta_triangulo_white_24dp.png").getRetornoAlert_OK();
//            return null;
//        }
//        TabEmailHomePageVO emailHomePageVO = new TabEmailHomePageVO();
//        emailHomePageVO.setId(0);
//        emailHomePageVO.setDescricao(strEmailHomePage);
//        int email = 0;
//        if (isEmail) email = 1;
//        emailHomePageVO.setIsEmail(email);
//
//        return emailHomePageVO;
//    }
//
//
//
//    TabContatoVO addContato() {
//        Pair<String, Object> pairContato;
//        AlertMensagem alertMensagem = new AlertMensagem();
//        alertMensagem.setPromptTextField("Contato");
//        alertMensagem.setPromptCombo("Cargo");
//        alertMensagem.setCabecalho("Adicionar dados [contato]");
//        alertMensagem.setPromptText(USUARIO_LOGADO_APELIDO + ", qual o contato a ser adicionado para a empresa "
//                + txtRazao.getText() + "?");
//        alertMensagem.setStrIco("ic_contato_add_white_24dp.png");
//        try {
//            pairContato = alertMensagem.getRetornoAlert_TextFieldEComboBox(cargoVOList,
//                    FormatarDado.gerarMascara("", 80, "@"), "").get();
//        } catch (Exception ex) {
//            if (!(ex instanceof NoSuchElementException)) {
//                ex.printStackTrace();
//            }
//            return null;
//        }
//        if (pairContato == null) return null;
//        TabContatoVO contatoVO = new TabContatoVO();
//        contatoVO.setId(0);
//        contatoVO.setDescricao(pairContato.getKey().toString());
//        contatoVO.setCargoVO((SisCargoVO) pairContato.getValue());
//        contatoVO.setCargo_id(contatoVO.getCargoVO().getId());
//        contatoVO.setTelefone_ids("");
//        contatoVO.setTelefoneVOList(new ArrayList<TabTelefoneVO>());
//        contatoVO.setEmailHomePage_ids("");
//        contatoVO.setEmailHomePageVOList(new ArrayList<TabEmailHomePageVO>());
//        return contatoVO;
//    }
//
//    TabContatoVO editContato(TabContatoVO contato) {
//        Pair<String, Object> pairContato;
//        AlertMensagem alertMensagem = new AlertMensagem();
//        alertMensagem.setPromptTextField("Contato");
//        alertMensagem.setPromptCombo("Cargo");
//        alertMensagem.setCabecalho("Editar informações [contato]");
//        alertMensagem.setPromptText(USUARIO_LOGADO_APELIDO + ", qual alteração será feita no contato da empresa "
//                + txtRazao.getText() + "?");
//        alertMensagem.setStrIco("ic_contato_add_white_24dp.png");
//        try {
//            pairContato = alertMensagem.getRetornoAlert_TextFieldEComboBox(cargoVOList,
//                    FormatarDado.gerarMascara("", 80, "@"),
//                    contato.getDescricao()).get();
//        } catch (Exception ex) {
//            if (!(ex instanceof NoSuchElementException)) {
//                ex.printStackTrace();
//            }
//            return null;
//        }
//        if (pairContato == null) return contato;
//        contato.setDescricao(pairContato.getKey().toString());
//        contato.setCargoVO((SisCargoVO) pairContato.getValue());
//        contato.setCargo_id(contato.getId());
//
//        return contato;
//    }
//
//
//    void exibirRetorno_WsCepPostmon(WsCepPostmonVO cepPostmonVO) {
//        String valCep = cepPostmonVO.getCep().replaceAll("[\\-/. \\[\\]]", "");
//        txtEndCEP.setText(FormatarDado.getCampoFormatado(valCep, "cep"));
//        txtEndLogradouro.setText(cepPostmonVO.getLogradouro());
//        txtEndNumero.setText("");
//        txtEndComplemento.setText("");
//        txtEndBairro.setText(cepPostmonVO.getBairro());
//        if (cepPostmonVO.getEstado_sigla().equals("")) cepPostmonVO.setEstado_sigla("AM");
//        cboEndUF.getSelectionModel().select(new SisUFDAO().getUfVO(cepPostmonVO.getEstado_sigla()));
//        if (cepPostmonVO.getCidade().equals("")) cepPostmonVO.setCidade("MANAUS");
//        cboEndMunicipio.getSelectionModel().select(new SisMunicipioDAO().getMunicipioVO(cepPostmonVO.getCidade()));
//        txtEndPontoReferencia.setText("");
//        guardarEndereco(listEndereco.getSelectionModel().getSelectedIndex());
//    }
//
//    void keyInsert() {
//        if (listHomePage.isFocused()) {
//            TabEmailHomePageVO homePage;
//            if ((homePage = addEmailHomePage(true, false)) == null) return;
//            getTtvEmailHomePageVO().add(homePage);
//            listHomePage.getItems().add(homePage);
//            listHomePage.getSelectionModel().selectLast();
//        }
//        if (listEmail.isFocused()) {
//            TabEmailHomePageVO email;
//            if ((email = addEmailHomePage(true, true)) == null) return;
//            getTtvEmailHomePageVO().add(email);
//            listEmail.getItems().add(email);
//            listEmail.getSelectionModel().selectLast();
//        }
//        if (listTelefone.isFocused()) {
//            TabTelefoneVO telefone;
//            if ((telefone = addTelefone(true)) == null) return;
//            getTtvTelefoneVO().add(telefone);
//            listTelefone.getItems().add(telefone);
//            listTelefone.getSelectionModel().selectLast();
//        }
//        if (listContatoNome.isFocused()) {
//            TabContatoVO contato;
//            if ((contato = addContato()) == null) return;
//            getTtvEmpresaVO().getContatoVOList().add(contato);
//            listContatoNome.getItems().setAll(getTtvEmpresaVO().getContatoVOList());
//            listContatoNome.getSelectionModel().selectLast();
//        }
//        if (listContatoHomePage.isFocused()) {
//            if (listContatoNome.getSelectionModel().getSelectedIndex() < 0)
//                listContatoNome.getSelectionModel().select(0);
//            TabEmailHomePageVO homePage;
//            if ((homePage = addEmailHomePage(false, false)) == null) return;
//            getTtvContatoEmailHomePageVO().add(homePage);
//            listContatoHomePage.getItems().add(homePage);
//            listContatoHomePage.getSelectionModel().selectLast();
//        }
//        if (listContatoEmail.isFocused()) {
//            if (listContatoNome.getSelectionModel().getSelectedIndex() < 0)
//                listContatoNome.getSelectionModel().select(0);
//            TabEmailHomePageVO email;
//            if ((email = addEmailHomePage(false, true)) == null) return;
//            getTtvContatoEmailHomePageVO().add(email);
//            listContatoEmail.getItems().add(email);
//            listContatoEmail.getSelectionModel().selectLast();
//        }
//        if (listContatoTelefone.isFocused()) {
//            if (listContatoNome.getSelectionModel().getSelectedIndex() < 0)
//                listContatoNome.getSelectionModel().select(0);
//            TabTelefoneVO telefone;
//            if ((telefone = addTelefone(false)) == null) return;
//            getTtvContatoTelefoneVO().add(telefone);
//            listContatoTelefone.getItems().add(telefone);
//            listContatoTelefone.getSelectionModel().selectLast();
//        }
//    }
//
//    void keyDelete() {
//        if ((listEndereco.isFocused()) && (listEndereco.getSelectionModel().getSelectedItem().getTipoEnderecoVO().getId() != 1)) {
//            getTtvEnderecoVO().remove(listEndereco.getSelectionModel().getSelectedItem());
//            listEndereco.getItems().remove(listEndereco.getSelectionModel().getSelectedItem());
//        }
//        if ((listHomePage.isFocused()) && (listHomePage.getSelectionModel().getSelectedIndex() >= 0)) {
//            getTtvEmailHomePageVO().remove(listHomePage.getSelectionModel().getSelectedItem());
//            listHomePage.getItems().remove(listHomePage.getSelectionModel().getSelectedItem());
//        }
//        if ((listEmail.isFocused()) && (listEmail.getSelectionModel().getSelectedIndex() >= 0)) {
//            getTtvEmailHomePageVO().remove(listEmail.getSelectionModel().getSelectedItem());
//            listEmail.getItems().remove(listEmail.getSelectionModel().getSelectedItem());
//        }
//        if ((listTelefone.isFocused()) && (listTelefone.getSelectionModel().getSelectedIndex() >= 0)) {
//            getTtvTelefoneVO().remove(listTelefone.getSelectionModel().getSelectedItem());
//            listTelefone.getItems().remove(listTelefone.getSelectionModel().getSelectedItem());
//        }
//        if ((listContatoNome.isFocused()) && (listContatoNome.getSelectionModel().getSelectedIndex() >= 0)) {
//            getTtvEmpresaVO().getContatoVOList().remove(listContatoNome.getSelectionModel().getSelectedItem());
//            listContatoNome.getItems().remove(listContatoNome.getSelectionModel().getSelectedItem());
//            listContatoHomePage.getItems().clear();
//            listContatoEmail.getItems().clear();
//            listContatoTelefone.getItems().clear();
//        }
//        if ((listContatoHomePage.isFocused()) && (listContatoHomePage.getSelectionModel().getSelectedIndex() >= 0)) {
//            getTtvContatoEmailHomePageVO().remove(listContatoHomePage.getSelectionModel().getSelectedItem());
//            listContatoHomePage.getItems().remove(listContatoHomePage.getSelectionModel().getSelectedItem());
//        }
//        if ((listContatoEmail.isFocused()) && (listContatoEmail.getSelectionModel().getSelectedIndex() >= 0)) {
//            getTtvContatoEmailHomePageVO().remove(listContatoEmail.getSelectionModel().getSelectedItem());
//            listContatoEmail.getItems().remove(listContatoEmail.getSelectionModel().getSelectedItem());
//        }
//        if ((listContatoTelefone.isFocused()) && (listContatoTelefone.getSelectionModel().getSelectedIndex() >= 0)) {
//            getTtvContatoTelefoneVO().remove(listContatoTelefone.getSelectionModel().getSelectedItem());
//            listContatoTelefone.getItems().remove(listContatoTelefone.getSelectionModel().getSelectedItem());
//        }
//    }
//
//    void keyShiftF6() {
//        if ((listHomePage.isFocused()) && (listHomePage.getSelectionModel().getSelectedIndex() >= 0)) {
//            TabEmailHomePageVO homePage = listHomePage.getSelectionModel().getSelectedItem();
//            int index = getTtvEmailHomePageVO().indexOf(homePage);
//            homePage = editEmailHomePage(true, homePage);
//            getTtvEmailHomePageVO().set(index, homePage);
//            listHomePage.getItems().set(listHomePage.getSelectionModel().getSelectedIndex(), homePage);
//            listHomePage.getSelectionModel().selectLast();
//        }
//        if ((listEmail.isFocused()) && (listEmail.getSelectionModel().getSelectedIndex() >= 0)) {
//            TabEmailHomePageVO email;
//            email = listEmail.getSelectionModel().getSelectedItem();
//            int index = getTtvEmailHomePageVO().indexOf(email);
//            email = editEmailHomePage(true, email);
//            getTtvEmailHomePageVO().set(index, email);
//            listEmail.getItems().set(listEmail.getSelectionModel().getSelectedIndex(), email);
//            listEmail.getSelectionModel().selectLast();
//        }
//        if ((listTelefone.isFocused()) && (listTelefone.getSelectionModel().getSelectedIndex() >= 0)) {
//            TabTelefoneVO telefone = listTelefone.getSelectionModel().getSelectedItem();
//            int index = listTelefone.getSelectionModel().getSelectedIndex();
//            telefone = editTelefone(true, telefone);
//            getTtvTelefoneVO().set(index, telefone);
//            listTelefone.getItems().set(index, telefone);
//            listTelefone.getSelectionModel().selectLast();
//        }
//        if ((listContatoNome.isFocused()) && (listContatoNome.getSelectionModel().getSelectedIndex() >= 0)) {
//            TabContatoVO contato = listContatoNome.getSelectionModel().getSelectedItem();
//            int index = getTtvEmpresaVO().getContatoVOList().indexOf(contato);
//            contato = editContato(contato);
//            getTtvEmpresaVO().getContatoVOList().set(index, contato);
//            listContatoNome.getItems().setAll(getTtvEmpresaVO().getContatoVOList());
//            listContatoNome.getSelectionModel().selectLast();
//        }
//        if ((listContatoHomePage.isFocused()) && (listContatoHomePage.getSelectionModel().getSelectedIndex() >= 0)) {
//            TabEmailHomePageVO homePage = listContatoHomePage.getSelectionModel().getSelectedItem();
//            int index = getTtvContatoEmailHomePageVO().indexOf(homePage);
//            homePage = editEmailHomePage(false, homePage);
//            getTtvEmailHomePageVO().set(index, homePage);
//            listContatoHomePage.getItems().set(listContatoHomePage.getSelectionModel().getSelectedIndex(), homePage);
//            listContatoHomePage.getSelectionModel().selectLast();
//        }
//        if ((listContatoEmail.isFocused()) && (listContatoEmail.getSelectionModel().getSelectedIndex() >= 0)) {
//            TabEmailHomePageVO email = listContatoEmail.getSelectionModel().getSelectedItem();
//            int index = getTtvContatoEmailHomePageVO().indexOf(email);
//            email = editEmailHomePage(false, email);
//            getTtvContatoEmailHomePageVO().set(index, email);
//            listContatoEmail.getItems().set(listContatoEmail.getSelectionModel().getSelectedIndex(), email);
//            listContatoEmail.getSelectionModel().selectLast();
//        }
//        if ((listContatoTelefone.isFocused()) && (listContatoTelefone.getSelectionModel().getSelectedIndex() >= 0)) {
//            TabTelefoneVO telefone = listContatoTelefone.getSelectionModel().getSelectedItem();
//            int index = getTtvContatoTelefoneVO().indexOf(telefone);
//            telefone = editTelefone(false, telefone);
//            if ((telefone = addTelefone(false)) == null) return;
//            getTtvContatoTelefoneVO().set(index, telefone);
//            listContatoTelefone.getItems().set(listContatoTelefone.getSelectionModel().getSelectedIndex(), telefone);
//            listContatoTelefone.getSelectionModel().selectLast();
//        }
//    }
//

    void keyDelete() {
        boolean isEmpresa = listHomePage.isFocused() || listEmail.isFocused() || listTelefone.isFocused();

        if ((listEndereco.isFocused()) && (listEndereco.getSelectionModel().getSelectedItem().getSisTipoEnderecoVO().getId() != 1))
            deletaEndereco(listEndereco.getSelectionModel().getSelectedItem());

        if (listHomePage.isFocused() && listHomePage.getSelectionModel().getSelectedIndex() >= 0)
            deletaEmailHomePage(isEmpresa, listHomePage.getSelectionModel().getSelectedItem());

        if (listEmail.isFocused() && listEmail.getSelectionModel().getSelectedIndex() >= 0)
            deletaEmailHomePage(isEmpresa, listEmail.getSelectionModel().getSelectedItem());

        if (listTelefone.isFocused() && listTelefone.getSelectionModel().getSelectedIndex() >= 0)
            deletaTelefone(isEmpresa, listTelefone.getSelectionModel().getSelectedItem());

        if (listContatoNome.isFocused() && listContatoNome.getSelectionModel().getSelectedIndex() >= 0)
            deletaContato(listContatoNome.getSelectionModel().getSelectedItem());

        if ((listContatoHomePage.isFocused()) && (listContatoHomePage.getSelectionModel().getSelectedIndex() >= 0))
            deletaEmailHomePage(isEmpresa, listContatoHomePage.getSelectionModel().getSelectedItem());

        if ((listContatoEmail.isFocused()) && (listContatoEmail.getSelectionModel().getSelectedIndex() >= 0))
            deletaEmailHomePage(isEmpresa, listContatoEmail.getSelectionModel().getSelectedItem());

        if ((listContatoTelefone.isFocused() && (listContatoTelefone.getSelectionModel().getSelectedIndex() >= 0)))
            deletaTelefone(isEmpresa, listContatoTelefone.getSelectionModel().getSelectedItem());
    }

    void keyInsert() {
        if (listEndereco.isFocused()) {
            TabEnderecoVO enderecoVO = null;
            guardarEndereco(listEndereco.getSelectionModel().getSelectedIndex());
            if ((enderecoVO = addEndereco()) == null) return;
            getTabEnderecoVOList().add(enderecoVO);
            atualizaListaEndereco();
            listEndereco.getSelectionModel().selectLast();
            txtEndCEP.requestFocus();
        }
        if (listHomePage.isFocused() || listEmail.isFocused() || listContatoHomePage.isFocused() || listContatoEmail.isFocused()) {
            boolean isEmail = (listEmail.isFocused() || listContatoEmail.isFocused());
            boolean isEmpresa = (listHomePage.isFocused() || listEmail.isFocused());
            TabEmailHomePageVO emailHomePageVO = null;
            if (!isEmpresa) {
                if (!(listContatoNome.getSelectionModel().getSelectedIndex() >= 0)) {
                    new AlertMensagem("Contato inválido!",
                            USUARIO_LOGADO_APELIDO + ", precisa escolher para qual contato vai ser adicionado o email/homepage",
                            "ic_dados_invalidos_white_24dp.png").getRetornoAlert_OK();
                    listContatoNome.requestFocus();
                    return;
                }
            }
            if ((emailHomePageVO = addEditEmailHomePage(isEmpresa, isEmail, emailHomePageVO)) == null) return;
            if (isEmpresa) {
                getTabEmailHomePageVOList().add(emailHomePageVO);
                atualizaListaEmailHomePage();
            } else {
                getTabContatoEmailHomePageVOList().add(emailHomePageVO);
                atualizaListaContatoEmailHomePage();
            }
        }
        if (listTelefone.isFocused() || listContatoTelefone.isFocused()) {
            boolean isEmpresa = listTelefone.isFocused();
            TabTelefoneVO telefoneVO = null;
            if (!isEmpresa)
                if (!(listContatoNome.getSelectionModel().getSelectedIndex() >= 0)) {
                    new AlertMensagem("Contato inválido!",
                            USUARIO_LOGADO_APELIDO + ", precisa escolher para qual contato vai ser adicionado o telefone",
                            "ic_dados_invalidos_white_24dp.png").getRetornoAlert_OK();
                    listContatoNome.requestFocus();
                    return;
                }
            if ((telefoneVO = addEditTelefone(isEmpresa, telefoneVO)) == null) return;
            if (isEmpresa) {
                getTabTelefoneVOList().add(telefoneVO);
                atualizaListaTelefone();
            } else {
                getTabContatoVO().getTabTelefoneVOList().add(telefoneVO);
                atualizaListaContatoTelefone();
            }
        }
        if (listContatoNome.isFocused()) {
            TabContatoVO contatoVO = null;
            if ((contatoVO = addEditContato(contatoVO)) == null) return;
            getTabContatoVOList().add(contatoVO);
            atualizaListaContato();
            listContatoNome.getSelectionModel().selectLast();
        }
    }

    void keyShiftF6() {
        if (listHomePage.isFocused() || listEmail.isFocused() || listContatoHomePage.isFocused() || listContatoEmail.isFocused()) {
            TabEmailHomePageVO emailHomePageVO = new TabEmailHomePageVO();
            TabEmailHomePageVO emailEdit = null;
            boolean isEmail = (listEmail.isFocused() || listContatoEmail.isFocused());
            boolean isEmpresa = (listHomePage.isFocused() || listEmail.isFocused());
            if (isEmpresa) {
                if (isEmail) {
                    if (listEmail.getSelectionModel().getSelectedIndex() >= 0) {
                        emailEdit = listEmail.getSelectionModel().getSelectedItem();
                    }
                } else {
                    if (listHomePage.getSelectionModel().getSelectedIndex() >= 0) {
                        emailEdit = listHomePage.getSelectionModel().getSelectedItem();
                    }
                }
            } else {
                if (!isEmail) {
                    if (listContatoHomePage.getSelectionModel().getSelectedIndex() >= 0)
                        emailEdit = listContatoHomePage.getSelectionModel().getSelectedItem();
                } else {
                    if (listContatoEmail.getSelectionModel().getSelectedIndex() >= 0)
                        emailEdit = listContatoEmail.getSelectionModel().getSelectedItem();
                }
            }
            if ((emailEdit == null) || (emailHomePageVO = addEditEmailHomePage(isEmpresa, isEmail, emailEdit)) == null)
                return;
            if (isEmpresa) {
                getTabEmailHomePageVOList().set(getTabEmailHomePageVOList().indexOf(emailEdit), emailHomePageVO);
                atualizaListaEmailHomePage();
            } else {
                getTabContatoEmailHomePageVOList().set(getTabContatoEmailHomePageVOList().indexOf(emailEdit), emailHomePageVO);
                atualizaListaContatoEmailHomePage();
            }
        }

        if (listTelefone.isFocused() || listContatoTelefone.isFocused()) {
            TabTelefoneVO telefoneVO = new TabTelefoneVO();
            TabTelefoneVO telefoneEdit = null;
            boolean isEmpresa = (listTelefone.isFocused());
            if (isEmpresa) {
                if (listTelefone.getSelectionModel().getSelectedIndex() >= 0)
                    telefoneEdit = listTelefone.getSelectionModel().getSelectedItem();
            } else {
                if (listContatoTelefone.getSelectionModel().getSelectedIndex() >= 0)
                    telefoneEdit = listContatoTelefone.getSelectionModel().getSelectedItem();
            }
            if ((telefoneEdit == null) || (telefoneVO = addEditTelefone(isEmpresa, telefoneEdit)) == null) return;
            if (isEmpresa) {
                getTabTelefoneVOList().set(getTabTelefoneVOList().indexOf(telefoneEdit), telefoneVO);
                atualizaListaTelefone();
            } else {
                getTabContatoTelefoneVOList().set(getTabContatoTelefoneVOList().indexOf(telefoneEdit), telefoneVO);
                atualizaListaContatoTelefone();
            }
        }

        if (listContatoNome.isFocused()) {
            TabContatoVO contatoVO = new TabContatoVO();
            TabContatoVO contatoEdit = null;
            if (listContatoNome.getSelectionModel().getSelectedIndex() >= 0)
                contatoEdit = listContatoNome.getSelectionModel().getSelectedItem();
            if ((contatoEdit == null) || (contatoVO = addEditContato(contatoEdit)) == null) return;
            getTabContatoVOList().set(getTabTelefoneVOList().indexOf(contatoEdit), contatoVO);
            atualizaListaContato();
        }

    }

    List<SisTipoEnderecoVO> getTipoEnderecoDisponivel() {
        List<SisTipoEnderecoVO> endDisponivel = new ArrayList<>();
        for (SisTipoEnderecoVO tipEnd : sisTipoEnderecoVOList) {
            int exite = 0;
            for (int i = 0; i < getTabEnderecoVOList().size(); i++) {
                if (tipEnd.getDescricao().equals(getTabEnderecoVOList().get(i).getSisTipoEnderecoVO().getDescricao()))
                    exite = 1;
            }
            if (exite == 0) endDisponivel.add(tipEnd);
        }
        return endDisponivel;
    }

    TabEnderecoVO addEndereco() {
        TabEnderecoVO enderecoVO = new TabEnderecoVO(1);
        if (getTabEmpresaVO().getTabEnderecoVOList().get(0).getSisTipoEndereco_id() == 1) {
            List<SisTipoEnderecoVO> list = getTipoEnderecoDisponivel();
            if (list.size() <= 0) {
                new AlertMensagem("Endereço não disponivél",
                        USUARIO_LOGADO_APELIDO + ", a empresa " + txtRazao.getText()
                                + " não tem disponibilidade de endereço!\nAtualize algum endereço já existente!",
                        "ic_endereco_add_white_24dp.png").getRetornoAlert_OK();
                return null;
            }
            Object o = null;
            try {
                o = new AlertMensagem("Adicionar dados [endereço]",
                        USUARIO_LOGADO_APELIDO + ", selecione o tipo endereço",
                        "ic_endereco_add_white_24dp.png").getRetornoAlert_ComboBox(list).get();
            } catch (Exception ex) {
                if (!(ex instanceof NoSuchElementException)) ex.printStackTrace();
            }
            if (o == null) return null;
            enderecoVO.setSisTipoEnderecoVO(new SisTipoEnderecoDAO().getSisTipoEnderecoVO(((SisTipoEnderecoVO) o).getId()));
            enderecoVO.setSisTipoEndereco_id(enderecoVO.getSisTipoEnderecoVO().getId());
            enderecoVO.setSisMunicipioVO(new SisMunicipioDAO().getSisMunicipioVO(listEndereco.getItems().get(0).getSisMunicipio_id()));
            enderecoVO.setSisMunicipio_id(enderecoVO.getSisMunicipioVO().getId());
        }
        return enderecoVO;
    }

    TabEmailHomePageVO addEditEmailHomePage(boolean isEmpresa, boolean isEmail, TabEmailHomePageVO emailHomePageVO) {
        String tipoInclusao, donoEmailHomePage, strIco, emailHomePageAdicao;
        if (isEmpresa)
            donoEmailHomePage = "a empresa: " + txtRazao.getText();
        else
            donoEmailHomePage = "o contato: " + listContatoNome.getSelectionModel().getSelectedItem();
        if (isEmail) {
            tipoInclusao = "email";
            strIco = "ic_web_email_white_24dp.png";
        } else {
            tipoInclusao = "home page";
            strIco = "ic_web_home_page_white_24dp.png";
        }
        try {
            if (emailHomePageVO == null) {
                emailHomePageVO = new TabEmailHomePageVO();
                emailHomePageVO.setId(0);
                emailHomePageVO.setIsEmail(isEmail);
                emailHomePageAdicao = new AlertMensagem("Adicionar dados [" + tipoInclusao + "]",
                        USUARIO_LOGADO_APELIDO + ", qual " + tipoInclusao + " a ser adicionada para " + donoEmailHomePage + " ?",
                        strIco).getRetornoAlert_TextField(FormatarDado.gerarMascara("", 80, "?"), "").get();
            } else {
                emailHomePageAdicao = new AlertMensagem("Editar informações [" + tipoInclusao + "]",
                        USUARIO_LOGADO_APELIDO + ", qual alteração será feita no " + tipoInclusao + " d" + donoEmailHomePage + " ?",
                        strIco).getRetornoAlert_TextField(FormatarDado.gerarMascara("", 80, "?"),
                        emailHomePageVO.getDescricao()).get();
            }
            if (emailHomePageAdicao == null || emailHomePageAdicao.equals("") || emailHomePageAdicao.equals(emailHomePageVO.getDescricao()))
                return null;
        } catch (Exception ex) {
            if (!(ex instanceof NoSuchElementException))
                ex.printStackTrace();
            return null;
        }
        emailHomePageVO.setDescricao(emailHomePageAdicao);
        if ((isEmail) && (!emailHomePageAdicao.contains("@"))) {
            new AlertMensagem("Dados inválidos", USUARIO_LOGADO_APELIDO
                    + ", o email informado é inválido!", "ic_msg_alerta_triangulo_white_24dp.png").getRetornoAlert_OK();
            addEditEmailHomePage(isEmpresa, isEmail, emailHomePageVO);
            return null;
        }
        if ((!isEmail) && (emailHomePageAdicao.contains("@"))) {
            new AlertMensagem("Dados inválidos", USUARIO_LOGADO_APELIDO
                    + ", a home page informada é inválida!", "ic_msg_alerta_triangulo_white_24dp.png").getRetornoAlert_OK();
            addEditEmailHomePage(isEmpresa, isEmail, emailHomePageVO);
            return null;
        }

        return emailHomePageVO;
    }

    TabTelefoneVO addEditTelefone(boolean isEmpresa, TabTelefoneVO telefoneVO) {
        String donoTelefone, strIco;
        if (isEmpresa)
            donoTelefone = "a empresa: " + txtRazao.getText();
        else
            donoTelefone = "o contato: " + listContatoNome.getSelectionModel().getSelectedItem();

        strIco = "ic_telefone_white_24dp.png";

        Pair<String, Object> pairTelefone = null;
        AlertMensagem alertMensagem = new AlertMensagem();
        alertMensagem.setPromptTextField("telefone");
        alertMensagem.setPromptCombo("Operadora");
        alertMensagem.setStrIco(strIco);
        try {
            if (telefoneVO == null) {
                alertMensagem.setCabecalho("Adicionar dados [telefone]");
                alertMensagem.setPromptText(USUARIO_LOGADO_APELIDO + ", qual telefone a ser adicionado para " + donoTelefone + " ?");
                telefoneVO = new TabTelefoneVO();
                telefoneVO.setId(0);
                pairTelefone = alertMensagem.getRetornoAlert_TextFieldEComboBox(sisTelefoneOperadoraVOList,
                        FormatarDado.gerarMascara("telefone", 9, "#"), "").get();
            } else {
                alertMensagem.setCabecalho("Editar informações [telefone]");
                alertMensagem.setPromptText(USUARIO_LOGADO_APELIDO + ", qual alteração será feita no telefone d" + donoTelefone + " ?");
                pairTelefone = alertMensagem.getRetornoAlert_TextFieldEComboBox(sisTelefoneOperadoraVOList,
                        FormatarDado.gerarMascara("telefone", 9, "#"), telefoneVO.getDescricao()).get();
            }
            if (pairTelefone == null || pairTelefone.getKey().equals("") ||
                    ((pairTelefone.getKey().equals(telefoneVO.getDescricao())) && (((SisTelefoneOperadoraVO) pairTelefone.getValue()).getId() == telefoneVO.getSisTelefoneOperadora_id())))
                return null;
        } catch (Exception ex) {
            if (!(ex instanceof NoSuchElementException))
                ex.printStackTrace();
            return null;
        }
        telefoneVO.setDescricao(pairTelefone.getKey().toString().replaceAll("[\\-/.() \\[\\]]", ""));
        if (telefoneVO.getDescricao().length() < 8 || telefoneVO.getDescricao().length() > 9) {
            new AlertMensagem("Dados inválidos", USUARIO_LOGADO_APELIDO
                    + ", o número de telefone informado é inválido!", "ic_msg_alerta_triangulo_white_24dp.png").getRetornoAlert_OK();
            addEditTelefone(isEmpresa, telefoneVO);
            return null;
        }

        telefoneVO.setSisTelefoneOperadoraVO((SisTelefoneOperadoraVO) pairTelefone.getValue());
        telefoneVO.setSisTelefoneOperadora_id(telefoneVO.getSisTelefoneOperadoraVO().getId());

        return telefoneVO;
    }

    TabContatoVO addEditContato(TabContatoVO contatoVO) {
        String strIco;
        strIco = "ic_contato_add_white_24dp.png";

        Pair<String, Object> pairContato = null;
        AlertMensagem alertMensagem = new AlertMensagem();
        alertMensagem.setPromptTextField("Contato");
        alertMensagem.setPromptCombo("Cargo");
        alertMensagem.setStrIco(strIco);
        try {
            if (contatoVO == null) {
                alertMensagem.setCabecalho("Adicionar dados [contato]");
                alertMensagem.setPromptText(USUARIO_LOGADO_APELIDO + ", qual contato a ser adicionado para empresa: " + txtRazao.getText() + " ?");
                contatoVO = new TabContatoVO();
                contatoVO.setId(0);
                pairContato = alertMensagem.getRetornoAlert_TextFieldEComboBox(sisCargoVOList,
                        FormatarDado.gerarMascara("", 40, "@"), "").get();
            } else {
                alertMensagem.setCabecalho("Editar informações [contato]");
                alertMensagem.setPromptText(USUARIO_LOGADO_APELIDO + ", qual alteração será feita no contato da empresa: " + txtRazao.getText() + " ?");
                pairContato = alertMensagem.getRetornoAlert_TextFieldEComboBox(sisCargoVOList,
                        FormatarDado.gerarMascara("", 40, "@"), contatoVO.getDescricao()).get();
            }
            if (pairContato == null || pairContato.getKey().equals("") ||
                    ((pairContato.getKey().equals(contatoVO.getDescricao())) && (((SisCargoVO) pairContato.getValue()).getId() == contatoVO.getSisCargo_id())))
                return null;
        } catch (Exception ex) {
            if (!(ex instanceof NoSuchElementException))
                ex.printStackTrace();
            return null;
        }
        contatoVO.setDescricao(pairContato.getKey().toString());

        contatoVO.setSisCargoVO((SisCargoVO) pairContato.getValue());
        contatoVO.setSisCargo_id(contatoVO.getSisCargoVO().getId());

        return contatoVO;
    }

    void salvarEmpresa() {
        Connection conn = ConnectionFactory.getConnection(); // cria conexao com banco de dados
        try {
            conn.setAutoCommit(false); // deixa para fazer o commit no final se tudo der certo

            if (getTabEmpresaVO().getId() == 0)  // se id empresa==0 (empresa nova) ele inclui no banco
                getTabEmpresaVO().setId(new TabEmpresaDAO().insertTabEmpresaVO(conn, getTabEmpresaVO()));
            else  // se id empresa != 0 (empresa no cadastro) ele atualiza os dados dela no banco
                new TabEmpresaDAO().updateTabEmpresaVO(conn, getTabEmpresaVO());

            if (deletadosTabEnderecoVOList != null) // se tiver algum endereco deletado abre para percorrer lista de enderecos deletados
                for (int i = 0; i < deletadosTabEnderecoVOList.size(); i++)  // percorre lista de enderecos deletados
                    new TabEnderecoDAO().deleteTabEnderecoVO(conn, deletadosTabEnderecoVOList.get(i)); // deleta endereco no banco de dados
            new RelEmpresaEnderecoDAO().dedeteRelEmpresaEnderecoVO(conn, getTabEmpresaVO().getId()); // apaga todos relacionamentos da empresa com enderecos
            for (int i = 0; i < getTabEnderecoVOList().size(); i++) { // percorre lista de enderecos da empresa
                if (getTabEnderecoVOList().get(i).getId() == 0)  // se id endereco==0 (endereco novo) ele inclui novo endereco no banco
                    getTabEnderecoVOList().get(i).setId(new TabEnderecoDAO().insertTabEnderecoVO(conn, getTabEnderecoVOList().get(i)));
                else  // se id endereco!=0 (endereco já no cadastro) ele atualiza dados do endereco
                    new TabEnderecoDAO().updateTabEnderecoVO(conn, getTabEnderecoVOList().get(i));
                // gera novo relacionamento entre empresa e endereco
                new RelEmpresaEnderecoDAO().insertrelEmpresaEnderecoVO(conn, getTabEmpresaVO().getId(), getTabEnderecoVOList().get(i).getId());
            }

            if (deletadosTabEmailHomePageVOList != null) // se tiver algum email ou home page deletado abre para percorrer lista de emails e homePage deletados
                for (int i = 0; i < deletadosTabEmailHomePageVOList.size(); i++)  // percorre lista de emails e homePage deletados
                    new TabEmailHomePageDAO().deleteTabEmailHomePageVO(conn, deletadosTabEmailHomePageVOList.get(i)); // deleta email e homePage no banco de dados
            new RelEmpresaEmailHomePageDAO().dedeteRelEmpresaEmailHomePageVO(conn, getTabEmpresaVO().getId()); // apaga todos relacionamentos da empresa com email e homePage
            for (int i = 0; i < getTabEmailHomePageVOList().size(); i++) { // percorre lista de email e homePage da empresa
                if (getTabEmailHomePageVOList().get(i).getId() == 0)  // se id emailHomePage==0 (emailHomePage novo) ele inclui novo emailHomePage no banco
                    getTabEmailHomePageVOList().get(i).setId(new TabEmailHomePageDAO().insertTabEmailHomePageVO(conn, getTabEmailHomePageVOList().get(i)));
                else  // se id emailHomePage!=0 (emailHomePage já no cadastro) ele atualiza dados do emailHomePage
                    new TabEmailHomePageDAO().updateTabEmailHomePageVO(conn, getTabEmailHomePageVOList().get(i));
                // gera novo relacionamento entre empresa e emailHomePage
                new RelEmpresaEmailHomePageDAO().insertRelEmpresaEmailHomePageVO(conn, getTabEmpresaVO().getId(), getTabEmailHomePageVOList().get(i).getId());
            }

            if (deletadosTabTelefoneVOList != null)
                for (int i = 0; i < deletadosTabTelefoneVOList.size(); i++)
                    new TabTelefoneDAO().deleteTabTelefoneVO(conn, deletadosTabTelefoneVOList.get(i));
            new RelEmpresaTelefoneDAO().deleteRelEmpresaTelefoneVO(conn, getTabEmpresaVO().getId());
            for (int i = 0; i < getTabTelefoneVOList().size(); i++) {
                if (getTabTelefoneVOList().get(i).getId() == 0)
                    getTabTelefoneVOList().get(i).setId(new TabTelefoneDAO().insertTabTelefoneVO(conn, getTabTelefoneVOList().get(i)));
                else
                    new TabTelefoneDAO().updateTabTelefoneVO(conn, getTabTelefoneVOList().get(i));
                new RelEmpresaTelefoneDAO().insertRelEmpresaTelefoneVO(conn, getTabEmpresaVO().getId(), getTabTelefoneVOList().get(i).getId());
            }

            if (deletadosTabContatoVOList != null)
                for (int i = 0; i < deletadosTabContatoVOList.size(); i++) {
                    setTabContatoVO(deletadosTabContatoVOList.get(i));
                    new TabContatoDAO().deleteTabContatoVO(conn, getTabContatoVO());
                    if (getTabContatoEmailHomePageVOList() != null)
                        for (int j = 0; j < getTabContatoEmailHomePageVOList().size(); j++)
                            if (getTabContatoEmailHomePageVOList().get(j).getId() > 0)
                                new TabEmailHomePageDAO().deleteTabEmailHomePageVO(conn, getTabContatoEmailHomePageVOList().get(j));
                    new RelContatoEmailHomePageDAO().deleteRelContatoEmailHomePageVO(conn, deletadosTabContatoVOList.get(i).getId());
                    if (getTabContatoTelefoneVOList() != null)
                        for (int j = 0; j < getTabContatoTelefoneVOList().size(); j++)
                            if (getTabContatoTelefoneVOList().get(j).getId() > 0)
                                new TabTelefoneDAO().deleteTabTelefoneVO(conn, getTabContatoTelefoneVOList().get(j));
                    new RelContatoTelefoneDAO().deleteRelContatoTelefoneVO(conn, deletadosTabContatoVOList.get(i).getId());
                }
            new RelEmpresaContatoDAO().deleteRelEmpresaContatoVO(conn, getTabEmpresaVO().getId());
            if (deletadosTabContatoEmailHomePageVOList != null)
                for (int i = 0; i < deletadosTabContatoEmailHomePageVOList.size(); i++)
                    new TabEmailHomePageDAO().deleteTabEmailHomePageVO(conn, deletadosTabContatoEmailHomePageVOList.get(i));
            if (deletadosTabContatoTelefoneVOList != null)
                for (int i = 0; i < deletadosTabContatoTelefoneVOList.size(); i++)
                    new TabTelefoneDAO().deleteTabTelefoneVO(conn, deletadosTabContatoTelefoneVOList.get(i));
            for (int i = 0; i < getTabContatoVOList().size(); i++) {
                setTabContatoVO(getTabContatoVOList().get(i));
                if (getTabContatoVO().getId() == 0)
                    getTabContatoVO().setId(new TabContatoDAO().insertTabContatoVO(conn, getTabContatoVO()));
                else
                    new TabContatoDAO().updateTabContatoVO(conn, getTabContatoVO());
                new RelEmpresaContatoDAO().insertRelEmpresaContatoVO(conn, getTabEmpresaVO().getId(), getTabContatoVO().getId());

                new RelContatoEmailHomePageDAO().deleteRelContatoEmailHomePageVO(conn, getTabContatoVO().getId());
                for (int j = 0; j < getTabContatoEmailHomePageVOList().size(); j++) {
                    if (getTabContatoEmailHomePageVOList().get(j).getId() == 0)
                        getTabContatoEmailHomePageVOList().get(j).setId(new TabEmailHomePageDAO().insertTabEmailHomePageVO(conn, getTabContatoEmailHomePageVOList().get(j)));
                    else
                        new TabEmailHomePageDAO().updateTabEmailHomePageVO(conn, getTabContatoEmailHomePageVOList().get(j));
                    new RelContatoEmailHomePageDAO().insertRelContatoEmailHomePageVO(conn, getTabContatoVO().getId(), getTabContatoEmailHomePageVOList().get(j).getId());
                }
                new RelContatoTelefoneDAO().deleteRelContatoTelefoneVO(conn, getTabContatoVO().getId());
                for (int j = 0; j < getTabContatoTelefoneVOList().size(); j++) {
                    if (getTabContatoTelefoneVOList().get(j).getId() == 0)
                        getTabContatoTelefoneVOList().get(j).setId(new TabTelefoneDAO().insertTabTelefoneVO(conn, getTabContatoTelefoneVOList().get(j)));
                    else
                        new TabTelefoneDAO().updateTabTelefoneVO(conn, getTabContatoTelefoneVOList().get(j));
                    new RelContatoTelefoneDAO().insertRelContatoTelefoneVO(conn, getTabContatoVO().getId(), getTabContatoTelefoneVOList().get(j).getId());
                }
            }


            conn.commit();
        } catch (SQLException ex) {
            ex.printStackTrace();
            try {
                System.out.println("tentando fazer o rollback!!!");
                conn.rollback();
                System.out.println("deu algum erro e foi feito o rollback!!!");
            } catch (SQLException ex1) {
                ex1.printStackTrace();
            }
        }
    }


//    public void salvarEmpresa() {
//        Connection conn = ConnectionFactory.getConnection();
//        try {
//            conn.setAutoCommit(false);
////            if (getTtvEmpresaVO().getContatoVOList() != null) {
////                for (TabContatoVO contatoVO : getTtvEmpresaVO().getContatoVOList()) {
////                    setTtvContatoVO(contatoVO);
////                    String contTel_id = "";
////                    String contEmailHome_id = "";
////                    if (getTtvContatoTelefoneVO().size() > 0) {
////                        for (TabTelefoneVO contTel : getTtvContatoTelefoneVO()) {
////                            int id = 0;
////                            if (contTel.getId() == 0) {
////                                id = new TabTelefoneDAO().insertTabTelefoneVO(conn, contTel);
////                            } else {
////                                id = contTel.getId();
////                                new TabTelefoneDAO().updateTabTelefoneVO(conn, contTel);
////                            }
////                            if ((!contTel_id.equals("")) & id > 0)
////                                contTel_id += ";";
////                            contTel_id += id;
////                        }
////                        contatoVO.setTelefone_ids(contTel_id);
////                    }
////                    if (getTtvContatoEmailHomePageVO().size() > 0) {
////                        for (TabEmailHomePageVO contEmailHome : getTtvContatoEmailHomePageVO()) {
////                            int id = 0;
////                            if (contEmailHome.getId() == 0) {
////                                id = new TabEmailHomePageDAO().insertTabEmailHomaPageVO(conn, contEmailHome);
////                            } else {
////                                id = contEmailHome.getId();
////                                new TabEmailHomePageDAO().updateTabEmailHomaPageVO(conn, contEmailHome);
////                            }
////                            if ((!contEmailHome_id.equals("")) & id > 0)
////                                contEmailHome_id += ";";
////                            contEmailHome_id += id;
////                        }
////                        contatoVO.setEmailHomePage_ids(contEmailHome_id);
////                    }
////                    int id = 0;
////                    if (contatoVO.getId() == 0) {
////                        id = new TabContatoDAO().insertTabContatoVO(conn, contatoVO);
////                    } else {
////                        id = contatoVO.getId();
////                        new TabContatoDAO().updateTabContatoVO(conn, contatoVO);
////                    }
//////                    if ((!cont_id.equals("")) & id > 0)
//////                        cont_id += ";";
//////                    cont_id += id;
////                }
////            }
////            String tel_id = "";
////            String emailHome_id = "";
////            String end_id = "";
////            if (getTtvTelefoneVO().size() > 0) {
////                for (TabTelefoneVO tel : getTtvTelefoneVO()) {
////                    int id = 0;
////                    if (tel.getId() == 0) {
////                        id = new TabTelefoneDAO().insertTabTelefoneVO(conn, tel);
////                    } else {
////                        id = tel.getId();
////                        new TabTelefoneDAO().updateTabTelefoneVO(conn, tel);
////                    }
////                    if ((!tel_id.equals("")) & id > 0)
////                        tel_id += ";";
////                    tel_id += id;
////                }
////            }
////            if (getTtvEmailHomePageVO().size() > 0) {
////                for (TabEmailHomePageVO emailHome : getTtvEmailHomePageVO()) {
////                    int id = 0;
////                    if (emailHome.getId() == 0) {
////                        id = new TabEmailHomePageDAO().insertTabEmailHomaPageVO(conn, emailHome);
////                    } else {
////                        id = emailHome.getId();
////                        new TabEmailHomePageDAO().updateTabEmailHomaPageVO(conn, emailHome);
////                    }
////                    if ((!emailHome_id.equals("")) & id > 0)
////                        emailHome_id += ";";
////                    emailHome_id += id;
////                }
////            }
//
//            setTtvEmpresaVO(guardarEmpresa());
//            int idEmpresa = 0;
//            if ((idEmpresa = getTtvEmpresaVO().getId()) == 0) {
//                idEmpresa = new TabEmpresaDAO().insertTabEmpresaVO(conn, getTtvEmpresaVO());
//            } else {
//                new TabEmpresaDAO().updateTabEmpresaVO(conn, getTtvEmpresaVO());
//                idEmpresa = getTtvEmpresaVO().getId();
//            }
//
//            for (TabEnderecoVO endereco : getTtvEnderecoVO()) {
//                int id = 0;
//                if (endereco.getId() == 0) {
//                    id = new TabEnderecoDAO().insertTabEnderecoVO(conn, endereco);
//                    new SisRelEmpresaEnderecoDAO().insertRelEmpresaEnderecoVO(conn, getTtvEmpresaVO().getId(), id);
//                } else {
//                    id = endereco.getId();
//                    new TabEnderecoDAO().updateTabEnderecoVO(conn, endereco);
//                }
//            }
//
////            if (getTtvDetalheReceitaFederalVO() != null)
////                for (TabEmpresaDetalheReceitaFederalVO detReceita : getTtvDetalheReceitaFederalVO())
////                    if (detReceita.getId() == 0) {
////                        detReceita.setEmpresa_id(idEmpresa);
////                        new TabEmpresaDetalheReceitaFederalDAO().insertTabEmpresa_DetalheReceitaFederalVO(conn, detReceita);
////                    } else {
////                        detReceita.setEmpresa_id(idEmpresa);
////                        new TabEmpresaDetalheReceitaFederalDAO().updateTabEmpresa_DetalheReceitaFederalVO(conn, detReceita);
////                    }
//            conn.commit();
//        } catch (Exception ex) {
//            ex.printStackTrace();
//            try {
//                ex.printStackTrace();
//                conn.rollback();
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        } finally {
//            ConnectionFactory.closeConnection(conn);
//        }
////        Connection conn1 = ConnectionFactory.getConnection();
////        try {
////            conn1.setAutoCommit(false);
////            for (TabEnderecoVO endereco : getTtvEnderecoVO()) {
////                int empresa_id = getTtvEmpresaVO().getId();
////                int endereco_id = endereco.getId();
////                if ((new SisRelEmpresaEnderecoDAO().getRelEmpresaEnderecoVO(empresa_id, endereco_id)) == null)
////                    new SisRelEmpresaEnderecoDAO().insertRelEmpresaEnderecoVO(conn1, empresa_id, endereco_id);
////            }
////            conn1.commit();
////        } catch (Exception ex) {
////            ex.printStackTrace();
////            try {
////                ex.printStackTrace();
////                conn1.rollback();
////            } catch (SQLException e) {
////                e.printStackTrace();
////            }
////        } finally {
////            ConnectionFactory.closeConnection(conn1);
////        }
//    }
//

}
