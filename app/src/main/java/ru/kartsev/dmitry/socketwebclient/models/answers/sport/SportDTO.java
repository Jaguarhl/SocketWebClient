package ru.kartsev.dmitry.socketwebclient.models.answers.sport;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SportDTO {

    @SerializedName("sportId")
    @Expose
    private int sportId;
    @SerializedName("sportName")
    @Expose
    private SportName sportName;

    public int getSportId() {
        return sportId;
    }

    public void setSportId(int sportId) {
        this.sportId = sportId;
    }

    public SportName getSportName() {
        return sportName;
    }

    public void setSportName(SportName sportName) {
        this.sportName = sportName;
    }

    @Override
    public String toString() {
        return sportId + " "
                + sportName.toString();
    }

}