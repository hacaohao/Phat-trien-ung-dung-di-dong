package com.example.hao.xosokienthiet;

import android.content.Intent;
import android.os.Bundle;

import com.example.hao.xosokienthiet.model.DataStore;

import java.util.List;

public class DateListFragment extends TemplateListFragment {
    private static final String ARG_AREA = "area";

    @Override
    protected List<String> getDatas() {
        DataStore dataStore = DataStore.getInstance();
        return dataStore.getAllDatesInOneArea(getArguments().getString(ARG_AREA));
    }

    @Override
    protected Intent getIntent(String data) {
        return ResultActivity.newIntent(getActivity(), getArguments().getString(ARG_AREA), data);
    }

    public static DateListFragment newFragment(String area){
        Bundle args = new Bundle();
        args.putSerializable(ARG_AREA, area);

        DateListFragment fragment = new DateListFragment();
        fragment.setArguments(args);

        return fragment;
    }
}
