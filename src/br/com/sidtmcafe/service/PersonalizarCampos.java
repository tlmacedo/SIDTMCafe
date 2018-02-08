package br.com.sidtmcafe.service;

import br.com.sidtmcafe.interfaces.Constants;
import com.jfoenix.controls.*;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.AnchorPane;

public class PersonalizarCampos implements Constants {

    //999@_CNPJ
    //(0, 3) qtdMax
    //(3, 4) Tipo [Numero] [AlfaNumerico]
    //(4, 5) Vlr campo Limpo
    //(5   ) Mascara
    // # -> numero
    // @ -> alfanum Maiusculo
    // ? -> alfanum Minusculo

    public static void fieldMaxLen(AnchorPane anchorPane) {
        for (Node node : anchorPane.getChildren()) {
            String textFormatador;
            if (node instanceof JFXTextField) {
                if (node.getAccessibleText() != null)
                    if ((textFormatador = node.getAccessibleText()).toLowerCase() != null) {
                        int qtdMax = Integer.parseInt(textFormatador.substring(0, 3));
                        FormatadorDeDados.maxField((JFXTextField) node, qtdMax);
                    }
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
                String textFormatador;
                if (node.getAccessibleText() != null)
                    if ((textFormatador = node.getAccessibleText()).toLowerCase() != null) {
                        int qtdMax = Integer.parseInt(textFormatador.substring(0, 3));
                        String tipoDados = textFormatador.substring(3, 4);
                        //String campoLimpo = textFormatador.substring(4, 5);
                        String mask = "";
                        if (textFormatador.length() > 5)
                            mask = textFormatador.substring(5);

                        String mascara = FormatadorDeDados.gerarMascara(mask, qtdMax, tipoDados);
                        new FormatadorDeDados().maskField((JFXTextField) node, mascara);
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
            String textFormatador, textCampoLimpo = "";
            if ((textFormatador = node.getAccessibleText()).toLowerCase() != null) {
                String tipoDados = textFormatador.substring(3, 4);
                String campoLimpo = textFormatador.substring(4, 5);

                if (textFormatador.length() < 4 || (tipoDados != "#" & tipoDados != "@" & tipoDados != "?")) {
                    textCampoLimpo = textFormatador;
                    if (textFormatador.equals("lblregistroslocalizados")) {
                        textCampoLimpo = "[pesquisa]  0 registro(s) localizado(s).";
                    } else if (textFormatador.equals("lbl")) {
                        textCampoLimpo = "";
                    } else if (textFormatador.equals("verdadeiro")) {
                        textCampoLimpo = "verdadeiro";
                    } else if (textFormatador.equals("falso")) {
                        textCampoLimpo = "falso";
                    }
                } else {
                    if (campoLimpo.equals("_")) {
                        textCampoLimpo = "";
                    } else {
                        textCampoLimpo = campoLimpo;
                        if (tipoDados.equals("$")) {
                            textCampoLimpo += ".0";
                        }
                    }
                }

                if (node instanceof JFXTextField) {
                    ((JFXTextField) node).setText(textCampoLimpo);
                } else if (node instanceof Label) {
                    ((Label) node).setText(textCampoLimpo);
                } else if (node instanceof JFXTextArea) {
                    ((JFXTextArea) node).setText(textCampoLimpo);
                } else if (node instanceof JFXCheckBox) {
                    ((JFXCheckBox) node).setSelected(textCampoLimpo == "verdadeiro");
                } else if (node instanceof JFXComboBox) {
                    ((JFXComboBox) node).getSelectionModel().select(0);
                } else if (node instanceof JFXTreeTableView) {
                    if (textCampoLimpo.equals("ttv")) ((JFXTreeTableView) node).getColumns().clear();
                } else if (node instanceof JFXListView) {
                    if (textCampoLimpo.equals("list")) ((JFXListView) node).getItems().clear();
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
    }

    public static void desabilitaCampos(AnchorPane anchorPane, boolean setDisable) {
        for (Node node : anchorPane.getChildren()) {
            if (node instanceof TitledPane) {
                desabilitaCampos((AnchorPane) ((TitledPane) node).getContent(), setDisable);
            } else if (node instanceof JFXTextField) {
                ((JFXTextField) node).setDisable(setDisable);
            } else if (node instanceof JFXTextArea) {
                ((JFXTextArea) node).setDisable(setDisable);
            } else if (node instanceof JFXComboBox) {
                ((JFXComboBox) node).setDisable(setDisable);
            } else if (node instanceof JFXCheckBox) {
                ((JFXCheckBox) node).setDisable(setDisable);
            } else if (node instanceof JFXTreeTableView) {
                ((JFXTreeTableView) node).setEditable(setDisable);
            } else if (node instanceof AnchorPane) {
                desabilitaCampos((AnchorPane) node, setDisable);
            } else if (node instanceof JFXTabPane) {
                for (Tab tab : ((JFXTabPane) node).getTabs()) {
                    desabilitaCampos((AnchorPane) tab.getContent(), setDisable);
                }
            }
        }
    }


}
