package br.com.sidtmcafe.componentes;

import br.com.sidtmcafe.interfaces.Constants;
import javafx.animation.*;
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
import java.util.*;
import java.util.stream.Collectors;


public class AlertMensagem extends JFrame implements Constants {
    Dialog dialog;
    DialogPane dialogPane;
    boolean transparenteDialog = false;
    Task<?> taskDialog;

    Random random;
    Image imageDialog;
    ImageView imageViewDialog;

    ProgressBar progressBarDialog;
    ProgressIndicator progressIndicatorDialog;

    HBox hBoxDialog;
    VBox vBoxDialog;

    Label lblMensagem, lblTextoMsg;
    String strContagem;
    Button botaoOk, botaoApply, botaoYes, botaoClose, botaoFinish, botaoNo, botaoCancel;

    Timeline tlRegressiva, tlLoop;
    int tempo = 0;
    String pontos = "";
    int rdnImg = 0;

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
        random = new Random();
        dialog = new Dialog();
        dialogPane = dialog.getDialogPane();

        if (transparenteDialog) {
            dialog.initStyle(StageStyle.TRANSPARENT);
            dialogPane.getScene().setFill(Color.TRANSPARENT);
        }
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
                dialog.setGraphic(new ImageView(this.getClass().getResource(PATH_IMAGENS + getStrIco()).toString()));
        }
    }

    VBox ativaProgressBar() {
        hBoxDialog = new HBox();
        hBoxDialog.setSpacing(7);
        hBoxDialog.setAlignment(Pos.CENTER_LEFT);

        vBoxDialog = new VBox();
        //vBoxDialog.setSpacing(5);
        vBoxDialog.setAlignment(Pos.CENTER);

        lblTextoMsg = new Label();
        lblMensagem = new Label();
        //lblMensagem.textProperty().bind(taskDialog.messageProperty());
        lblMensagem.getStyleClass().add("msg");
        if (transparenteDialog) {
            System.setProperty("random",
                    String.valueOf(Integer.parseInt(System.getProperty("random", "-1"))
                            >= (IMAGE_LOADING.length - 1) ? 0 : Integer.parseInt(System.getProperty("random", "-1")) + 1));
            int random = Integer.parseInt(System.getProperty("random", "0"));
            imageDialog = new Image(IMAGE_LOADING[random]);
            imageViewDialog = new ImageView();
            imageViewDialog.setImage(imageDialog);
            imageViewDialog.setClip(new Circle(120, 120, 120));
            vBoxDialog.getChildren().addAll(imageViewDialog, lblMensagem);
        } else {
            progressIndicatorDialog = new ProgressIndicator();
            progressIndicatorDialog.progressProperty().bind(taskDialog.progressProperty());
            progressIndicatorDialog.setPrefSize(25, 25);
            hBoxDialog.getChildren().addAll(progressIndicatorDialog, lblMensagem);
            vBoxDialog.getChildren().add(hBoxDialog);
        }

        progressBarDialog = new ProgressBar();
        progressBarDialog.progressProperty().bind(taskDialog.progressProperty());

        vBoxDialog.getChildren().add(progressBarDialog);

        return vBoxDialog;
    }

    public void getProgressBar(Task<?> task, boolean transparente, boolean showAndWait, boolean geraMsgRetorno) {
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

        dialogPane.setContent(ativaProgressBar());

        contagemRegressiva(30);

        taskDialog.setOnSucceeded(event -> {
            if (!showAndWait) {
                closeDialog();
            } else {
                botaoOk.setDisable(false);
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
        dialogPane.getStyleClass().add("yes_no");

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
}
