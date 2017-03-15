package model;

import android.util.Log;

import java.util.List;

import utils.XMLParser;

/**
 * Created by hao on 3/15/2017.
 */

public class DataStore {
    private static DataStore sDataStore;
    private List<Currency> mCurrencies;

    private DataStore(){
        XMLParser parser = new XMLParser();
        mCurrencies = parser.getCurrencies();
    }

    public static DataStore getInstance(){
        if(sDataStore == null){
            sDataStore = new DataStore();
        }

        return sDataStore;
    }

    public List<Currency> getCurrencies(){
        return mCurrencies;
    }
}
