//package ru.kartsev.dmitry.socketwebclient.view.impl.fragments;
//
//import android.os.Bundle;
//import android.support.annotation.Nullable;
//import android.support.v7.widget.LinearLayoutManager;
//import android.support.v7.widget.RecyclerView;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import butterknife.Bind;
//import butterknife.ButterKnife;
//import ru.kartsev.dmitry.socketwebclient.R;
//import ru.kartsev.dmitry.socketwebclient.presenter.ITournamentInfoPresenter;
//import ru.kartsev.dmitry.socketwebclient.presenter.impl.SportMapImpl;
//import ru.kartsev.dmitry.socketwebclient.presenter.impl.TournamentInfoImpl;
//import ru.kartsev.dmitry.socketwebclient.presenter.vo.Sports;
//import ru.kartsev.dmitry.socketwebclient.presenter.vo.TournamentInfo;
//import ru.kartsev.dmitry.socketwebclient.view.impl.MainActivity;
//import ru.kartsev.dmitry.socketwebclient.view.impl.adapters.TournamentsAdapter;
//
///**
// * Created by dmitry on 25.06.17.
// */
//
//public class FrameTournamentsList extends BaseFragment implements ITournamentInfoPresenter {
//    private ITournamentInfoPresenter presenter = new TournamentInfoImpl(this,
//            MainActivity.getContext(), MainActivity.getInstance());
//
//    private TournamentsAdapter adapter;
//
//    @Bind(R.id.recyclerView)
//    RecyclerView recyclerView;
//
//    @Nullable
//    @Override
//    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        View view = inflater.inflate(R.layout.frame_items_list, container, false);
//        ButterKnife.bind(this, view);
//
//        LinearLayoutManager llm = new LinearLayoutManager(getContext());
//        recyclerView.setLayoutManager(llm);
//        adapter = new TournamentsAdapter(new ArrayList<TournamentInfo>(), presenter);
//        recyclerView.setAdapter(adapter);
//
//        presenter.onCreate(savedInstanceState);
//
//        return view;
//    }
//
//    @Override
//    public void connectToServer() {
//
//    }
//
//    @Override
//    public void askForSportMap() {
//
//    }
//
//    @Override
//    public void askForTournamentMap(int sportId) {
//
//    }
//}
