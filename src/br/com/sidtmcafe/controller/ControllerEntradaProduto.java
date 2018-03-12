package br.com.sidtmcafe.controller;

import br.com.sidtmcafe.componentes.Tarefa;
import br.com.sidtmcafe.componentes.Variavel;
import br.com.sidtmcafe.interfaces.FormularioModelo;
import br.com.sidtmcafe.model.dao.*;
import br.com.sidtmcafe.model.model.TabModel;
import br.com.sidtmcafe.model.vo.SisFiscalSefazTributoVO;
import br.com.sidtmcafe.model.vo.TabLojaVO;
import br.com.sidtmcafe.model.vo.TabProdutoEanVO;
import br.com.sidtmcafe.model.vo.TabProdutoVO;
import br.com.sidtmcafe.service.ExecutaComandoTecladoMouse;
import br.com.sidtmcafe.service.PersonalizarCampo;
import com.jfoenix.controls.*;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.util.Callback;
import javafx.util.Pair;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import static br.com.sidtmcafe.interfaces.Constants.DECIMAL_FORMAT;

public class ControllerEntradaProduto extends Variavel implements Initializable, FormularioModelo {
    public AnchorPane painelViewEntradaProduto;
    public TitledPane tpnDadoNfe;
    public TitledPane tpnDetalheNfe;
    public JFXComboBox<TabLojaVO> cboLojaDestino;
    public JFXTextField txtChaveNfe;
    public JFXTextField txtNumeroNfe;
    public JFXTextField txtNumeroSerie;
    public JFXComboBox cboFornecedor;
    public JFXDatePicker dtpEmissaoNfe;
    public JFXDatePicker dtpEntradaNfe;
    public TitledPane tpnImpostoNfe;
    public JFXTextField txtFiscalNumeroControle;
    public JFXTextField txtFiscalDocumentoOrigem;
    public JFXComboBox cboFiscalTributo;
    public JFXTextField txtFiscalVlrTributo;
    public JFXTextField txtFiscalVlrMulta;
    public JFXTextField txtFiscalVlrJuros;
    public JFXTextField txtFiscalVlrTaxa;
    public JFXTextField txtFiscalVlrTotalTributo;
    public JFXTextField txtFiscalVlrPercentualTributo;
    public TitledPane tpnDetalheFrete;
    public JFXTextField txtFreteChaveCte;
    public JFXComboBox cboFreteTomadorServico;
    public JFXTextField txtFreteNumeroCte;
    public JFXTextField txtFreteSerieCte;
    public JFXComboBox cboFreteModeloCte;
    public JFXComboBox cboFreteSistuacaoTributaria;
    public JFXComboBox cboFreteTransportadora;
    public JFXDatePicker dtpFreteEmissao;
    public JFXTextField txtFreteVlrNfe;
    public JFXTextField txtFreteQtdVolume;
    public JFXTextField txtFretePesoBruto;
    public JFXTextField txtFreteVlrBruto;
    public JFXTextField txtFreteVlrImposto;
    public JFXTextField txtFreteVlrLiquido;
    public TitledPane tpnFreteImposto;
    public JFXTextField txtFreteFiscalNumeroControle;
    public JFXTextField txtFreteFiscalImpostoDocumentoOrigem;
    public JFXComboBox cboFreteFiscalTributo;
    public JFXTextField txtFreteFiscalVlrTributo;
    public JFXTextField txtFreteFiscalVlrMulta;
    public JFXTextField txtFreteFiscalVlrJuros;
    public JFXTextField txtFreteFiscalVlrTaxa;
    public JFXTextField txtFreteFiscalVlrTotalTributo;
    public JFXTextField txtFreteFiscalVlrPercentualTributo;
    public TitledPane tpnItensTotaisNfe;
    public TitledPane tpnCadastroProduto;
    public JFXTextField txtPesquisaProduto;
    public JFXTreeTableView ttvProduto;
    public Label lblRegistrosLocalizados;
    public TitledPane tpnItensNfe;
    public JFXTreeTableView ttvItensNfe;

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

        new Tarefa().tarefaAbreEntradaProduto(this, listaTarefas);

