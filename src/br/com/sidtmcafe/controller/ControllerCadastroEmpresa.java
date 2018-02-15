package br.com.sidtmcafe.controller;

import br.com.sidtmcafe.componentes.AlertMensagem;
import br.com.sidtmcafe.componentes.Tarefa;
import br.com.sidtmcafe.componentes.Variaveis;
import br.com.sidtmcafe.service.ExecutaComandoTecladoMouse;
import br.com.sidtmcafe.service.FormatadorDeDados;
import br.com.sidtmcafe.service.PersonalizarCampos;
import br.com.sidtmcafe.service.ValidadorDeDados;
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
import java.util.ResourceBundle;

import static br.com.sidtmcafe.interfaces.Constants.DTFORMAT_DATA;

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
    public Label labelDataCadastro;
    public Label labelDataCadastroDiff;
    public Label labelDataAtualizacao;
    public Label labelDataAtualizacaoDiff;
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
    public JFXListView listHomePage;
    public JFXListView listEmail;
    public JFXListView listTelefone;
    public JFXListView listContatoNome;
    public JFXListView listContatoHomePage;
    public JFXListView listContatoEmail;
    public JFXListView listContatoTelefone;
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
        Thread thread = new Thread(() -> {
            carregarTodosMunicipios();
        });
        carregaListas();
        //thread.start();
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
        ControllerPrincipal.ctrlPrincipal.tabPaneViewPrincipal.getSelectionModel().selectedItemProperty().addListener((ov, o, n) -> {
            if (ControllerPrincipal.ctrlPrincipal.tabPaneViewPrincipal.getTabs().size() <= 0) return;
            if (n.getText().equals(ViewCadastroEmpresa.getTituloJanela())) {
                ControllerPrincipal.ctrlPrincipal.painelViewPrincipal.addEventFilter(KeyEvent.KEY_RELEASED, event -> {
                    switch (event.getCode()) {
                        case F1:
                            if (!getStatusBarFormulario().contains(event.getCode().toString())) break;
                            setStatusFormulario("Incluir");

                            break;
                        case F2:
                            if (!getStatusBarFormulario().contains(event.getCode().toString())) break;
                            setStatusFormulario("Pesquisa");

                            break;
                        case F3:
                            if (!getStatusBarFormulario().contains(event.getCode().toString())) break;
                            switch (getStatusFormulario().toLowerCase()) {
                                case "incluir":
                                    if (new AlertMensagem("Cancelar inclusão", USUARIO_LOGADO_APELIDO
                                            + ", deseja cancelar inclusão no cadastro de empresa?",
                                            "ic_cadastro_empresas_black_24dp.png").getRetornoAlert_YES_NO().get() == ButtonType.NO)
                                        return;
                                    PersonalizarCampos.clearField((AnchorPane) tpnDadosCadastrais.getContent());
                                    break;
                                case "editar":
                                    if (new AlertMensagem("Cancelar edição", USUARIO_LOGADO_APELIDO
                                            + ", deseja cancelar edição do cadastro de empresa?",
                                            "ic_cadastro_empresas_black_24dp.png").getRetornoAlert_YES_NO().get() == ButtonType.NO)
                                        return;
//                                    setTtvEmpresaVO(new TabEmpresaDAO().getEmpresaVO(getTtvEmpresaVO().getId()));
//                                    exibirDadosEmpresa();
                                    PersonalizarCampos.clearField((AnchorPane) tpnDadosCadastrais.getContent());
                                    break;
                            }
                            setStatusFormulario("Pesquisa");
                            break;
                        case F4:
                            if ((!getStatusBarFormulario().contains(event.getCode().toString())) || (getTtvEmpresaVO() == null))
                                break;
                            setStatusFormulario("Editar");
                            break;
                        case F5:
                            if (!getStatusBarFormulario().contains(event.getCode().toString())) break;
                            setStatusFormulario("Pesquisa");

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
                    }
                });
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

        cboEndUF.getSelectionModel().selectedItemProperty().addListener((ov, o, n) -> {
            if (n == null) return;
            carregarPesquisaMunicipios(n.getSigla());
            formatIE.setMascara("ie" + n.getSigla());
            txtIE.setText(FormatadorDeDados.getCampoFormatado(txtIE.getText(), "ie" + n.getSigla()));
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

        ttvEmpresa.getSelectionModel().selectedItemProperty().addListener((ov, o, n) -> {
            if (n == null) return;
            setTtvEmpresaVO(n.getValue());
            exibirDadosEmpresa();
        });

        listEndereco.getSelectionModel().selectedIndexProperty().addListener((ov, o, n) -> {
            if (!getStatusFormulario().toLowerCase().equals("pesquisa"))
                if ((o.intValue() >= 0) && (n.intValue() != o.intValue()) && (n.intValue() >= 0)) {
                    guardarEndereco(o.intValue());
                }
            if (n == null || n.intValue() < 0) return;
            exibirDadosEndereco();
        });

        listContatoNome.getSelectionModel().selectedIndexProperty().addListener((ov, o, n) -> {
            if (n == null || n.intValue() < 0) return;
            exiberDadosContato();
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

        txtEndCEP.addEventHandler(KeyEvent.KEY_RELEASED, event -> {
            if (event.getCode() == KeyCode.ENTER) {
                String valCep = txtEndCEP.getText().replaceAll("[\\-/. \\[\\]]", "");
                if (valCep.length() != 8) {
                    return;
                }
                listaTarefas = new ArrayList<>();
                listaTarefas.add(new Pair("pesquisa cep", "Pesquisando C.E.P.: [" + txtEndCEP.getText() + "]"));

                new Tarefa().tarefaWsCepPostmon(this, listaTarefas);
                exibirDadosEndereco();
                txtEndNumero.requestFocus();
            }
        });

        listEndereco.addEventHandler(KeyEvent.KEY_RELEASED, event -> {
            if (getStatusFormulario().toLowerCase().equals("pesquisa")) return;
            if (event.getCode() == KeyCode.HELP) {
                guardarEndereco(listEndereco.getSelectionModel().getSelectedIndex());
                addEndereco();
            }
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

    TabEmpresaVO ttvEmpresaVO;
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
    List<SisTipoEnderecoVO> tipoEnderecoVOList;
    List<SisTelefoneOperadoraVO> telefoneOperadoraVOList;

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

    int qtdRegistrosLocalizados = 0;
    String statusFormulario, statusBarFormulario;

    static String STATUSBARPESQUISA = "[F1-Novo]  [F3-Excluir]  [F4-Editar]  [F7-Pesquisar]  [F8-Filtro pesquisa]  [F12-Sair]  ";
    static String STATUSBAREDITAR = "[F3-Cancelar edição]  [F5-Atualizar]  ";
    static String STATUSBARINCLUIR = "[F2-Incluir]  [F3-Cancelar inclusão]  ";

    public TabEmpresaVO getTtvEmpresaVO() {
        return ttvEmpresaVO;
    }

    public void setTtvEmpresaVO(TabEmpresaVO ttvEmpresaVO) {
        this.ttvEmpresaVO = ttvEmpresaVO;
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
    }

    void carregaListas() {
        listaTarefas.add(new Pair("carregarSisTipoEndereco", "carregando lista tipo endereço"));
        listaTarefas.add(new Pair("carregarSisTelefoneOperadora", "carregando lista operadoras telefone"));
        listaTarefas.add(new Pair("carregarTodosMunicipios", "carregando listas de municipios"));
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
                if (param.getValue().getValue().getEnderecoVOList().get(0).logradouroProperty().get() != "")
                    return new SimpleStringProperty(FormatadorDeDados.getCampoFormatado(param.getValue().getValue().ieProperty().getValue(), "ie" + param.getValue().getValue().getEnderecoVOList().get(0).getUfVO().getSigla()));
                return new SimpleStringProperty(FormatadorDeDados.getCampoFormatado(param.getValue().getValue().ieProperty().getValue(), "ie"));
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

    public void carregarSisTipoEndereco() {
        tipoEnderecoVOList = new ArrayList<SisTipoEnderecoVO>(new SisTipoEnderecoDAO().getTipoEnderecoVOList());
    }

    public void carregarSisTelefoneOperadora() {
        telefoneOperadoraVOList = new ArrayList<SisTelefoneOperadoraVO>(new SisTelefoneOperadoraDAO().getTelefoneOperadoraVOList());
    }

    public void carregarTodosMunicipios() {
        municipioVOObservableList = FXCollections.observableArrayList(new SisMunicipioDAO().getMunicipioVOList());
    }

    void carregarEmailHomePage() {
        emailHomePageVOObservableList = FXCollections.observableArrayList(getTtvEmpresaVO().getEmailHomePageVOList());
        carregarHomePageEmpresa();
        carregarEmailEmpresa();
    }

    void carregarContatoEmailHomePage(List<TabEmailHomePageVO> contatoEmailHomePageVOList) {
        if (contatoEmailHomePageVOList == null)
            contatoEmailHomePageVOList = new ArrayList<>();
        contatoEmailHomePageVOObservableList = FXCollections.observableArrayList(contatoEmailHomePageVOList);
        carregarContatoHomePage();
        carregarContatoEmail();
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

    void preencherCboEndMunicipio() {
        cboEndMunicipio.getItems().clear();
        if (municipioVOFilteredList != null)
            cboEndMunicipio.getItems().addAll(municipioVOFilteredList);
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

    void carregarPesquisaMunicipios(String siglaUF) {
        municipioVOFilteredList = new FilteredList<SisMunicipioVO>(municipioVOObservableList, municipio -> true);
        municipioVOFilteredList.setPredicate(municipio -> municipio.getUfVO().getSigla().equals(siglaUF));
        preencherCboEndMunicipio();
    }

    void carregarEmailEmpresa() {
        emailVOFilteredList = new FilteredList<TabEmailHomePageVO>(emailHomePageVOObservableList, email -> true);
        emailVOFilteredList.setPredicate(email -> email.getIsEmail() == 1);
        preencherEmailEmpresa();
    }

    void carregarHomePageEmpresa() {
        homePageVOFilteredList = new FilteredList<TabEmailHomePageVO>(emailHomePageVOObservableList, homePage -> true);
        homePageVOFilteredList.setPredicate(homePage -> homePage.getIsEmail() == 0);
        preencherHomePageEmpresa();
    }

    void carregarContatoEmail() {
        contatoEmailVOFilteredList = new FilteredList<TabEmailHomePageVO>(contatoEmailHomePageVOObservableList, contEmail -> true);
        contatoEmailVOFilteredList.setPredicate(contEmail -> contEmail.getIsEmail() == 1);
        preencherContatoEmail();
    }

    void carregarContatoHomePage() {
        contatoHomePageVOFilteredList = new FilteredList<TabEmailHomePageVO>(contatoEmailHomePageVOObservableList, contHomePage -> true);
        contatoHomePageVOFilteredList.setPredicate(contHomePage -> contHomePage.getIsEmail() == 0);
        preencherContatoHomaPage();
    }

    void preencherListaEnderecoEmpresa() {
        listEndereco.getItems().clear();
        if (getTtvEmpresaVO().getEnderecoVOList() != null)
            listEndereco.getItems().setAll(getTtvEmpresaVO().getEnderecoVOList());
        listEndereco.getSelectionModel().select(0);
    }

    void preencherListaTelefoneEmpresa() {
        listTelefone.getItems().clear();
        if (getTtvEmpresaVO().getTelefoneVOList() != null)
            listTelefone.getItems().setAll(getTtvEmpresaVO().getTelefoneVOList());
    }

    void preencherListaTelefoneContato(List<TabTelefoneVO> contatoTelefoneVOList) {
        listContatoTelefone.getItems().clear();
        if (contatoTelefoneVOList == null)
            contatoTelefoneVOList = new ArrayList<>();
        listContatoTelefone.getItems().setAll(contatoTelefoneVOList);
    }

    void preencherEmailEmpresa() {
        listEmail.getItems().clear();
        if (emailVOFilteredList == null)
            carregarEmailEmpresa();
        listEmail.getItems().setAll(emailVOFilteredList);
    }

    void preencherHomePageEmpresa() {
        listHomePage.getItems().clear();
        if (homePageVOFilteredList == null)
            carregarHomePageEmpresa();
        listHomePage.getItems().setAll(homePageVOFilteredList);
    }

    void preencherContatoEmail() {
        listContatoEmail.getItems().clear();
        if (contatoEmailVOFilteredList == null)
            carregarContatoEmail();
        listContatoEmail.getItems().setAll(contatoEmailVOFilteredList);
    }

    void preencherContatoHomaPage() {
        listContatoHomePage.getItems().clear();
        if (contatoHomePageVOFilteredList == null)
            carregarContatoHomePage();
        listContatoHomePage.getItems().setAll(contatoHomePageVOFilteredList);
    }

    void preencherListaContatoEmpresa() {
        listContatoNome.getItems().clear();
        if (getTtvEmpresaVO().getContatoVOList() != null) {
            listContatoNome.getItems().setAll(getTtvEmpresaVO().getContatoVOList());
            exiberDadosContato();
        }
    }

    void exibirDadosEmpresa() {
        if (getTtvEmpresaVO() == null) return;
        //setTtvEmpresaVO(new TabEmpresaVO());
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
        preencherListaEnderecoEmpresa();
        preencherListaTelefoneEmpresa();
        carregarEmailHomePage();
        preencherListaContatoEmpresa();
    }

    void exibirDadosEndereco() {
        int index = listEndereco.getSelectionModel().getSelectedIndex();
        if (index < 0)
            index = 0;
        TabEnderecoVO enderecoVO = listEndereco.getItems().get(index);
        if (enderecoVO == null) return;
        //enderecoVO = new TabEnderecoVO();
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
        int index = listContatoNome.getSelectionModel().getSelectedIndex();
        if (index < 0)
            index = 0;
        TabContatoVO contatoVO = getTtvEmpresaVO().getContatoVOList().get(index);
        if (contatoVO == null) return;
        carregarContatoEmailHomePage(contatoVO.getEmailHomePageVOList());
        preencherListaTelefoneContato(contatoVO.getTelefoneVOList());
    }

    void guardarEndereco(int index) {
        if (index < 0) return;
        TabEnderecoVO endVO = new TabEnderecoVO();
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
        listEndereco.getItems().set(index, endVO);
    }

    public void updateEndRetornoBuscaCep(WsCepPostmonVO wsCepPostmonVO) {
        if (wsCepPostmonVO == null) {
            new AlertMensagem("Resultado consulta web service", USUARIO_LOGADO_APELIDO + ", resultado busca cep: "
                    + txtEndCEP.getText() + ": " + "\nCEP informado é inválido ou não existe!",
                    "ic_web_service_err_white_24dp").getRetornoAlert_OK();
            txtEndCEP.requestFocus();
            txtEndCEP.selectAll();
        } else {
            int index = listEndereco.getSelectionModel().getSelectedIndex();
            TabEnderecoVO endRetornoWs = new TabEnderecoDAO().getEnderecoVO(wsCepPostmonVO, listEndereco.getSelectionModel().getSelectedItem());
            listEndereco.getItems().set(index, endRetornoWs);
            //ttvEmpresaVO.getEnderecoVOList().set(index, endRetornoWs);
        }
    }

    void addEndereco() {
        int tipEnd = 1;
        if (listEndereco.getItems().get(0).getTipoEndereco_id() == 1) {
            if (!validaEnd()) {
                new AlertMensagem("Endereço invalido",
                        USUARIO_LOGADO_APELIDO + ", para adicionar endereço 1˚ informe endereço principal valido",
                        "ic_endereco_add_white_24dp.png").getRetornoAlert_OK();
                return;
            } else {
                List<SisTipoEnderecoVO> list = getTipoEnderecoDisponivel();
                if (list.size() <= 0) {
                    new AlertMensagem("Endereço não disponivél",
                            USUARIO_LOGADO_APELIDO + ", a empresa " + txtRazao.getText()
                                    + " não tem disponibilidade de endereço!\nAtualize algum endereço já existente!",
                            "ic_endereco_add_white_24dp.png").getRetornoAlert_OK();
                    return;
                }
                Object o = new AlertMensagem("Adicionar dados [endereço]",
                        USUARIO_LOGADO_APELIDO + ", selecione o tipo endereço",
                        "ic_endereco_add_white_24dp.png").getRetornoAlert_ComboBox(list).get();
                if (o == null || o == "") return;
                tipEnd = ((SisTipoEnderecoVO) o).getId();
            }
            txtEndCEP.requestFocus();
        }
        TabEnderecoVO newEndereco = new TabEnderecoVO();
        newEndereco.setId(0);
        newEndereco.setTipoEndereco_id(tipEnd);
        newEndereco.setTipoEnderecoVO(new SisTipoEnderecoDAO().getTipoEnderecoVO(tipEnd));
        newEndereco.setUf_id(3);
        newEndereco.setUfVO(new SisUFDAO().getUfVO(newEndereco.getUf_id()));
        newEndereco.setMunicipio_id(112);
        newEndereco.setMunicipioVO(new SisMunicipioDAO().getMunicipioVO(newEndereco.getMunicipio_id()));
        listEndereco.getItems().add(newEndereco);
        listEndereco.getSelectionModel().selectLast();
    }

    boolean validaEnd() {
        if (txtEndCEP.getText().replaceAll("[\\-/. \\[\\]]", "").length() != 8 || txtEndCEP.getText().equals(""))
            return false;
        if (txtEndLogradouro.getText().equals("")) return false;
        if (txtEndNumero.getText().equals("")) return false;
        if (txtEndBairro.getText().equals("")) return false;

        return true;
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

    void exibirRetorno_WsCnpjReceitaWs(WsCnpjReceitaWsVO receitaWsVO) {
        cboClassificacaoJuridica.getSelectionModel().select(1);
        txtCNPJ.setText(receitaWsVO.getCnpj());
        txtRazao.setText(receitaWsVO.getNome());
        txtFantasia.setText(receitaWsVO.getFantasia());

        LocalDate dtTemp = LocalDate.parse(receitaWsVO.getAbertura(), DTFORMAT_DATA);
        LocalDateTime dtAbertura = LocalDateTime.of(dtTemp.getYear(), dtTemp.getMonth(), dtTemp.getDayOfMonth(), 0, 0, 0);
        //ttvEmpresaVO.setDataAbertura(Timestamp.valueOf(dtAbertura));
        lblDataAbertura.setText(dtAbertura.toString());
        //ttvEmpresaVO.setNaturezaJuridica(receitaWsVO.getNaturezaJuridica());
        lblNaturezaJuridica.setText(receitaWsVO.getNaturezaJuridica());

        txtEndCEP.setText(receitaWsVO.getCep().replaceAll("[\\-/. \\[\\]]", ""));
        txtEndLogradouro.setText(receitaWsVO.getLogradouro());
        txtEndNumero.setText(receitaWsVO.getNumero());
        txtEndComplemento.setText(receitaWsVO.getComplemento());
        txtEndBairro.setText(receitaWsVO.getBairro());
        if (receitaWsVO.getUf().equals("")) receitaWsVO.setUf("AM");
        cboEndUF.getSelectionModel().select(new SisUFDAO().getUfVO(receitaWsVO.getUf()));
        if (receitaWsVO.getMunicipio().equals("")) receitaWsVO.setMunicipio("MANAUS");
        cboEndMunicipio.getSelectionModel().select(new SisMunicipioDAO().getMunicipioVO(receitaWsVO.getMunicipio()));
        guardarEndereco(0);

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
                if (listTelefone.getItems().size() > i) {
                    tel.setId(((TabTelefoneVO) listTelefone.getItems().get(i)).getId());
                    listTelefone.getItems().set(i, tel);
                } else {
                    tel.setId(0);
                    listTelefone.getItems().add(tel);
                }
            }
        }

        if (receitaWsVO.getEmail() != "") {
            String emails = receitaWsVO.getEmail().replaceAll("@", "");
            int qtdEmails = receitaWsVO.getEmail().length() - emails.length();

            for (int i = 0; i < qtdEmails; i++) {
                TabEmailHomePageVO email = new TabEmailHomePageVO();
                email.setDescricao(receitaWsVO.getEmail());
                email.setIsEmail(1);
                if (listEmail.getItems().size() > i) {
                    email.setId(((TabEmailHomePageVO) listEmail.getItems().get(i)).getId());
                    listEmail.getItems().set(i, email);
                } else {
                    email.setId(0);
                    listEmail.getItems().add(email);
                }
            }
        }


    }

}
