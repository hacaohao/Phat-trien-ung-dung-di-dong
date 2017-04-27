package com.example.hao.xosokienthiet.model;

import com.example.hao.xosokienthiet.io.JSONFileProcessor;
import com.example.hao.xosokienthiet.utils.JSONParser;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

/**
 * Created by hao on 4/25/2017.
 */

public class DataStore {
    private static DataStore sDataStore = new DataStore();
    private Map<String, List<Prizes>> mPrizes;

    private DataStore(){
        JSONParser parser = new JSONParser(new JSONFileProcessor());
        mPrizes = parser.getPrizes();
    }

    public static DataStore getInstance(){
        return sDataStore;
    }

    public List<Prize> getAllPrizesInDate(String area, String date){
        List<Prizes> prizesOfAllDates = mPrizes.get(area);

        for (Prizes prizes : prizesOfAllDates) {
            if(prizes.getDate().equals(date)){
                List<Prize> prize = prizes.getPrize();

                Collections.sort(prize, new Comparator<Prize>() {
                    @Override
                    public int compare(Prize o1, Prize o2) {
                        if(o1.getPosition().equals("DB")){
                            return 1;
                        }else{
                            return -o1.getPosition().compareTo(o2.getPosition());
                        }
                    }
                });

                return prize;
            }
        }

        return new ArrayList<>();
    }

    public List<String> getAllAreas(){
        return new ArrayList<>(mPrizes.keySet());
    }

    public List<String> getAllDatesInOneArea(String area){
        List<String> allDatesInOneArea = new ArrayList<>();
        List<Prizes> prizesOfAllDates = mPrizes.get(area);

        for (Prizes prizes : prizesOfAllDates){
            allDatesInOneArea.add(prizes.getDate());
        }

        return allDatesInOneArea;
    }
}
