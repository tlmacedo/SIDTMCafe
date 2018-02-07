package br.com.sidtmcafe.controller;

import br.com.sidtmcafe.componentes.AlertMensagem;
import br.com.sidtmcafe.componentes.Tarefa;
import br.com.sidtmcafe.componentes.Variaveis;
import br.com.sidtmcafe.service.FormatadorDeDados;
import br.com.sidtmcafe.service.PersonalizarCampos;
import br.com.sidtmcafe.service.ValidadorDeDados;
import br.com.sidtmcafe.interfaces.FormularioModelo;
import br.com.sidtmcafe.model.dao.*;
import br.com.sidtmcafe.model.vo.*;
import br.com.sidtmcafe.view.ViewCadastroEmpresa;
import com.jfoenix.controls.*;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.util.Pair;
import org.apache.velocity.runtime.directive.contrib.For;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

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

        PersonalizarCampos.mascara(painelViewCadastroEmpresa);
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
                            setStatusFormulario("Pesquisa");

                            break;
                        case F4:
                            if (!getStatusBarFormulario().contains(event.getCode().toString())) break;
                            setStatusFormulario("Editar");

                            break;
                        case F5:
                            if (!getStatusBarFormulario().contains(event.getCode().toString())) break;

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

                            break;
                    }
                });
            }
        });

        cboClassificacaoJuridica.getSelectionModel().selectedIndexProperty().addListener((ov, o, n) -> {
            if (n.intValue() == 1) {
                txtCNPJ.setPromptText("C.N.P.J.");
                //txtCNPJ.setText(FormatadorDeDados.getFormatado(txtCNPJ.getText(), "cnpj"));
                FormatadorDeDados.campoMask(txtCNPJ, FormatadorDeDados.geraMascara("cnpj", 0, "#"));
                txtRazao.setPromptText("Razão");
                txtFantasia.setPromptText("Fantasia");
            } else {
                txtCNPJ.setPromptText("C.P.F.");
                //txtCNPJ.setText(FormatadorDeDados.getFormatado(txtCNPJ.getText(), "cpf"));
                FormatadorDeDados.campoMask(txtCNPJ, FormatadorDeDados.geraMascara("cpf", 0, "#"));
                txtRazao.setPromptText("Nome");
                txtFantasia.setPromptText("Apelido");
            }
        });

        cboEndUF.getSelectionModel().selectedItemProperty().addListener((ov, o, n) -> {
            if (n != null)
                carregarPesquisaMunicipios(n.getSigla());
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
            if (n == null || n.intValue() < 0) return;
            exibirDadosEndereco(n.intValue());
        });

        txtCNPJ.addEventHandler(KeyEvent.KEY_RELEASED, event -> {
            if (event.getCode() == KeyCode.ENTER) {
                String valCnpj = txtCNPJ.getText().replaceAll("[\\-/.]", "");
                if (valCnpj.length() != 11 && valCnpj.length() != 14) {
                    System.out.println("cnpj número invalido");
                    return;
                }
                if (!ValidadorDeDados.isCnpjCpfValido(valCnpj)) {
                    switch (valCnpj.length()) {
                        case 11:
                            System.out.println("cpf informado é inválido!");
                            break;
                        case 14:
                            System.out.println("cnpj informado é inválido!!");
                            break;
                    }
                    return;
                }
                System.out.println("CNPJ OK");
            }
        });

        txtEndCEP.addEventHandler(KeyEvent.KEY_RELEASED, event -> {
            if (event.getCode() == KeyCode.ENTER) {
                String valCep = txtEndCEP.getText().replaceAll("[\\-/.]", "");
                if (valCep.length() != 8) {
                    System.out.println("cep número invalido");
                    return;
                }
                WsCepPostmonVO wsCepPostmonVO = new WsCepPostmonDAO().getCepPostmonVO(valCep);
                if (wsCepPostmonVO == null) {
                    new AlertMensagem("Resultado consulta web service", USUARIO_LOGADO_APELIDO + ", resultado busca cep: "
                            + txtEndCEP.getText() + ": " + "\nCEP informado é invalido ou não existe!",
                            "ic_web_service_err_white_24dp").getRetornoAlert_OK();
                    txtEndCEP.requestFocus();
                    txtEndCEP.selectAll();
                }

                listEndereco.getItems().set(listEndereco.getSelectionModel().getSelectedIndex(),
                        new TabEnderecoDAO().getEnderecoVO(wsCepPostmonVO, listEndereco.getSelectionModel().getSelectedItem()));
                exibirDadosEndereco(listEndereco.getSelectionModel().getSelectedIndex());
                txtEndNumero.requestFocus();
            }
        });

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        preencherObjetos();
        fatorarObjetos();
        escutarTeclas();
        setStatusFormulario("Pesquisa");
    }

    TabEmpresaVO ttvEmpresaVO;
    List<Pair> listaTarefas;

    ObservableList<TabEmpresaVO> empresaVOObservableList;
    FilteredList<TabEmpresaVO> empresaVOFilteredList;
    ObservableList<SisMunicipioVO> municipioVOObservableList;
    FilteredList<SisMunicipioVO> municipioVOFilteredList;
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
            this.statusBarFormulario = STATUSBARINCLUIR;
            PersonalizarCampos.desabilitaCampos((AnchorPane) tpnCadastroEmpresa.getContent(), true);
            PersonalizarCampos.desabilitaCampos((AnchorPane) tpnDadosCadastrais.getContent(), false);
            PersonalizarCampos.limpeza((AnchorPane) tpnDadosCadastrais.getContent());
            cboClassificacaoJuridica.requestFocus();
        } else if (statusFormulario.toLowerCase().contains("editar")) {
            this.statusBarFormulario = STATUSBAREDITAR;
            PersonalizarCampos.desabilitaCampos((AnchorPane) tpnCadastroEmpresa.getContent(), true);
            PersonalizarCampos.desabilitaCampos((AnchorPane) tpnDadosCadastrais.getContent(), false);
            txtCNPJ.requestFocus();
            txtCNPJ.selectAll();
        } else if (statusFormulario.toLowerCase().contains("pesquisa")) {
            this.statusBarFormulario = STATUSBARPESQUISA;
            PersonalizarCampos.desabilitaCampos((AnchorPane) tpnCadastroEmpresa.getContent(), false);
            PersonalizarCampos.desabilitaCampos((AnchorPane) tpnDadosCadastrais.getContent(), true);
            txtPesquisa.requestFocus();
            txtPesquisa.selectAll();
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
            colunaCnpj.setCellValueFactory(param -> new SimpleStringProperty(FormatadorDeDados.getFormatado(param.getValue().getValue().getCnpj(), "cnpj")));

            Label lblIe = new Label(("IE / RG"));
            lblIe.setPrefWidth(75);
            colunaIe = new JFXTreeTableColumn<TabEmpresaVO, String>();
            colunaIe.setGraphic(lblIe);
            colunaIe.setPrefWidth(75);
            colunaIe.setStyle("-fx-alignment: center-right;");
            colunaIe.setCellValueFactory(param -> param.getValue().getValue().ieProperty());

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
        System.out.println("Começou a carregar municipios");
        municipioVOObservableList = FXCollections.observableArrayList(new SisMunicipioDAO().getMunicipioVOList());
        System.out.println("Terminou de carregar municipios");
    }

    public void carregarListaEmpresa() {
        empresaVOObservableList = FXCollections.observableArrayList(new TabEmpresaDAO().getEmpresaVOList());
    }

    public void preencherCboEndUF() {
        cboEndUF.getItems().clear();
        cboEndUF.getItems().add(new SisUFVO());
        cboEndUF.getItems().addAll(new SisUFDAO().getUfVOList());
        cboEndUF.getSelectionModel().select(0);
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
        cboClassificacaoJuridica.getSelectionModel().select(1);
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

    void exibirDadosEmpresa() {
        if (ttvEmpresaVO == null)
            ttvEmpresaVO = new TabEmpresaVO();
        cboClassificacaoJuridica.getSelectionModel().select(ttvEmpresaVO.getIsPessoaJuridica());
        String tipFormat = "cnpj";
        if (cboClassificacaoJuridica.getSelectionModel().getSelectedIndex() == 0)
            tipFormat = "cpf";
        txtCNPJ.setText(FormatadorDeDados.getFormatado(ttvEmpresaVO.getCnpj(), tipFormat));
        txtIE.setText(ttvEmpresaVO.getIe());
        cboSituacaoSistema.getSelectionModel().select(ttvEmpresaVO.getSituacaoSistemaVO());
        txtRazao.setText(ttvEmpresaVO.getRazao());
        txtFantasia.setText(ttvEmpresaVO.getFantasia());
        chkIsCliente.setSelected(ttvEmpresaVO.getIsCliente() == 1);
        chkIsFornecedor.setSelected(ttvEmpresaVO.getIsFornecedor() == 1);
        chkIsTransportadora.setSelected(ttvEmpresaVO.getIsTransportadora() == 1);
        carregaListaEnderecoEmpresa(ttvEmpresaVO.getEnderecoVOList());
    }

    void carregaListaEnderecoEmpresa(List<TabEnderecoVO> listEmpresaEndereco) {
        if (listEmpresaEndereco == null)
            listEndereco.getItems().clear();
        listEndereco.getItems().setAll(listEmpresaEndereco);
        listEndereco.getSelectionModel().select(0);
    }

    void exibirDadosEndereco(int index) {
        TabEnderecoVO enderecoVO = ttvEmpresaVO.getEnderecoVOList().get(index);
        if (enderecoVO == null) return;
        //enderecoVO = new TabEnderecoVO();
        txtEndCEP.setText(FormatadorDeDados.getFormatado(enderecoVO.getCep(), "cep"));
        txtEndLogradouro.setText(enderecoVO.getLogradouro());
        txtEndNumero.setText(enderecoVO.getNumero());
        txtEndComplemento.setText(enderecoVO.getComplemento());
        txtEndBairro.setText(enderecoVO.getBairro());
        txtEndPontoReferencia.setText(enderecoVO.getPontoReferencia());
        cboEndUF.getSelectionModel().select(enderecoVO.getUfVO().getId());
        cboEndMunicipio.getSelectionModel().select(enderecoVO.getMunicipioVO());
    }


}
