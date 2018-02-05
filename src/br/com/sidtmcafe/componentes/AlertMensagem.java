package br.com.sidtmcafe.componentes;

import br.com.sidtmcafe.interfaces.Constants;
import com.jfoenix.controls.JFXTextArea;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.concurrent.Task;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.StageStyle;
import javafx.util.Callback;
import javafx.util.Duration;

import javax.swing.*;
import java.util.Optional;
import java.util.Random;


public class AlertMensagem extends JFrame implements Constants {
    Dialog dialog;
    DialogPane dialogPane;
    boolean transparenteDialog = false;
    boolean geraMsgRetornoDialog = false;
    Task<?> taskDialog;

    Random random;
    Image imageDialog;
    ImageView imageViewDialog;

    ProgressBar progressBarDialog;
    ProgressIndicator progressIndicatorDialog;

    HBox hBoxDialog;
    VBox vBoxDialog;

    Label lblMensagem, lblTextoMsg;
    JFXTextArea textArea;
    String strContagem;
    Button botaoOk, botaoApply, botaoYes, botaoClose, botaoFinish, botaoNo, botaoCancel;

    Timeline tlRegressiva, tlLoop;
    int tempo = 0;
    String pontos = "";
    int rdnImg = 0;

    public String cabecalho, promptText, strIco;
    public String resultCabecalho, resultPromptText;
    public Exception exceptionErr;

    public AlertMensagem() {

    }

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
        dialogPane = dialog.getDialogPane();

        dialog.initStyle(StageStyle.TRANSPARENT);
        dialogPane.getScene().setFill(Color.TRANSPARENT);
        dialogPane.getStylesheets().add(STYLE_SHEETS);

        dialogPane.getButtonTypes().clear();
    }

    void preparaDialogPane() {
        if (transparenteDialog) {
            dialogPane.getStyleClass().add("dialog-pane-transparent");
        } else {
            dialog.setHeaderText(getCabecalho());
            dialog.setContentText(getPromptText());
            if (!getStrIco().equals(""))
                try {
                    dialog.setGraphic(new ImageView(this.getClass().getResource(PATH_IMAGENS + getStrIco()).toString()));
                } catch (Exception ex) {

                }
        }
    }

    VBox preencheDialogBasico() {
        hBoxDialog = new HBox();
        hBoxDialog.setSpacing(7);
        hBoxDialog.setAlignment(Pos.CENTER_LEFT);

        vBoxDialog = new VBox();
        vBoxDialog.setAlignment(Pos.CENTER);

        lblTextoMsg = new Label();
        lblMensagem = new Label();
        lblMensagem.getStyleClass().add("msg");
        if (transparenteDialog) {
            int random = (int) (Math.random() * IMAGE_LOADING.length);
            imageViewDialog = new ImageView();
            addImagem(IMAGE_LOADING[random]);
            vBoxDialog.getChildren().addAll(imageViewDialog, lblMensagem);
        } else {
            progressIndicatorDialog = new ProgressIndicator();
            progressIndicatorDialog.progressProperty().bind(taskDialog.progressProperty());
            progressIndicatorDialog.setPrefSize(25, 25);
            hBoxDialog.getChildren().addAll(progressIndicatorDialog, lblMensagem);
            vBoxDialog.getChildren().add(hBoxDialog);
        }

        progressBarDialog = new ProgressBar();

        if (geraMsgRetornoDialog) {
            textArea = new JFXTextArea();
            textArea.setWrapText(true);
            textArea.setEditable(false);
            vBoxDialog.getChildren().add(textArea);
            progressBarDialog.setProgress(ProgressBar.INDETERMINATE_PROGRESS);
        } else {
            progressBarDialog.progressProperty().bind(taskDialog.progressProperty());
        }

        vBoxDialog.getChildren().add(progressBarDialog);

        return vBoxDialog;
    }

    void addImagem(String strImage) {
        imageDialog = new Image(strImage);
        imageViewDialog.setImage(imageDialog);
        imageViewDialog.setClip(new Circle(120, 120, 120));
    }

    void contagemRegressiva(final int duracaoTimeOut) {
        lblTextoMsg.textProperty().bind(taskDialog.messageProperty());
        tlRegressiva = new Timeline(new KeyFrame(
                Duration.millis(100),
                ae -> {
                    if (tempo % 10 == 1)
                        if (pontos.length() < 3) {
                            pontos += ".";
                        } else {
                            pontos = "";
                        }
                    tempo++;
                    strContagem = " (" + (duracaoTimeOut - (tempo / 10)) + ")" + pontos;
                    lblMensagem.setText(lblTextoMsg.getText() + strContagem);
                }));
        tlRegressiva.setCycleCount(Animation.INDEFINITE);
        tlRegressiva.play();
    }

    void closeDialog() {
        dialog.setResult(ButtonType.CANCEL);
        dialog.close();
    }

    public void getRetornoAlert_OK() {
        carregaDialog();
        preparaDialogPane();
        dialogPane.getStyleClass().add("dialog_ok");

        dialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
        botaoOk = (Button) dialog.getDialogPane().lookupButton(ButtonType.OK);
        botaoOk.setDefaultButton(true);
        botaoOk.setCancelButton(false);

        botaoOk = new Button();
        botaoOk.setOnAction(event -> {
            closeDialog();
        });

        dialog.showAndWait();

    }

    public void getProgressBar(Task<?> task, boolean transparente, boolean showAndWait) {
        transparenteDialog = transparente;
        taskDialog = task;
        carregaDialog();
        preparaDialogPane();
        if (showAndWait) {
            botaoOk = new Button();
            botaoOk.setOnAction(event -> {
                closeDialog();
            });

            dialogPane.getButtonTypes().add(ButtonType.OK);
            botaoOk = (Button) dialogPane.lookupButton(ButtonType.OK);
            botaoOk.setDefaultButton(true);
            botaoOk.setDisable(true);
        }
        dialogPane.setContent(preencheDialogBasico());

        contagemRegressiva(30);

        taskDialog.setOnSucceeded(event -> {
            if (!showAndWait) {
                closeDialog();
            } else {
                addImagem(IMAGE_CAFE_PERFEITO_240DP);
                botaoOk.setDisable(false);
                if (getResultPromptText() != null) {
                    lblMensagem.setText(getResultPromptText());
                    progressBarDialog.setVisible(false);
                }
            }
            tlRegressiva.stop();
        });

        Thread thread = new Thread(taskDialog);
        thread.start();

        dialog.showAndWait();
    }

    public Optional<ButtonType> getRetornoAlert_YES_NO() {
        carregaDialog();
        preparaDialogPane();
        dialogPane.getStyleClass().add("dialog_yes_no");

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
