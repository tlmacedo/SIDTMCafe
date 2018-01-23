package br.com.sidtmcafe.controller;

import br.com.sidtmcafe.interfaces.FormularioModelo;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

public class ControllerCadastroEmpresa implements Initializable, FormularioModelo {
    public JFXComboBox cboFiltroPesquisa;
    public AnchorPane tabCadastroEmpresasPrincipal;
    public JFXTextField textCNPJ;
    public JFXCheckBox checkIsCliente;

    @Override
    public void fechar() {

    }

    @Override
    public void preencherObjetos() {

    }

    @Override
    public void fatorarObjetos() {

    }

    @Override
    public void escutarTeclas() {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("getStylesheets: " + checkIsCliente.getStylesheets());
        System.out.println("getStyleClass: " + checkIsCliente.getStyleClass());
        System.out.println("getStyle: " + checkIsCliente.getStyle());
    }
}
