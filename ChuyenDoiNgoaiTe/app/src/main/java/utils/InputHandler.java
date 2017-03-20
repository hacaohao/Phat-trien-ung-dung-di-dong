package utils;

import java.util.List;

import model.Currency;
import model.DataStore;

/**
 * Created by hao on 3/17/2017.
 */

public class InputHandler {
    private static final String NOT_SUPPORT = "Không hỗ trợ";
    private static final String INVALID = "Không hợp lệ";
    private static final String CURRENCY_FORMAT = "%.2f";

    private String mInput;
    private int mFromPosition;
    private int mToPosition;

    public InputHandler(String input){
        mInput = input;
    }

    public void setFromPosition(int fromPosition) {
        mFromPosition = fromPosition;
    }

    public void setToPosition(int toPosition) {
        mToPosition = toPosition;
    }

    public String getResult(){
        String result = "";

        if(!mInput.isEmpty()){
            result = getResultFromNotEmptyInput();
        }

        return result;
    }

    private String getResultFromNotEmptyInput() {
        String result = INVALID;

        try {
            double quantity = Double.parseDouble(mInput);

            if(quantity > 0){
                result = getResultFromValidInput(quantity);
            }
        }catch (Exception exception){
            exception.printStackTrace();
        }

        return result;
    }

    private String getResultFromValidInput(double quantity){
        String result = String.valueOf(quantity);

        if( mFromPosition != mToPosition){
            result = getResultFromDifferentCurrencies(quantity);
        }

        return result;
    }

    private String getResultFromDifferentCurrencies(double quantity){
        DataStore dataStore = DataStore.getInstance();
        List<Currency> currencies = dataStore.getAllCurrencies();

        Currency fromCurrency = currencies.get(mFromPosition);
        Currency toCurrency = currencies.get(mToPosition);

        String result = NOT_SUPPORT;

        if(toCurrency.getBuy() != 0){
            double rate = fromCurrency.getSell() / toCurrency.getBuy();
            double value = rate * quantity;

            result = String.format(CURRENCY_FORMAT, value);
        }

        return result;
    }
}
