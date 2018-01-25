package br.com.sidtmcafe.interfaces;

import javafx.scene.input.KeyCharacterCombination;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

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
    String BD_DATABASE_STB = BD_HOST + BD_PORTA + BD_DATABASE;


    String STYLE_SHEETS = "/styles/css/sidtm.css"; //"/styles/min/sidtm.min.css";
    String PATH_IMAGENS = "/images/icos/";
    String PATH_FXML = "/fxml/";
    String PATH_TOKEN_BIBLIOTECA = "/certificados";
    String TOKEN_EXTENSAO_ARQUIVO = "cfg";


    String LOCAL_TIME_ZONE = "America/Manaus";
    ZoneId FUSO_TIME_ZONE = ZoneId.of(LOCAL_TIME_ZONE);
    LocalDateTime DATAHORA_LOCAL = LocalDateTime.now().atZone(FUSO_TIME_ZONE).toLocalDateTime();
    DateTimeFormatter DTFORMAT_DATA = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    DateTimeFormatter DTFORMAT_HORA = DateTimeFormatter.ofPattern("HH:mm:ss");
    DateTimeFormatter DTFORMAT_DATAHORA = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
    DateTimeFormatter DTFORMAT_DATAHORAFUSO = DateTimeFormatter.ofPattern("dd/MM/yyyy'T'HH:mm:ssXXX");
    DateTimeFormatter DTFORMAT_MYSQL_DATA = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    DateTimeFormatter DTFORMAT_MYSQL_DATAHORA = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    DateTimeFormatter DTFORMAT_MYSQL_DATAHORAFUSO = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ssXXX");

    KeyCombination CODE_KEY_SHIFT_CTRL_POSITIVO = new KeyCodeCombination(KeyCode.PLUS, KeyCombination.SHIFT_DOWN, KeyCombination.CONTROL_DOWN);
    KeyCombination CHAR_KEY_SHIFT_CTRL_POSITIVO = new KeyCharacterCombination("+", KeyCombination.SHIFT_DOWN, KeyCombination.CONTROL_DOWN);
    KeyCombination CODE_KEY_SHIFT_CTRL_NEGATIVO = new KeyCodeCombination(KeyCode.MINUS, KeyCombination.SHIFT_DOWN, KeyCombination.CONTROL_DOWN);
    KeyCombination CHAR_KEY_SHIFT_CTRL_NEGATIVO = new KeyCharacterCombination("-", KeyCombination.SHIFT_DOWN, KeyCombination.CONTROL_DOWN);

    //KeyCombination codeKeyCtrlF12 = new KeyCodeCombination(KeyCombination.CONTROL_DOWN, KeyCode.F12);

//    String certificadoValidoCor = "#0091ff";
//    String certificadoInvalidoCor = "#ff1c00";

}
