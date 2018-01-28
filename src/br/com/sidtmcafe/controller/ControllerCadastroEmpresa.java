package br.com.sidtmcafe.controller;

import br.com.sidtmcafe.componentes.Tarefa;
import br.com.sidtmcafe.configuracao.ValidadorDeDados;
import br.com.sidtmcafe.interfaces.FormularioModelo;
import br.com.sidtmcafe.model.dao.SisMunicipioDAO;
import br.com.sidtmcafe.model.dao.SisSituacaoSistemaDAO;
import br.com.sidtmcafe.model.dao.SisUFDAO;
import br.com.sidtmcafe.model.dao.WsCepPostmonDAO;
import br.com.sidtmcafe.model.vo.SisMunicipioVO;
import br.com.sidtmcafe.model.vo.SisUFVO;
import com.jfoenix.controls.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TitledPane;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.util.Pair;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ControllerCadastroEmpresa implements Initializable, FormularioModelo {

    public AnchorPane painelViewCadastroEmpresa;
    public TitledPane tpnCadastroEmpresa;
    public JFXTextField txtPesquisa;
    public JFXTreeTableView ttvEmpresa;
    public JFXComboBox cboFiltroPesquisa;
    public Label lblRegistrosLocalizados;
    public TitledPane tpnDadosCadastrais;
    public JFXComboBox cboClassificacaoJuridica;
    public JFXTextField txtCNPJ;
    public JFXTextField txtIE;
    public JFXComboBox cboSituacaoSistema;
    public JFXTextField txtRazao;
    public JFXTextField txtFantasia;
    public JFXCheckBox chkIsCliente;
    public JFXCheckBox chkIsFornecedor;
    public JFXCheckBox chkIsTransportadora;
    public Label labelDataCadastro;
    public Label labelDataCadastroDiff;
    public Label labelDataAtualizacao;
    public Label labelDataAtualizacaoDiff;
    public JFXListView listEndereco;
    public JFXTextField txtEndCEP;
    public JFXTextField txtEndLogradouro;
    public JFXTextField txtEndNumero;
    public JFXTextField txtEndComplemento;
    public JFXTextField txtEndBairro;
    public JFXComboBox cboEndUF;
    public JFXComboBox cboEndMunicipio;
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
        preencherCombos();
    }

    @Override
    public void fatorarObjetos() {

    }

    @Override
    public void escutarTeclas() {
        cboEndUF.getSelectionModel().selectedItemProperty().addListener((ov, o, n) -> {
            municipioVOFilteredList = new FilteredList<SisMunicipioVO>(municipioVOObservableList, m -> true);
            if (n == null) {
                municipioVOFilteredList.setPredicate(m -> true);
            } else {
                municipioVOFilteredList.setPredicate(m -> m.getUf_id() == ((SisUFVO) n).getId());
            }
            preencherCboEndMunicipio();
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
                 new WsCepPostmonDAO().getCepPostmonVO(txtEndCEP.getText());

            }
        });
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        preencherObjetos();
        fatorarObjetos();
        escutarTeclas();
    }

    FilteredList<SisMunicipioVO> municipioVOFilteredList;
    ObservableList<SisMunicipioVO> municipioVOObservableList;

    void preencherCombos() {
        List<Pair> listaTarefas = new ArrayList<>();
        listaTarefas.add(new Pair("preencherCboEndUF", "preenchendo dados UF"));
        //listaTarefas.add(new Pair("carregarTodosMunicipios", "preenchendo dados de municipios"));
        listaTarefas.add(new Pair("preencherCboSituacaoSistema", "preenchendo dados situações do sistema"));
        listaTarefas.add(new Pair("preencherCboFiltroPesquisa", "preenchendo filtros pesquisa"));
        listaTarefas.add(new Pair("preencherCboClassificacaoJuridica", "preenchendo dados classificações jurídicas"));


        new Tarefa().tarefaAbreCadastroEmpresa(this, listaTarefas);
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

    public void preencherCboSituacaoSistema() {
        cboSituacaoSistema.getItems().clear();
        cboSituacaoSistema.getItems().setAll(new SisSituacaoSistemaDAO().getSituacaoSistemaVOList());
        cboSituacaoSistema.getSelectionModel().select(0);
    }

    public void preencherCboEndUF() {
        cboEndUF.getItems().clear();
        cboEndUF.getItems().add(new SisUFVO());
        cboEndUF.getItems().addAll(new SisUFDAO().getUfVOList());
        cboEndUF.getSelectionModel().select(0);
    }

    public void carregarTodosMunicipios() {
        municipioVOObservableList = FXCollections.observableArrayList(new SisMunicipioDAO().getMunicipioVOList());
    }

    void preencherCboEndMunicipio() {
        cboEndMunicipio.getItems().clear();
        cboEndMunicipio.getItems().addAll(municipioVOFilteredList);
        cboEndMunicipio.getSelectionModel().select(0);
    }


}
