package br.com.sidtmcafe.service;

import javafx.stage.Modality;
import javafx.stage.Stage;

public class OpenView {
    public OpenView(Stage stage, boolean showAndWait) {
        if (showAndWait) {
            try {
                stage.initModality(Modality.APPLICATION_MODAL);
            } catch (Exception ex) {

            } finally {
                stage.showAndWait();
            }
        } else {
            stage.show();
        }
    }
}
