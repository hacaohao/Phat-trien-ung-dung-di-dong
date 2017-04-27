package com.example.hao.xosokienthiet;

import android.content.Intent;

import com.example.hao.xosokienthiet.model.DataStore;

import java.util.List;

/**
 * Created by hao on 4/26/2017.
 */

public class AreaListFragment extends TemplateListFragment {
    @Override
    protected List<String> getDatas() {
        DataStore dataStore = DataStore.getInstance();
        return dataStore.getAllAreas();
    }

    @Override
    protected Intent getIntent(String data) {
        return DateListActivity.newIntent(getActivity(), data);
    }
}
