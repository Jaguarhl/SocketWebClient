package ru.kartsev.dmitry.socketwebclient.view.impl.adapters;

import android.util.Log;
import android.view.View;

import java.util.List;

import ru.kartsev.dmitry.socketwebclient.presenter.ISportMapPresenter;
import ru.kartsev.dmitry.socketwebclient.presenter.vo.Sports;

/**
 * Created by dmitry on 24.06.17.
 */

public class SportMapAdapter extends BaseAdapterSportMap<Sports> {
    public static final String LOG_TAG = "SportMapAdapter";
    private ISportMapPresenter presenter;

    public SportMapAdapter(List<Sports> list, ISportMapPresenter presenter) {
        super(list);
        this.presenter = presenter;
    }

    @Override
    public void onBindViewHolder(BaseAdapterSportMap.ViewHolder holder, int position) {
        final Sports sport = list.get(position);
        Log.d(LOG_TAG, sport.getSportNameByLng("ru") + " position " + position);
        holder.text.setText(sport.getSportNameByLng("ru"));
        holder.text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.clickSport(sport);
            }
        });
    }

    public void setSportMap(List<Sports> sport) {
        this.list = sport;
        notifyDataSetChanged();
    }

}
