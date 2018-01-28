package br.com.sidtmcafe.model.dao;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

public class BuscaWebService {
    Object retorno;
    BufferedReader bufferedReader;
    StringBuilder stringBuilder;
    String linhaRetorno = null;

    JSONObject jsonObject;

    public JSONObject getJsonObjectWebService(String strURL) {
        try {
            retorno = new URL(strURL).openStream();
            bufferedReader = new BufferedReader(new InputStreamReader((InputStream) retorno, "UTF-8"));
            stringBuilder = new StringBuilder();

            while ((linhaRetorno = bufferedReader.readLine()) != null) {
                stringBuilder.append(linhaRetorno);
            }

            jsonObject = new JSONObject(stringBuilder.toString());

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return jsonObject;
    }
}
