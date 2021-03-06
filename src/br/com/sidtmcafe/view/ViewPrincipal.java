package br.com.sidtmcafe.view;

import br.com.sidtmcafe.interfaces.Constants;
import br.com.sidtmcafe.service.OpenView;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class ViewPrincipal extends Application implements Constants {

    public static Stage stage;

    public static Stage getStage() {
        return stage;
    }


    public void openViewPrincipal(boolean showAndWait) {
        stage = new Stage();
        Parent root;
        Scene scene = null;

        try {
            root = FXMLLoader.load(getClass().getResource(PATH_FXML + "FxmlPrincipal.fxml"));
            scene = new Scene(root);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        stage.setTitle("Principal");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.getIcons().setAll(new Image(this.getClass().getResource(PATH_IMAGENS + "ic_grao_cafe_black_24dp.png").toString()));
        scene.getStylesheets().setAll(STYLE_SHEETS);
        scene.getRoot().getStyleClass().add("view-principal");

        new OpenView(stage, showAndWait);

        ViewPrincipal.stage = stage;
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        ViewPrincipal.stage = primaryStage;

        openViewPrincipal(false);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
