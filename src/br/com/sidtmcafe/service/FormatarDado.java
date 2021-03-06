package br.com.sidtmcafe.service;

import br.com.sidtmcafe.interfaces.Constants;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTreeTableColumn;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Pos;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeTableCell;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.cell.CheckBoxTreeTableCell;
import javafx.util.Callback;

import javax.swing.text.MaskFormatter;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FormatarDado implements Constants {

    String mascara;
    Matcher matcher;

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
        String strValue = value.replaceAll("[\\-/., \\[\\]]", "");
        String mascara = tipMascOrMascara;
        String ms = "#@?", digt = tipMascOrMascara.substring(0, 1);
        if (!ms.contains(digt))
            mascara = gerarMascara(tipMascOrMascara, value.length(), "");
        String mascTmp = mascara.replaceAll("[\\-/., \\[\\]]", "");
        if (strValue.length() > mascTmp.length())
            strValue = strValue.substring(0, mascTmp.length());
        if (strValue.length() > 0)
            try {
                MaskFormatter mf = new MaskFormatter(mascara);
                mf.setValueContainsLiteralCharacters(false);
                return mf.valueToString(strValue);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        return strValue;
    }

    public static Double getDoubleValorCampo(String valor) {
        double multiplicador = 1.;
//        System.out.println("valor: [" + valor + "]");
        if (valor.contains("-")) multiplicador = -1.;
//        System.out.println("multiplicador: [" + multiplicador + "]");
        String value = valor.replaceAll("\\D", "");
//        System.out.println("value(0): [" + value + "]");
        if (value.isEmpty()) value = "000";
        for (int i = value.length(); i < 3; i++)
            value = "0" + value;
//        System.out.println("value(1): [" + value + "]");
//
//        System.out.println("value(2): [" + (Double.parseDouble(value.replaceAll("(\\d{1})(\\d{2})$", "$1.$2"))) + "]");
//
//        System.out.println("value(retorno): [" + (multiplicador * (Double.parseDouble(value.replaceAll("(\\d{1})(\\d{2})$", "$1.$2")))) + "]");

        return (multiplicador * (Double.parseDouble(value.replaceAll("(\\d{1})(\\d{2})$", "$1.$2"))));
    }

    public static String gerarMascara(String tipMasc, int qtd, String caractere) {
        String tipM = tipMasc.toLowerCase();
        if (tipM.contains("cnpj")) {
            return "##.###.###/####-##";
        } else if (tipM.contains("!dt")) {
            return "##/##/####";
        } else if (tipM.contains("cpf")) {
            return "###.###.###-##";
        } else if (tipM.contains("barcode") || tipM.contains("ean") || tipM.contains("codbarras")) {
            return "#############";
        } else if (tipM.contains("cep")) {
            return "##.###-###";
        } else if (tipM.contains("moeda") || tipM.contains("numero") || tipM.contains("peso")) {
            String casasDec = "0.";
            for (int i = 0; i < qtd; i++)
                casasDec += "0";
            return casasDec;
        } else if (tipM.contains("ncm")) {
            return "####.##.##";
        } else if (tipM.contains("cest")) {
            return "##.###.##";
        } else if (tipM.contains("nfechave")) {
            return "#### #### #### #### #### #### #### #### #### #### ####";
        } else if (tipM.contains("nfenumero")) {
            return "###.###.###";
        } else if (tipM.contains("nfedocorigem")) {
            return "###########-#";
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
            matcher = PATTERN.matcher(getMascara());
            if (matcher.find())
                alphaAndDigits = textField.getText().replaceAll("[\\-/. \\[\\]]", "");
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
                                if (Character.isDigit(alphaAndDigits.charAt(quant)))
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

    public static String getValueMoeda(String valor, int casaDecimal) {
        String sinal = "";
        String value = String.valueOf(Long.parseLong(valor.replaceAll("\\D", "")));
        for (int i = value.length(); i < (casaDecimal + 1); i++)
            value = "0" + value;

        value = value.replaceAll("(\\d{1})(\\d{" + (casaDecimal + 18) + "})$", "$1.$2");
        value = value.replaceAll("(\\d{1})(\\d{" + (casaDecimal + 15) + "})$", "$1.$2");
        value = value.replaceAll("(\\d{1})(\\d{" + (casaDecimal + 12) + "})$", "$1.$2");
        value = value.replaceAll("(\\d{1})(\\d{" + (casaDecimal + 9) + "})$", "$1.$2");
        value = value.replaceAll("(\\d{1})(\\d{" + (casaDecimal + 6) + "})$", "$1.$2");
        value = value.replaceAll("(\\d{1})(\\d{" + (casaDecimal + 3) + "})$", "$1.$2");
        if (casaDecimal > 0) {
            value = value.replaceAll("(\\d{1})(\\d{" + casaDecimal + "})$", "$1,$2");
        }
        if (valor.substring(0, 1).equals("-")) sinal = "-";
        return sinal + value;
    }

    public void maskFieldMoeda(final JFXTextField textField, int casaDecimal) {
        textField.setAlignment(Pos.CENTER_RIGHT);
        textField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                Platform.runLater(() -> {
                    textField.setText(getValueMoeda(newValue, casaDecimal));
                    textField.positionCaret(newValue.length());
                });
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
