package br.com.sidtmcafe.service;

import br.com.sidtmcafe.interfaces.Constants;

public class ValidadorDeDados implements Constants {
    private static final int[] pesoCpf = {11, 10, 9, 8, 7, 6, 5, 4, 3, 2};
    private static final int[] pesoCnpj = {6, 5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2};

    public static boolean isCnpjCpfValido(final String numero) {
        numero.replaceAll("[\\-/. \\[\\]]", "");

        if ((numero == null) || (numero.length() != 11 && numero.length() != 14)
                || (numero.matches(numero.charAt(0) + "{11}") && numero.matches(numero.charAt(0) + "{14}")))
            return false;
        String dv = numero.substring(numero.length() - 2);
        String base = numero.substring(0, numero.length() - 2);
        int[] peso = pesoCpf;
        if (base.length() == 12)
            peso = pesoCnpj;
        
        return (dv.equals(calculaDV(base, peso)));
    }

    private static String calculaDV(final String str, final int[] peso) {
        Integer[] digitoDV = {0, 0};
        String valor = str;
        for (int i = 0; i < 2; i++) {
            int soma = 0;
            if (i == 1)
                valor += digitoDV[0];
            for (int indice = valor.length() - 1, digito; indice >= 0; indice--) {
                digito = Integer.parseInt(valor.substring(indice, indice + 1));
                soma += digito * peso[peso.length - valor.length() + indice];
            }
            soma = 11 - soma % 11;
            digitoDV[i] = soma > 9 ? 0 : soma;
        }
        return digitoDV[0].toString() + digitoDV[1].toString();
    }
}
