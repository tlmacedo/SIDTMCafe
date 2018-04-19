package br.com.sidtmcafe.service;

import br.com.sidtmcafe.interfaces.Constants;
import com.jfoenix.controls.*;
import javafx.scene.Node;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.AnchorPane;

import java.time.LocalDate;

public class PersonalizarCampo implements Constants {

    //999@_CNPJ
    //(0, 3) qtdMax
    //(3, 4) Tipo [Numero] [AlfaNumerico]
    //(4, 5) Vlr campo Limpo
    //(5, 6) Deshabilitado
    //(6   ) Mascara
    // # -> numero
    // $ -> moeda
    // @ -> alfanum Maiusculo
    // ? -> alfanum Minusculo

    public static void fieldMaxLen(AnchorPane anchorPane) {
        for (Node node : anchorPane.getChildren()) {
            if (node instanceof JFXTextField) {
                String accessibleText = null, newTextCampo = "";
                if (node.getAccessibleText() != null)
                    if ((accessibleText = node.getAccessibleText().toLowerCase()) != null)
                        FormatarDado.maxField((JFXTextField) node, Integer.parseInt(accessibleText.substring(0, 3)));
            } else if (node instanceof AnchorPane) {
                fieldMaxLen((AnchorPane) node);
            } else if (node instanceof TitledPane) {
                fieldMaxLen((AnchorPane) ((TitledPane) node).getContent());
            } else if (node instanceof JFXTabPane) {
                for (Tab tab : ((JFXTabPane) node).getTabs()) {
                    fieldMaxLen((AnchorPane) tab.getContent());
                }
            }
        }
    }

    public static void maskFields(AnchorPane anchorPane) {
        for (Node node : anchorPane.getChildren()) {
            if (node instanceof JFXTextField) {
                int qtdMax;
                String accessibleText = null, tipoDado, mascara;
                if (node.getAccessibleText() != null)
                    if ((accessibleText = node.getAccessibleText().toLowerCase()) != null) {
                        qtdMax = Integer.parseInt(accessibleText.substring(0, 3));
                        tipoDado = accessibleText.substring(3, 4);
                        try {
                            mascara = accessibleText.substring(6).toLowerCase();
                        } catch (Exception ex) {
                            mascara = "";
                        }
                        switch (tipoDado) {
                            case "#":
                            case "@":
                            case "?":
                                mascara = FormatarDado.gerarMascara(mascara, qtdMax, tipoDado);
                                new FormatarDado().maskField((JFXTextField) node, mascara);
                                break;
                            case "$":
                                new FormatarDado().maskFieldMoeda((JFXTextField) node, Integer.parseInt(mascara));
                                break;
                        }
                    }
            } else if (node instanceof AnchorPane) {
                maskFields((AnchorPane) node);
            } else if (node instanceof TitledPane) {
                maskFields((AnchorPane) ((TitledPane) node).getContent());
            } else if (node instanceof JFXTabPane) {
                for (Tab tab : ((JFXTabPane) node).getTabs()) {
                    maskFields((AnchorPane) tab.getContent());
                }
            }
        }

    }

