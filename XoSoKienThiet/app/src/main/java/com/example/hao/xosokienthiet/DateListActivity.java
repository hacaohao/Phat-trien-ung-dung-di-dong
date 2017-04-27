package com.example.hao.xosokienthiet;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;

/**
 * Created by hao on 4/26/2017.
 */

public class DateListActivity extends SingleFragmentActivity {
    private static final String EXTRA_AREA = "com.example.hao.xosokienthiet.area";

    @Override
    protected Fragment createFragment() {
        return DateListFragment.newFragment(getIntent().getStringExtra(EXTRA_AREA));
    }

    public static Intent newIntent(Context packageContext, String area){
        Intent intent = new Intent(packageContext, DateListActivity.class);
        intent.putExtra(EXTRA_AREA, area);
        return intent;
    }
}
