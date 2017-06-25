package ru.kartsev.dmitry.socketwebclient.view.impl.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import ru.kartsev.dmitry.socketwebclient.R;

/**
 * Created by dmitry on 24.06.17.
 */

public abstract class BaseAdapterSportMap<T> extends RecyclerView.Adapter<BaseAdapterSportMap.ViewHolder> {

    public static final String LOG_TAG = "BaseAdapterSportMap";
    protected List<T> list;

    public BaseAdapterSportMap(List<T> list) {
        this.list = list;
    }

    public List<T> getList() {
        return list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.text_item_layout,
                viewGroup, false);
        return new ViewHolder(v);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView text;

        public ViewHolder(View itemView) {
            super(itemView);
            text = (TextView) itemView.findViewById(R.id.textView);
        }
    }

}
