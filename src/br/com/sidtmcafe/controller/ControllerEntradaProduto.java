package br.com.sidtmcafe.controller;

import br.com.sidtmcafe.componentes.AlertMensagem;
import br.com.sidtmcafe.componentes.Tarefa;
import br.com.sidtmcafe.componentes.Variavel;
import br.com.sidtmcafe.interfaces.FormularioModelo;
import br.com.sidtmcafe.model.dao.*;
import br.com.sidtmcafe.model.model.TabModel;
import br.com.sidtmcafe.model.vo.*;
import br.com.sidtmcafe.service.ExecutaComandoTecladoMouse;
import br.com.sidtmcafe.service.FormatarDado;
import br.com.sidtmcafe.service.PersonalizarCampo;
import br.com.sidtmcafe.view.ViewEntradaProduto;
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
import javafx.util.Callback;
import javafx.util.Pair;

import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import static br.com.sidtmcafe.interfaces.Constants.*;

public class ControllerEntradaProduto extends Variavel implements Initializable, FormularioModelo {
//    public AnchorPane painelViewEntradaProduto;
//    public TitledPane tpnDadoNfe;
//    public TitledPane tpnDetalheNfe;
//    public JFXComboBox<TabLojaVO> cboLojaDestino;
//    public JFXTextField txtChaveNfe;
//    public JFXTextField txtNumeroNfe;
//    public JFXTextField txtNumeroSerie;
//    public JFXComboBox<TabEmpresaVO> cboFornecedor;
//    public DatePicker dtpEmissaoNfe;
//    public DatePicker dtpEntradaNfe;
//    public TitledPane tpnImpostoNfe;
//    public JFXTextField txtFiscalNumeroControle;
//    public JFXTextField txtFiscalDocumentoOrigem;
//    public JFXComboBox<SisFiscalSefazTributoVO> cboFiscalTributo;
//    public JFXTextField txtFiscalVlrTributo;
//    public JFXTextField txtFiscalVlrMulta;
//    public JFXTextField txtFiscalVlrJuros;
//    public JFXTextField txtFiscalVlrTaxa;
//    public JFXTextField txtFiscalVlrTotalTributo;
//    public JFXTextField txtFiscalVlrPercentualTributo;
//    public TitledPane tpnDetalheFrete;
//    public JFXTextField txtFreteChaveCte;
//    public JFXComboBox<SisFreteTomadorVO> cboFreteTomadorServico;
//    public JFXTextField txtFreteNumeroCte;
//    public JFXTextField txtFreteSerieCte;
//    public JFXComboBox<SisFiscalModeloNfeCteVO> cboFreteModeloCte;
//    public JFXComboBox<SisFreteSituacaoTributariaVO> cboFreteSistuacaoTributaria;
//    public JFXComboBox<TabEmpresaVO> cboFreteTransportadora;
//    public DatePicker dtpFreteEmissao;
//    public JFXTextField txtFreteVlrNfe;
//    public JFXTextField txtFreteQtdVolume;
//    public JFXTextField txtFretePesoBruto;
//    public JFXTextField txtFreteVlrBruto;
//    public JFXTextField txtFreteVlrImposto;
//    public JFXTextField txtFreteVlrLiquido;
//    public TitledPane tpnFreteImposto;
//    public JFXTextField txtFreteFiscalNumeroControle;
//    public JFXTextField txtFreteFiscalImpostoDocumentoOrigem;
//    public JFXComboBox<SisFiscalSefazTributoVO> cboFreteFiscalTributo;
//    public JFXTextField txtFreteFiscalVlrTributo;
//    public JFXTextField txtFreteFiscalVlrMulta;
//    public JFXTextField txtFreteFiscalVlrJuros;
//    public JFXTextField txtFreteFiscalVlrTaxa;
//    public JFXTextField txtFreteFiscalVlrTotalTributo;
//    public JFXTextField txtFreteFiscalVlrPercentualTributo;
//    public TitledPane tpnItensTotaisNfe;
//    public TitledPane tpnCadastroProduto;
//    public JFXTextField txtPesquisaProduto;
//    public JFXTreeTableView ttvProduto;
//    public Label lblRegistrosLocalizados;
//    public TitledPane tpnItensNfe;
//    public JFXTreeTableView ttvItensNfe;

    @Override
    public void fechar() {

    }

    @Override
    public void criarObjetos() {

    }

