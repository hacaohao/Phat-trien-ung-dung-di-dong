package com.example.hao.xosokienthiet;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;

/**
 * Created by hao on 4/26/2017.
 */

public class ResultActivity extends SingleFragmentActivity {
    private static final String EXTRA_AREA = "com.example.hao.xosokienthiet.area";
    private static final String EXTRA_DATE = "com.example.hao.xosokienthiet.date";

    @Override
    protected Fragment createFragment() {
        String area = getIntent().getStringExtra(EXTRA_AREA);
        String date = getIntent().getStringExtra(EXTRA_DATE);
        return ResultFragment.newFragment(area, date);
    }

    public static Intent newIntent(Context packageContext, String area, String date){
        Intent intent = new Intent(packageContext, ResultActivity.class);
        intent.putExtra(EXTRA_AREA, area);
        intent.putExtra(EXTRA_DATE, date);
        return intent;
    }
}
