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

    public Currency() {
    }

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

    public void setCurrencyCode(String currencyCode) {
        mCurrencyCode = currencyCode;
    }

    public String getCurrencyName() {
        return mCurrencyName;
    }

    public void setCurrencyName(String currencyName) {
        mCurrencyName = currencyName;
    }

    public double getBuy() {
        return mBuy;
    }

    public void setBuy(double buy) {
        mBuy = buy;
    }

    public double getTransfer() {
        return mTransfer;
    }

    public void setTransfer(double transfer) {
        mTransfer = transfer;
    }

    public double getSell() {
        return mSell;
    }

    public void setSell(double sell) {
        mSell = sell;
    }
}
