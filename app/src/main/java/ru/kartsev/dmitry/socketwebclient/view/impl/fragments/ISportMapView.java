package ru.kartsev.dmitry.socketwebclient.view.impl.fragments;

import java.util.List;

import ru.kartsev.dmitry.socketwebclient.presenter.vo.Sports;
import ru.kartsev.dmitry.socketwebclient.view.IView;

/**
 * Created by dmitry on 24.06.17.
 */

public interface ISportMapView extends IView {
    void showSportsList(List<Sports> vo);
    void showMessage(String message);

//    void startRepoInfoFragment(Repository vo);

    void showEmptyList();

    void showSportTournamentInfo(int sportId);
}
