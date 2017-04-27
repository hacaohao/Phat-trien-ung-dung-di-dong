package com.example.hao.xosokienthiet.io;

import android.os.Environment;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.nio.channels.FileChannel;

/**
 * Created by hao on 4/25/2017.
 */

public class JSONFileProcessor {
    private static final String FILE_PATH = Environment.getExternalStorageDirectory().getAbsolutePath() + "/" + "xskt.json";
    private static final int MAX_SIZE_BUFFER = 1024;
    private String mTempFilePath;

    public void write(InputStream inputStream) {
        try {
            writeToTempFile(inputStream);
            copyToRealFile();
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
    }

    public void writeToTempFile(InputStream inputStream) throws IOException {
        FileOutputStream fileOutput = new FileOutputStream(new File(mTempFilePath));

        byte[] buffer = new byte[MAX_SIZE_BUFFER];
        int bufferLength;

        while ( (bufferLength = inputStream.read(buffer)) >= 0 ) {
            fileOutput.write(buffer, 0, bufferLength);
        }

        fileOutput.close();
    }

    public String read() throws IOException{
        BufferedReader fileBuffer = new BufferedReader(new FileReader(new File(FILE_PATH)));
        StringBuffer stringBuffer = new StringBuffer();
        String line;

        while( (line = fileBuffer.readLine()) != null ){
            stringBuffer.append(line);
        }

        fileBuffer.close();
        return stringBuffer.toString();
    }

    public void setTempFilePath(String tempFilePath) {
        mTempFilePath = tempFilePath;
    }

    public void copyToRealFile() throws IOException {
        FileChannel inChannel = new FileInputStream(mTempFilePath).getChannel();
        FileChannel outChannel = new FileOutputStream(FILE_PATH).getChannel();
        try {
            inChannel.transferTo(0, inChannel.size(), outChannel);
        } finally {
            if (inChannel != null)
                inChannel.close();
            if (outChannel != null)
                outChannel.close();
        }
    }
}
