package ru.kartsev.dmitry.socketwebclient.view.impl.fragments;

import java.util.List;

import ru.kartsev.dmitry.socketwebclient.presenter.vo.TournamentInfo;
import ru.kartsev.dmitry.socketwebclient.view.IView;

/**
 * Created by dmitry on 25.06.17.
 */

public interface ITournamentInfoView extends IView {
    void showTournaments(List<TournamentInfo> tournamentsList);
    void setTournamentsMap(List<TournamentInfo> tournamentsList);
}
