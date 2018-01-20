package br.com.sidtmcafe.view;

import br.com.sidtmcafe.interfaces.Constants;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class ViewLogin extends Application implements Constants {

    private static Stage stage;

    public static Stage getStage() {
        return stage;
    }

    public void openViewLogin(boolean abertoPeloSistema) {
        stage = new Stage();
        Parent root;
        Scene scene = null;

        try {
            root = FXMLLoader.load(getClass().getResource(PATH_FXML + "FxmlLogin.fxml"));
            scene = new Scene(root);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        stage.setTitle("Login");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.getIcons().setAll(new Image(this.getClass().getResource(PATH_IMAGENS + "ic_security_black_24dp.png").toString()));
        scene.getStylesheets().setAll(STYLE_SHEETS);
        scene.getRoot().getStyleClass().add("login");

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

    @Override
    public void start(Stage primaryStage) throws Exception {
        ViewLogin.stage = primaryStage;

        openViewLogin(false);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