    @Override
    public void preencherObjetos() {
//        listaTarefas = new ArrayList<>();
//        criarTabelas();
//        carregaListas();
//        preencherCombos();
//        preencherTabelas();
//
//        new Tarefa().tarefaAbreEntradaProduto(this, listaTarefas);
//
//        PersonalizarCampo.fieldMaxLen(painelViewEntradaProduto);
//        PersonalizarCampo.maskFields(painelViewEntradaProduto);
    }

    @Override
    public void fatorarObjetos() {
//        cboLojaDestino.setCellFactory(
//                new Callback<ListView<TabLojaVO>, ListCell<TabLojaVO>>() {
//                    @Override
//                    public ListCell<TabLojaVO> call(ListView<TabLojaVO> param) {
//                        final ListCell<TabLojaVO> cell = new ListCell<TabLojaVO>() {
//                            @Override
//                            public void updateItem(TabLojaVO item, boolean empty) {
//                                super.updateItem(item, empty);
//                                if (item != null) {
//                                    if (getIndex() == -1) {
//                                        setText(item.toString());
//                                    } else {
//                                        String textoCombo = "";
//                                        for (String detalheLoja : item.getDetalheLoja().split(";")) {
//                                            if (textoCombo != "")
//                                                textoCombo += "\r\n";
//                                            textoCombo += detalheLoja;
//                                        }
//                                        setText(textoCombo);
//                                    }
//                                } else {
//                                    setText(null);
//                                }
//                            }
//                        };
//                        return cell;
//                    }
//                });
//        cboFornecedor.setCellFactory(
//                new Callback<ListView<TabEmpresaVO>, ListCell<TabEmpresaVO>>() {
//                    @Override
//                    public ListCell<TabEmpresaVO> call(ListView<TabEmpresaVO> param) {
//                        final ListCell<TabEmpresaVO> cell = new ListCell<TabEmpresaVO>() {
//                            @Override
//                            public void updateItem(TabEmpresaVO item, boolean empty) {
//                                super.updateItem(item, empty);
//                                if (item != null) {
//                                    if (getIndex() == -1) {
//                                        setText(item.toString());
//                                    } else {
//                                        String textoCombo = "";
//                                        for (String detalheFornecedor : item.getDetalheFornecedor().split(";")) {
//                                            if (textoCombo != "")
//                                                textoCombo += "\r\n";
//                                            textoCombo += detalheFornecedor;
//                                        }
//                                        setText(textoCombo);
//                                    }
//                                } else {
//                                    setText(null);
//                                }
//                            }
//                        };
//                        return cell;
//                    }
//                });
//        cboFreteTransportadora.setCellFactory(
//                new Callback<ListView<TabEmpresaVO>, ListCell<TabEmpresaVO>>() {
//                    @Override
//                    public ListCell<TabEmpresaVO> call(ListView<TabEmpresaVO> param) {
//                        final ListCell<TabEmpresaVO> cell = new ListCell<TabEmpresaVO>() {
//                            @Override
//                            public void updateItem(TabEmpresaVO item, boolean empty) {
//                                super.updateItem(item, empty);
//                                if (item != null) {
//                                    if (getIndex() == -1) {
//                                        setText(item.toString());
//                                    } else {
//                                        String textoCombo = "";
//                                        for (String detalheTransportadora : item.getDetalheTransportadora().split(";")) {
//                                            if (textoCombo != "")
//                                                textoCombo += "\r\n";
//                                            textoCombo += detalheTransportadora;
//                                        }
//                                        setText(textoCombo);
//                                    }
//                                } else {
//                                    setText(null);
//                                }
//                            }
//                        };
//                        return cell;
//                    }
//                });
    }

