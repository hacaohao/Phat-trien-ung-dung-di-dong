package model;

/**
 * Created by hao on 3/15/2017.
 */

public class Currency {
    private String mCurrencyCode;
    private String mCurrencyName;
    private double mBuy;
    private double mTransfer;
    private double mSell;

    public Currency(String currencyCode, String currencyName, double buy, double transfer, double sell) {
        mCurrencyCode = currencyCode;
        mCurrencyName = currencyName;
        mBuy = buy;
        mTransfer = transfer;
        mSell = sell;
    }

    public String getCurrencyCode() {
        return mCurrencyCode;
    }

    public String getCurrencyName() {
        return mCurrencyName;
    }

    public double getBuy() {
        return mBuy;
    }

    public double getSell() {
        return mSell;
    }
}
