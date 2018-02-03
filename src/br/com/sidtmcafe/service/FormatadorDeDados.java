package br.com.sidtmcafe.service;

import javax.swing.text.MaskFormatter;

public class FormatadorDeDados {

    public static String getFormat(String value, String format) {
        MaskFormatter mf = null;
        try {
            switch (format) {
                case "cnpj":
                    mf = new MaskFormatter("##.###.###/####-##");
                    break;
            }
            mf.setValueContainsLiteralCharacters(false);
            return mf.valueToString(value);
        } catch (Exception ex) {
            return value;
        }
    }
}
