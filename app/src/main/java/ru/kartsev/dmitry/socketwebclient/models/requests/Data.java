package ru.kartsev.dmitry.socketwebclient.models.requests;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Data {

    @SerializedName("service")
    @Expose
    private String service;
    @SerializedName("sportId")
    @Expose
    private int sportId;

    public Data(String service) {
        this.service = service;
    }

    public Data(String service, int sportId) {
        this.service = service;
        this.sportId = sportId;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public int getSportId() {
        return sportId;
    }

    public void setSportId(int sportId) {
        this.sportId = sportId;
    }
}