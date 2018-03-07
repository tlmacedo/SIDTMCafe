package br.com.sidtmcafe.controller;

import br.com.sidtmcafe.componentes.AlertMensagem;
import br.com.sidtmcafe.componentes.Tarefa;
import br.com.sidtmcafe.componentes.Variavel;
import br.com.sidtmcafe.database.ConnectionFactory;
import br.com.sidtmcafe.interfaces.FormularioModelo;
import br.com.sidtmcafe.model.dao.*;
import br.com.sidtmcafe.model.vo.*;
import br.com.sidtmcafe.service.*;
import br.com.sidtmcafe.view.ViewCadastroProduto;
import com.jfoenix.controls.*;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
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

import static br.com.sidtmcafe.interfaces.Constants.*;

public class ControllerCadastroProduto extends Variavel implements Initializable, FormularioModelo {


    public AnchorPane painelViewCadastroProduto;
    public TitledPane tpnCadastroProduto;
    public JFXTextField txtPesquisaProduto;
    public JFXTreeTableView<TabProdutoVO> ttvProduto;
    public Label lblRegistrosLocalizados;
    public TitledPane tpnDadoCadastral;
    public JFXTextField txtCodigo;
    public JFXTextField txtDescricao;
    public JFXTextField txtPeso;
    public JFXComboBox<SisUnidadeComercialVO> cboUnidadeComercial;
    public JFXComboBox<SisSituacaoSistemaVO> cboSituacaoSistema;
    public JFXTextField txtPrecoFabrica;
    public JFXTextField txtMargem;
    public JFXTextField txtPrecoConsumidor;
    public JFXTextField txtLucroLiquido;
    public JFXTextField txtLucratividade;
    public JFXTextField txtVarejo;
    public JFXTextField txtComissao;
    public Label lblDataCadastro;
    public Label lblDataCadastroDiff;
    public Label lblDataAtualizacao;
    public Label lblDataAtualizacaoDiff;
    public JFXListView<TabProdutoEanVO> listCodigoBarras;
    public JFXTextField txtFiscalNcm;
    public JFXTextField txtFiscalGenero;
    public JFXTextField txtFiscalCest;
    public JFXComboBox<SisFiscalCstOrigemVO> cboFiscalOrigem;
    public JFXComboBox<SisFiscalCstIcmsVO> cboFiscalIcms;
    public JFXComboBox<SisFiscalCstPisCofinsVO> cboFiscalPis;
    public JFXComboBox<SisFiscalCstPisCofinsVO> cboFiscalCofins;

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

        new Tarefa().tarefaAbreCadastroProduto(this, listaTarefas);

