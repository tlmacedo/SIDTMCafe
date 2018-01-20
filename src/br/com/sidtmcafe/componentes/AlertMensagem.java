package br.com.sidtmcafe.componentes;

import br.com.sidtmcafe.interfaces.Constants;
import com.jfoenix.controls.JFXTextArea;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.stage.StageStyle;
import javafx.util.Callback;

import java.util.Optional;


public class AlertMensagem implements Constants {
    Dialog dialog;
    DialogPane dialogPane;
    ProgressBar progressBar;
    ProgressIndicator progressIndicator;
    Label label;
    JFXTextArea jfxTextArea;
    Button botaoOk, botaoApply, botaoYes, botaoClose, botaoFinish, botaoNo, botaoCancel;


    public String cabecalho, promptText, strIco;
    public String resultCabecalho, resultPromptText;
    public Exception exceptionErr;


    public AlertMensagem(String cabecalho, String promptText, String strIco) {
        this.cabecalho = cabecalho;
        this.promptText = promptText;
        this.strIco = strIco;
    }

    public String getCabecalho() {
        return cabecalho;
    }

    public void setCabecalho(String cabecalho) {
        this.cabecalho = cabecalho;
    }

    public String getPromptText() {
        return promptText;
    }

    public void setPromptText(String promptText) {
        this.promptText = promptText;
    }

    public String getStrIco() {
        return strIco;
    }

    public void setStrIco(String strIco) {
        this.strIco = strIco;
    }

    public String getResultCabecalho() {
        return resultCabecalho;
    }

    public void setResultCabecalho(String resultCabecalho) {
        this.resultCabecalho = resultCabecalho;
    }

    public String getResultPromptText() {
        return resultPromptText;
    }

    public void setResultPromptText(String resultPromptText) {
        this.resultPromptText = resultPromptText;
    }

    public Exception getExceptionErr() {
        return exceptionErr;
    }

    public void setExceptionErr(Exception exceptionErr) {
        this.exceptionErr = exceptionErr;
    }

    void carregaDialog() {
        dialog = new Dialog();
        dialog.setHeaderText(getCabecalho());
        dialog.setContentText(getPromptText());
        if (!getStrIco().equals(""))
            dialog.setGraphic(new ImageView(this.getClass().getResource(getStrIco()).toString()));
    }

    void addStyleDialogPane(String styleAdd) {
        dialogPane = dialog.getDialogPane();
        dialogPane.getStylesheets().add(STYLESHEETS);
        if (styleAdd != "")
            dialogPane.getStyleClass().add(styleAdd);
        dialog.initStyle(StageStyle.UNDECORATED);
        dialog.setDialogPane(dialogPane);

        dialog.getDialogPane().getButtonTypes().clear();
    }

    public Optional<ButtonType> getRetornoAlert_YES_NO() {
        carregaDialog();
        addStyleDialogPane("yes_no");

        dialog.getDialogPane().getButtonTypes().add(ButtonType.YES);
        botaoYes = (Button) dialog.getDialogPane().lookupButton(ButtonType.YES);
        botaoYes.setDefaultButton(true);
        botaoYes.setCancelButton(false);

        dialog.getDialogPane().getButtonTypes().add(ButtonType.NO);
        botaoNo = (Button) dialog.getDialogPane().lookupButton(ButtonType.NO);
        botaoNo.setCancelButton(true);
        botaoNo.setDefaultButton(false);

        dialog.setResultConverter(new Callback() {
            @Override
            public Object call(Object param) {
                if (param == ButtonType.YES)
                    return ButtonType.YES;
                return ButtonType.NO;
            }
        });
        Optional<ButtonType> result = dialog.showAndWait();
        return result;
    }

}
