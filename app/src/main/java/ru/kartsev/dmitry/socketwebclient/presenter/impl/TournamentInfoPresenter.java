package ru.kartsev.dmitry.socketwebclient.presenter.impl;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import java.util.List;

import ru.kartsev.dmitry.socketwebclient.presenter.ITournamentInfoPresenter;
import ru.kartsev.dmitry.socketwebclient.presenter.vo.Sports;
import ru.kartsev.dmitry.socketwebclient.presenter.vo.TournamentInfo;
import ru.kartsev.dmitry.socketwebclient.view.impl.MainActivity;

/**
 * Created by dmitry on 26.06.17.
 */

public class TournamentInfoPresenter implements ITournamentInfoPresenter {
    private ITournamentInfoPresenter view;
    private Context context;
    private MainActivity activity;

    public TournamentInfoPresenter(ITournamentInfoPresenter view, Context context, MainActivity activity) {
        this.view = view;
        this.context = context;
        this.activity = activity;
    }

    @Override
    public void connectToServer() {

    }

    @Override
    public void askForSportMap() {

    }

    @Override
    public void askForTournamentMap(int sportId) {

    }

    @Override
    public void onDestroy() {

    }

    public void onCreate(Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            //sportsList = (List<Sports>) savedInstanceState.getSerializable(BUNDLE_SPORTS_LIST_KEY);
        }

        /*if (!isSportsListEmpty()) {
            view.showSportsList(sportsList);
        }*/
    }
}
