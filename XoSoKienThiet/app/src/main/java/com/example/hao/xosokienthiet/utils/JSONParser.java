package com.example.hao.xosokienthiet.utils;

import com.example.hao.xosokienthiet.io.JSONFileProcessor;
import com.example.hao.xosokienthiet.model.Prize;
import com.example.hao.xosokienthiet.model.Prizes;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by hao on 4/25/2017.
 */

public class JSONParser {
    private String mJSONData;
    private JSONFileProcessor mFileProcessor;

    public JSONParser(JSONFileProcessor fileProcessor) {
        mFileProcessor = fileProcessor;
    }

    public Map<String, List<Prizes>> getPrizes(){
        try {
            return parse();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new HashMap<String, List<Prizes>>();
    }

    private Map<String, List<Prizes>> parse() throws JSONException, IOException {
        mJSONData = mFileProcessor.read();
        JSONObject allAreas = new JSONObject(mJSONData);
        Map<String, List<Prizes>> prizesOfAllAreas = new HashMap<>();

        for(int i = 0; i < allAreas.length(); i++){
            String currentAreaName = allAreas.names().getString(i);
            JSONObject currentArea = allAreas.getJSONObject(currentAreaName);

            prizesOfAllAreas.put(StringHelper.translate(currentAreaName), getPrizesFromOneArea(currentArea));
        }

        return prizesOfAllAreas;
    }

    private List<Prizes> getPrizesFromOneArea(JSONObject currentArea) throws JSONException {
        List<Prizes> prizesFromCurrentArea = new ArrayList<>();

        for(int i = 0; i < currentArea.length(); i++){
            String date = currentArea.names().getString(i);
            JSONObject currentDate = currentArea.getJSONObject(date);

            Prizes prizes = new Prizes();
            prizes.setDate(date);
            prizes.setPrize(getPrizesFromCurrentDate(currentDate));

            prizesFromCurrentArea.add(prizes);
        }

        return prizesFromCurrentArea;
    }

    private List<Prize> getPrizesFromCurrentDate(JSONObject currentDate) throws JSONException {
        List<Prize> prizesFormCurrentDate = new ArrayList<>();

        for(int i = 0; i < currentDate.length(); i++){
            String position = currentDate.names().getString(i);
            JSONArray currentNumbers = currentDate.getJSONArray(position);

            Prize prize = new Prize();
            prize.setPosition(position);
            prize.setNumbers(getNumbersFormCurrentPosition(currentNumbers));

            prizesFormCurrentDate.add(prize);
        }

        return prizesFormCurrentDate;
    }

    private List<String> getNumbersFormCurrentPosition(JSONArray currentPosition) throws JSONException {
        List<String> numbers = new ArrayList<>();

        for(int i = 0; i < currentPosition.length(); i++){
            numbers.add(currentPosition.getString(i));
        }

        return numbers;
    }
 }
