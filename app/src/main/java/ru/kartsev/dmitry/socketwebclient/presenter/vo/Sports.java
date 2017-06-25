package ru.kartsev.dmitry.socketwebclient.presenter.vo;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by dmitry on 23.06.17.
 */

public class Sports implements Parcelable {

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.sportId);
        dest.writeInt(this.sportName.size());
        for (Map.Entry<String, String> entry : this.sportName.entrySet()) {
            dest.writeString(entry.getKey());
            dest.writeString(entry.getValue());
        }
    }

    protected Sports(Parcel in) {
        this.sportId = in.readInt();
        int sportNameSize = in.readInt();
        this.sportName = new HashMap<String, String>(sportNameSize);
        for (int i = 0; i < sportNameSize; i++) {
            String key = in.readString();
            String value = in.readString();
            this.sportName.put(key, value);
        }
    }

    public static final Creator<Sports> CREATOR = new Creator<Sports>() {
        @Override
        public Sports createFromParcel(Parcel source) {
            return new Sports(source);
        }

        @Override
        public Sports[] newArray(int size) {
            return new Sports[size];
        }
    };
}
