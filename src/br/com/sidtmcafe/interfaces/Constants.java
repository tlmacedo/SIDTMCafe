package br.com.sidtmcafe.interfaces;

public interface Constants {

    String LOJA_ID = "1";
    String BD_DATABASE = "cafeperfeito";
    String BD_HOST = "127.0.0.1";
    String BD_DRIVER_CONN = "jdbc:mysql://";
    String BD_PORTA = ":3306/";
    String BD_USER = "root";
    String BD_PASS = "4879";
    String BD_DRIVER = "com.mysql.jdbc.Driver";
    String BD_URL = BD_DRIVER_CONN + BD_HOST + BD_PORTA + BD_DATABASE + "?useSSL=true";


    String PATH_IMAGENS = "/images/material-design-icons/";
    String TIME_ZONE = "America/Manaus";
    String TOKEN_DIRETORIO_BIBLIOTECA = "/certificados";
    String TOKEN_EXTENSAO_BIBLIOTECA = "cfg";
//    String certificadoValidoCor = "#0091ff";
//    String certificadoInvalidoCor = "#ff1c00";

}
