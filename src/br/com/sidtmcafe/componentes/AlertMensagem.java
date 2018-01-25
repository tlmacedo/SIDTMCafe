package br.com.sidtmcafe.componentes;

import br.com.sidtmcafe.interfaces.Constants;
import com.jfoenix.controls.JFXTextArea;
import javafx.concurrent.Task;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.StageStyle;
import javafx.util.Callback;

import java.util.Optional;


public class AlertMensagem implements Constants {
    Dialog dialog;
    DialogPane dialogPane;
    ProgressBar progressBar;
    ProgressIndicator progressIndicator;
    Label label;
    JFXTextArea textArea;
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
            dialog.setGraphic(new ImageView(this.getClass().getResource(PATH_IMAGENS + getStrIco()).toString()));
    }

    void ativaProgressBar(boolean geraMsgRetorno) {
        progressIndicator = new ProgressIndicator();
        progressIndicator.setPrefSize(25, 25);
        progressBar = new ProgressBar();
        progressBar.setPrefWidth(520);

        HBox hBox = new HBox();
        hBox.setSpacing(7);
        hBox.setAlignment(Pos.CENTER_LEFT);
        label = new Label();
        hBox.getChildren().addAll(progressIndicator, label);

        VBox vBox = new VBox();
        vBox.setSpacing(5);
        vBox.setAlignment(Pos.CENTER_LEFT);
        vBox.setPadding(new Insets(0, 25, 0, 25));
        if (geraMsgRetorno) {
            textArea = new JFXTextArea();
            textArea.setWrapText(true);
            textArea.setEditable(false);
            vBox.getChildren().addAll(hBox, textArea, progressBar);
        } else {
            vBox.getChildren().addAll(hBox, progressBar);
        }
        dialogPane.setContent(vBox);

    }

    void addStyleDialogPane(String styleAdd) {
        dialogPane = dialog.getDialogPane();
        dialogPane.getStylesheets().add(STYLE_SHEETS);
        if (styleAdd != "")
            dialogPane.getStyleClass().add(styleAdd);
        dialog.initStyle(StageStyle.UNDECORATED);
        dialog.setDialogPane(dialogPane);
    }

    public Optional<ButtonType> getRetornoAlert_YES_NO() {
        carregaDialog();
        addStyleDialogPane("yes_no");

        dialog.getDialogPane().getButtonTypes().clear();
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

    public void getProgresBar(Task<?> task, boolean infinito, boolean geraMsgRetorno) {
        carregaDialog();
        addStyleDialogPane("");
        ativaProgressBar(geraMsgRetorno);

        dialog.getDialogPane().getButtonTypes().clear();
        if (infinito) {
            dialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
            botaoOk = (Button) dialog.getDialogPane().lookupButton(ButtonType.OK);
            botaoOk.setDisable(true);
            botaoOk.setDefaultButton(true);
        } else {
            progressBar.progressProperty().bind(task.progressProperty());
        }
        label.textProperty().bind(task.messageProperty());

        task.setOnSucceeded(event -> {
            if (infinito) {
                dialog.setHeaderText(getResultCabecalho());
                dialogPane.lookupButton(ButtonType.OK).setDisable(false);
                progressBar.setVisible(false);
                progressIndicator.setVisible(false);
                if (geraMsgRetorno)
                    textArea.appendText(getResultPromptText());
            } else {
                closeDialog();
            }
        });
        Thread thread = new Thread(task);
        thread.start();

        dialog.showAndWait();
    }

    void closeDialog() {
        dialog.setResult(ButtonType.CANCEL);
        dialog.close();
    }
}
