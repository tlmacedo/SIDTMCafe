package br.com.sidtmcafe.service;

import br.com.sidtmcafe.interfaces.Constants;
import com.jfoenix.controls.*;
import com.sun.tools.javac.code.Attribute;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.AnchorPane;

public class PersonalizarCampos implements Constants {

    public static void mascara(AnchorPane anchorPane) {
        for (Node node : anchorPane.getChildren()) {
            if (node instanceof JFXTextField) {
                String accessibleText = node.getAccessibleText();
                if (accessibleText == null) accessibleText = "";
                accessibleText = accessibleText.toLowerCase();
                if (accessibleText.contains("moeda")) {

                } else {
                    // # -> numero
                    // @ -> alfanum Maiusculo
                    // ? -> alfanum Minusculo
                    String caractere = "";
                    String mask = "";
                    boolean num = accessibleText.contains("num");
                    boolean txt = accessibleText.contains("alfa");
                    boolean mai = accessibleText.contains("mai");
                    if (txt) {
                        if (num) {
                            if (mai) {
                                caractere = "@";
                            } else {
                                caractere = "?";
                            }
                        }
                    } else {
                        if (num) {
                            caractere = "#";
                        }
                    }
                    if (accessibleText.contains("numero")) {
                        if (Character.isDigit(accessibleText.charAt(7))) {
                            mask = FormatadorDeDados.geraMascara("", Integer.parseInt(accessibleText.substring(7, 10)), caractere);
                        } else {
                            mask = FormatadorDeDados.geraMascara(accessibleText.substring(7), 0, caractere);
                        }
                    } else if (accessibleText.contains("alfanum_")) {
                        mask = FormatadorDeDados.geraMascara("", Integer.parseInt(accessibleText.substring(8, 11)), caractere);
                    } else if (accessibleText.contains("alfa_")) {
                        mask = FormatadorDeDados.geraMascara("", Integer.parseInt(accessibleText.substring(5, 8)), caractere);
                    }
                    FormatadorDeDados.campoMask((JFXTextField) node, mask);
                }
            } else if (node instanceof AnchorPane) {
                mascara((AnchorPane) node);
            } else if (node instanceof TitledPane) {
                mascara((AnchorPane) ((TitledPane) node).getContent());
            } else if (node instanceof JFXTabPane) {
                for (Tab tab : ((JFXTabPane) node).getTabs()) {
                    mascara((AnchorPane) tab.getContent());
                }
            }
        }
        //limpeza(anchorPane);
    }


    public static void limpeza(AnchorPane anchorPane) {
        String vlrCampoLimpo = "";
        for (Node node : anchorPane.getChildren()) {
            String accessibleText = node.getAccessibleText();
            if (accessibleText == null) accessibleText = "";
            accessibleText = accessibleText.toLowerCase();
            if (accessibleText.contains("numero_")) {
                vlrCampoLimpo = "";
            } else if (accessibleText.contains("numero0")) {
                vlrCampoLimpo = "0";
            } else if (accessibleText.contains("numero1")) {
                vlrCampoLimpo = "1";
            } else if (accessibleText.contains("moeda0")) {
                vlrCampoLimpo = "0.0";
            } else if (accessibleText.contains("moeda1")) {
                vlrCampoLimpo = "1.0";
            } else if (accessibleText.contains("alfanum_")) {
                vlrCampoLimpo = "";
            } else if (accessibleText.contains("alfa_")) {
                vlrCampoLimpo = "";
            } else if (accessibleText.contains("lblregistroslocalizados")) {
                vlrCampoLimpo = "[pesquisa]  0 registro(s) localizado(s).";
            } else if (accessibleText.contains("lbl")) {
                vlrCampoLimpo = "";
            } else if (accessibleText.contains("verdadeiro")) {
                vlrCampoLimpo = "verdadeiro";
            } else if (accessibleText.contains("falso")) {
                vlrCampoLimpo = "falso";
            } else if (node instanceof JFXTextField) {
                ((JFXTextField) node).setText(vlrCampoLimpo);
            } else if (node instanceof Label) {
                if (node.getAccessibleText() != "")
                    ((Label) node).setText(vlrCampoLimpo);
            } else if (node instanceof JFXTextArea) {
                ((JFXTextArea) node).setText(vlrCampoLimpo);
            } else if (node instanceof JFXCheckBox) {
                ((JFXCheckBox) node).setSelected(vlrCampoLimpo == "verdadeiro");
            } else if (node instanceof JFXComboBox) {
                ((JFXComboBox) node).getSelectionModel().select(0);
            } else if (node instanceof JFXTreeTableView) {
                switch (vlrCampoLimpo) {
                    case "ttv":
                        ((JFXTreeTableView) node).getColumns().clear();
                        break;
                }
            } else if (node instanceof JFXListView) {
                switch (vlrCampoLimpo) {
                    case "list":
                        ((JFXListView) node).getItems().clear();
                        break;
                }
            } else if (node instanceof AnchorPane) {
                limpeza((AnchorPane) node);
            } else if (node instanceof TitledPane) {
                limpeza((AnchorPane) ((TitledPane) node).getContent());
            } else if (node instanceof JFXTabPane) {
                for (Tab tab : ((JFXTabPane) node).getTabs()) {
                    limpeza((AnchorPane) tab.getContent());
                }
            }
        }
    }

}