        PersonalizarCampo.fieldMaxLen(painelViewEntradaProduto);
        PersonalizarCampo.maskFields(painelViewEntradaProduto);
    }

    @Override
    public void fatorarObjetos() {
        cboLojaDestino.setCellFactory(
                new Callback<ListView<TabLojaVO>, ListCell<TabLojaVO>>() {
                    @Override
                    public ListCell<TabLojaVO> call(ListView<TabLojaVO> param) {
                        final ListCell<TabLojaVO> cell = new ListCell<TabLojaVO>() {
                            @Override
                            public void updateItem(TabLojaVO item, boolean empty) {
                                super.updateItem(item, empty);
                                if (item != null) {
                                    if (getIndex() == -1) {
                                        setText(item.toString());
                                    } else {
                                        String textoCombo = "";
                                        for (String detalheLoja : item.getDetalheLoja().split(";")) {
                                            if (textoCombo != "")
                                                textoCombo += "\r\n";
                                            textoCombo += detalheLoja;
                                        }
                                        setText(textoCombo);
                                    }
                                } else {
                                    setText(null);
                                }
                            }
                        };
                        return cell;
                    }
                });
    }

    @Override
    public void escutarTeclas() {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        preencherObjetos();
        fatorarObjetos();
        escutarTeclas();
        Platform.runLater(() -> {
            //painelViewEntradaProduto.fireEvent(ExecutaComandoTecladoMouse.pressTecla(KeyCode.F7));
        });

    }

    int indexObservableEntradaProduto = 0;
    int qtdRegistrosLocalizados = 0;
    ObservableList<TabProdutoVO> produtoVOObservableList;
    FilteredList<TabProdutoVO> produtoVOFilteredList;

    List<Pair> listaTarefas;

    public int getQtdRegistrosLocalizados() {
        return qtdRegistrosLocalizados;
    }

    public void setQtdRegistrosLocalizados(int qtdRegistrosLocalizados) {
        this.qtdRegistrosLocalizados = qtdRegistrosLocalizados;
        atualizaLblRegistrosLocalizados();
    }

    void atualizaLblRegistrosLocalizados() {
        lblRegistrosLocalizados.setText(String.valueOf(getQtdRegistrosLocalizados()) + " registro(s) localizado(s).");
    }

    void criarTabelas() {
        listaTarefas.add(new Pair("criarTabelaProduto", "criando tabela produto"));
    }

    void carregaListas() {
    }

    void preencherCombos() {
        listaTarefas.add(new Pair("carregarLojaDestino", "carregando lista lojas"));
        listaTarefas.add(new Pair("carregarFornecedor", "carregando lista fornecedor"));
        listaTarefas.add(new Pair("carregarTributo", "carregando lista tributo"));
        listaTarefas.add(new Pair("carregarTomadorServico", "carregando lista tomador serviço"));
        listaTarefas.add(new Pair("carregarModelo", "carregando lista modelo"));
        listaTarefas.add(new Pair("carregarSituacaoTributaria", "carregando lista situação tributária"));
        listaTarefas.add(new Pair("carregarTransportadora", "carregando lista transportadora"));
        listaTarefas.add(new Pair("carregarListaProduto", "carregando lista de produtos"));
    }

    void preencherTabelas() {
        listaTarefas.add(new Pair("preencherTabelaProduto", "preenchendo tabela produto"));
    }

    public void carregarLojaDestino() {
        cboLojaDestino.getItems().clear();
        cboLojaDestino.getItems().setAll(new TabLojaDAO().getLojaVOList());
        //cboLojaDestino.getSelectionModel().select(0);
    }

    public void carregarFornecedor() {
        cboFornecedor.getItems().clear();
        cboFornecedor.getItems().setAll(new TabEmpresaDAO().getEmpresaVOList(false, true, false));
        //cboFornecedor.getSelectionModel().select(0);
    }

    public void carregarTributo() {
        List<SisFiscalSefazTributoVO> sefazTributoVOList = new SisFiscalSefazTributoDAO().getFiscalSefazTributoVOList();

        cboFiscalTributo.getItems().clear();
        cboFiscalTributo.getItems().setAll(sefazTributoVOList);
        //cboFiscalTributo.getSelectionModel().select(0);

        cboFreteFiscalTributo.getItems().clear();
        cboFreteFiscalTributo.getItems().setAll(sefazTributoVOList);
        //cboFreteFiscalTributo.getSelectionModel().select(0);
    }

    public void carregarTomadorServico() {
        cboFreteTomadorServico.getItems().clear();
        cboFreteTomadorServico.getItems().setAll(new SisFreteTomadorDAO().getSisFreteTomadorVOList());
        //cboFreteTomadorServico.getSelectionModel().select(0);
    }

    public void carregarModelo() {
        cboFreteModeloCte.getItems().clear();
        cboFreteModeloCte.getItems().setAll(new SisFiscalModelonfeCteDAO().getSisFiscalModeloNfeCteVOList());
        //cboFreteModeloCte.getSelectionModel().select(0);
    }

    public void carregarSituacaoTributaria() {
        cboFreteSistuacaoTributaria.getItems().clear();
        cboFreteSistuacaoTributaria.getItems().setAll(new SisFreteSituacaoTributariaDAO().getSisFreteSituacaoTributariaVOList());
        //cboFreteSistuacaoTributaria.getSelectionModel().select(0);
    }

    public void carregarTransportadora() {
        cboFreteTransportadora.getItems().clear();
        cboFreteTransportadora.getItems().setAll(new TabEmpresaDAO().getEmpresaVOList(false, false, true));
        //cboFreteTransportadora.getSelectionModel().select(0);
    }

    public void carregarListaProduto() {
        produtoVOObservableList = FXCollections.observableArrayList(new TabProdutoDAO().getProdutoVOList());
    }

    public void preencherTabelaProduto() {
        try {
            if (produtoVOFilteredList == null)
                carregarPesquisaProduto(txtPesquisaProduto.getText());
            setQtdRegistrosLocalizados(produtoVOFilteredList.size());
            final TreeItem<TabProdutoVO> root = new RecursiveTreeItem<TabProdutoVO>(produtoVOFilteredList, RecursiveTreeObject::getChildren);
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

}
