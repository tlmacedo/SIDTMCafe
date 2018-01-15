package br.com.sidtmcafe.view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class ViewLogin extends Application {

    private static Stage stage;

    public static Stage getStage() {
        return stage;
    }

    public void Login(boolean abertoPeloSistema) {
        stage = new Stage();
        Parent root;
        Scene scene = null;

        try {
            root = FXMLLoader.load(getClass().getResource("/fxml/Login.fxml"));
            scene = new Scene(root);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        stage.setTitle("Login.");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.getIcons().clear();
        stage.getIcons().add(new Image(this.getClass().getResource("/images/material-design-icons/hardware/1x_web/ic_security_black_18dp.png").toString()));

        if (abertoPeloSistema) {
            try {
                stage.initModality(Modality.APPLICATION_MODAL);
            } catch (Exception ex) {

            } finally {
                stage.showAndWait();
            }
        } else {
            stage.show();
        }
        ViewLogin.stage = stage;
    }

    public void start(Stage primaryStage) throws Exception {
        ViewLogin.stage = primaryStage;

        Login(false);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
