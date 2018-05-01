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
import br.com.sidtmcafe.view.ViewCadastroProduto;
import com.jfoenix.controls.*;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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
import org.apache.velocity.runtime.directive.contrib.For;

import java.math.BigDecimal;
import java.math.RoundingMode;
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

import static br.com.sidtmcafe.interfaces.Constants.DECIMAL_FORMAT;
import static br.com.sidtmcafe.interfaces.Constants.DTF_DATA;
import static br.com.sidtmcafe.interfaces.Constants.DTF_DATAHORA;

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
    public JFXTextField txtVarejo;
    public JFXTextField txtComissaoPorc;
    public JFXTextField txtLucroBruto;
    public JFXTextField txtPrecoUltimoFrete;
    public JFXTextField txtComissaoReal;
    public JFXTextField txtLucroLiquido;
    public JFXTextField txtLucratividade;
    public Label lblDataCadastro;
    public Label lblDataCadastroDiff;
    public Label lblDataAtualizacao;
    public Label lblDataAtualizacaoDiff;
    public JFXListView<TabProdutoEanVO> listCodigoBarras;
    public JFXTextField txtFiscalNcm;
    public JFXTextField txtFiscalGenero;
    public JFXTextField txtFiscalCest;
    public JFXComboBox<FiscalCSTOrigemVO> cboFiscalOrigem;
    public JFXComboBox<FiscalICMSVO> cboFiscalIcms;
    public JFXComboBox<FiscalPISCOFINSVO> cboFiscalPis;
    public JFXComboBox<FiscalPISCOFINSVO> cboFiscalCofins;


    @Override
    public void fechar() {
        for (int i = 0; i < ControllerPrincipal.ctrlPrincipal.tabPaneViewPrincipal.getTabs().size(); i++)
            if (ControllerPrincipal.ctrlPrincipal.tabPaneViewPrincipal.getTabs().get(i).getText().toLowerCase().equals(tituloTab.toLowerCase())) {
                ControllerPrincipal.ctrlPrincipal.fecharTab(i);
                ControllerPrincipal.ctrlPrincipal.painelViewPrincipal.removeEventHandler(KeyEvent.KEY_PRESSED, eventCadastroProduto);
            }
    }

    @Override
    public void criarObjetos() {
        listaTarefas.add(new Pair("criarTabelaProduto", "criando tabela produtos"));
    }

    @Override
    public void preencherObjetos() {
        listaTarefas.add(new Pair("carregarListaProduto", "carregando lista de produtos"));
        listaTarefas.add(new Pair("preencherCboUnidadeComercial", "preenchendo dados unidade comercial"));
        listaTarefas.add(new Pair("preencherCboSituacaoSistema", "preenchendo situaão no istema"));
        listaTarefas.add(new Pair("preencherCboFiscalOrigem", "preenchendo dados fiscais de Origem"));
        listaTarefas.add(new Pair("preencherCboFiscalIcms", "preenchendo dados fiscal ICMS"));
        listaTarefas.add(new Pair("preencherCboFiscalPis", "preenchendo dados fiscal PIS"));
        listaTarefas.add(new Pair("preencherCboFiscalCofins", "preenchendo dados fiscal COFINS"));

        listaTarefas.add(new Pair("carregarListaProduto", "carregando lista de produtos"));
        listaTarefas.add(new Pair("preencherTabelaProduto", "preenchendo tabela produto"));

        new Tarefa().tarefaAbreCadastroProduto(this, listaTarefas);

        PersonalizarCampo.fieldMaxLen(painelViewCadastroProduto);
        PersonalizarCampo.maskFields(painelViewCadastroProduto);

    }

    @Override
    public void fatorarObjetos() {

    }

    @Override
    public void escutarTeclas() {

        ttvProduto.getSelectionModel().selectedIndexProperty().addListener((ov, o, n) -> {
            if (n == null || n.intValue() < 0)
                setTabProdutoVO(new TabProdutoVO());
            else
                setTabProdutoVO(ttvProduto.getTreeItem(n.intValue()).getValue());
            exibirDadosProduto();
        });

        ControllerPrincipal.ctrlPrincipal.tabPaneViewPrincipal.getSelectionModel().selectedItemProperty().addListener((ov, o, n) -> {
            if (!(ControllerPrincipal.ctrlPrincipal.getTabAtual().equals(tituloTab)))
                return;
            if (n != o && n != null)
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
                        setTabProdutoVO(new TabProdutoVO());
                        setStatusFormulario("Incluir");
                        break;
                    case F2:
                    case F5:
                        if (!getStatusBarFormulario().contains(event.getCode().toString())) break;

                        salvarEmpresa();
                        limparCampoDadoCadastral();
                        setStatusFormulario("Pesquisa");
                        carregarListaProduto();
                        preencherTabelaProduto();
                        carregarPesquisaProduto(txtPesquisaProduto.getText());
                        txtPesquisaProduto.requestFocus();
                        break;
                    case F3:
                        if (!getStatusBarFormulario().contains(event.getCode().toString())) break;
                        switch (getStatusFormulario().toLowerCase()) {
                            case "incluir":
                                if (new AlertMensagem("Cancelar inclusão", USUARIO_LOGADO_APELIDO
                                        + ", deseja cancelar inclusão no cadastro de produto?",
                                        "ic_cadastro_produto_grao_cafe_orange_24dp.png").getRetornoAlert_YES_NO().get() == ButtonType.NO)
                                    return;
                                limparCampoDadoCadastral();
                                break;
                            case "editar":
                                if (new AlertMensagem("Cancelar edição", USUARIO_LOGADO_APELIDO
                                        + ", deseja cancelar edição do cadastro de produto?",
                                        "ic_cadastro_produto_grao_cafe_orange_24dp.png").getRetornoAlert_YES_NO().get() == ButtonType.NO)
                                    return;
                                limparCampoDadoCadastral();
                                carregarListaProduto();
                                preencherTabelaProduto();
                                carregarPesquisaProduto(txtPesquisaProduto.getText());
                                setTabProdutoVO(tabProdutoVOObservableList.get(indexObservableProduto));
                                break;
                        }
                        setStatusFormulario("Pesquisa");
                        break;
                    case F4:
                        if ((!getStatusBarFormulario().contains(event.getCode().toString())) || !(ttvProduto.getSelectionModel().getSelectedIndex() >= 0))
                            break;
                        indexObservableProduto = tabProdutoVOObservableList.indexOf(getTabProdutoVO());
                        setStatusFormulario("Editar");
                        break;
                    case F6:
                        if (getStatusFormulario().toLowerCase().equals("pesquisa") || (!event.isShiftDown())) break;
                        keyShiftF6();
                        break;
                    case F7:
                        if (!getStatusBarFormulario().contains(event.getCode().toString())) break;
                        txtPesquisaProduto.requestFocus();
                        break;
                    case F8:
                        if (!getStatusBarFormulario().contains(event.getCode().toString())) break;
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

        ControllerPrincipal.ctrlPrincipal.painelViewPrincipal.addEventHandler(KeyEvent.KEY_RELEASED, eventCadastroProduto);

        txtPesquisaProduto.textProperty().addListener((ov, o, n) -> {
            carregarPesquisaProduto(n);
        });

        txtPesquisaProduto.addEventHandler(KeyEvent.KEY_PRESSED, event -> {
            if (event.getCode() == KeyCode.ENTER) {
                ttvProduto.requestFocus();
                ttvProduto.getFocusModel().focus(0);
                ttvProduto.getSelectionModel().select(0);
            }
        });

        txtPrecoFabrica.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!txtPrecoFabrica.isFocused()) return;
                vlrConsumidor();
                vlrLucroBruto();
            }
        });

        txtMargem.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!txtMargem.isFocused()) {
                    return;
                }
                vlrConsumidor();
                vlrLucroBruto();
            }
        });

        txtPrecoConsumidor.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!txtPrecoConsumidor.isFocused()) return;
                vlrMargem();
                vlrLucroBruto();
            }
        });

        txtComissaoPorc.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!txtComissaoPorc.isFocused()) return;
                vlrComissaoReal();
                vlrLucroBruto();
            }
        });

        txtPrecoUltimoFrete.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!txtPrecoUltimoFrete.isFocused()) return;
                vlrLucroBruto();
            }
        });

    }

    @SuppressWarnings("Duplicates")
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

    int qtdRegistrosLocalizados = 0;
    int indexObservableProduto = 0;
    String tituloTab = ViewCadastroProduto.getTituloJanela();
    String statusFormulario, statusBarFormulario;
    EventHandler<KeyEvent> eventCadastroProduto;

    static String STATUSBARPESQUISA = "[F1-Novo]  [F3-Excluir]  [F4-Editar]  [F7-Pesquisar]  [F12-Sair]  ";
    static String STATUSBAREDITAR = "[F3-Cancelar edição]  [F5-Atualizar]  ";
    static String STATUSBARINCLUIR = "[F2-Incluir]  [F3-Cancelar inclusão]  ";

    List<Pair> listaTarefas;
    ObservableList<TabProdutoVO> tabProdutoVOObservableList;
    FilteredList<TabProdutoVO> tabProdutoVOFilteredList;
    TabProdutoVO tabProdutoVO;
    List<TabProdutoEanVO> tabProdutoEanVOList;
    List<TabProdutoEanVO> deletadosTabProdutoEanVOList;

    List<SisUnidadeComercialVO> sisUnidadeComercialVOList;
    List<SisSituacaoSistemaVO> sisSituacaoSistemaVOList;
    List<FiscalCSTOrigemVO> fiscalCSTOrigemVOList;
    List<FiscalICMSVO> fiscalICMSVOList;
    List<FiscalPISCOFINSVO> fiscalPISVOList;
    List<FiscalPISCOFINSVO> fiscalCOFINSVOList;

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
            PersonalizarCampo.desabilitaCampos((AnchorPane) tpnCadastroProduto.getContent(), true);
            PersonalizarCampo.desabilitaCampos((AnchorPane) tpnDadoCadastral.getContent(), false);
            limparCampoDadoCadastral();
            txtCodigo.requestFocus();
            this.statusBarFormulario = STATUSBARINCLUIR;
        } else if (statusFormulario.toLowerCase().contains("editar")) {
            PersonalizarCampo.desabilitaCampos((AnchorPane) tpnCadastroProduto.getContent(), true);
            PersonalizarCampo.desabilitaCampos((AnchorPane) tpnDadoCadastral.getContent(), false);
            txtCodigo.requestFocus();
            this.statusBarFormulario = STATUSBAREDITAR;
        } else if (statusFormulario.toLowerCase().contains("pesquisa")) {
            PersonalizarCampo.desabilitaCampos((AnchorPane) tpnCadastroProduto.getContent(), false);
            PersonalizarCampo.desabilitaCampos((AnchorPane) tpnDadoCadastral.getContent(), true);
            txtPesquisaProduto.requestFocus();
            this.statusBarFormulario = STATUSBARPESQUISA;
        }
        ControllerPrincipal.ctrlPrincipal.atualizarTeclasStatusBar(statusBarFormulario);
    }

    void atualizaLblQtdRegistroLocalizado() {
        lblRegistrosLocalizados.setText("[" + getStatusFormulario() + "]  " + String.valueOf(getQtdRegistrosLocalizados()) + " registro(s) localizado(s).");
    }

    public TabProdutoVO getTabProdutoVO() {
        return tabProdutoVO;
    }

    public void setTabProdutoVO(TabProdutoVO tabProdutoVO) {
        if (tabProdutoVO == null)
            tabProdutoVO = new TabProdutoVO();
        this.tabProdutoVO = tabProdutoVO;
        setTabProdutoEanVOList(getTabProdutoVO().getTabProdutoEanVOList());
    }

    public List<TabProdutoEanVO> getTabProdutoEanVOList() {
        return tabProdutoEanVOList;
    }

    public void setTabProdutoEanVOList(List<TabProdutoEanVO> tabProdutoEanVOList) {
        if (tabProdutoEanVOList == null)
            tabProdutoEanVOList = new ArrayList<TabProdutoEanVO>();
        this.tabProdutoEanVOList = tabProdutoEanVOList;
        atualizaListaCodigoBarrasEan();
    }

    void atualizaListaCodigoBarrasEan() {
        listCodigoBarras.getItems().clear();
        if (tabProdutoEanVOList == null)
            return;
        listCodigoBarras.getItems().setAll(tabProdutoEanVOList);
    }

    void limparCampoDadoCadastral() {
        PersonalizarCampo.clearField((AnchorPane) tpnDadoCadastral.getContent());
    }

    public void preencherCboUnidadeComercial() {
        cboUnidadeComercial.getItems().clear();
        if ((sisUnidadeComercialVOList = new ArrayList<>(new SisUnidadeComercialDAO().getSisUnidadeComercialVOList())) == null)
            return;
        cboUnidadeComercial.getItems().setAll(sisUnidadeComercialVOList);
    }

    public void preencherCboSituacaoSistema() {
        cboSituacaoSistema.getItems().clear();
        if ((sisSituacaoSistemaVOList = new ArrayList<>(new SisSituacaoSistemaDAO().getSisSituacaoSistemaVOList())) == null)
            return;
        cboSituacaoSistema.getItems().setAll(sisSituacaoSistemaVOList);
    }

    public void preencherCboFiscalOrigem() {
        cboFiscalOrigem.getItems().clear();
        if ((fiscalCSTOrigemVOList = new ArrayList<FiscalCSTOrigemVO>(new FiscalCSTOrigemDAO().getFiscalCSTOrigemVOList())) == null)
            return;
        cboFiscalOrigem.getItems().setAll(fiscalCSTOrigemVOList);
    }

    public void preencherCboFiscalIcms() {
        cboFiscalIcms.getItems().clear();
        if ((fiscalICMSVOList = new ArrayList<FiscalICMSVO>(new FiscalICMSDAO().getFiscalICMSVOList())) == null)
            return;
        cboFiscalIcms.getItems().setAll(fiscalICMSVOList);
    }

    public void preencherCboFiscalPis() {
        cboFiscalPis.getItems().clear();
        if ((fiscalPISVOList = new ArrayList<FiscalPISCOFINSVO>(new FiscalPISCOFINSDAO().getFiscalPISCOFINSVOList())) == null)
            return;
        cboFiscalPis.getItems().setAll(fiscalPISVOList);
    }

    public void preencherCboFiscalCofins() {
        cboFiscalCofins.getItems().clear();
        if ((fiscalCOFINSVOList = new ArrayList<FiscalPISCOFINSVO>(new FiscalPISCOFINSDAO().getFiscalPISCOFINSVOList())) == null)
            return;
        cboFiscalCofins.getItems().setAll(fiscalCOFINSVOList);
    }

    public void preencherTabelaProduto() {
        try {
            if (tabProdutoVOFilteredList == null)
                carregarPesquisaProduto(txtPesquisaProduto.getText());
            setQtdRegistrosLocalizados(tabProdutoVOFilteredList.size());
            final TreeItem<TabProdutoVO> root = new RecursiveTreeItem<TabProdutoVO>(tabProdutoVOFilteredList, RecursiveTreeObject::getChildren);
            ttvProduto.getColumns().setAll(TabModel.getColunaIdProduto(), TabModel.getColunaCodigo(),
                    TabModel.getColunaDescricao(), TabModel.getColunaUndCom(), TabModel.getColunaVarejo(),
                    TabModel.getColunaPrecoFabrica(), TabModel.getColunaPrecoConsumidor(),
                    TabModel.getColunaSituacaoSistema(), TabModel.getColunaQtdEstoque());
            ttvProduto.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
            ttvProduto.setRoot(root);
            ttvProduto.setShowRoot(false);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    void carregarPesquisaProduto(String strPesq) {
        String busca = strPesq.toLowerCase().trim();

        tabProdutoVOFilteredList = new FilteredList<TabProdutoVO>(tabProdutoVOObservableList, produto -> true);
        tabProdutoVOFilteredList.setPredicate(produto -> {
            if (busca.length() <= 0) return true;

            if (produto.getCodigo().toLowerCase().contains(busca)) return true;
            if (produto.getDescricao().toLowerCase().contains(busca)) return true;

            return false;
        });
        preencherTabelaProduto();
    }

    public void carregarListaProduto() {
        tabProdutoVOObservableList = FXCollections.observableArrayList(new TabProdutoDAO().getTabProdutoVOList());
    }

    void exibirDadosProduto() {
        if (getTabProdutoVO() == null) return;
        txtCodigo.setText(getTabProdutoVO().getCodigo());
        txtDescricao.setText(getTabProdutoVO().getDescricao());
        txtPeso.setText(getTabProdutoVO().pesoProperty().toString());
        for (int i = 0; i < cboUnidadeComercial.getItems().size(); i++) {
            cboUnidadeComercial.getSelectionModel().select(i);
            if (cboUnidadeComercial.getItems().get(i).getId() == getTabProdutoVO().getSisUnidadeComercial_id())
                break;
        }
        for (int i = 0; i < cboSituacaoSistema.getItems().size(); i++) {
            cboSituacaoSistema.getSelectionModel().select(i);
            if (cboSituacaoSistema.getItems().get(i).getId() == getTabProdutoVO().getSisSituacaoSistema_id())
                break;
        }
        txtPrecoFabrica.setText(FormatarDado.getValueMoeda(getTabProdutoVO().precoFabricaProperty().toString(), 2));
        System.out.println("txtPrecoFabrica: [" + txtPrecoFabrica.getText() + "]");
        txtPrecoConsumidor.setText(FormatarDado.getValueMoeda(getTabProdutoVO().precoVendaProperty().toString(), 2));
        System.out.println("txtPrecoConsumidor: [" + txtPrecoConsumidor.getText() + "]");
        txtPrecoUltimoFrete.setText(FormatarDado.getValueMoeda(getTabProdutoVO().precoUltimoFreteProperty().toString(), 2));
        System.out.println("txtPrecoUltimoFrete: [" + txtPrecoUltimoFrete.getText() + "]");
        vlrMargem();
        vlrLucroLiq();
        vlrLucratividade();
        txtVarejo.setText(getTabProdutoVO().varejoProperty().toString());
        txtComissaoPorc.setText(FormatarDado.getValueMoeda(getTabProdutoVO().comissaoProperty().toString(), 2));
        txtFiscalNcm.setText(FormatarDado.getCampoFormatado(getTabProdutoVO().getNfeNcm(), "ncm"));
        txtFiscalGenero.setText(getTabProdutoVO().getNfeGenero());
        txtFiscalCest.setText(FormatarDado.getCampoFormatado(getTabProdutoVO().getNfeCest(), "cest"));

        for (int i = 0; i < cboFiscalOrigem.getItems().size(); i++) {
            cboFiscalOrigem.getSelectionModel().select(i);
            if (cboFiscalOrigem.getItems().get(i).getId() == getTabProdutoVO().getFiscalCSTOrigem_id())
                return;
        }
        for (int i = 0; i < cboFiscalIcms.getItems().size(); i++) {
            cboFiscalIcms.getSelectionModel().select(i);
            if (cboFiscalIcms.getItems().get(i).getId() == getTabProdutoVO().getFiscalICMS_id())
                return;
        }
        for (int i = 0; i < cboFiscalPis.getItems().size(); i++) {
            if (cboFiscalPis.getItems().get(i).getId() == getTabProdutoVO().getFiscalPIS_id())
                return;
        }
        for (int i = 0; i < cboFiscalCofins.getItems().size(); i++) {
            if (cboFiscalCofins.getItems().get(i).getId() == getTabProdutoVO().getFiscalCOFINS_id())
                return;
        }

        if (getTabProdutoVO().getDataCadastro() != null) {//if (!getStatusFormulario().toLowerCase().equals("incluir")) {
            LocalDateTime ldtCadastro = getTabProdutoVO().getDataCadastro().toLocalDateTime();
            lblDataCadastro.setText("data cadastro: " + ldtCadastro.format(DTF_DATAHORA) + " [" + getTabProdutoVO().getUsuarioCadastroVO().getApelido() + "]");
            lblDataCadastroDiff.setText("tempo de cadastro: " + DataTrabalhada.getStrIntervaloDatas(ldtCadastro.toLocalDate(), null));
            lblDataAtualizacao.setText("");
            lblDataAtualizacaoDiff.setText("");
            if (getTabProdutoVO().getDataAtualizacao() != null) {
                LocalDateTime ldtAtualizacao = getTabProdutoVO().getDataAtualizacao().toLocalDateTime();
                lblDataAtualizacao.setText("data atualização: " + ldtAtualizacao.format(DTF_DATAHORA) + " [" + getTabProdutoVO().getUsuarioAtualizacaoVO().getApelido() + "]");
                lblDataAtualizacaoDiff.setText("tempo de atualização: " + DataTrabalhada.getStrIntervaloDatas(ldtAtualizacao.toLocalDate(), null));
            }
        }

    }

    boolean guardarProduto() {
        try {
            getTabProdutoVO().setCodigo(txtCodigo.getText());
            getTabProdutoVO().setDescricao(txtDescricao.getText());
            getTabProdutoVO().setPeso(Double.parseDouble(txtPeso.getText()));
            getTabProdutoVO().setSisUnidadeComercial_id(cboUnidadeComercial.getSelectionModel().getSelectedItem().getId());
            getTabProdutoVO().setSisSituacaoSistema_id(cboSituacaoSistema.getSelectionModel().getSelectedItem().getId());

            getTabProdutoVO().setPrecoFabrica(Double.parseDouble(txtPrecoFabrica.getText()));
            getTabProdutoVO().setPrecoVenda(Double.parseDouble(txtPrecoConsumidor.getText()));
            getTabProdutoVO().setVarejo(Integer.parseInt(txtVarejo.getText()));
            getTabProdutoVO().setComissao(Double.parseDouble(txtComissaoPorc.getText()));
            getTabProdutoVO().setNfeNcm(txtFiscalNcm.getText());
            getTabProdutoVO().setNfeGenero(txtFiscalGenero.getText());
            getTabProdutoVO().setNfeCest(txtFiscalCest.getText());
            getTabProdutoVO().setFiscalCSTOrigem_id(cboFiscalOrigem.getSelectionModel().getSelectedItem().getId());
            getTabProdutoVO().setFiscalICMS_id(cboFiscalIcms.getSelectionModel().getSelectedItem().getId());
            getTabProdutoVO().setFiscalPIS_id(cboFiscalPis.getSelectionModel().getSelectedItem().getId());
            getTabProdutoVO().setFiscalCOFINS_id(cboFiscalCofins.getSelectionModel().getSelectedItem().getId());

            getTabProdutoVO().setUsuarioCadastro_id(Integer.parseInt(USUARIO_LOGADO_ID));
            getTabProdutoVO().setUsuarioAtualizacao_id(Integer.parseInt(USUARIO_LOGADO_ID));

        } catch (Exception ex) {
            return false;
        }
        return true;
    }

    void deletaCodigoBarros(TabProdutoEanVO produtoEanVO) {
        if (produtoEanVO.getId() != 0) {
            if (deletadosTabProdutoEanVOList == null)
                deletadosTabProdutoEanVOList = new ArrayList<TabProdutoEanVO>();
            deletadosTabProdutoEanVOList.add(produtoEanVO);
        }
        getTabProdutoEanVOList().remove(produtoEanVO);
        atualizaListaCodigoBarrasEan();
    }

    void keyDelete() {
        if (listCodigoBarras.isFocused() && listCodigoBarras.getSelectionModel().getSelectedIndex() >= 0)
            deletaCodigoBarros(listCodigoBarras.getSelectionModel().getSelectedItem());
    }

    void keyInsert() {
        if (listCodigoBarras.isFocused()) {
            TabProdutoEanVO produtoEanVO = null;
            if ((produtoEanVO = addCodigoBarras(produtoEanVO)) == null) return;
            getTabProdutoEanVOList().add(produtoEanVO);
            atualizaListaCodigoBarrasEan();
        }
    }

    void keyShiftF6() {
        if (listCodigoBarras.isFocused()) {
            TabProdutoEanVO produtoEanVO = new TabProdutoEanVO();
            TabProdutoEanVO produtoEanEdit = null;
            if (listCodigoBarras.getSelectionModel().getSelectedIndex() >= 0)
                produtoEanEdit = listCodigoBarras.getSelectionModel().getSelectedItem();
            if ((produtoEanEdit == null) || (produtoEanVO = addCodigoBarras(produtoEanEdit)) == null) return;
            getTabProdutoEanVOList().set(getTabProdutoEanVOList().indexOf(produtoEanEdit), produtoEanVO);
            atualizaListaCodigoBarrasEan();
        }
    }

    TabProdutoEanVO addCodigoBarras(TabProdutoEanVO produtoEanVO) {
        String strIco, codigoBarrasAdicao;
        strIco = "ic_codigo_barras_orange_24dp.png";
        try {
            if (produtoEanVO == null) {
                produtoEanVO = new TabProdutoEanVO();
                produtoEanVO.setId(0);
                produtoEanVO.setProduto_id(0);
                codigoBarrasAdicao = new AlertMensagem("Adicionar dados [código barras]",
                        USUARIO_LOGADO_APELIDO + ", qual o código de barras a ser adicionada para o produto" + txtDescricao.getText() + " ?",
                        strIco).getRetornoAlert_TextField(FormatarDado.gerarMascara("barcode", 13, "#"), "").get();
            } else {
                codigoBarrasAdicao = new AlertMensagem("Editar dados [código barras]",
                        USUARIO_LOGADO_APELIDO + ", qual alteração será feita no código de barras " + produtoEanVO.getCodigoEan() +
                                " do produto " + txtDescricao.getText() + "?",
                        strIco).getRetornoAlert_TextField(FormatarDado.gerarMascara("barcode", 13, "#"),
                        produtoEanVO.getCodigoEan()).get();
            }
            if (codigoBarrasAdicao == null || codigoBarrasAdicao.equals("") || codigoBarrasAdicao.equals(produtoEanVO.getCodigoEan()))
                return null;
        } catch (Exception ex) {
            if (!(ex instanceof NoSuchElementException))
                ex.printStackTrace();
            return null;
        }
        produtoEanVO.setCodigoEan(codigoBarrasAdicao);

        listaTarefas = new ArrayList<>();
        listaTarefas.add(new Pair("buscaEan", produtoEanVO.getCodigoEan()));

        TabProdutoVO tabProdutoVO = new Tarefa().tarefaWsEanCosmos(listaTarefas);
        if (tabProdutoVO != null) {
            guardarProduto();
            getTabProdutoVO().setNfeNcm(tabProdutoVO.getNfeNcm());
            //txtFiscalNcm.setText(getTabProdutoVO().getNfeNcm());
            getTabProdutoVO().setDescricao(tabProdutoVO.getDescricao());
            //txtDescricao.setText(getTabProdutoVO().getDescricao());
            exibirDadosProduto();
        }


        return produtoEanVO;
    }

    void salvarEmpresa() {
        Connection conn = ConnectionFactory.getConnection(); // cria conexao com banco de dados
        try {
            conn.setAutoCommit(false);

            if (getTabProdutoVO().getId() == 0)
                getTabProdutoVO().setId(new TabProdutoDAO().insertTabProdutoVO(conn, getTabProdutoVO()));
            else
                new TabProdutoDAO().updateTabProdutoVO(conn, getTabProdutoVO());

            if (getTabProdutoEanVOList() != null)
                for (int i = 0; i < getTabProdutoEanVOList().size(); i++) {
                    getTabProdutoEanVOList().get(i).setProduto_id(getTabProdutoVO().getId());
                    if (getTabProdutoEanVOList().get(i).getId() == 0)
                        getTabProdutoEanVOList().get(i).setId(new TabProdutoEanDAO().insertTabProdutoEanVO(conn, getTabProdutoEanVOList().get(i)));
                    else
                        new TabProdutoEanDAO().updateTabProdutoEanVO(conn, getTabProdutoEanVOList().get(i));
                }

            conn.commit();
        } catch (SQLException ex) {
            ex.printStackTrace();
            try {
                conn.rollback();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    void vlrConsumidor() {
        Double prcFabrica = FormatarDado.getDoubleValorCampo(txtPrecoFabrica.getText());
        Double margem = FormatarDado.getDoubleValorCampo(txtMargem.getText());
        Double prcConsumidor = 0.;

        if (margem.equals(0.))
            prcConsumidor = prcFabrica;
        else
            prcConsumidor = (prcFabrica * (1. + (margem / 100.)));

        txtPrecoConsumidor.setText(FormatarDado.getValueMoeda(BigDecimal.valueOf(prcConsumidor).setScale(2, RoundingMode.HALF_UP).toString(), 2));
    }

    void vlrMargem() {
        Double prcFabrica = FormatarDado.getDoubleValorCampo(txtPrecoFabrica.getText());
        System.out.println("prcFabrica: [" + prcFabrica + "]");
        Double prcConsumidor = FormatarDado.getDoubleValorCampo(txtPrecoConsumidor.getText());
        System.out.println("prcConsumidor: [" + prcConsumidor + "]");
        Double margem = 0.;
        System.out.println("margem(0): [" + margem + "]");

        if (prcConsumidor.equals(prcFabrica)) {
            margem = 0.;
            System.out.println("margem(1): [" + margem + "]");
        } else {
            margem = (((prcConsumidor - prcFabrica) * 100.) / prcFabrica);
            System.out.println("margem(2): [" + margem + "]");
        }

        txtMargem.setText(FormatarDado.getValueMoeda(BigDecimal.valueOf(margem).setScale(2, RoundingMode.HALF_UP).toString(), 2));
    }

    void vlrLucroBruto() {
        Double prcFabrica = FormatarDado.getDoubleValorCampo(txtPrecoFabrica.getText());
        Double prcConsumidor = FormatarDado.getDoubleValorCampo(txtPrecoConsumidor.getText());
        Double lucroBruto;

        if (prcConsumidor.equals(prcFabrica))
            lucroBruto = 0.;
        else
            lucroBruto = (prcConsumidor - prcFabrica);

        txtLucroBruto.setText(FormatarDado.getValueMoeda(BigDecimal.valueOf(lucroBruto).setScale(2, RoundingMode.HALF_UP).toString(), 2));
        vlrLucroLiq();
    }

    void vlrLucroLiq() {
        Double lucroBruto = FormatarDado.getDoubleValorCampo(txtLucroBruto.getText());
        Double ultimoFrete = FormatarDado.getDoubleValorCampo(txtPrecoUltimoFrete.getText());
        Double comissaoReal = FormatarDado.getDoubleValorCampo(txtComissaoReal.getText());
        Double lucroLiquido;

        if (lucroBruto.equals(0.))
            lucroLiquido = 0.;
        else
            lucroLiquido = (lucroBruto - (ultimoFrete + comissaoReal));

        txtLucroLiquido.setText(FormatarDado.getValueMoeda(BigDecimal.valueOf(lucroLiquido).setScale(2, RoundingMode.HALF_UP).toString(), 2));
        vlrLucratividade();
    }

    void vlrLucratividade() {
        Double prcConsumidor = FormatarDado.getDoubleValorCampo(txtPrecoConsumidor.getText());
        Double lucroLiquido = FormatarDado.getDoubleValorCampo(txtLucroLiquido.getText());
        Double lucratividade;

        if (lucroLiquido.equals(0.))
            lucratividade = 0.;
        else
            lucratividade = ((lucroLiquido * 100.) / prcConsumidor);

        txtLucratividade.setText(FormatarDado.getValueMoeda(BigDecimal.valueOf(lucratividade).setScale(2, RoundingMode.HALF_UP).toString(), 2));
    }

    void vlrComissaoReal() {
        Double prcConsumidor = FormatarDado.getDoubleValorCampo(txtPrecoConsumidor.getText());
        Double comissaoPorc = FormatarDado.getDoubleValorCampo(txtComissaoPorc.getText());
        Double comissaoReal;

        if (comissaoPorc.equals(0.))
            comissaoReal = 0.;
        else
            comissaoReal = prcConsumidor * (comissaoPorc / 100.);

        txtComissaoReal.setText(FormatarDado.getValueMoeda(BigDecimal.valueOf(comissaoReal).setScale(2, RoundingMode.HALF_UP).toString(), 2));
    }
}
