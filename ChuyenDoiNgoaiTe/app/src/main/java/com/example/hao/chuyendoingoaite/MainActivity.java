package com.example.hao.chuyendoingoaite;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import java.util.List;

import background.BackgroundDownload;
import model.Currency;
import model.DataStore;

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

        mButtonWatch = (Button) findViewById(R.id.button_watch);
        mButtonWatch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ShowActivity.class);
                startActivity(intent);
            }
        });

        mButtonTransfer = (Button) findViewById(R.id.button_transfer);
        mButtonTransfer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, TransferActivity.class);
                startActivity(intent);
            }
        });
    }
}
