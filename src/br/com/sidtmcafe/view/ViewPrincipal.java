package br.com.sidtmcafe.view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class ViewPrincipal extends Application {

    private static Stage stage;

    public static Stage getStage() {
        return stage;
    }


    public void openViewPrincipal() {
        stage = new Stage();
        Parent root;
        Scene scene = null;

        try {
            root = FXMLLoader.load(getClass().getResource("/fxml/FxmlPrincipal.fxml"));
            scene = new Scene(root);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        stage.setTitle("Login.");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.getIcons().clear();
        stage.getIcons().add(new Image(this.getClass().getResource("/images/material-design-icons/hardware/1x_web/ic_security_black_18dp.png").toString()));

        stage.show();
        ViewPrincipal.stage = stage;
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        ViewPrincipal.stage = primaryStage;

        openViewPrincipal();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
