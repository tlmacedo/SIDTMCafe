package br.com.sidtmcafe.configuracao;

public class VariaveisSistema {

    public VariaveisSistema() {
        System.setProperty("LojaId", "1");

        System.setProperty("BD_DATABASE", "cafeperfeito");
        System.setProperty("BD_HOST", "127.0.0.1");
        System.setProperty("BD_DRIVER_CONN", "jdbc:mysql://");
        System.setProperty("BD_PORTA", ":3306/");
        System.setProperty("BD_USER", "root");
        System.setProperty("BD_PASS", "4879");
        System.setProperty("BD_DRIVER", "com.mysql.jdbc.Driver");

        System.setProperty("zoneId", "America/Manaus");
        System.setProperty("tokenLibDiretorio", "/certificados");
        System.setProperty("tokenLibExtensao", "cfg");
        System.setProperty("certificadoValidoCor", "#0091ff");
        System.setProperty("certificadoInvalidoCor", "#ff1c00");
    }
}
