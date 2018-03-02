package br.com.sidtmcafe.interfaces;

import javafx.scene.image.Image;
import javafx.scene.input.*;

import javax.swing.text.Element;
import javax.swing.text.html.ImageView;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Set;
import java.util.regex.Pattern;

public interface Constants {

    Pattern PATTERN = Pattern.compile("[\\-/. \\[\\]]");
    String COPYRIGHT = "Café Perfeito " + "\u00a9 " + LocalDate.now().getYear();
    String LOJA_ID = "1";

    /*Constants para conexao Mysql*/
    String BD_DATABASE = "cafeperfeito";
    String BD_HOST = "127.0.0.1";
    String BD_DRIVER_CONN = "jdbc:mysql://";
    String BD_PORTA = ":3306/";
    String BD_USER = "root";
    String BD_PASS = "4879";
    String BD_DRIVER = "com.mysql.jdbc.Driver";
    String BD_URL = BD_DRIVER_CONN + BD_HOST + BD_PORTA + BD_DATABASE + "?useSSL=true";//"?useTimezone=true&serverTimezone=UTC&useSSL=true";
    String BD_DATABASE_STB = BD_HOST + BD_PORTA + BD_DATABASE;

    /* Valores para webServices */
    String WS_FONTE_DE_DADOS_LOGIN_NAME = "t.l.macedo";
    String WS_FONTE_DE_DADOS_LOGIN_SENHA = "Tlm487901";


    /* Constants para estilos do sistema*/
    String STYLE_SHEETS = "/styles/css/sidtm.css"; //"/styles/min/sidtm.min.css";
    String PATH_IMAGENS = "/images/icos/";
    String PATH_FXML = "/fxml/";
    String[] IMAGE_LOADING = {PATH_IMAGENS + "img_loading_coffee0.gif",
            PATH_IMAGENS + "img_loading_coffee1.gif",
            PATH_IMAGENS + "img_loading_coffee2.gif",
            PATH_IMAGENS + "img_loading_coffee3.gif",
            PATH_IMAGENS + "img_loading_coffee4.gif",
            PATH_IMAGENS + "img_loading_coffee5.gif",
            PATH_IMAGENS + "img_loading_coffee6.gif",
            PATH_IMAGENS + "img_loading_coffee7.gif",
            PATH_IMAGENS + "img_loading_coffee8.gif",
            PATH_IMAGENS + "img_loading_coffee9.gif",
            PATH_IMAGENS + "img_loading_coffee10.gif"};
    String IMAGE_CAFE_PERFEITO_240DP = PATH_IMAGENS + "ic_cafe_perfeito_240dp.png";
    String PATH_TOKEN_BIBLIOTECA = "/certificados";
    String TOKEN_EXTENSAO_ARQUIVO = "cfg";

    /* Constants para utilizacao de datas e horas */
    String LOCAL_TIME_ZONE = "America/Manaus";
    ZoneId MY_ZONE_TIME = ZoneId.of(LOCAL_TIME_ZONE);
    LocalDateTime DATAHORA_LOCAL = LocalDateTime.now().atZone(MY_ZONE_TIME).toLocalDateTime();
    DataFormat DT_DATA = new DataFormat("dd/MM/yyyy");
    DateTimeFormatter DTF_DATA = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    DateTimeFormatter DTF_HORA = DateTimeFormatter.ofPattern("HH:mm:ss");
    DateTimeFormatter DTF_DATAHORA = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
    DateTimeFormatter DTF_DATAHORAFUSO = DateTimeFormatter.ofPattern("dd/MM/yyyy'T'HH:mm:ssXXX");
    DateTimeFormatter DTF_MYSQL_DATA = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    DateTimeFormatter DTF_MYSQL_DATAHORA = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    DateTimeFormatter DTF_MYSQL_DATAHORAFUSO = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ssXXX");

    KeyCombination CODE_KEY_SHIFT_CTRL_POSITIVO = new KeyCodeCombination(KeyCode.PLUS, KeyCombination.SHIFT_DOWN, KeyCombination.CONTROL_DOWN);
    KeyCombination CHAR_KEY_SHIFT_CTRL_POSITIVO = new KeyCharacterCombination("+", KeyCombination.SHIFT_DOWN, KeyCombination.CONTROL_DOWN);
    KeyCombination CODE_KEY_SHIFT_CTRL_NEGATIVO = new KeyCodeCombination(KeyCode.MINUS, KeyCombination.SHIFT_DOWN, KeyCombination.CONTROL_DOWN);
    KeyCombination CHAR_KEY_SHIFT_CTRL_NEGATIVO = new KeyCharacterCombination("-", KeyCombination.SHIFT_DOWN, KeyCombination.CONTROL_DOWN);

    /* ****** web services */

    /* receitaws */
    String WS_RECEITAWS_URL = "https://www.receitaws.com.br/v1/cnpj/";
    String WS_RECEITAWS_TOKEN = "1953100c818519b43b895394c25b0fa38525e2800587a8b140a42e6baff7a8af";

    /* webmania */
    String WS_WEBMANIA_URL = "https://webmaniabr.com/api/1/cep/";
    String WS_WEBMANIA_APP_KEY = "GOxHMxSXNbX99szfTE7A6mMDmb26P1Ch";
    String WS_WEBMANIA_APP_SECRET = "kMx5QczId1GqVLbpZ52qgEgfRhiKWFPZfa39IZfp6NZhFmTq";

    /* postmon */
    String WS_POSTMON_URL = "http://api.postmon.com.br/v1/cep/";


}
