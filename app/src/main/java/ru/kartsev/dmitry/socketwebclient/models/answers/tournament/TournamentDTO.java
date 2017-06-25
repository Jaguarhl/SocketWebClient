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
        if (sportName != null) {
            return sportName;
        } else {
            return new SportName();
        }
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
        if (tournamentName != null) {
            return tournamentName;
        } else {
            return new TournamentName();
        }
    }

    public void setTournamentName(TournamentName tournamentName) {
        this.tournamentName = tournamentName;
    }

    public CategoryName getCategoryName() {
        if (categoryName != null) {
            return categoryName;
        } else {
            return new CategoryName();
        }
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
        return getSportName().getRu() + " " + tournamentId + " " + getTournamentName().getRu()
                    + " " + categoryId + " " + getCategoryName().getRu() + " " + sportId + " "
                    + categoryId;
    }
}