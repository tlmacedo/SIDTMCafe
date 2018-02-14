package br.com.sidtmcafe.service;

import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTreeTableColumn;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Pos;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeTableCell;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.cell.CheckBoxTreeTableCell;
import javafx.util.Callback;

import javax.swing.text.MaskFormatter;
import java.text.ParseException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FormatadorDeDados {

    String mascara;

    public String getMascara() {
        return mascara;
    }

    public void setMascara(String tipMascOrMascara) {
        String ms = "#@?", digt = tipMascOrMascara.substring(0, 1);
        if (!ms.contains(digt)) {
            tipMascOrMascara = gerarMascara(tipMascOrMascara, 0, "");
        }
        this.mascara = tipMascOrMascara;
    }

    public static String getCampoFormatado(String value, String tipMascOrMascara) {
        String ms = "#@?", digt = tipMascOrMascara.substring(0, 1);
        if (!ms.contains(digt)) {
            tipMascOrMascara = gerarMascara(tipMascOrMascara, value.length(), "");
        }
        Pattern pattern = Pattern.compile("[\\-/.\\[\\]]");
        Matcher matcher = pattern.matcher(tipMascOrMascara);
        if (matcher.find())
            value = value.replaceAll("[\\-/.\\[\\]]", "");
        if (value.length() > 0)
            try {
                MaskFormatter mf = new MaskFormatter(tipMascOrMascara);
                mf.setValueContainsLiteralCharacters(false);
                return mf.valueToString(value);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        return value;
    }

    public static String gerarMascara(String tipMasc, int qtd, String caractere) {
        String tipM = tipMasc.toLowerCase();
        if (tipM.contains("cnpj")) {
            return "##.###.###/####-##";
        } else if (tipM.contains("cpf")) {
            return "###.###.###-##";
        } else if (tipM.contains("cep")) {
            return "##.###-###";
        } else if (tipM.contains("telefone")) {
            if (qtd < 9)
                return "####-####";
            return "# ####-####";
        } else if (tipM.contains("ie")) {
            return gerarMascaraIE(tipM);
        } else {
            String masc = "";
            for (int i = 0; i < qtd; i++) {
                masc += caractere;
            }
            return masc;
        }
    }

    static String gerarMascaraIE(String tipMasc) {
        if (tipMasc.length() < 4)
            return "############";

        switch (tipMasc.substring(2, 4).toUpperCase()) {
            case "AC":
                return "##.###.###/###-##";
            case "AL":
                return "#########";
            case "AM":
                return "##.###.###-#";
            case "AP":
                return "#########";
            case "BA":
                return "###.###.##-#";
            case "CE":
                return "########-#";
            case "DF":
                return "###########-##";
            case "ES":
                return "###.###.##-#";
            case "GO":
                return "##.###.###-#";
            case "MA":
                return "#########";
            case "MG":
                return "###.###.###/####";
            case "MS":
                return "#########";
            case "MT":
                return "#########";
            case "PA":
                return "##-######-#";
            case "PB":
                return "########-#";
            case "PE":
                return "##.#.###.#######-#";
            case "PI":
                return "#########";
            case "PR":
                return "########-##";
            case "RJ":
                return "##.###.##-#";
            case "RN":
                return "##.###.###-#";
            case "RO":
                return "###.#####-#";
            case "RR":
                return "########-#";
            case "RS":
                return "###-#######";
            case "SC":
                return "###.###.###";
            case "SE":
                return "#########-#";
            case "SP":
                return "###.###.###.###";
            case "TO":
                return "###########";
            default:
                return "############";


        }


    }

    public void maskField(JFXTextField textField, String strMascara) {
        if (strMascara.length() > 0)
            setMascara(strMascara);
        textField.lengthProperty().addListener((ObservableValue<? extends Number> observable, Number n1, Number n2) -> {
            String alphaAndDigits = textField.getText();
            Pattern pattern = Pattern.compile("[\\-/.\\[\\]]");
            Matcher matcher = pattern.matcher(getMascara());
            if (matcher.find())
                alphaAndDigits = textField.getText().replaceAll("[\\-/.\\[\\]]", "");
            StringBuilder resultado = new StringBuilder();
            int i = 0;
            int quant = 0;
            String mascara = getMascara();
            int lenMascara = mascara.length();
            if (n2.intValue() > n1.intValue()) {
                if (textField.getText().length() <= lenMascara) {
                    while (i < lenMascara) {
                        if (quant < alphaAndDigits.length()) {
                            if ("@".equals(mascara.substring(i, i + 1))) {
                                resultado.append(alphaAndDigits.substring(quant, quant + 1).toUpperCase());
                                quant++;
                            } else if ("?".equals(mascara.substring(i, i + 1))) {
                                resultado.append(alphaAndDigits.substring(quant, quant + 1).toLowerCase());
                                quant++;
                            } else if ("#".equals(mascara.substring(i, i + 1))) {
                                if (Character.isDigit(alphaAndDigits.charAt(quant))) //{
                                    resultado.append(alphaAndDigits.substring(quant, quant + 1));
                                quant++;
                            } else {
                                resultado.append(mascara.substring(i, i + 1));
                            }
                        }
                        i++;
                    }
                    textField.setText(resultado.toString());
                    //positionCaret(textField);
                } else {
                    textField.setText(textField.getText(0, lenMascara));
                }
            }
        });

    }

    private static void positionCaret(final TextField textField) {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                // Posiciona o cursor sempre a direita.
                textField.positionCaret(textField.getText().length());
            }
        });
    }

    public static void maxField(final TextField textField, final Integer tamMax) {
        textField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String oldValue, String newValue) {
                if (newValue.length() > tamMax)
                    textField.setText(oldValue);
            }
        });
    }

    public static void fatorarColunaCheckBox(JFXTreeTableColumn colunaGenerica) {
        colunaGenerica.getStyleClass().add("chkbox");
        colunaGenerica.setCellFactory(new Callback<TreeTableColumn, TreeTableCell>() {
            @Override
            public TreeTableCell call(TreeTableColumn param) {
                CheckBoxTreeTableCell cell = new CheckBoxTreeTableCell();
                cell.setAlignment(Pos.TOP_CENTER);
                return cell;
            }
        });
    }


}
