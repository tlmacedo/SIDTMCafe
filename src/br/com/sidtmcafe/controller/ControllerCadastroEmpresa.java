package br.com.sidtmcafe.controller;

import br.com.sidtmcafe.interfaces.FormularioModelo;
import br.com.sidtmcafe.view.ViewCadastroEmpresa;
import com.jfoenix.controls.*;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.AnchorPane;

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

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        preencherObjetos();
    }

    void preencherCombos() {
        preencherCboFiltroPesquisa();
        preencherCboClassificacaoJuridica();
    }

    void preencherCboFiltroPesquisa() {
        cboFiltroPesquisa.getItems().clear();
        List<String> listFiltroPesquisa = new ArrayList<>();
        listFiltroPesquisa.add(0, "");
        listFiltroPesquisa.add(1, "Clientes");
        listFiltroPesquisa.add(2, "Fornecedores");
        listFiltroPesquisa.add(3, "Transportadoras");

        cboFiltroPesquisa.getItems().setAll(listFiltroPesquisa);
        cboFiltroPesquisa.getSelectionModel().select(0);

    }

    void preencherCboClassificacaoJuridica() {
        cboClassificacaoJuridica.getItems().clear();
        List<String> listClassifcacaoJuridica = new ArrayList<>();
        listClassifcacaoJuridica.add(0, "FÍSICA");
        listClassifcacaoJuridica.add(1, "JURÍDICA");

        cboClassificacaoJuridica.getItems().setAll(listClassifcacaoJuridica);
        cboClassificacaoJuridica.getSelectionModel().select(1);
    }


}
