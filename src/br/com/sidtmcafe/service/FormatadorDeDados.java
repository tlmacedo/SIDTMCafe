package br.com.sidtmcafe.service;

import com.jfoenix.controls.JFXTextField;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TextField;

import javax.swing.text.MaskFormatter;
import java.text.ParseException;

public class FormatadorDeDados {

    public static String getFormatado(String value, String format) {
        MaskFormatter mf = null;
        try {
            mf = new MaskFormatter(geraMascara(format, 0, "@"));
            mf.setValueContainsLiteralCharacters(false);
            return mf.valueToString(value);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return value;
    }

    public static String geraMascara(String mascara, int qtd, String caractere) {
        switch (mascara.toLowerCase()) {
            case "cnpj":
                return "##.###.###/####-##";
            case "cpf":
                return "###.###.###-##";
            case "cep":
                return "##.###-###";
            case "telefone":
                return "# ####-####";
            default:
                String mf = "";
                for (int i = 0; i < qtd; i++) {
                    mf += caractere;
                }
                return mf;
        }
    }

//    public static void campoMask(JFXTextField textField, boolean numero, boolean texto, boolean maiuscula, String mascara) {
//        //public static String campoMask(String textField, String mascara) {
//        System.out.println("textField: " + textField);
//        String alphaAndDigits = textField.getText().replaceAll("[\\-/.]", "");
//        System.out.println("alphaAndDigits: " + alphaAndDigits);
//        StringBuilder resultado = new StringBuilder();
//        int i = 0;
//        int quant = 0;
//        if (textField.getText().length() <= mascara.length()) {
//            while (i < mascara.length()) {
//                if (quant < alphaAndDigits.length()) {
//                    switch (mascara.substring(i, i + 1)) {
//                        case "@":
//                            resultado.append(alphaAndDigits.substring(quant, quant + 1).toUpperCase());
//                            quant++;
//                            break;
//                        case "?":
//                            resultado.append(alphaAndDigits.substring(quant, quant + 1).toLowerCase());
//                            quant++;
//                            break;
//                        case "#":
//                            if (Character.isDigit(alphaAndDigits.charAt(quant))) {
//                                resultado.append(alphaAndDigits.substring(quant, quant + 1));
//                            } else {
//                                quant++;
//                            }
//                            break;
//                        default:
//                            resultado.append(mascara.substring(i, i + 1));
//                    }
//                }
//
//
////                if (quant < alphaAndDigits.length()) {
////                    if ("#".equals(mascara.substring(i, i + 1))) {
////                        resultado.append(alphaAndDigits.substring(quant, quant + 1));
////                        quant++;
////                    } else {
////                        resultado.append(mascara.substring(i, i + 1));
////                    }
////                }
//                i++;
//            }
//        } else {
//            textField.setText(textField.getText().substring(0, mascara.length()));
//        }
//
//    }


    public static void campoMask(JFXTextField textField, String mascara) {
        maxField(textField, mascara.length());
        textField.lengthProperty().addListener((ObservableValue<? extends Number> ov, Number n1, Number n2) -> {
            String alphaAndDigits = textField.getText().replaceAll("[\\-/.]", "");
            StringBuilder resultado = new StringBuilder();
            int i = 0;
            int quant = 0;
            if (n2.intValue() > n1.intValue()) {
                if (textField.getText().length() <= mascara.length()) {
                    while (i < mascara.length()) {
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
                    positionCaret(textField);
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

    /**
     * @param textField TextField.
     * @param length    Tamanho do campo.
     */
    private static void maxField(final TextField textField, final Integer length) {
        textField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String oldValue, String newValue) {
                if (newValue.length() > length)
                    textField.setText(oldValue);
            }
        });
    }

}
