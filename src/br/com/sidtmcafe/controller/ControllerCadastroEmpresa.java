package br.com.sidtmcafe.controller;

import br.com.sidtmcafe.componentes.Tarefa;
import br.com.sidtmcafe.service.FormatadorDeDados;
import br.com.sidtmcafe.service.ValidadorDeDados;
import br.com.sidtmcafe.interfaces.FormularioModelo;
import br.com.sidtmcafe.model.dao.*;
import br.com.sidtmcafe.model.vo.*;
import com.jfoenix.controls.*;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.util.Pair;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ControllerCadastroEmpresa extends FormatadorDeDados implements Initializable, FormularioModelo {

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
        carregaListas();
        preencherCombos();
        preencherTabelas();

        new Tarefa().tarefaAbreCadastroEmpresa(this, listaTarefas);
    }

    @Override
    public void fatorarObjetos() {

    }

    @Override
    public void escutarTeclas() {
        cboEndUF.getSelectionModel().selectedItemProperty().addListener((ov, o, n) -> {
            if (n != null)
                carregarPesquisaMunicipios(n.getSigla());
        });

        txtCNPJ.addEventHandler(KeyEvent.KEY_RELEASED, event -> {
            if (event.getCode() == KeyCode.ENTER) {
                if (txtCNPJ.getText().length() != 11 && txtCNPJ.getText().length() != 14) {
                    System.out.println("número invalido");
                    return;
                }
                if (!ValidadorDeDados.isCnpjCpfValido(txtCNPJ.getText())) {
                    switch (txtCNPJ.getText().length()) {
                        case 11:
                            System.out.println("cpf informado é inválido!");
                            break;
                        case 14:
                            System.out.println("cnpj informado é inválido!!");
                            break;
                    }
                    return;
                }
                System.out.println("OK");
            }
        });

        txtEndCEP.addEventHandler(KeyEvent.KEY_RELEASED, event -> {
            if (event.getCode() == KeyCode.ENTER) {
                if (txtEndCEP.getText().length() != 8) {
                    System.out.println("número invalido");
                    return;
                }
                String strCep = txtEndCEP.getText().replaceAll("[\\-/.]", "");
                WsCepPostmonVO wsCepPostmonVO = new WsCepPostmonDAO().getCepPostmonVO(strCep);
                exibirDadosEndereco(new TabEnderecoDAO().getEnderecoVO(wsCepPostmonVO, -1, 1));
            }
        });
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        preencherObjetos();
        fatorarObjetos();
        escutarTeclas();
    }

    TabEmpresaVO tabEmpresaVO;
    List<Pair> listaTarefas;

    ObservableList<TabEmpresaVO> empresaVOObservableList;
    FilteredList<TabEmpresaVO> empresaVOFilteredList;
    ObservableList<SisMunicipioVO> municipioVOObservableList;
    FilteredList<SisMunicipioVO> municipioVOFilteredList;
    List<SisTipoEnderecoVO> tipoEnderecoVOList;
    List<SisTelefoneOperadoraVO> telefoneOperadoraVOList;

    JFXTreeTableColumn<TabEmpresaVO, Integer> colunaId;
    JFXTreeTableColumn<TabEmpresaVO, String> colunaCnpj;
    JFXTreeTableColumn<TabEmpresaVO, String> colunaRazao;
    JFXTreeTableColumn<TabEmpresaVO, String> colunaFantasia;
    JFXTreeTableColumn<TabEmpresaVO, String> colunaEndereco;
    JFXTreeTableColumn<TabEmpresaVO, Boolean> colunaIsCliente;
    JFXTreeTableColumn<TabEmpresaVO, Boolean> colunaIsFornecedor;
    JFXTreeTableColumn<TabEmpresaVO, Boolean> colunaIsTransportadora;
    JFXTreeTableColumn<TabEmpresaVO, Boolean> colunaAtivo;

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
        //preencherTabelaEmpresa();
    }

    public void criarTabelaEmpresa() {
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
        colunaCnpj.setCellValueFactory(param -> new SimpleStringProperty(getFormat(param.getValue().getValue().getCnpj(), "cnpj")));

        Label lblRazao = new Label("Razão / Nome");
        lblRazao.setPrefWidth(300);
        colunaRazao = new JFXTreeTableColumn<TabEmpresaVO, String>();
        colunaRazao.setGraphic(lblRazao);
        colunaRazao.setPrefWidth(300);
        colunaRazao.setCellValueFactory(param -> param.getValue().getValue().razaoProperty());

//        colunaFantasia;
//        colunaEndereco;
//        colunaIsCliente;
//        colunaIsFornecedor;
//        colunaIsTransportadora;
//        colunaAtivo;


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
            final TreeItem<TabEmpresaVO> root = new RecursiveTreeItem<TabEmpresaVO>(empresaVOFilteredList, RecursiveTreeObject::getChildren);
            ttvEmpresa.getColumns().setAll(colunaId, colunaCnpj, colunaRazao); //colunaFantasia, colunaEndereco, colunaIsCliente, colunaIsFornecedor, colunaIsTransportadora, colunaAtivo);
            ttvEmpresa.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
//            ttvEmpresa.getStyleClass().add("tabEmpresa");
            ttvEmpresa.setRoot(root);
            ttvEmpresa.setShowRoot(false);
//            System.out.println("ttvEmpresa.getStyleableParent(): " + ttvEmpresa.getStyleableParent());
//            System.out.println("ttvEmpresa.getStylesheets(): " + ttvEmpresa.getStylesheets());
//            System.out.println("ttvEmpresa.getStyleClass(): " + ttvEmpresa.getStyleClass());
//            System.out.println("ttvEmpresa.getStyle(): " + ttvEmpresa.getStyle());
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
        empresaVOFilteredList = new FilteredList<TabEmpresaVO>(empresaVOObservableList, m -> true);
        empresaVOFilteredList.setPredicate(empresa -> {
            String busca = strPesq.toLowerCase().trim();
            int filtro = cboFiltroPesquisa.getSelectionModel().getSelectedIndex();
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
    }

    void carregarPesquisaMunicipios(String siglaUF) {
        municipioVOFilteredList = new FilteredList<SisMunicipioVO>(municipioVOObservableList, m -> true);
        municipioVOFilteredList.setPredicate(municipio -> municipio.getUfVO().getSigla() == siglaUF);
        preencherCboEndMunicipio();
    }

    void exibirDadosEndereco(TabEnderecoVO enderecoVO) {
        txtEndCEP.setText(enderecoVO.getCep());
        txtEndLogradouro.setText(enderecoVO.getLogradouro());
        txtEndNumero.setText("");
        txtEndComplemento.setText(enderecoVO.getComplemento());
        txtEndBairro.setText(enderecoVO.getBairro());
        cboEndUF.getSelectionModel().select(enderecoVO.getUfVO());
        cboEndMunicipio.getSelectionModel().select(enderecoVO.getMunicipioVO());
        txtEndPontoReferencia.setText(enderecoVO.getPontoReferencia());
    }

}
