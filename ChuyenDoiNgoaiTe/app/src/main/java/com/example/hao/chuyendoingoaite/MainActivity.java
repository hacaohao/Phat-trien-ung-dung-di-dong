package com.example.hao.chuyendoingoaite;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;

import java.util.List;

import background.BackgroundDownload;
import model.Currency;
import model.DataStore;
import utils.XMLDownloader;

public class MainActivity extends AppCompatActivity {
    private Button mButtonWatch;
    private Button mButtonTransfer;
    private DataStore mDataStore;
    private List<Currency> mCurrencies;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BackgroundDownload task = new BackgroundDownload();
        task.execute();

        mDataStore = DataStore.getInstance();
        mCurrencies = mDataStore.getCurrencies();

        mButtonWatch = (Button) findViewById(R.id.button_watch);
        mButtonTransfer = (Button) findViewById(R.id.button_transfer);
    }
}
