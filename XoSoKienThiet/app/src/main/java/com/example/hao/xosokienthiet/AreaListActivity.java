package com.example.hao.xosokienthiet;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.example.hao.xosokienthiet.background.BackgroundTask;

/**
 * Created by hao on 4/26/2017.
 */

public class AreaListActivity extends SingleFragmentActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        String tempFilePath = getCacheDir().getPath() + "/" + "xskt.json";

        if(isNetworkAvailable()){
            BackgroundTask task = new BackgroundTask();
            task.execute(tempFilePath);
        }
    }

    @Override
    protected Fragment createFragment() {
        return new AreaListFragment();
    }

    private boolean isNetworkAvailable(){
        ConnectivityManager cm = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();

        return activeNetwork != null && activeNetwork.isConnectedOrConnecting() && activeNetwork.isAvailable();
    }
}
