package com.example.hao.chuyendoingoaite;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.TextView;

import java.util.List;

import model.Currency;
import model.DataStore;

public class ShowActivity extends AppCompatActivity {
    private static final int VND_INDEX = 0;
    private GridView mGridView;
    private DataStore mDataStore = DataStore.getInstance();
    private List<Currency> mCurrencies;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);

        mCurrencies = mDataStore.getCurrencies();

        mGridView = (GridView) findViewById(R.id.grid_view_main);
        mGridView.setAdapter(new CurrencyAdapter(this, getShownCurrencies()));
    }

    private List<Currency> getShownCurrencies(){
        int startIndex = VND_INDEX + 1;
        int endIndex = mCurrencies.size() - 1;
        return mCurrencies.subList(startIndex, endIndex);
    }

    private class CurrencyAdapter extends BaseAdapter{
        private Context mContext;
        private List<Currency> mCurrencies;

        public CurrencyAdapter(Context context, List<Currency> currencies) {
            mContext = context;
            mCurrencies = currencies;
        }

        @Override
        public int getCount() {
            return mCurrencies.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View gridView;

            if(convertView == null){
                Currency currency = mCurrencies.get(position);
                gridView = inflater.inflate(R.layout.grid_view_item, null);
                bindDataToView(currency, gridView);
            }else{
                gridView = convertView;
            }

            return gridView;
        }

        private void bindDataToView(Currency currency, View gridView){
            TextView textViewCurrencyCode = (TextView) gridView.findViewById(R.id.grid_item_text_view_currency_code);
            textViewCurrencyCode.setText(currency.getCurrencyCode());

            TextView textViewCurrencyName = (TextView) gridView.findViewById(R.id.grid_item_text_view_currency_name);
            textViewCurrencyName.setText(currency.getCurrencyName());

            TextView textViewBuy = (TextView) gridView.findViewById(R.id.grid_item_text_view_buy);
            String buyPrice = currency.getBuy() == 0 ? "-" : String.format("%.2f", currency.getBuy());
            textViewBuy.setText(buyPrice);

            TextView textViewSell = (TextView) gridView.findViewById(R.id.grid_item_text_view_sell);
            textViewSell.setText(String.format("%.2f", currency.getSell()));
        }
    }
}
