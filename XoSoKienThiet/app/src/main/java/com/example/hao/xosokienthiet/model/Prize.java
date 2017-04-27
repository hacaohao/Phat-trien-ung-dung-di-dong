package com.example.hao.xosokienthiet.model;

import java.util.List;

/**
 * Created by hao on 4/25/2017.
 */

public class Prize {
    String mPosition;
    List<String> mNumbers;

    public String getPosition() {
        return mPosition;
    }

    public void setPosition(String position) {
        mPosition = position;
    }

    public List<String> getNumbers() {
        return mNumbers;
    }

    public void setNumbers(List<String> numbers) {
        mNumbers = numbers;
    }
}