        PersonalizarCampo.fieldMaxLen(painelViewCadastroProduto);
        PersonalizarCampo.maskFields(painelViewCadastroProduto);
    }

    @Override
    public void fatorarObjetos() {

    }

    @Override
    public void escutarTeclas() {
        String tituloTab = ViewCadastroProduto.getTituloJanela();

        ttvProduto.getSelectionModel().selectedItemProperty().addListener((ov, o, n) -> {
            if (n == null) return;
            setTtvProdutoVO(n.getValue());
            exibirDadosProduto();
        });


        ControllerPrincipal.ctrlPrincipal.tabPaneViewPrincipal.getSelectionModel().selectedItemProperty().addListener((ov, o, n) -> {
            if (!(ControllerPrincipal.ctrlPrincipal.getTabAtual().equals(tituloTab))) {
                return;
            }
            if ((n != null) & (n != o))
                setStatusBarFormulario(getStatusFormulario());
        });

        eventCadastroProduto = new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (!(ControllerPrincipal.ctrlPrincipal.getTabAtual().equals(tituloTab)))
                    return;
                switch (event.getCode()) {
                    case F1:
                        if (!getStatusBarFormulario().contains(event.getCode().toString())) break;
                        setStatusFormulario("Incluir");
                        setTtvProdutoVO(new TabProdutoVO());
                        break;
                    case F2:
                        if (!getStatusBarFormulario().contains(event.getCode().toString())) break;

                        salvarProduto();
                        setTtvProdutoVO(new TabProdutoDAO().getProdutoVO(getTtvProdutoVO().getId()));
                        produtoVOObservableList.set(indexObservableProduto, getTtvProdutoVO());

                        setStatusFormulario("Pesquisa");
                        carregarPesquisaProduto(txtPesquisaProduto.getText());
                        break;
                    case F3:
                        if (!getStatusBarFormulario().contains(event.getCode().toString())) break;
                        switch (getStatusFormulario().toLowerCase()) {
                            case "incluir":
                                if (new AlertMensagem("Cancelar inclusão", USUARIO_LOGADO_APELIDO
                                        + ", deseja cancelar inclusão no cadastro de produto?",
                                        "ic_cadastro_empresas_white_24dp.png").getRetornoAlert_YES_NO().get() == ButtonType.NO)
                                    return;
                                PersonalizarCampo.clearField((AnchorPane) tpnDadoCadastral.getContent());
                                break;
                            case "editar":
                                if (new AlertMensagem("Cancelar edição", USUARIO_LOGADO_APELIDO
                                        + ", deseja cancelar edição do cadastro de produto?",
                                        "ic_cadastro_empresas_white_24dp.png").getRetornoAlert_YES_NO().get() == ButtonType.NO)
                                    return;
                                setTtvProdutoVO(new TabProdutoDAO().getProdutoVO(getTtvProdutoVO().getId()));
                                produtoVOObservableList.set(indexObservableProduto, getTtvProdutoVO());
                                break;
                        }
                        setStatusFormulario("Pesquisa");
                        carregarPesquisaProduto(txtPesquisaProduto.getText());
                        exibirDadosProduto();
                        break;
                    case F4:
                        if ((!getStatusBarFormulario().contains(event.getCode().toString())) || (getTtvProdutoVO() == null))
                            break;
                        indexObservableProduto = produtoVOObservableList.indexOf(getTtvProdutoVO());
                        setStatusFormulario("Editar");
                        break;
                    case F5:
                        if (!getStatusBarFormulario().contains(event.getCode().toString())) break;

                        salvarProduto();
                        setTtvProdutoVO(new TabProdutoDAO().getProdutoVO(getTtvProdutoVO().getId()));
                        produtoVOObservableList.set(indexObservableProduto, getTtvProdutoVO());

                        setStatusFormulario("Pesquisa");
                        carregarPesquisaProduto(txtPesquisaProduto.getText());
                        exibirDadosProduto();
                        ttvProduto.requestFocus();
                        break;
                    case F6:
                        if (getStatusFormulario().toLowerCase().equals("pesquisa") || (!event.isShiftDown())) break;
                        keyShiftF6();
                        break;
                    case F7:
                        if (!getStatusBarFormulario().contains(event.getCode().toString())) break;
                        txtPesquisaProduto.requestFocus();
                        break;
                    case F12:
                        if (!getStatusBarFormulario().contains(event.getCode().toString())) break;
                        fecharTab(tituloTab);
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

        ControllerPrincipal.ctrlPrincipal.painelViewPrincipal.addEventHandler(KeyEvent.KEY_RELEASED, eventCadastroProduto);

        txtPesquisaProduto.textProperty().addListener((ov, o, n) -> {
            carregarPesquisaProduto(n);
        });

        txtPesquisaProduto.addEventHandler(KeyEvent.KEY_RELEASED, event -> {
            if (event.getCode() == KeyCode.ENTER) {
                ttvProduto.requestFocus();
                ttvProduto.getSelectionModel().select(0);
            }
        });

    }

    @SuppressWarnings("Duplicates")
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        preencherObjetos();
        fatorarObjetos();
        escutarTeclas();
        setStatusFormulario("Pesquisa");
        Platform.runLater(() -> {
            painelViewCadastroProduto.fireEvent(ExecutaComandoTecladoMouse.pressTecla(KeyCode.F7));
        });
    }

    EventHandler<KeyEvent> eventCadastroProduto;

    int indexObservableProduto = 0;
    TabProdutoVO ttvProdutoVO;
    List<TabProdutoEanVO> ttvProdutoEanVO;

    List<Pair> listaTarefas;

    ObservableList<TabProdutoVO> produtoVOObservableList;
    FilteredList<TabProdutoVO> produtoVOFilteredList;

    JFXTreeTableColumn<TabProdutoVO, Integer> colunaId;
    JFXTreeTableColumn<TabProdutoVO, String> colunaCodigo;
    JFXTreeTableColumn<TabProdutoVO, String> colunaDescricao;
    JFXTreeTableColumn<TabProdutoVO, String> colunaUndCom;
    JFXTreeTableColumn<TabProdutoVO, String> colunaPrecoFabrica;
    JFXTreeTableColumn<TabProdutoVO, String> colunaPrecoConsumidor;
    JFXTreeTableColumn<TabProdutoVO, Integer> colunaQtdEstoque;
    JFXTreeTableColumn<TabProdutoVO, String> colunaSituacaoSistema;
    JFXTreeTableColumn<TabProdutoVO, Integer> colunaVarejo;


    int qtdRegistrosLocalizados = 0;
    String statusFormulario, statusBarFormulario;

    static String STATUSBARPESQUISA = "[F1-Novo]  [F3-Excluir]  [F4-Editar]  [F7-Pesquisar]  [F8-Filtro pesquisa]  [F12-Sair]  ";
    static String STATUSBAREDITAR = "[F3-Cancelar edição]  [F5-Atualizar]  ";
    static String STATUSBARINCLUIR = "[F2-Incluir]  [F3-Cancelar inclusão]  ";

    public TabProdutoVO getTtvProdutoVO() {
        return ttvProdutoVO;
    }

    public void setTtvProdutoVO(TabProdutoVO ttvProdutoVO) {
        if (ttvProdutoVO == null)
            ttvProdutoVO = new TabProdutoVO();
        this.ttvProdutoVO = ttvProdutoVO;

        setTtvProdutoEanVO(getTtvProdutoVO().getProdutoEanVOList());
    }

    public List<TabProdutoEanVO> getTtvProdutoEanVO() {
        return ttvProdutoEanVO;
    }

    public void setTtvProdutoEanVO(List<TabProdutoEanVO> ttvProdutoEanVO) {
        if (ttvProdutoEanVO == null)
            ttvProdutoEanVO = new ArrayList<>();
        this.ttvProdutoEanVO = ttvProdutoEanVO;
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
            PersonalizarCampo.desabilitaCampos((AnchorPane) tpnCadastroProduto.getContent(), true);
            PersonalizarCampo.desabilitaCampos((AnchorPane) tpnDadoCadastral.getContent(), false);
            PersonalizarCampo.clearField((AnchorPane) tpnDadoCadastral.getContent());
            txtCodigo.requestFocus();
            txtCodigo.selectAll();
            this.statusBarFormulario = STATUSBARINCLUIR;
        } else if (statusFormulario.toLowerCase().contains("editar")) {
            PersonalizarCampo.desabilitaCampos((AnchorPane) tpnCadastroProduto.getContent(), true);
            PersonalizarCampo.desabilitaCampos((AnchorPane) tpnDadoCadastral.getContent(), false);
            txtCodigo.requestFocus();
            txtCodigo.selectAll();
            this.statusBarFormulario = STATUSBAREDITAR;
        } else if (statusFormulario.toLowerCase().contains("pesquisa")) {
            PersonalizarCampo.desabilitaCampos((AnchorPane) tpnCadastroProduto.getContent(), false);
            PersonalizarCampo.desabilitaCampos((AnchorPane) tpnDadoCadastral.getContent(), true);
            txtPesquisaProduto.requestFocus();
            this.statusBarFormulario = STATUSBARPESQUISA;
        }
        ControllerPrincipal.ctrlPrincipal.atualizarTeclasStatusBar(statusBarFormulario);
    }

    void atualizaLblRegistrosLocalizados() {
        lblRegistrosLocalizados.setText("[" + getStatusFormulario() + "]  " + String.valueOf(getQtdRegistrosLocalizados()) + " registro(s) localizado(s).");
    }

    void criarTabelas() {
        listaTarefas.add(new Pair("criarTabelaProduto", "criando tabela produto"));
    }

    void carregaListas() {
        listaTarefas.add(new Pair("carregarListaProduto", "carregando lista de produtos"));
    }

    void preencherCombos() {
        listaTarefas.add(new Pair("preencherCboUndCom", "preenchendo dados unidade comercial"));
        listaTarefas.add(new Pair("preencherCboSituacaoSistema", "preenchendo situaão no istema"));
        listaTarefas.add(new Pair("preencherCboFiscalOrigem", "preenchendo dados fiscais de Origem"));
        listaTarefas.add(new Pair("preencherCboFiscalIcms", "preenchendo dados fiscal ICMS"));
        listaTarefas.add(new Pair("preencherCboFiscalPis", "preenchendo dados fiscal PIS"));
        listaTarefas.add(new Pair("preencherCboFiscalCofins", "preenchendo dados fiscal COFINS"));
    }

    void preencherTabelas() {
        listaTarefas.add(new Pair("preencherTabelaProduto", "preenchendo tabela produto"));
    }

    public void criarTabelaProduto() {
        try {
            Label lblId = new Label("id");
            lblId.setPrefWidth(28);
            colunaId = new JFXTreeTableColumn<TabProdutoVO, Integer>();
            colunaId.setGraphic(lblId);
            colunaId.setPrefWidth(28);
            colunaId.setStyle("-fx-alignment: center-right;");
            colunaId.setCellValueFactory(param -> param.getValue().getValue().idProperty().asObject());

            Label lblCodigo = new Label("Código");
            lblCodigo.setPrefWidth(60);
            colunaCodigo = new JFXTreeTableColumn<TabProdutoVO, String>();
            colunaCodigo.setGraphic(lblCodigo);
            colunaCodigo.setPrefWidth(60);
            colunaCodigo.setStyle("-fx-alignment: center-right;");
            colunaCodigo.setCellValueFactory(param -> param.getValue().getValue().codigoProperty());

            Label lblDescricao = new Label("Descrição");
            lblDescricao.setPrefWidth(350);
            colunaDescricao = new JFXTreeTableColumn<TabProdutoVO, String>();
            colunaDescricao.setGraphic(lblDescricao);
            colunaDescricao.setPrefWidth(350);
            colunaDescricao.setCellValueFactory(param -> param.getValue().getValue().descricaoProperty());

            Label lblUndComercial = new Label("Und Com");
            lblUndComercial.setPrefWidth(70);
            colunaUndCom = new JFXTreeTableColumn<TabProdutoVO, String>();
            colunaUndCom.setGraphic(lblUndComercial);
            colunaUndCom.setPrefWidth(70);
            colunaUndCom.setCellValueFactory(param -> param.getValue().getValue().getUnidadeComercialVO().siglaProperty());

            Label lblVarejo = new Label("Varejo");
            lblVarejo.setPrefWidth(50);
            colunaVarejo = new JFXTreeTableColumn<TabProdutoVO, Integer>();
            colunaVarejo.setGraphic(lblVarejo);
            colunaVarejo.setPrefWidth(50);
            colunaVarejo.setStyle("-fx-alignment: center-right;");
            colunaVarejo.setCellValueFactory(param -> param.getValue().getValue().varejoProperty().asObject());

            Label lblPrecoFab = new Label("Preço Fab.");
            lblPrecoFab.setPrefWidth(90);
            colunaPrecoFabrica = new JFXTreeTableColumn<TabProdutoVO, String>();
            colunaPrecoFabrica.setGraphic(lblPrecoFab);
            colunaPrecoFabrica.setPrefWidth(90);
            colunaPrecoFabrica.setStyle("-fx-alignment: center-right;");
            colunaPrecoFabrica.setCellValueFactory(param -> {
                try {
                    return new SimpleStringProperty(DECIMAL_FORMAT.format(param.getValue().getValue().precoFabricaProperty().getValue()).replace(".", ","));
                } catch (Exception ex) {
                    return new SimpleStringProperty("0");
                }
            });

            Label lblPrecoCons = new Label("Preço Cons.");
            lblPrecoCons.setPrefWidth(90);
            colunaPrecoConsumidor = new JFXTreeTableColumn<TabProdutoVO, String>();
            colunaPrecoConsumidor.setGraphic(lblPrecoCons);
            colunaPrecoConsumidor.setPrefWidth(90);
            colunaPrecoConsumidor.setStyle("-fx-alignment: center-right;");
            colunaPrecoConsumidor.setCellValueFactory(param -> {
                try {
                    return new SimpleStringProperty(DECIMAL_FORMAT.format(param.getValue().getValue().precoConsumidorProperty().getValue()).replace(".", ","));
                } catch (Exception ex) {
                    return new SimpleStringProperty("0");
                }
            });

            Label lblSituacaoSistema = new Label("Situação");
            lblSituacaoSistema.setPrefWidth(100);
            colunaSituacaoSistema = new JFXTreeTableColumn<TabProdutoVO, String>();
            colunaSituacaoSistema.setGraphic(lblSituacaoSistema);
            colunaSituacaoSistema.setPrefWidth(100);
            colunaSituacaoSistema.setCellValueFactory(param -> param.getValue().getValue().getSituacaoSistemaVO().descricaoProperty());

            Label lblEstoque = new Label("Estoque");
            lblEstoque.setPrefWidth(65);
            colunaQtdEstoque = new JFXTreeTableColumn<TabProdutoVO, Integer>();
            colunaQtdEstoque.setGraphic(lblEstoque);
            colunaQtdEstoque.setPrefWidth(65);
            colunaQtdEstoque.setStyle("-fx-alignment: center-right;");
            colunaQtdEstoque.setCellValueFactory(param -> param.getValue().getValue().estoqueProperty().asObject());

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void carregarListaProduto() {
        produtoVOObservableList = FXCollections.observableArrayList(new TabProdutoDAO().getProdutoVOList());
    }

    public void preencherCboUndCom() {
        cboUnidadeComercial.getItems().clear();
        cboUnidadeComercial.getItems().add(new SisUnidadeComercialVO());
        cboUnidadeComercial.getItems().addAll(new SisUnidadeComercialDAO().getUnidadeComercialVOList());
        cboUnidadeComercial.getSelectionModel().select(0);
    }

    public void preencherCboSituacaoSistema() {
        cboSituacaoSistema.getItems().clear();
        cboSituacaoSistema.getItems().add(new SisSituacaoSistemaVO());
        cboSituacaoSistema.getItems().addAll(new SisSituacaoSistemaDAO().getSituacaoSistemaVOList());
        cboSituacaoSistema.getSelectionModel().select(0);
    }

    public void preencherCboFiscalOrigem() {
        cboFiscalOrigem.getItems().clear();
        cboFiscalOrigem.getItems().addAll(new SisFiscalCstOrigemDAO().getFiscalCstOrigemVOList());
        cboFiscalOrigem.getSelectionModel().select(0);
    }

    public void preencherCboFiscalIcms() {
        cboFiscalIcms.getItems().clear();
        cboFiscalIcms.getItems().add(new SisFiscalCstIcmsVO());
        cboFiscalIcms.getItems().addAll(new SisFiscalCstIcmsDAO().getFiscalCstIcmsVOList());
        cboFiscalIcms.getSelectionModel().select(0);
    }

    public void preencherCboFiscalPis() {
        cboFiscalPis.getItems().clear();
        cboFiscalPis.getItems().add(new SisFiscalCstPisCofinsVO());
        cboFiscalPis.getItems().addAll(new SisFiscalCstPisCofinsDAO().getSisFiscalCstPisCofinsVOList());
        cboFiscalPis.getSelectionModel().select(0);
    }

    public void preencherCboFiscalCofins() {
        cboFiscalCofins.getItems().clear();
        cboFiscalCofins.getItems().add(new SisFiscalCstPisCofinsVO());
        cboFiscalCofins.getItems().addAll(new SisFiscalCstPisCofinsDAO().getSisFiscalCstPisCofinsVOList());
        cboFiscalCofins.getSelectionModel().select(0);
    }

    public void preencherTabelaProduto() {
        try {
            if (produtoVOFilteredList == null)
                carregarPesquisaProduto(txtPesquisaProduto.getText());
            setQtdRegistrosLocalizados(produtoVOFilteredList.size());
            final TreeItem<TabProdutoVO> root = new RecursiveTreeItem<TabProdutoVO>(produtoVOFilteredList, RecursiveTreeObject::getChildren);
            ttvProduto.getColumns().setAll(colunaId, colunaCodigo, colunaDescricao, colunaUndCom, colunaVarejo, colunaPrecoFabrica,
                    colunaPrecoConsumidor, colunaSituacaoSistema, colunaQtdEstoque);
            ttvProduto.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
            ttvProduto.setRoot(root);
            ttvProduto.setShowRoot(false);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    void preencherListaProdutoEan() {
        listCodigoBarras.getItems().clear();
        if (getTtvProdutoEanVO() != null)
            listCodigoBarras.getItems().setAll(getTtvProdutoEanVO());
        listCodigoBarras.getSelectionModel().select(0);
    }

    void carregarPesquisaProduto(String strPesq) {
        String busca = strPesq.toLowerCase().trim();

        produtoVOFilteredList = new FilteredList<TabProdutoVO>(produtoVOObservableList, produto -> true);
        produtoVOFilteredList.setPredicate(produto -> {
            if (produto.getCodigo().toLowerCase().contains(busca)) return true;
            if (produto.getDescricao().toLowerCase().contains(busca)) return true;
            if (produto.getProdutoEanVOList().size() > 0)
                for (TabProdutoEanVO prodEan : produto.getProdutoEanVOList())
                    if (prodEan.getDescricao().toLowerCase().contains(busca)) return true;
            return false;
        });
        preencherTabelaProduto();
    }

    void exibirDadosProduto() {
        if (getTtvProdutoVO() == null) return;
        txtCodigo.setText(getTtvProdutoVO().getCodigo());
        txtDescricao.setText(getTtvProdutoVO().getDescricao());
        txtPeso.setText(PESO_FORMAT.format(getTtvProdutoVO().getPeso()));
        cboUnidadeComercial.getSelectionModel().select(getTtvProdutoVO().getUnidadeComercialVO());
        cboSituacaoSistema.getSelectionModel().select(getTtvProdutoVO().getSituacaoSistemaVO());
        txtPrecoFabrica.setText(DECIMAL_FORMAT.format(getTtvProdutoVO().getPrecoFabrica()).replace(".", ","));
        txtMargem.setText("0,00");
        txtPrecoConsumidor.setText(DECIMAL_FORMAT.format(getTtvProdutoVO().getPrecoConsumidor()).replace(".", ","));
        txtLucroLiquido.setText("0,00");
        txtLucratividade.setText("0,00");
        txtVarejo.setText(String.valueOf(getTtvProdutoVO().getVarejo()));
        txtComissao.setText(DECIMAL_FORMAT.format(getTtvProdutoVO().getComissao()));
        txtFiscalNcm.setText(getTtvProdutoVO().getFiscalNcm());
        txtFiscalCest.setText(getTtvProdutoVO().getFiscalCest());
        txtFiscalGenero.setText(getTtvProdutoVO().getFiscalGenero());
        System.out.println();
        cboFiscalOrigem.getSelectionModel().select(getTtvProdutoVO().getFiscalCstOrigemVO());
        cboFiscalIcms.getSelectionModel().select(getTtvProdutoVO().getFiscalCstIcmsVO());
        cboFiscalPis.getSelectionModel().select(getTtvProdutoVO().getFiscalCstPisVO());
        cboFiscalCofins.getSelectionModel().select(getTtvProdutoVO().getFiscalCstCofinsVO());

        LocalDateTime ldtCadastro = getTtvProdutoVO().getDataCadastro().toLocalDateTime();
        lblDataCadastro.setText("data cadastro: " + ldtCadastro.format(DTF_DATAHORA) + " [" + getTtvProdutoVO().getUsuarioCadastroVO().getApelido() + "]");
        lblDataCadastroDiff.setText("tempo de cadastro: " + DataTrabalhada.getStrIntervaloDatas(ldtCadastro.toLocalDate(), null));
        lblDataAtualizacao.setText("");
        lblDataAtualizacaoDiff.setText("");
        if (getTtvProdutoVO().getDataAtualizacao() != null) {
            LocalDateTime ldtAtualizacao = getTtvProdutoVO().getDataAtualizacao().toLocalDateTime();
            lblDataAtualizacao.setText("data atualização: " + ldtAtualizacao.format(DTF_DATAHORA) + " [" + getTtvProdutoVO().getUsuarioAtualizacaoVO().getApelido() + "]");
            lblDataAtualizacaoDiff.setText("tempo de atualização: " + DataTrabalhada.getStrIntervaloDatas(ldtAtualizacao.toLocalDate(), null));
        }

        preencherListaProdutoEan();
    }

    TabProdutoEanVO addProdutoEan() {
        String produto = "o produto: " + txtDescricao.getText();
        String ico = "ic_web_email_white_24dp.png";
        String codigoBarras;
        try {
            codigoBarras = new AlertMensagem("Adicionar dados [Código de Barras]",
                    USUARIO_LOGADO_APELIDO + ", qual o código de barras a ser adicionado para " + produto + " ?",
                    ico).getRetornoAlert_TextField(FormatarDado.gerarMascara("", 13, "#"), "").get();
        } catch (Exception ex) {
            //if (!(ex instanceof NoSuchElementException))
            ex.printStackTrace();
            return null;
        }
        if (codigoBarras == null) return null;
        TabProdutoEanVO produtoEanVO = new TabProdutoEanVO();
        TabEmailHomePageVO emailHomePageVO = new TabEmailHomePageVO();
        produtoEanVO.setId(0);
        produtoEanVO.setProduto_id(0);
        produtoEanVO.setDescricao(codigoBarras);

        return produtoEanVO;
    }

    TabProdutoEanVO editProdutoEan(TabProdutoEanVO produtoEanVO) {
        String produto = "o produto: " + txtDescricao.getText();
        String ico = "ic_web_email_white_24dp.png";
        String codigoBarras;
        try {
            codigoBarras = new AlertMensagem("Editar informações [Código de Barras]",
                    USUARIO_LOGADO_APELIDO + ", qual alteração será feita no código de barras d" + produto + " ?",
                    ico).getRetornoAlert_TextField(FormatarDado.gerarMascara("", 13, "#"),
                    produtoEanVO.getDescricao()).get();
        } catch (Exception ex) {
            if (!(ex instanceof NoSuchElementException))
                ex.printStackTrace();
            return null;
        }
        if (codigoBarras == null) return produtoEanVO;
        produtoEanVO.setDescricao(codigoBarras);

        return produtoEanVO;
    }


    void keyInsert() {
        if (listCodigoBarras.isFocused()) {
            TabProdutoEanVO produtoEanVO;
            if ((produtoEanVO = addProdutoEan()) == null) return;
            getTtvProdutoEanVO().add(produtoEanVO);
            listCodigoBarras.getItems().add(produtoEanVO);
            listCodigoBarras.getSelectionModel().selectLast();
        }
    }

    void keyDelete() {
        if ((listCodigoBarras.isFocused()) && (listCodigoBarras.getSelectionModel().getSelectedIndex() >= 0)) {
            getTtvProdutoEanVO().remove(listCodigoBarras.getSelectionModel().getSelectedItem());
            listCodigoBarras.getItems().remove(listCodigoBarras.getSelectionModel().getSelectedItem());
        }
    }

    void keyShiftF6() {
        if ((listCodigoBarras.isFocused()) && (listCodigoBarras.getSelectionModel().getSelectedIndex() >= 0)) {
            TabProdutoEanVO produtoEanVO = listCodigoBarras.getSelectionModel().getSelectedItem();
            int index = getTtvProdutoEanVO().indexOf(produtoEanVO);
            produtoEanVO = editProdutoEan(produtoEanVO);
            getTtvProdutoEanVO().set(index, produtoEanVO);
            listCodigoBarras.getItems().set(listCodigoBarras.getSelectionModel().getSelectedIndex(), produtoEanVO);
            listCodigoBarras.getSelectionModel().selectLast();
        }
    }

    TabProdutoVO guardarProduto() {
        TabProdutoVO prod = getTtvProdutoVO();
        prod.setCodigo(txtCodigo.getText());
        prod.setDescricao(txtDescricao.getText());
        prod.setPeso(Double.parseDouble(txtPeso.getText().replace(",", ".")));
        prod.setUnidadeComercial_id(((SisUnidadeComercialVO) cboUnidadeComercial.getSelectionModel().getSelectedItem()).getId());
        prod.setUnidadeComercialVO(cboUnidadeComercial.getSelectionModel().getSelectedItem());
        prod.setSituacaoSistema_id(((SisSituacaoSistemaVO) cboSituacaoSistema.getSelectionModel().getSelectedItem()).getId());
        prod.setSituacaoSistemaVO(cboSituacaoSistema.getSelectionModel().getSelectedItem());
        prod.setPrecoFabrica(Double.parseDouble(txtPrecoFabrica.getText().replace(",", ".")));
        prod.setPrecoConsumidor(Double.parseDouble(txtPrecoConsumidor.getText().replace(",", ".")));
        prod.setVarejo(Integer.parseInt(txtVarejo.getText()));
        prod.setComissao(Double.parseDouble(txtComissao.getText().replace(",", ".")));
        prod.setFiscalCstIcms_id(((SisFiscalCstIcmsVO) cboFiscalIcms.getSelectionModel().getSelectedItem()).getId());
        prod.setFiscalCstIcmsVO(cboFiscalIcms.getSelectionModel().getSelectedItem());
        prod.setFiscalCstPis_id(((SisFiscalCstPisCofinsVO) cboFiscalPis.getSelectionModel().getSelectedItem()).getId());
        prod.setFiscalCstPisVO(cboFiscalPis.getSelectionModel().getSelectedItem());
        prod.setFiscalCstCofins_id(((SisFiscalCstPisCofinsVO) cboFiscalCofins.getSelectionModel().getSelectedItem()).getId());
        prod.setFiscalCstCofinsVO(cboFiscalCofins.getSelectionModel().getSelectedItem());
        prod.setFiscalNcm(txtFiscalNcm.getText());
        prod.setFiscalCest(txtFiscalCest.getText());
        prod.setFiscalOrigem_id(((SisFiscalCstOrigemVO) cboFiscalOrigem.getSelectionModel().getSelectedItem()).getId());
        prod.setFiscalCstOrigemVO(cboFiscalOrigem.getSelectionModel().getSelectedItem());
        prod.setFiscalGenero(txtFiscalGenero.getText());
        prod.setUsuarioCadastro_id(Integer.parseInt(USUARIO_LOGADO_ID));
        prod.setUsuarioAtualizacao_id(Integer.parseInt(USUARIO_LOGADO_ID));

        return prod;
    }

    public void salvarProduto() {
        Connection conn = ConnectionFactory.getConnection();
        try {
            conn.setAutoCommit(false);

            setTtvProdutoVO(guardarProduto());
            int idProduto = 0;
            if ((idProduto = getTtvProdutoVO().getId()) == 0) {
                idProduto = new TabProdutoDAO().insertTabProdutoVO(conn, getTtvProdutoVO());
            } else {
                new TabProdutoDAO().updateTabProdutoVO(conn, getTtvProdutoVO());
            }

            if (getTtvProdutoEanVO().size() > 0)
                for (TabProdutoEanVO prodEanVO : getTtvProdutoEanVO())
                    if (prodEanVO.getId() == 0) {
                        prodEanVO.setProduto_id(idProduto);
                        new TabProdutoEanDAO().insertTabProdutoEanVO(conn, prodEanVO);
                    } else {
                        prodEanVO.setProduto_id(idProduto);
                        new TabProdutoEanDAO().updateTabProdutoEanVO(conn, prodEanVO);
                    }
            conn.commit();
        } catch (Exception ex) {
            ex.printStackTrace();
            try {
                conn.rollback();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } finally {
            ConnectionFactory.closeConnection(conn);
        }
    }

    void fecharTab(String tituloTab) {
        for (int i = 0; i < ControllerPrincipal.ctrlPrincipal.tabPaneViewPrincipal.getTabs().size(); i++)
            if (ControllerPrincipal.ctrlPrincipal.tabPaneViewPrincipal.getTabs().get(i).getText().toLowerCase().equals(tituloTab.toLowerCase())) {
                ControllerPrincipal.ctrlPrincipal.fecharTab(i);
                ControllerPrincipal.ctrlPrincipal.painelViewPrincipal.removeEventHandler(KeyEvent.KEY_RELEASED, eventCadastroProduto);
            }
    }

}
