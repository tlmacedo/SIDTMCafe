package br.com.sidtmcafe.componentes;

import com.jfoenix.controls.JFXTextArea;
import javafx.scene.control.DialogPane;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ProgressIndicator;

import java.awt.*;

public class AlertMensagem {
    Dialog dialog;
    DialogPane dialogPane;
    ProgressBar progressBar;
    ProgressIndicator progressIndicator;
    Label label;
    JFXTextArea jfxTextArea;

    public String cabecalho, mensagemOUsetPromptText, strImageIco;

    public AlertMensagem(String cabecalho, String mensagemOUsetPromptText, String strImageIco) {
        this.cabecalho = cabecalho;
        this.mensagemOUsetPromptText = mensagemOUsetPromptText;
        this.strImageIco = strImageIco;
    }

    public String getCabecalho() {
        return cabecalho;
    }

    public void setCabecalho(String cabecalho) {
        this.cabecalho = cabecalho;
    }

    public String getMensagemOUsetPromptText() {
        return mensagemOUsetPromptText;
    }

    public void setMensagemOUsetPromptText(String mensagemOUsetPromptText) {
        this.mensagemOUsetPromptText = mensagemOUsetPromptText;
    }

    public String getStrImageIco() {
        return strImageIco;
    }

    public void setStrImageIco(String strImageIco) {
        this.strImageIco = strImageIco;
    }



}
