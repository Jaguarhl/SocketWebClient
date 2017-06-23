package ru.kartsev.dmitry.socketwebclient.models.answers.sport;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SportName {

    @SerializedName("ru")
    @Expose
    private String ru;
    @SerializedName("en")
    @Expose
    private String en;

    public String getRu() {
        return ru;
    }

    public void setRu(String ru) {
        this.ru = ru;
    }

    public String getEn() {
        return en;
    }

    public void setEn(String en) {
        this.en = en;
    }

    @Override
    public String toString() {
        return ru + " "
                + en;
    }

}