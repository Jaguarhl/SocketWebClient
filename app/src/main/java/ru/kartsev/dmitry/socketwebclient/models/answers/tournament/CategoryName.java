package ru.kartsev.dmitry.socketwebclient.models.answers.tournament;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CategoryName {

    @SerializedName("en")
    @Expose
    private String en = "";
    @SerializedName("ru")
    @Expose
    private String ru = "";

    public String getEn() {
        if (en != null) {
            return en;
        } else {
            return "";
        }
    }

    public void setEn(String en) {
        if (en != null) {
            this.en = en;
        }
    }

    public String getRu() {
        if (ru != null) {
            return ru;
        } else {
            return "";
        }
    }

    public void setRu(String ru) {
        if (ru != null) {
            this.ru = ru;
        }
    }

}