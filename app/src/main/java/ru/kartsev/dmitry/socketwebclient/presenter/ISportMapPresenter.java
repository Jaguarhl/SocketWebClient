package ru.kartsev.dmitry.socketwebclient.presenter;

import android.os.Bundle;

import java.util.List;
import java.util.Map;

import ru.kartsev.dmitry.socketwebclient.presenter.impl.SportMapImpl;
import ru.kartsev.dmitry.socketwebclient.presenter.vo.Sports;

/**
 * Created by dmitry on 23.06.17.
 */

public interface ISportMapPresenter {
    void clearSportsList();
    void addSportToList(int sportId, Map<String, String> sportNames);
    void askForSportMap();
    void updateListInView();
    void clickSport(Sports sport);
    void onCreate(Bundle savedInstanceState);
}
