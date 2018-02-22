package br.com.sidtmcafe.model.dao;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.SocketTimeoutException;
import java.net.URL;

public class BuscaWebService {
    Object retorno;
    BufferedReader bufferedReader;
    StringBuilder stringBuilder;
    String linhaRetorno = null;

    HttpURLConnection urlConnection;

    JSONObject jsonObject;

    public JSONObject getJsonObjectWebService(String strURL) {
        jsonObject = null;
        try {
            retorno = new URL(strURL).openStream();
            jsonObject = new JSONObject(getStringBuilder(retorno).toString());
        } catch (MalformedURLException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
        }
        return jsonObject;
    }

    public JSONObject getJsonObjectHttpUrlConnection(String strURL, String token, String compl) {
        jsonObject = null;
        try {
            urlConnection = (HttpURLConnection) new URL(strURL).openConnection();
            urlConnection.setConnectTimeout(30000);
            urlConnection.setReadTimeout(30000);
            urlConnection.connect();
            retorno = urlConnection.getInputStream();
            if (urlConnection.getResponseCode() == 200)
                jsonObject = new JSONObject(getStringBuilder(retorno).toString());
        } catch (SocketTimeoutException ex) {
            try {
                urlConnection = (HttpURLConnection) new URL(strURL + compl).openConnection();
                urlConnection.setConnectTimeout(40000);
                urlConnection.setReadTimeout(40000);
                urlConnection.setRequestProperty("Authorization", "Bearer " + token);
                urlConnection.connect();
                retorno = urlConnection.getInputStream();
                if (urlConnection.getResponseCode() == 200)
                    jsonObject = new JSONObject(getStringBuilder(retorno));
            } catch (Exception ex1) {
                ex1.printStackTrace();
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return jsonObject;
    }

    StringBuilder getStringBuilder(Object retorno) {
        try {
            bufferedReader = new BufferedReader(new InputStreamReader((InputStream) retorno, "UTF-8"));
            stringBuilder = new StringBuilder();
            //System.out.println("linhaRetorno: ");
            while ((linhaRetorno = bufferedReader.readLine()) != null) {
                //System.out.println(linhaRetorno);
                stringBuilder.append(linhaRetorno);
            }
        } catch (Exception ex) {
        }
        return stringBuilder;
    }

}
