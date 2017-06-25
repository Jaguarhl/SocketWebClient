//package ru.kartsev.dmitry.socketwebclient.view.impl.adapters;
//
//import android.util.Log;
//
//import java.util.List;
//
//import ru.kartsev.dmitry.socketwebclient.presenter.Constants;
//import ru.kartsev.dmitry.socketwebclient.presenter.ISportMapPresenter;
//import ru.kartsev.dmitry.socketwebclient.presenter.vo.TournamentInfo;
//
///**
// * Created by dmitry on 25.06.17.
// */
//
//public class TournamentsAdapter extends BaseAdapterTournamentInfo<TournamentInfo> {
//
//    private ISportMapPresenter presenter;
//
//    public TournamentsAdapter(List<TournamentInfo> list, ISportMapPresenter presenter) {
//        super(list);
//        this.presenter = presenter;
//    }
//
//    @Override
//    public void onBindViewHolder(ViewHolder holder, int position) {
//        TournamentInfo tournament = list.get(position);
//        Log.d(LOG_TAG, tournament.getTournamentNameByLanguage(Constants.CODE_RU) + " position " + position);
//        holder.textCategoryName.setText(tournament.getCategoryNameByLanguage(Constants.CODE_RU));
//        holder.textTournamentName.setText(tournament.getTournamentNameByLanguage(Constants.CODE_RU));
//    }
//
//    public void setTournamentsMap(List<TournamentInfo> tournamentsList) {
//        this.list = tournamentsList;
//        notifyDataSetChanged();
//    }
//}