    @Override
    public void escutarTeclas() {
//        String tituloTab = ViewEntradaProduto.getTituloJanela();
//
//        eventEntradaProduto = new EventHandler<KeyEvent>() {
//            @Override
//            public void handle(KeyEvent event) {
//                if (!(ControllerPrincipal.ctrlPrincipal.getTabAtual().equals(tituloTab)))
//                    return;
//                switch (event.getCode()) {
////                    case F1:
////                        if (!getStatusBarFormulario().contains(event.getCode().toString())) break;
////                        setStatusFormulario("Incluir");
////                        setTtvEmpresaVO(new TabEmpresaVO());
////                        break;
////                    case F2:
////                        if (!getStatusBarFormulario().contains(event.getCode().toString())) break;
////                        guardarEndereco(listEndereco.getSelectionModel().getSelectedIndex());
////                        if (!validarDadosCadastrais()) break;
////
////                        salvarEmpresa();
////                        setTtvEmpresaVO(new TabEmpresaDAO().getEmpresaVO(getTtvEmpresaVO().getId()));
////                        tabEmpresaVOObservableList.set(indexObservableEmpresa, getTtvEmpresaVO());
////
////                        setStatusFormulario("Pesquisa");
////                        carregarPesquisaEmpresa(txtPesquisa.getText());
////                        break;
////                    case F3:
////                        if (!getStatusBarFormulario().contains(event.getCode().toString())) break;
////                        switch (getStatusFormulario().toLowerCase()) {
////                            case "incluir":
////                                if (new AlertMensagem("Cancelar inclusão", USUARIO_LOGADO_APELIDO
////                                        + ", deseja cancelar inclusão no cadastro de empresa?",
////                                        "ic_cadastro_empresas_white_24dp.png").getRetornoAlert_YES_NO().get() == ButtonType.NO)
////                                    return;
////                                PersonalizarCampo.clearField((AnchorPane) tpnDadoCadastral.getContent());
////                                break;
////                            case "editar":
////                                if (new AlertMensagem("Cancelar edição", USUARIO_LOGADO_APELIDO
////                                        + ", deseja cancelar edição do cadastro de empresa?",
////                                        "ic_cadastro_empresas_white_24dp.png").getRetornoAlert_YES_NO().get() == ButtonType.NO)
////                                    return;
////                                setTtvEmpresaVO(new TabEmpresaDAO().getEmpresaVO(getTtvEmpresaVO().getId()));
////                                tabEmpresaVOObservableList.set(indexObservableEmpresa, getTtvEmpresaVO());
////                                break;
////                        }
////                        setStatusFormulario("Pesquisa");
////                        carregarPesquisaEmpresa(txtPesquisa.getText());
////                        exibirDadosEmpresa();
////                        break;
////                    case F4:
////                        if ((!getStatusBarFormulario().contains(event.getCode().toString())) || (getTtvEmpresaVO() == null))
////                            break;
////                        indexObservableEmpresa = tabEmpresaVOObservableList.indexOf(getTtvEmpresaVO());
////                        setStatusFormulario("Editar");
////                        break;
////                    case F5:
////                        if (!getStatusBarFormulario().contains(event.getCode().toString())) break;
////                        guardarEndereco(listEndereco.getSelectionModel().getSelectedIndex());
////                        if (!validarDadosCadastrais()) break;
////
////                        salvarEmpresa();
////                        setTtvEmpresaVO(new TabEmpresaDAO().getEmpresaVO(getTtvEmpresaVO().getId()));
////                        tabEmpresaVOObservableList.set(indexObservableEmpresa, getTtvEmpresaVO());
////
////                        setStatusFormulario("Pesquisa");
////                        carregarPesquisaEmpresa(txtPesquisa.getText());
////                        exibirDadosEmpresa();
////                        ttvEmpresa.requestFocus();
////                        break;
//                    case F6:
//                        if (!getStatusBarFormulario().contains(event.getCode().toString())) break;
//                        txtChaveNfe.requestFocus();
//                        break;
//                    case F7:
//                        if (!getStatusBarFormulario().contains(event.getCode().toString())) break;
//                        txtPesquisaProduto.requestFocus();
//                        break;
////                    case F8:
////                        if (!getStatusBarFormulario().contains(event.getCode().toString())) break;
////                        cboFiltroPesquisa.requestFocus();
////                        break;
//                    case F12:
//                        if (!getStatusBarFormulario().contains(event.getCode().toString())) break;
//                        fecharTab(tituloTab);
//                        break;
////                    case HELP:
////                        if (getStatusFormulario().toLowerCase().equals("pesquisa")) return;
////                        keyInsert();
////                        break;
////                    case DELETE:
////                        if (getStatusFormulario().toLowerCase().equals("pesquisa")) return;
////                        keyDelete();
////                        break;
//                }
//            }
//        };
//
//        ControllerPrincipal.ctrlPrincipal.painelViewPrincipal.addEventHandler(KeyEvent.KEY_PRESSED, eventEntradaProduto);
//
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
//        preencherObjetos();
//        fatorarObjetos();
//        escutarTeclas();
//        setStatusFormulario("Nf-e");
//        Platform.runLater(() -> {
//            cboLojaDestino.requestFocus();
//        });

    }
//
//    EventHandler<KeyEvent> eventEntradaProduto;
//
//    int indexObservableEntradaProduto = 0;
//
//    ObservableList<TabProdutoVO> produtoVOObservableList;
//    FilteredList<TabProdutoVO> produtoVOFilteredList;
//
//    List<Pair> listaTarefas;
//
//    int qtdRegistrosLocalizados = 0;
//    String statusFormulario, statusBarFormulario;
//
//    static String STATUSBARNFE = "[F1-Novo]  [F4-Lancar itens]  [F6-Chave nfe]  [F12-Sair]  ";
//    static String STATUSBARLANCANDO = "[F2-Finaliza nfe]  [F3-Excluir nfe]  [F5-Salvar nfe temporariamente]  [F7-Pesquisar produto]  [F8-Itens da nfe]  [F12-Sair]  ";
//
//    public int getQtdRegistrosLocalizados() {
//        return qtdRegistrosLocalizados;
//    }
//
//    public void setQtdRegistrosLocalizados(int qtdRegistrosLocalizados) {
//        this.qtdRegistrosLocalizados = qtdRegistrosLocalizados;
//        atualizaLblQtdRegistroLocalizado();
//    }
//
//    public String getStatusFormulario() {
//        return statusFormulario;
//    }
//
//    public void setStatusFormulario(String statusFormulario) {
//        this.statusFormulario = statusFormulario;
//        atualizaLblQtdRegistroLocalizado();
//        setStatusBarFormulario(statusFormulario);
//    }
//
//    public String getStatusBarFormulario() {
//        return statusBarFormulario;
//    }
//
//    public void setStatusBarFormulario(String statusFormulario) {
//        if (statusFormulario.toLowerCase().contains("nf-e")) {
//            PersonalizarCampo.desabilitaCampos((AnchorPane) tpnDadoNfe.getContent(), false);
//            PersonalizarCampo.desabilitaCampos((AnchorPane) tpnItensTotaisNfe.getContent(), true);
//            PersonalizarCampo.clearField(painelViewEntradaProduto);
//            cboLojaDestino.requestFocus();
//            this.statusBarFormulario = STATUSBARNFE;
//        } else if (statusFormulario.toLowerCase().contains("lancando")) {
//            PersonalizarCampo.desabilitaCampos((AnchorPane) tpnDadoNfe.getContent(), true);
//            PersonalizarCampo.desabilitaCampos((AnchorPane) tpnItensTotaisNfe.getContent(), false);
//            txtPesquisaProduto.requestFocus();
//            this.statusBarFormulario = STATUSBARLANCANDO;
//        }
//        ControllerPrincipal.ctrlPrincipal.atualizarTeclasStatusBar(statusBarFormulario);
//    }
//
//    void atualizaLblQtdRegistroLocalizado() {
//        lblRegistrosLocalizados.setText("[" + getStatusFormulario() + "]  " + String.valueOf(getQtdRegistrosLocalizados()) + " registro(s) localizado(s).");
//    }
//
//    void criarTabelas() {
//        listaTarefas.add(new Pair("criarTabelaProduto", "criando tabela produto"));
//    }
//
//    void carregaListas() {
//    }
//
//    void preencherCombos() {
//        listaTarefas.add(new Pair("carregarLojaDestino", "carregando lista lojas"));
//        listaTarefas.add(new Pair("carregarFornecedor", "carregando lista fornecedor"));
//        listaTarefas.add(new Pair("carregarTributo", "carregando lista tributo"));
//        listaTarefas.add(new Pair("carregarTomadorServico", "carregando lista tomador serviço"));
//        listaTarefas.add(new Pair("carregarModelo", "carregando lista modelo"));
//        listaTarefas.add(new Pair("carregarSituacaoTributaria", "carregando lista situação tributária"));
//        listaTarefas.add(new Pair("carregarTransportadora", "carregando lista transportadora"));
//        listaTarefas.add(new Pair("carregarListaProduto", "carregando lista de produtos"));
//    }
//
//    void preencherTabelas() {
//        listaTarefas.add(new Pair("preencherTabelaProduto", "preenchendo tabela produto"));
//    }
//
//    public void carregarLojaDestino() {
//        cboLojaDestino.getItems().clear();
//        cboLojaDestino.getItems().setAll(new TabLojaDAO().getLojaVOList());
//        //cboLojaDestino.getSelectionModel().select(0);
//    }
//
//    public void carregarFornecedor() {
//        cboFornecedor.getItems().clear();
//        cboFornecedor.getItems().setAll(new TabEmpresaDAO().getEmpresaVOList());
//        //cboFornecedor.getSelectionModel().select(0);
//    }
//
//    public void carregarTributo() {
//        List<SisFiscalSefazTributoVO> sefazTributoVOList = new SisFiscalSefazTributoDAO().getFiscalSefazTributoVOList();
//
//        cboFiscalTributo.getItems().clear();
//        cboFiscalTributo.getItems().setAll(sefazTributoVOList);
//        //cboFiscalTributo.getSelectionModel().select(0);
//
//        cboFreteFiscalTributo.getItems().clear();
//        cboFreteFiscalTributo.getItems().setAll(sefazTributoVOList);
//        //cboFreteFiscalTributo.getSelectionModel().select(0);
//    }
//
//    public void carregarTomadorServico() {
//        cboFreteTomadorServico.getItems().clear();
//        cboFreteTomadorServico.getItems().setAll(new SisFreteTomadorDAO().getSisFreteTomadorVOList());
//        //cboFreteTomadorServico.getSelectionModel().select(0);
//    }
//
//    public void carregarModelo() {
//        cboFreteModeloCte.getItems().clear();
//        cboFreteModeloCte.getItems().setAll(new SisFiscalModelonfeCteDAO().getSisFiscalModeloNfeCteVOList());
//        //cboFreteModeloCte.getSelectionModel().select(0);
//    }
//
//    public void carregarSituacaoTributaria() {
//        cboFreteSistuacaoTributaria.getItems().clear();
//        cboFreteSistuacaoTributaria.getItems().setAll(new SisFreteSituacaoTributariaDAO().getSisFreteSituacaoTributariaVOList());
//        //cboFreteSistuacaoTributaria.getSelectionModel().select(0);
//    }
//
//    public void carregarTransportadora() {
//        cboFreteTransportadora.getItems().clear();
//        cboFreteTransportadora.getItems().setAll(new TabEmpresaDAO().getEmpresaVOList());
//        //cboFreteTransportadora.getSelectionModel().select(0);
//    }
//
//    public void carregarListaProduto() {
//        produtoVOObservableList = FXCollections.observableArrayList(new TabProdutoDAO().getProdutoVOList());
//    }
//
//    public void preencherTabelaProduto() {
//        try {
//            if (produtoVOFilteredList == null)
//                carregarPesquisaProduto(txtPesquisaProduto.getText());
//            setQtdRegistrosLocalizados(produtoVOFilteredList.size());
//            final TreeItem<TabProdutoVO> root = new RecursiveTreeItem<TabProdutoVO>(produtoVOFilteredList, RecursiveTreeObject::getChildren);
//            ttvProduto.getColumns().setAll(TabModel.getColunaIdProduto(), TabModel.getColunaCodigo(),
//                    TabModel.getColunaDescricao(), TabModel.getColunaUndCom(), TabModel.getColunaVarejo(),
//                    TabModel.getColunaPrecoFabrica(), TabModel.getColunaPrecoConsumidor(),
//                    TabModel.getColunaSituacaoSistema(), TabModel.getColunaQtdEstoque());
//            ttvProduto.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
//            ttvProduto.setRoot(root);
//            ttvProduto.setShowRoot(false);
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//    }
//
//    void carregarPesquisaProduto(String strPesq) {
//        String busca = strPesq.toLowerCase().trim();
//
//        produtoVOFilteredList = new FilteredList<TabProdutoVO>(produtoVOObservableList, produto -> true);
//        produtoVOFilteredList.setPredicate(produto -> {
//            if (produto.getCodigo().toLowerCase().contains(busca)) return true;
//            if (produto.getDescricao().toLowerCase().contains(busca)) return true;
//            if (produto.getProdutoEanVOList().size() > 0)
//                for (TabProdutoEanVO prodEan : produto.getProdutoEanVOList())
//                    if (prodEan.getDescricao().toLowerCase().contains(busca)) return true;
//            return false;
//        });
//        preencherTabelaProduto();
//    }
//
//    boolean validarDadoNfe() {
//        boolean result = true;
//        if (cboLojaDestino.getSelectionModel().getSelectedIndex() < 0)
//            result = false;
//
//        return result;
//    }
//
//    void fecharTab(String tituloTab) {
//        for (int i = 0; i < ControllerPrincipal.ctrlPrincipal.tabPaneViewPrincipal.getTabs().size(); i++)
//            if (ControllerPrincipal.ctrlPrincipal.tabPaneViewPrincipal.getTabs().get(i).getText().toLowerCase().equals(tituloTab.toLowerCase())) {
//                ControllerPrincipal.ctrlPrincipal.fecharTab(i);
//                ControllerPrincipal.ctrlPrincipal.painelViewPrincipal.removeEventHandler(KeyEvent.KEY_PRESSED, eventEntradaProduto);
//            }
//    }
}
