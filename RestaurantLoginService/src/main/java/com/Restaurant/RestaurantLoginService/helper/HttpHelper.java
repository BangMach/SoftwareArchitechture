package com.Restaurant.RestaurantLoginService.helper;

import com.Restaurant.RestaurantLoginService.model.Account;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class HttpHelper {
    private static HttpURLConnection httpURLConnection;
    private static final String URL = "http://localhost:8080/";
    private static ObjectMapper mapper = new ObjectMapper();

    public static Account setUpGetConnection(String path) throws IOException {
        setupConnection(path, "GET");
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
        String line;
        StringBuilder stringBuilder = new StringBuilder();
        while ((line = bufferedReader.readLine()) != null) {
            stringBuilder.append(line);
        }
        String result = stringBuilder.toString();
        return (result.equals("")) ? null : mapper.readValue(result, Account.class);
    }

    private static void setupConnection(String path, String method) throws MalformedURLException {
        java.net.URL url = new URL(HttpHelper.URL + path);
        httpURLConnection = null;
        try {
            httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod(method);
        } catch (IOException exception) {
            exception.printStackTrace();
        } finally {
            if (httpURLConnection != null) {
                httpURLConnection.disconnect();
            }
        }
    }


}
