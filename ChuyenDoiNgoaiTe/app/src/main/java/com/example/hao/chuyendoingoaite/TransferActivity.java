package com.example.hao.chuyendoingoaite;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.List;

import model.Currency;
import model.DataStore;

public class TransferActivity extends AppCompatActivity {
    private Spinner mSpinnerFrom;
    private Spinner mSpinnerTo;
    private EditText mEditTextQuantity;
    private TextView mTextViewResult;

    private DataStore mDataStore;
    private List<Currency> mCurrencies;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transfer);

        mDataStore = DataStore.getInstance();
        String[] currencyNames = mDataStore.getCurrencyNames();
        mCurrencies = mDataStore.getCurrencies();

        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, currencyNames);

        mSpinnerFrom = (Spinner) findViewById(R.id.spinner_from);
        mSpinnerFrom.setAdapter(adapter);
        mSpinnerFrom.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                setResult(mEditTextQuantity.getText().toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        mSpinnerTo = (Spinner) findViewById(R.id.spinner_to);
        mSpinnerTo.setAdapter(adapter);
        mSpinnerFrom.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                setResult(mEditTextQuantity.getText().toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        mTextViewResult = (TextView) findViewById(R.id.text_view_result);

        mEditTextQuantity = (EditText) findViewById(R.id.edit_text_quantity);
        mEditTextQuantity.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                setResult(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private void setResult(String input){
        String result = "";
        if(input.equals("")){
            result = "";
        }else{
            try{
                double quantity = Double.parseDouble(input);

                if(quantity <= 0){
                    result = "Không hợp lệ";
                }else{
                    int fromPosition = mSpinnerFrom.getSelectedItemPosition();
                    int toPosition = mSpinnerTo.getSelectedItemPosition();

                    if( fromPosition == toPosition){
                        result = String.valueOf(quantity);
                    }else{
                        Currency fromCurrency = mCurrencies.get(fromPosition);
                        Currency toCurrency = mCurrencies.get(toPosition);

                        if(toCurrency.getBuy() == 0){
                            result = "Không hỗ trợ";
                        }else{
                            double rate = fromCurrency.getSell() / toCurrency.getBuy();
                            double value = rate * quantity;

                            result = String.valueOf(value);
                        }
                    }
                }
            }catch (Exception e){
                result = "Không hợp lệ";
            }
        }

        mTextViewResult.setText(result);
    }
}
