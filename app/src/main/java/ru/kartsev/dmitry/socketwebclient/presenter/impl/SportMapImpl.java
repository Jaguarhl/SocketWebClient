package ru.kartsev.dmitry.socketwebclient.presenter.impl;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import io.socket.client.Socket;
import ru.kartsev.dmitry.socketwebclient.R;
import ru.kartsev.dmitry.socketwebclient.models.requests.Data;
import ru.kartsev.dmitry.socketwebclient.models.requests.RequestToServerDTO;
import ru.kartsev.dmitry.socketwebclient.models.service.ClientService;
import ru.kartsev.dmitry.socketwebclient.presenter.IPresenter;
import ru.kartsev.dmitry.socketwebclient.presenter.ISportMapPresenter;
import ru.kartsev.dmitry.socketwebclient.presenter.vo.Sports;
import ru.kartsev.dmitry.socketwebclient.view.impl.fragments.ISportMapView;

/**
 * SportMap presenter implementation
 * 22.06.17
 */

public class SportMapImpl implements IPresenter, ISportMapPresenter {
    public static final String LOG_TAG = "SportMapPresenter";
    public static final String MATCH_STORAGE_LOAD_SPORT_MAP = "matchStorage:loadSportMap";
    private static final String BUNDLE_SPORTS_LIST_KEY = "BUNDLE_SPORTS_LIST_KEY";
    private ISportMapView view;
    private Context context;
    private Socket mSocket;
    private FragmentActivity activity;
    private ClientService service = null;
    // we will store loaded sports for recyclerview in this list
    private List<Sports> sportsList = new ArrayList<>();

    public SportMapImpl(ISportMapView view, Context context, FragmentActivity Activity) {
        this.view = view;
        this.context = context;
        this.activity = Activity;
        connectToServer();
    }

    public void addSportToList(int sportId, Map<String, String> sportNames) {
        sportsList.add(new Sports(sportId, sportNames));
        Log.d(LOG_TAG, "Added to sportsList: ID " + sportId + " names " + sportNames.toString()
                + " Map size is: " + sportNames.size());
    }

    public void clearSportsList() {
        sportsList.clear();
    }

    @Override
    public void connectToServer() {
        service = new ClientService(context, activity, this);
    }

    @Override
    public void askForSportMap() {
        Log.d(LOG_TAG, "askForSportMap() called");
        if (service != null) {
            String msgid = service.generateMessageId();
            RequestToServerDTO request = new RequestToServerDTO(new Data("live"),
                    msgid, MATCH_STORAGE_LOAD_SPORT_MAP);
            GsonBuilder gsonBuilder = new GsonBuilder();
            Gson gson = gsonBuilder.create();
            Log.d(LOG_TAG, gson.toJson(request));

            try {
                JSONObject object = new JSONObject(gson.toJson(request));
                service.sendMessage(object, msgid);
            } catch (Exception e) {
                e.printStackTrace();
//                view.displayError(e.getMessage());
            }
        } else {
            view.showMessage(context.getResources().getString(R.string.warn_no_connection));
        }
    }

    @Override
    public void updateListInView() {
        view.showSportsList(sportsList);
    }

    @Override
    public void askForTournamentMap(int sportId) {
        if (service != null) {

        } else {
            view.showMessage(context.getResources().getString(R.string.warn_no_connection));
        }
    }

    @Override
    public void onDestroy() {
        service.stopService();
    }

    public void onCreate(Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            sportsList = (List<Sports>) savedInstanceState.getSerializable(BUNDLE_SPORTS_LIST_KEY);
        }

        if (!isSportsListEmpty()) {
            view.showSportsList(sportsList);
        }
    }

    private boolean isSportsListEmpty() {
        return sportsList == null || sportsList.isEmpty();
    }

    public void onSaveInstanceState(Bundle outState) {
        if (!isSportsListEmpty()) {
            outState.putSerializable(BUNDLE_SPORTS_LIST_KEY, new ArrayList<>(sportsList));
        }
    }

    public void clickSport(Sports sport) {
        //view.startRepoInfoFragment(sport);
        view.showMessage("Clicked " + sport.getSportNameByLng("ru"));
    }
}
