package br.com.sidtmcafe.view;

import br.com.sidtmcafe.interfaces.Constants;
import br.com.sidtmcafe.service.OpenView;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Tab;

import java.io.IOException;

public class ViewCadastroEmpresa implements Constants {

    private static String tituloJanela;

    public static String getTituloJanela() {
        return tituloJanela;
    }

    public static void setTituloJanela(String tituloJanela) {
        ViewCadastroEmpresa.tituloJanela = tituloJanela;
    }

    public Tab openTabViewCadastroEmpresa(String tituloJanela) {
        setTituloJanela(tituloJanela);
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            Parent root = fxmlLoader.load(getClass().getResource(PATH_FXML + "FxmlCadastroEmpresa.fxml"));
            root.getStylesheets().setAll(STYLE_SHEETS);
            root.getStyleClass().add("view-cadastro-empresa");

            Tab tab = new Tab(tituloJanela);
            tab.setContent(root);
            return tab;
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return null;
    }

}
