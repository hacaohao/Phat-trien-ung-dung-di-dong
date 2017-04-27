package com.example.hao.xosokienthiet.model;

import java.util.List;

/**
 * Created by hao on 4/25/2017.
 */

public class Prizes {
    String mDate;
    List<Prize> mPrize;

    public String getDate() {
        return mDate;
    }

    public void setDate(String date) {
        mDate = date;
    }

    public List<Prize> getPrize() {
        return mPrize;
    }

    public void setPrize(List<Prize> prize) {
        mPrize = prize;
    }
}
