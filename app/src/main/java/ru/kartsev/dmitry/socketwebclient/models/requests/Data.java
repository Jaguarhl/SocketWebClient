package ru.kartsev.dmitry.socketwebclient.models.requests;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Data {

    @SerializedName("service")
    @Expose
    private String service;

    public Data(String service) {
        this.service = service;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

}