package ru.kartsev.dmitry.socketwebclient.presenter;

import android.os.Bundle;

import java.util.List;
import java.util.Map;

import ru.kartsev.dmitry.socketwebclient.presenter.impl.SportMapImpl;
import ru.kartsev.dmitry.socketwebclient.presenter.vo.Sports;
import ru.kartsev.dmitry.socketwebclient.presenter.vo.TournamentInfo;

/**
 * Created by dmitry on 23.06.17.
 */

public interface ISportMapPresenter extends IPresenter {
    void clearSportsList();
    void clearTournamentsList();
    void addSportToList(int sportId, Map<String, String> sportNames);
    void addTournamentToList(TournamentInfo info);
    void askForSportMap();
    void askForTournamentMap(int sportId);
    void updateListInView();
    void clickSport(Sports sport);
    void onCreate(Bundle savedInstanceState);
    void onSaveInstanceState(Bundle outState);
    void showTournamentInfo();
}
