package ru.kartsev.dmitry.socketwebclient.models.answers.tournament;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TournamentDTO {

    @SerializedName("sportName")
    @Expose
    private SportName sportName;
    @SerializedName("tournamentId")
    @Expose
    private int tournamentId;
    @SerializedName("tournamentName")
    @Expose
    private TournamentName tournamentName;
    @SerializedName("categoryName")
    @Expose
    private CategoryName categoryName;
    @SerializedName("sportId")
    @Expose
    private int sportId;
    @SerializedName("categoryId")
    @Expose
    private int categoryId;

    public SportName getSportName() {
        return sportName;
    }

    public void setSportName(SportName sportName) {
        this.sportName = sportName;
    }

    public int getTournamentId() {
        return tournamentId;
    }

    public void setTournamentId(int tournamentId) {
        this.tournamentId = tournamentId;
    }

    public TournamentName getTournamentName() {
        return tournamentName;
    }

    public void setTournamentName(TournamentName tournamentName) {
        this.tournamentName = tournamentName;
    }

    public CategoryName getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(CategoryName categoryName) {
        this.categoryName = categoryName;
    }

    public int getSportId() {
        return sportId;
    }

    public void setSportId(int sportId) {
        this.sportId = sportId;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    @Override
    public String toString() {
        return sportName.getRu() + " " + tournamentId + " " + tournamentName.getRu()
                + " " +categoryId + " " + categoryName.getRu() + " " + sportId + " "
                + categoryId;
    }
}