package model;

import java.util.List;

import utils.XMLParser;

/**
 * Created by hao on 3/15/2017.
 */

public class DataStore {
    private static final int VND_INDEX = 0;
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

    public List<Currency> getAllCurrencies(){
        return mCurrencies;
    }

    public String[] getCurrencyNames(){
        String[] currencyNames = new String[mCurrencies.size()];

        for(int i = 0; i < mCurrencies.size(); i++){
            currencyNames[i] = mCurrencies.get(i).getCurrencyName();
        }

        return currencyNames;
    }

    public List<Currency> getShownCurrencies(){
        int startIndex = VND_INDEX + 1;
        int endIndex = mCurrencies.size() - 1;
        return mCurrencies.subList(startIndex, endIndex);
    }
}
