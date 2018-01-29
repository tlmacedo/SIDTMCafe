package br.com.sidtmcafe.componentes;

import br.com.sidtmcafe.interfaces.Constants;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import javafx.animation.*;
import javafx.concurrent.Task;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;
import javafx.util.Duration;

import javax.swing.*;
import java.util.Optional;


public class AlertMensagem extends JFrame implements Constants {
    Dialog dialog;
    Stage stage;
    ImageView imageView;

    Scene scene;
    DialogPane dialogPane;

    ProgressBar progressBar;
    ProgressIndicator progressIndicator;

    Label lblImagem;
    Label lblMensagem, lblTextoMsg;
    String strContagem;
    Button botaoOk, botaoApply, botaoYes, botaoClose, botaoFinish, botaoNo, botaoCancel;

    Timeline tlRegressiva, tlLoop;
    int tempo = 0;
    String pontos = "";


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
        dialog.setHeaderText(getCabecalho());
        dialog.setContentText(getPromptText());
        if (!getStrIco().equals(""))
            dialog.setGraphic(new ImageView(this.getClass().getResource(PATH_IMAGENS + getStrIco()).toString()));
    }

    void carregaStage(VBox vBox) {
        StackPane stackPane = new StackPane(vBox);
        stackPane.setStyle("-fx-background-color: rgba(255, 255, 255, 0.8);");
        scene = new Scene(stackPane);
        scene.setFill(Color.TRANSPARENT);
        scene.getStylesheets().setAll(STYLE_SHEETS);
        scene.getRoot().getStyleClass().add("progress-bar-transparent");
        stage = new Stage(StageStyle.TRANSPARENT);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(scene);
    }


    VBox ativaProgressBar() {
        progressIndicator = new ProgressIndicator();
        progressIndicator.setPrefSize(25, 25);
        progressBar = new ProgressBar();
        progressBar.setPrefWidth(520);

        HBox hBox = new HBox();
        hBox.setSpacing(7);
        hBox.setAlignment(Pos.CENTER_LEFT);
        lblMensagem = new Label();
        lblMensagem.getStyleClass().add("progress-bar");
        hBox.getChildren().addAll(progressIndicator, lblMensagem);

        VBox vBox = new VBox();
        vBox.setSpacing(5);
        vBox.setAlignment(Pos.CENTER_LEFT);
        vBox.setPadding(new Insets(0, 25, 0, 25));
        vBox.getChildren().addAll(hBox, progressBar);

        return vBox;
    }

    VBox ativaProgressBarTransparent(boolean showAndWait) {
        imageView = new ImageView(new Image(this.getClass().getResource(PATH_IMAGENS + "img_loading_coffe_1.gif").toString()));
        lblImagem = new Label("", imageView);
        lblImagem.getStyleClass().add("progress-bar-loading");
        contagemLoop();

        lblTextoMsg = new Label();
        lblMensagem = new Label();
        lblMensagem.getStyleClass().add("progress-bar-msg");

        VBox vBox = new VBox();
        vBox.setSpacing(5);

        if (!showAndWait) {
            progressBar = new ProgressBar();
            vBox.getChildren().addAll(lblImagem, lblMensagem, progressBar);
        } else {
            botaoOk = new Button("OK");
            botaoOk.setGraphic(new Label(""));
            botaoOk.setDefaultButton(true);
            botaoOk.getStyleClass().add("btn_ok");
            botaoOk.setDisable(true);
            HBox hBox = new HBox(botaoOk);
            hBox.setAlignment(Pos.CENTER_RIGHT);
            hBox.setPadding(new Insets(5,15,5,0));
            vBox.getChildren().addAll(lblImagem, lblMensagem, hBox);
            System.out.println("botaoOk.getStyleClass(): " + botaoOk.getStyleClass());
        }
        return vBox;
    }


    void addStyleDialogPane(String styleAdd) {
        dialogPane = dialog.getDialogPane();
        dialogPane.getStylesheets().add(STYLE_SHEETS);
        if (styleAdd != "")
            dialogPane.getStyleClass().add(styleAdd);
        dialog.initStyle(StageStyle.TRANSPARENT);
        //dialog.setDialogPane(dialogPane);
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

    public void getProgressBarTransparent(Task<?> task, boolean showAndWait) {
        carregaStage(ativaProgressBarTransparent(showAndWait));

        if (!showAndWait) {
            progressBar.progressProperty().bind(task.progressProperty());
        } else {
            botaoOk.setOnAction(event -> {
                stage.close();
            });
        }

        lblTextoMsg.textProperty().bind(task.messageProperty());

        contagemRegressiva(30);

        task.setOnSucceeded(event -> {
            if (!showAndWait) {
                stage.close();
            } else {
                botaoOk.setDisable(false);
            }
            tlRegressiva.stop();
            tlLoop.stop();
        });
        Thread thread = new Thread(task);
        thread.start();

        stage.showAndWait();
    }

    public void getProgressBar(Task<?> task, boolean showAndWait, boolean geraMsgRetorno) {
//        carregaDialog();
//        dialog.initStyle(StageStyle.TRANSPARENT);
//        addStyleDialogPane("barra-progressiva");
////        addStyleDialogPane("barra-progressiva-transparente");
//        ativaProgressBar(geraMsgRetorno);
//
//
//        dialog.getDialogPane().getButtonTypes().clear();
//        if (showAndWait) {
//            dialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
//            botaoOk = (Button) dialog.getDialogPane().lookupButton(ButtonType.OK);
//            botaoOk.setDisable(true);
//            botaoOk.setDefaultButton(true);
//        } else {
//            progressBar.progressProperty().bind(task.progressProperty());
//        }
//        labelMsg = new Label();
//        labelMsg.textProperty().bind(task.messageProperty());
//
//        Timeline timeline = new Timeline(new KeyFrame(
//                Duration.millis(100),
//                ae -> {
//                    if (tempo % 10 == 1)
//                        if (pontos.length() < 3) {
//                            pontos += ".";
//                        } else {
//                            pontos = "";
//                        }
//                    tempo++;
//                    strContagem = " (" + (duracaoTimeOut - (tempo / 10)) + ")" + pontos;
//                    label.setText(labelMsg.getText() + strContagem);
//                }));
//        timeline.setCycleCount(Animation.INDEFINITE);
//        timeline.play();
//
//        Timeline finalTimeline = timeline;
//        task.setOnSucceeded(event -> {
//            if (showAndWait) {
//                dialog.setHeaderText(getResultCabecalho());
//                dialogPane.lookupButton(ButtonType.OK).setDisable(false);
//                progressBar.setVisible(false);
//                progressIndicator.setVisible(false);
//                if (geraMsgRetorno)
//                    textArea.appendText(getResultPromptText());
//            } else {
//                closeDialog();
//            }
//            finalTimeline.stop();
//        });
//        Thread thread = new Thread(task);
//        thread.start();
//
//        dialog.showAndWait();
    }

    void contagemRegressiva(final int duracaoTimeOut) {
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

    void contagemLoop() {
        tlLoop = new Timeline(new KeyFrame(
                Duration.millis(7500),
                ae -> {
                    imageView = new ImageView(new Image(this.getClass().getResource(PATH_IMAGENS + "img_loading_coffe_1.gif").toString()));
                    lblImagem.setGraphic(imageView);
                }));
        tlLoop.setCycleCount(Animation.INDEFINITE);
        tlLoop.play();
    }

    void closeDialog() {
        dialog.setResult(ButtonType.CANCEL);
        dialog.close();
    }
}
