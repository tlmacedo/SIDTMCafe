package br.com.sidtmcafe.controller;

import br.com.sidtmcafe.interfaces.FormularioModelo;
import br.com.sidtmcafe.model.dao.TabColaboradorDAO;
import br.com.sidtmcafe.model.vo.TabColaboradorVO;
import br.com.sidtmcafe.view.ViewLogin;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXPasswordField;
import javafx.fxml.Initializable;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.net.URL;
import java.util.ResourceBundle;

public class ControllerLogin implements Initializable, FormularioModelo {
    public JFXComboBox cboUsuarioLogin;
    public JFXPasswordField pswUsuarioSenha;
    public JFXButton btnCancela;
    public JFXButton btnOK;

    @Override
    public void fechar(Stage stage) {
        stage.close();
    }

    @Override
    public void preencherObjetos() {
        cboUsuarioLogin.setPromptText("Selecione usuário");
        cboUsuarioLogin.getItems().add(new TabColaboradorVO());
        cboUsuarioLogin.getItems().addAll(new TabColaboradorDAO().getColaboradorVOList());
    }

    @Override
    public void fatorarObjetos() {
        cboUsuarioLogin.setCellFactory(
                new Callback<ListView<TabColaboradorVO>, ListCell<TabColaboradorVO>>() {
                    @Override
                    public ListCell<TabColaboradorVO> call(ListView<TabColaboradorVO> param) {
                        final ListCell<TabColaboradorVO> cell = new ListCell<TabColaboradorVO>() {
                            @Override
                            public void updateItem(TabColaboradorVO item, boolean empty) {
                                super.updateItem(item, empty);
                                if (item != null) {
                                    if (getIndex() == -1) {
                                        setText(item.toString());
                                    } else {
                                        String textoCombo = "";
                                        for (String detalheColaborador : item.getDetalheColaborador().split(";")) {
                                            if (textoCombo != "")
                                                textoCombo += "\r\n";
                                            textoCombo += detalheColaborador;
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
        btnOK.setDisable(true);
        btnCancela.setOnAction(event -> {
            fechar(ViewLogin.getStage());
        });
        cboUsuarioLogin.getSelectionModel().selectedIndexProperty().addListener((ov, o, n) -> {
            if (n != o)
                habilitarBotaoOK();
        });
        pswUsuarioSenha.textProperty().addListener((ov, o, n) -> {
            if (n != o)
                habilitarBotaoOK();
        });
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        preencherObjetos();
        fatorarObjetos();
        escutarTeclas();
    }

    void habilitarBotaoOK() {
        btnOK.setDisable((cboUsuarioLogin.getSelectionModel().getSelectedIndex() <= 0) || (pswUsuarioSenha.getText().length() == 0));
    }
}
