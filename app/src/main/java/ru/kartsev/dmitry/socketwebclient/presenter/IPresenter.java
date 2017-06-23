package ru.kartsev.dmitry.socketwebclient.presenter;

/**
 * Created by dmitry on 22.06.17.
 */

public interface IPresenter {
    void connectToServer();
    void askForSportMap();
    void askForTournamentMap(int sportId);
    void onDestroy();
}
