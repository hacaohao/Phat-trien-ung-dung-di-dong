package com.example.hao.chuyendoingoaite;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import background.BackgroundDownload;

public class MainActivity extends AppCompatActivity {
    private Button mButtonWatch;
    private Button mButtonTransfer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(isNetworkAvailable()){
            BackgroundDownload task = new BackgroundDownload();
            task.execute();
        }

        mButtonWatch = (Button) findViewById(R.id.button_watch);
        mButtonWatch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, ShowActivity.class));
            }
        });

        mButtonTransfer = (Button) findViewById(R.id.button_transfer);
        mButtonTransfer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, TransferActivity.class));
            }
        });
    }

    private boolean isNetworkAvailable(){
        ConnectivityManager cm = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        return activeNetwork != null && activeNetwork.isConnectedOrConnecting() && activeNetwork.isAvailable();
    }
}
