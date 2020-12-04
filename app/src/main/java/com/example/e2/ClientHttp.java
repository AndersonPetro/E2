package com.example.e2;

import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class ClientHttp extends AsyncTask<String, Void, String> {

    private String sendHTTPData(String URL) throws MalformedURLException {
        java.net.URL url = new URL(URL);
        int codigoResposta;
        HttpURLConnection conexao = null;
        InputStream is = null;
        String retornoString = "";
        try {

            conexao = (HttpURLConnection) url.openConnection();
            conexao.setDoInput(true);
            conexao.setRequestMethod("GET");
            conexao.setRequestProperty("Content-Type", "application/json");
            conexao.setRequestProperty("Accept", "application/json");
            conexao.connect();

            codigoResposta = conexao.getResponseCode();
            if (codigoResposta < HttpURLConnection.HTTP_BAD_REQUEST) {
                is = conexao.getInputStream();
            } else {
                is = conexao.getErrorStream();
            }
            retornoString = converterInputStreamToString(is);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            ;
            conexao.disconnect();
        }

        return retornoString;
    }



    private String converterInputStreamToString(InputStream is) throws Exception {
        StringBuffer buffer = new StringBuffer();

        BufferedReader br = null;
        String linha;
        try {
            br = new BufferedReader(new InputStreamReader(is));
            while ((linha = br.readLine()) != null) {
                buffer.append(linha);
            }
        } finally {
            br.close();
        }

        return buffer.toString();
    }

    @Override
    protected String doInBackground(String... params) {
        String ret = "";

        try {
            ret =  sendHTTPData(params[0]);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return ret;

    }

}