package br.com.sidtmcafe.controller;

import br.com.sidtmcafe.componentes.Variavel;
import br.com.sidtmcafe.interfaces.Constants;
import br.com.sidtmcafe.interfaces.FormularioModelo;
import br.com.sidtmcafe.model.dao.TabColaboradorDAO;
import br.com.sidtmcafe.model.vo.TabColaboradorVO;
import br.com.sidtmcafe.service.PasswordUtil;
import br.com.sidtmcafe.service.ServiceError;
import br.com.sidtmcafe.view.ViewLogin;
import br.com.sidtmcafe.view.ViewPrincipal;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXPasswordField;
import javafx.application.Platform;
import javafx.fxml.Initializable;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.util.Callback;

import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Locale;
import java.util.ResourceBundle;

public class ControllerLogin extends Variavel implements Initializable, FormularioModelo, Constants {
    public AnchorPane painelViewLogin;
    public JFXComboBox cboUsuarioLogin;
    public JFXPasswordField pswUsuarioSenha;
    public JFXButton btnCancela;
    public JFXButton btnOK;

    @Override
    public void fechar() {
        ViewLogin.getStage().close();
    }

    @Override
    public void criarObjetos() {

    }

    @Override
    public void preencherObjetos() {
        cboUsuarioLogin.setPromptText("Selecione usuário");
        cboUsuarioLogin.getItems().addAll(new TabColaboradorDAO().getTabColaboradorVOList());
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
        painelViewLogin.addEventHandler(KeyEvent.KEY_PRESSED, event -> {
            if (event.getCode() == KeyCode.ENTER & btnOK.isDisable()) {
                if (cboUsuarioLogin.isFocused()) {
                    pswUsuarioSenha.requestFocus();
                } else {
                    cboUsuarioLogin.requestFocus();
                }
            }
            if (event.getCode() == KeyCode.F12) {
                btnCancela.fire();
            }
        });

        btnCancela.setOnAction(event -> {
            fechar();
        });

        btnOK.setDisable(true);
        btnOK.setOnAction(event -> {
            executaLogin((TabColaboradorVO) cboUsuarioLogin.getSelectionModel().getSelectedItem());
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
        Platform.runLater(()->{
            Locale.setDefault(MY_LOCALE);
        });

    }

    void habilitarBotaoOK() {
        btnOK.setDisable((cboUsuarioLogin.getSelectionModel().getSelectedIndex() < 0) || (pswUsuarioSenha.getText().length() == 0));
    }

    void executaLogin(TabColaboradorVO colaboradorVO) {
        boolean passwordMatch = PasswordUtil.verifyUserPassword(pswUsuarioSenha.getText(),
                colaboradorVO.getSenha(), colaboradorVO.getSenhaSalt());
        if (passwordMatch) {
            criarVariaveisSistema_UsuarioLogado(colaboradorVO);
            fechar();
            new ViewPrincipal().openViewPrincipal(false);
        } else {
            tremeLogin();
        }
    }

    void tremeLogin() {
        ServiceError serviceError = new ServiceError();
        serviceError.setStage(ViewLogin.getStage());
        Thread thread = new Thread(serviceError);
        thread.start();
    }

    void criarVariaveisSistema_UsuarioLogado(TabColaboradorVO colaboradorVO) {
        USUARIO_LOGADO_ID = String.valueOf(colaboradorVO.getId());
        USUARIO_LOGADO_NOME = colaboradorVO.getNome();
        USUARIO_LOGADO_APELIDO = colaboradorVO.getApelido();
        DATA_HORA = LocalDateTime.now();
        DATA_HORA_STR = DATA_HORA.format(DTF_DATAHORA);
        USUARIO_LOGADO_DATA = LocalDate.now();
        USUARIO_LOGADO_DATA_STR = USUARIO_LOGADO_DATA.format(DTF_DATA);
        USUARIO_LOGADO_HORA = LocalTime.now();
        USUARIO_LOGADO_HORA_STR = USUARIO_LOGADO_HORA.format(DTF_HORA);

    }
}
