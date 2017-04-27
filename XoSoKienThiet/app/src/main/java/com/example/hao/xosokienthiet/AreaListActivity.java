package com.example.hao.xosokienthiet;

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
        BackgroundTask task = new BackgroundTask();
        task.execute(tempFilePath);
    }

    @Override
    protected Fragment createFragment() {
        return new AreaListFragment();
    }
}
