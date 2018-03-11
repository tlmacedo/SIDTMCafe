package br.com.sidtmcafe.controller;

import br.com.sidtmcafe.componentes.Tarefa;
import br.com.sidtmcafe.componentes.Variavel;
import br.com.sidtmcafe.interfaces.FormularioModelo;
import br.com.sidtmcafe.model.vo.TabProdutoVO;
import br.com.sidtmcafe.service.ExecutaComandoTecladoMouse;
import br.com.sidtmcafe.service.PersonalizarCampo;
import com.jfoenix.controls.*;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TitledPane;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
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
    public JFXComboBox cboLojaDestino;
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
        //preencherCombos();
        //preencherTabelas();

        new Tarefa().tarefaAbreEntradaProduto(this, listaTarefas);

        PersonalizarCampo.fieldMaxLen(painelViewEntradaProduto);
        PersonalizarCampo.maskFields(painelViewEntradaProduto);
    }

    @Override
    public void fatorarObjetos() {

    }

    @Override
    public void escutarTeclas() {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        preencherObjetos();
        fatorarObjetos();
        escutarTeclas();
        //setStatusFormulario("Pesquisa");
        Platform.runLater(() -> {
            //painelViewEntradaProduto.fireEvent(ExecutaComandoTecladoMouse.pressTecla(KeyCode.F7));
        });

    }

    int indexObservableEntradaProduto = 0;
    ObservableList<TabProdutoVO> produtoVOObservableList;
    FilteredList<TabProdutoVO> produtoVOFilteredList;

    List<Pair> listaTarefas;

    void criarTabelas() {
        listaTarefas.add(new Pair("criarTabelaProduto", "criando tabela produto"));
    }

    void carregaListas() {
        listaTarefas.add(new Pair("carregarLojaDestino", "carregando lista lojas"));
        listaTarefas.add(new Pair("carregarFornecedor", "carregando lista fornecedor"));
        listaTarefas.add(new Pair("carregarTributo", "carregando lista tributo"));
        listaTarefas.add(new Pair("carregarTomadorServico", "carregando lista tomador serviço"));
        listaTarefas.add(new Pair("carregarModelo", "carregando lista modelo"));
        listaTarefas.add(new Pair("carregarSituacaoTributaria", "carregando lista situação tributária"));
        listaTarefas.add(new Pair("carregarTransportadora", "carregando lista transportadora"));
        listaTarefas.add(new Pair("carregarListaProduto", "carregando lista de produtos"));
    }


}
