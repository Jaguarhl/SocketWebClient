package ru.kartsev.dmitry.socketwebclient.view.impl.fragments;

import java.util.List;

import ru.kartsev.dmitry.socketwebclient.presenter.vo.Sports;

/**
 * Created by dmitry on 24.06.17.
 */

public interface ISportMapView {
    void showSportsList(List<Sports> vo);
    void showMessage(String message);

//    void startRepoInfoFragment(Repository vo);

    void showEmptyList();
}
