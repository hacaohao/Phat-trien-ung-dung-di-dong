package com.example.hao.xosokienthiet.background;

import android.os.AsyncTask;

import com.example.hao.xosokienthiet.io.JSONFileProcessor;
import com.example.hao.xosokienthiet.model.DataStore;
import com.example.hao.xosokienthiet.utils.JSONDownloader;

/**
 * Created by hao on 4/25/2017.
 */

public class BackgroundTask extends AsyncTask<String, Void, Void> {
    @Override
    protected Void doInBackground(String... params) {
        JSONFileProcessor io = new JSONFileProcessor();
        io.setTempFilePath(params[0]);
        JSONDownloader downloader = new JSONDownloader(io);
        downloader.download();
        return null;
    }

    @Override
    protected void onPostExecute(Void result) {
        super.onPostExecute(result);
        DataStore.getInstance();
    }
}
