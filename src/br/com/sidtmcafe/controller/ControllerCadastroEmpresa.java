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
import com.jfoenix.controls.*;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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
        thread.start();
        preencherCombos();
        preencherTabelas();

        new Tarefa().tarefaAbreCadastroEmpresa(this, listaTarefas);

        PersonalizarCampos.limpeza(painelViewCadastroEmpresa);
        PersonalizarCampos.mascara(painelViewCadastroEmpresa);
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

        txtPesquisa.textProperty().addListener((ov, o, n) -> {
            carregarPesquisaEmpresas(n);
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
    JFXTreeTableColumn<TabEmpresaVO, String> colunaRazao;
    JFXTreeTableColumn<TabEmpresaVO, String> colunaFantasia;
    JFXTreeTableColumn<TabEmpresaVO, String> colunaEndereco;
    JFXTreeTableColumn<TabEmpresaVO, Boolean> colunaIsCliente;
    JFXTreeTableColumn<TabEmpresaVO, Boolean> colunaIsFornecedor;
    JFXTreeTableColumn<TabEmpresaVO, Boolean> colunaIsTransportadora;
    JFXTreeTableColumn<TabEmpresaVO, Boolean> colunaAtivo;

    int qtdRegistrosLocalizados = 0;
    String statusFormulario = "Pesquisa";

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
        //listaTarefas.add(new Pair("carregarTodosMunicipios", "carregando listas de municipios"));
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
            ttvEmpresa.getColumns().setAll(colunaId, colunaCnpj, colunaRazao); //colunaFantasia, colunaEndereco, colunaIsCliente, colunaIsFornecedor, colunaIsTransportadora, colunaAtivo);
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
        if (enderecoVO == null)
            enderecoVO = new TabEnderecoVO();
        txtEndCEP.setText(FormatadorDeDados.getFormatado(enderecoVO.getCep(), "cep"));
        txtEndLogradouro.setText(enderecoVO.getLogradouro());
        txtEndNumero.setText("");
        txtEndComplemento.setText(enderecoVO.getComplemento());
        txtEndBairro.setText(enderecoVO.getBairro());
        cboEndUF.getSelectionModel().select(enderecoVO.getUfVO());
        cboEndMunicipio.getSelectionModel().select(enderecoVO.getMunicipioVO());
        txtEndPontoReferencia.setText(enderecoVO.getPontoReferencia());
    }

    public void teclaEspecial(KeyCode keyCode) {
        switch (keyCode) {
            case F4:

        }
    }

}
