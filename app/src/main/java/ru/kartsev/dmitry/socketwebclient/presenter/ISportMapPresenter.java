package ru.kartsev.dmitry.socketwebclient.presenter;

import java.util.Map;

/**
 * Created by dmitry on 23.06.17.
 */

public interface ISportMapPresenter {
    void clearSportsList();
    void addSportToList(int sportId, Map<String, String> sportNames);
    void askForSportMap();
}
