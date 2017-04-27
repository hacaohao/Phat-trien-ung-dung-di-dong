package com.example.hao.xosokienthiet.utils;

import com.example.hao.xosokienthiet.io.JSONFileProcessor;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by hao on 4/25/2017.
 */

public class JSONDownloader {
    private static final String SITE = "http://thanhhungqb.tk:8080/kqxsmn";

    private JSONFileProcessor mFileProcessor;

    public JSONDownloader(JSONFileProcessor fileProcessor) {
        mFileProcessor = fileProcessor;
    }

    public void download() {
        try {
            process();
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    private void process() throws IOException {
        HttpURLConnection connection = getConnection();
        InputStream inputStream = new BufferedInputStream(connection.getInputStream());

        mFileProcessor.write(inputStream);
        connection.disconnect();
    }

    private HttpURLConnection getConnection() throws IOException {
        System.setProperty("http.keepAlive", "false");
        System.setProperty("http.maxConnections", "1");

        URL url = new URL(SITE);
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        urlConnection.connect();
        return urlConnection;
    }
}
