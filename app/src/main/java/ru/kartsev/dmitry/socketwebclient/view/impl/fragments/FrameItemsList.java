package ru.kartsev.dmitry.socketwebclient.view.impl.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import ru.kartsev.dmitry.socketwebclient.R;
import ru.kartsev.dmitry.socketwebclient.presenter.ISportMapPresenter;
import ru.kartsev.dmitry.socketwebclient.presenter.impl.SportMapPresenter;
import ru.kartsev.dmitry.socketwebclient.presenter.vo.Sports;
import ru.kartsev.dmitry.socketwebclient.view.impl.MainActivity;
import ru.kartsev.dmitry.socketwebclient.view.impl.adapters.SportMapAdapter;

/**
 * Created by dmitry on 24.06.17.
 */

public class FrameItemsList extends BaseFragment implements ISportMapView {
    private ISportMapPresenter presenter = new SportMapPresenter(this, MainActivity.getContext(),
            MainActivity.getInstance());
    private SportMapAdapter adapter;

    @Bind(R.id.recycler_view)
    RecyclerView recyclerView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frame_items_list, container, false);
        ButterKnife.bind(this, view);

        LinearLayoutManager llm = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(llm);
        adapter = new SportMapAdapter(new ArrayList<Sports>(), presenter);
        recyclerView.setAdapter(adapter);

        presenter.onCreate(savedInstanceState);

        return view;
    }

    private void makeToast(String message) {
        Toast.makeText(this.getContext(), message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void showSportsList(List<Sports> vo) {
        adapter.setSportMap(vo);
    }

    @Override
    public void showMessage(String message) {
        makeToast(message);
    }

    @Override
    public void showEmptyList() {
        makeToast(this.getResources().getString(R.string.msg_list_is_empty));
    }

    @Override
    public void showSportTournamentInfo(int sportId) {
        presenter.askForTournamentMap(sportId);
    }

    @Override
    public void displayError(String message) {
        makeToast(message);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        presenter.onSaveInstanceState(outState);
    }
}
