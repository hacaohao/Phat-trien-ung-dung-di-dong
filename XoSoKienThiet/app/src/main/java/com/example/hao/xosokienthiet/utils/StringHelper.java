package com.example.hao.xosokienthiet.utils;

import java.util.List;

/**
 * Created by hao on 4/26/2017.
 */

public class StringHelper {
    public static String translate(String area){
        switch (area){
            case "AG":
                return "An Giang";
            case "BD":
                return "Binh Duong";
            case "BL":
                return "Bac Lieu";
            case "BP":
                return "Binh Phuoc";
            case "BTH":
                return "Binh Thuan";
            case "CM":
                return "Ca Mau";
            case "CT":
                return "Can Tho";
            case "HCM":
                return "Ho Chi Minh";
            default:
                return area;
        }
    }

    public static String constructNumber(List<String> numbers){
        String finalText = "";

        for(int i=0; i < numbers.size(); i++){
            finalText += numbers.get(i);
            if(i != numbers.size() - 1){
                finalText += "\n";
            }
        }

        return finalText;
    }

    public static String constructPosition(List<String> numbers, String position){
        int padding = (numbers.size() - 1) / 2;
        String finalText = "";

        finalText = addPadding(finalText, padding);
        finalText += position;
        finalText = addPadding(finalText, padding);

        return finalText;
    }

    private static String addPadding(String originalText, int paddingSize){
        String finalText = originalText;

        for(int i=0; i < paddingSize; i++){
            finalText += "\n";
        }

        return finalText;
    }
}
