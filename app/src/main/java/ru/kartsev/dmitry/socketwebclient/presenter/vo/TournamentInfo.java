package ru.kartsev.dmitry.socketwebclient.presenter.vo;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by dmitry on 25.06.17.
 */

public class TournamentInfo implements Parcelable{
    private int sportId;
    private Map<String, String> categoryName = new HashMap<>();
    private Map<String, String> tournamentName = new HashMap<>();

    public TournamentInfo(int sportId, Map<String, String> categoryName, Map<String, String> tournamentName) {
        this.sportId = sportId;
        this.categoryName = categoryName;
        this.tournamentName = tournamentName;
    }

    public int getSportId() {
        return sportId;
    }

    public void setSportId(int sportId) {
        this.sportId = sportId;
    }

    public Map<String, String> getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(Map<String, String> categoryName) {
        this.categoryName = categoryName;
    }

    public Map<String, String> getTournamentName() {
        return tournamentName;
    }

    public void setTournamentName(Map<String, String> tournamentName) {
        this.tournamentName = tournamentName;
    }

    public String getCategoryNameByLanguage(String lng) {
        return categoryName.get(lng);
    }

    public String getTournamentNameByLanguage(String lng) {
        return tournamentName.get(lng);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.sportId);
        dest.writeInt(this.categoryName.size());
        for (Map.Entry<String, String> entry : this.categoryName.entrySet()) {
            dest.writeString(entry.getKey());
            dest.writeString(entry.getValue());
        }
        dest.writeInt(this.tournamentName.size());
        for (Map.Entry<String, String> entry : this.tournamentName.entrySet()) {
            dest.writeString(entry.getKey());
            dest.writeString(entry.getValue());
        }
    }

    protected TournamentInfo(Parcel in) {
        this.sportId = in.readInt();
        int categoryNameSize = in.readInt();
        this.categoryName = new HashMap<String, String>(categoryNameSize);
        for (int i = 0; i < categoryNameSize; i++) {
            String key = in.readString();
            String value = in.readString();
            this.categoryName.put(key, value);
        }
        int tournamentNameSize = in.readInt();
        this.tournamentName = new HashMap<String, String>(tournamentNameSize);
        for (int i = 0; i < tournamentNameSize; i++) {
            String key = in.readString();
            String value = in.readString();
            this.tournamentName.put(key, value);
        }
    }

    public static final Creator<TournamentInfo> CREATOR = new Creator<TournamentInfo>() {
        @Override
        public TournamentInfo createFromParcel(Parcel source) {
            return new TournamentInfo(source);
        }

        @Override
        public TournamentInfo[] newArray(int size) {
            return new TournamentInfo[size];
        }
    };
}
