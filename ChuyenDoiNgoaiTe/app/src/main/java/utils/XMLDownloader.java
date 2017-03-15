package utils;

import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by hao on 3/15/2017.
 */

public class XMLDownloader {
    private static final String SITE = "https://www.vietcombank.com.vn/exchangerates/ExrateXML.aspx";
    private static final String REQUEST_METHOD = "GET";

    public void downloadFile() {
        try {
            HttpURLConnection connection = getConnection();
            FileOutputStream fileOutput = getFileOutputStream();
            downloadAndSave(connection, fileOutput);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private HttpURLConnection getConnection() throws IOException {
        URL url = new URL(SITE);
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

        urlConnection.setRequestMethod(REQUEST_METHOD);
        urlConnection.setDoOutput(true);
        urlConnection.connect();

        return urlConnection;
    }

    private FileOutputStream getFileOutputStream() throws FileNotFoundException {
        String SDCardRoot = Environment.getExternalStorageDirectory().getAbsolutePath();
        File file = new File(SDCardRoot + "/" + XMLParser.FILE_NAME);

        return new FileOutputStream(file);
    }

    private void downloadAndSave(HttpURLConnection connection, FileOutputStream fileOutput){
        try {
            InputStream inputStream = connection.getInputStream();

            int totalSize = connection.getContentLength();
            int downloadedSize = 0;

            byte[] buffer = new byte[1024];
            int bufferLength = 0;

            while ( (bufferLength = inputStream.read(buffer)) >= 0 ) {
                fileOutput.write(buffer, 0, bufferLength);
                downloadedSize += bufferLength;
            }

            fileOutput.close();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            connection.disconnect();
        }
    }
}
