package ru.kartsev.dmitry.socketwebclient.presenter.vo;

import java.util.Map;

/**
 * Created by dmitry on 23.06.17.
 */

public class Sports {

    private int sportId;
    private Map<String, String> sportName;

    public Sports(int sportId, Map<String, String> sportName) {
        this.sportId = sportId;
        this.sportName = sportName;
    }

    public int getSportId() {
        return sportId;
    }

    public void setSportId(int sportId) {
        this.sportId = sportId;
    }

    public Map<String, String> getSportName() {
        return sportName;
    }

    public void setSportName(Map<String, String> sportName) {
        this.sportName = sportName;
    }

    public String getSportNameByLng(String lang) {
        String returnValue = "";
        try {
            returnValue = sportName.get(lang);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return returnValue.isEmpty()?sportName.get("en"):returnValue;
    }
}
