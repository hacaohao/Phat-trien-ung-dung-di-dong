package utils;

import java.util.List;

import model.Currency;
import model.DataStore;

/**
 * Created by hao on 3/17/2017.
 */

public class InputHandler {
    private String mInput;
    private int mFromPosition;
    private int mToPosition;
    private List<Currency> mCurrencies;

    public InputHandler(String input){
        mInput = input;
        mCurrencies = DataStore.getInstance().getCurrencies();
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
        String result = "Không hợp lệ";

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
        Currency fromCurrency = mCurrencies.get(mFromPosition);
        Currency toCurrency = mCurrencies.get(mToPosition);

        String result = "Không hỗ trợ";

        if(toCurrency.getBuy() != 0){
            double rate = fromCurrency.getSell() / toCurrency.getBuy();
            double value = rate * quantity;

            result = String.format("%.2f", value);
        }

        return result;
    }
}
