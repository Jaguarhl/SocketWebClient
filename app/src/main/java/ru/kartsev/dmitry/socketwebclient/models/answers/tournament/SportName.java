package ru.kartsev.dmitry.socketwebclient.models.answers.tournament;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SportName {

    @SerializedName("en")
    @Expose
    private String en = "";
    @SerializedName("ru")
    @Expose
    private String ru = "";

    public String getEn() {
        return en;
    }

    public void setEn(String en) {
        if (en != null) {
            this.en = en;
        } else {
            en = "";
        }
    }

    public String getRu() {
        return ru;
    }

    public void setRu(String ru) {
        if (ru != null) {
            this.ru = ru;
        } else {
            ru = "";
        }
    }

}