    public static void clearField(AnchorPane anchorPane) {
        for (Node node : anchorPane.getChildren()) {
            String accessibleText = null, tipoDado, vlrPadrao, qtdCasaDecimal, newTextCampo = "";
            if (node.getAccessibleText() != null)
                if ((accessibleText = node.getAccessibleText().toLowerCase()) != null)
                    if (Character.isDigit(accessibleText.charAt(0))) {
                        tipoDado = accessibleText.substring(3, 4);
                        vlrPadrao = accessibleText.substring(4, 5);
                        switch (tipoDado) {
                            case "#":
                                newTextCampo = vlrPadrao;
                                break;
                            case "@":
                            case "?":
                                if (!vlrPadrao.equals("_"))
                                    newTextCampo = vlrPadrao;
                                break;
                            case "$":
                                qtdCasaDecimal = accessibleText.substring(5, 6);
                                newTextCampo = vlrPadrao;
                                if (Integer.parseInt(qtdCasaDecimal) > 0)
                                    newTextCampo += ".";
                                for (int i = 0; i < Integer.parseInt(qtdCasaDecimal); i++) {
                                    newTextCampo += "0";
                                }
                                break;
                        }
                    } else {
                        if (accessibleText.equals("lbl")) {
                            newTextCampo = "";
                        } else if (accessibleText.equals("verdadeiro")) {
                            newTextCampo = "verdadeiro";
                        } else if (accessibleText.equals("falso")) {
                            newTextCampo = "falso";
                        }
                    }
            if (node instanceof JFXTextField) {
                ((JFXTextField) node).setText(newTextCampo);
            } else if (node instanceof DatePicker) {
                //if (newTextCampo.equals(""))
                ((DatePicker) node).setValue(LocalDate.now());
            } else if (node instanceof Label) {
                ((Label) node).setText(newTextCampo);
            } else if (node instanceof JFXTextArea) {
                ((JFXTextArea) node).setText(newTextCampo);
            } else if (node instanceof JFXCheckBox) {
                ((JFXCheckBox) node).setSelected(newTextCampo == "verdadeiro");
                //} else if (node instanceof JFXComboBox) {
                //    ((JFXComboBox) node).getSelectionModel().select(0);
            } else if (node instanceof JFXTreeTableView) {
                //if (newTextCampo.toLowerCase().equals("ttv"))
                    ((JFXTreeTableView) node).getColumns().clear();
            } else if (node instanceof JFXListView) {
                //if (newTextCampo.toLowerCase().equals("list"))
                    ((JFXListView) node).getItems().clear();
            } else if (node instanceof AnchorPane) {
                clearField((AnchorPane) node);
            } else if (node instanceof TitledPane) {
                clearField((AnchorPane) ((TitledPane) node).getContent());
            } else if (node instanceof JFXTabPane) {
                for (Tab tab : ((JFXTabPane) node).getTabs()) {
                    clearField((AnchorPane) tab.getContent());
                }
            }


        }
    }

    public static void desabilitaCampos(AnchorPane anchorPane, boolean setDisable) {
        for (Node node : anchorPane.getChildren()) {
            String accessibleText = null;
            boolean deshabilitado = true;
            if (node.getAccessibleText() != null)
                if ((accessibleText = node.getAccessibleText().toLowerCase()) != null)
                    if (accessibleText.length() > 5)
                        if (accessibleText.substring(5, 6).equals("1"))
                            if (!setDisable)
                                deshabilitado = false;
            if (node instanceof DatePicker) {
                //((DatePicker) node).setEditable(setDisable & deshabilitado);
                ((DatePicker) node).setDisable(setDisable & deshabilitado);
            } else if (node instanceof JFXTextField) {
                //((JFXTextField) node).setEditable(setDisable & deshabilitado);
                ((JFXTextField) node).setDisable(setDisable & deshabilitado);
            } else if (node instanceof JFXTextArea) {
                //((JFXTextArea) node).setEditable(setDisable & deshabilitado);
                ((JFXTextArea) node).setDisable(setDisable & deshabilitado);
            } else if (node instanceof JFXComboBox) {
//                ((JFXComboBox) node).setEditable(setDisable & deshabilitado);
                ((JFXComboBox) node).setDisable(setDisable & deshabilitado);
            } else if (node instanceof JFXCheckBox) {
                ((JFXCheckBox) node).setDisable(setDisable & deshabilitado);
            } else if (node instanceof JFXTreeTableView) {
                //((JFXTreeTableView) node).setEditable(setDisable & deshabilitado);
                ((JFXTreeTableView) node).setDisable(setDisable & deshabilitado);
            } else if (node instanceof AnchorPane) {
                desabilitaCampos((AnchorPane) node, setDisable);
            } else if (node instanceof TitledPane) {
                desabilitaCampos((AnchorPane) ((TitledPane) node).getContent(), setDisable);
            } else if (node instanceof JFXTabPane) {
                for (Tab tab : ((JFXTabPane) node).getTabs()) {
                    desabilitaCampos((AnchorPane) tab.getContent(), setDisable);
                }
            }
        }
    }


}
