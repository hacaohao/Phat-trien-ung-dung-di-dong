package utils;

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
    private static final int MAX_SIZE_BUFFER = 1024;

    public void downloadFile() {
        try {
            setupAndDownload();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    private void setupAndDownload() throws Exception {
        HttpURLConnection connection = getConnection();
        FileOutputStream fileOutput = getFileOutputStream();
        downloadAndSave(connection, fileOutput);
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
        File file = new File(XMLParser.FILE_PATH);
        return new FileOutputStream(file);
    }

    private void downloadAndSave(HttpURLConnection connection, FileOutputStream fileOutput) throws Exception {
        InputStream inputStream = connection.getInputStream();

        byte[] buffer = new byte[MAX_SIZE_BUFFER];
        int bufferLength;

        while ( (bufferLength = inputStream.read(buffer)) >= 0 ) {
            fileOutput.write(buffer, 0, bufferLength);
        }

        fileOutput.close();
        connection.disconnect();
    }
}
