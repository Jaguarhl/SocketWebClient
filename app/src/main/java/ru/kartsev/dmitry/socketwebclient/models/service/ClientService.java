package ru.kartsev.dmitry.socketwebclient.models.service;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONArray;
import org.json.JSONObject;

import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

import io.socket.client.IO;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;
import io.socket.engineio.client.transports.WebSocket;
import ru.kartsev.dmitry.socketwebclient.R;
import ru.kartsev.dmitry.socketwebclient.models.answers.sport.SportDTO;
import ru.kartsev.dmitry.socketwebclient.models.answers.tournament.TournamentDTO;
import ru.kartsev.dmitry.socketwebclient.presenter.Constants;
import ru.kartsev.dmitry.socketwebclient.presenter.ISportMapPresenter;
import ru.kartsev.dmitry.socketwebclient.presenter.impl.SportMapPresenter;
import ru.kartsev.dmitry.socketwebclient.presenter.vo.TournamentInfo;


public class ClientService {
    public static final String LOG_TAG = "ClientService";
    private final int MIN_SYMBOLS_NUM = 12;
    private final int MAX_SYMBOLS_NUM = 16;
    private Socket mSocket;
    private Context mContext;
    private FragmentActivity activity;
    private ISportMapPresenter sportsMapImpl;
    private boolean isConnected = false;
    // we will store requests types for server here (key is uniq msgid)
    private Map<String, String> requests = new HashMap<>();
    // server port
    private static final int SERVER_PORT = 18500;
    // server IP address
    private static final String SERVER_IP = /*"91.121.64.108";*/"81.176.228.82";


    public ClientService(Context context, FragmentActivity activity, SportMapPresenter sportMap) {
        this.mContext = context;
        this.activity = activity;
        this.sportsMapImpl = sportMap;
        try {
            // setting transport to only websocket to avoid polling
            IO.Options options = new IO.Options();
            options.transports = new String[] { WebSocket.NAME };
            mSocket = IO.socket("http://" + SERVER_IP + ":" + SERVER_PORT, options);
            mSocket.on(Socket.EVENT_CONNECT, onConnect);
            mSocket.on("a", onGetMessage);
            mSocket.connect();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

    public Socket getSocket() {
        return mSocket;
    }

    private Emitter.Listener onConnect = new Emitter.Listener() {
        @Override
        public void call(Object... args) {
            activity.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if(!isConnected) {
                        Toast.makeText(mContext, R.string.warn_connect, Toast.LENGTH_LONG).show();
                        Log.d(LOG_TAG, mContext.getResources().getString(R.string.warn_connect));
                        sportsMapImpl.askForSportMap();
                        isConnected = true;
                    }
                }
            });
        }
    };

    private Emitter.Listener onGetMessage = new Emitter.Listener() {
        @Override
        public void call(final Object... args) {
            activity.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    JSONObject data = (JSONObject) args[0];
                    Log.d(LOG_TAG, "Answer: " + data.toString());
                    parseAnswer(data);
                }
            });
        }
    };

    private void parseAnswer(JSONObject data) {
        try {
            JSONObject dataObj = data.getJSONObject(Constants.ANSWER_DATA);
            if (dataObj.get(Constants.ANSWER_STATUS).toString().equals(Constants.ANSWER_STATUS_OK)) {
                switch (requests.get(data.get(Constants.REQUEST_MSGID).toString())) {
                    case Constants.MATCH_STORAGE_LOAD_SPORT_MAP:
                        parseSportMapAnswer(dataObj);
                        break;
                    case Constants.MATCH_STORAGE_LOAD_SPORT_TOURNAMENT_MAP:
                        parseTournamentMapAnswer(dataObj);
                        break;
                    default:
                        Log.d(LOG_TAG, mContext.getResources().getString(R.string.warn_unknown_response));
                        break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void parseSportMapAnswer(JSONObject dataObj) {
        // let's clear our sportsMap list before
        sportsMapImpl.clearSportsList();
        try {
            JSONObject sportMap = dataObj.getJSONObject(Constants.JSON_SPORT_MAP);
            Log.d(LOG_TAG, "sportMap: " + sportMap.toString());
            JSONArray sportMapArray = sportMap.toJSONArray(sportMap.names());
            Log.d(LOG_TAG, "sportMapArray: " + sportMapArray.toString());
            GsonBuilder gsonBuilder = new GsonBuilder();
            Gson gson = gsonBuilder.create();
            SportDTO[] data = gson.fromJson(sportMapArray.toString(), SportDTO[].class);
            if (data != null) {
                for (int l = 0; l < data.length; l++) {
                    Log.d(LOG_TAG, data[l].toString());
                    Map<String, String> names = new HashMap<>();
                    names.put(data[l].getSportName().getEnCode(), data[l].getSportName().getEn());
                    names.put(data[l].getSportName().getRuCode(), data[l].getSportName().getRu());
                    sportsMapImpl.addSportToList(data[l].getSportId(),
                            names);
                }
            }
            sportsMapImpl.updateListInView();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void parseTournamentMapAnswer(JSONObject dataObj) {
        // let's clear tournamentsList before
        sportsMapImpl.clearTournamentsList();
        try {
            JSONObject sportTournamentMap = dataObj.getJSONObject(Constants.JSON_SPORT_TOURNAMENT_MAP);
            Log.d(LOG_TAG, "sportTournamentMap: " + sportTournamentMap.toString());
            JSONArray sportTournamentMapArray = sportTournamentMap.toJSONArray(sportTournamentMap.names());
            Log.d(LOG_TAG, "sportTournamentMapArray: " + sportTournamentMapArray.toString());
            for (int i = 0; i < sportTournamentMapArray.length(); i++) {
                JSONObject sportTournamentObj = sportTournamentMapArray.getJSONObject(i);
                Log.d(LOG_TAG, "sportTournamentObj: " + sportTournamentObj.toString());
                JSONArray sportTournamentObjArr = sportTournamentObj.toJSONArray(sportTournamentObj.names());
                Log.d(LOG_TAG, "sportTournamentObjArr: " + sportTournamentObjArr.toString());
                GsonBuilder gsonBuilder = new GsonBuilder();
                Gson gson = gsonBuilder.create();
                TournamentDTO[] data = gson.fromJson(sportTournamentObjArr.toString(), TournamentDTO[].class);
                if (data != null) {
                    for (int l = 0; l < data.length; l++) {
                        Log.d(LOG_TAG, "" + data[l].toString());
                        Map<String, String> categoryNames = new HashMap<>();
                        categoryNames.put(Constants.CODE_EN, data[l].getCategoryName().getEn());
                        categoryNames.put(Constants.CODE_RU, data[l].getCategoryName().getRu());
                        Map<String, String> tournamentNames = new HashMap<>();
                        tournamentNames.put(Constants.CODE_EN, data[l].getTournamentName().getEn());
                        tournamentNames.put(Constants.CODE_RU, data[l].getTournamentName().getRu());
                        sportsMapImpl.addTournamentToList(new TournamentInfo(data[l].getSportId(),
                                categoryNames, tournamentNames));
                    }
                }
                sportsMapImpl.showTournamentInfo();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void sendMessage(JSONObject message, String msgid, String param) {
        if (mSocket.connected()) {
            Log.d(LOG_TAG, "Request to server: " + message);
            requests.put(msgid, param);
            mSocket.emit(Constants.KEY_SEND, message);
        }

    }

    private int getRandom(int min, int max)
    {
        max -= min;
        return (int) (Math.random() * ++max) + min;
    }

    public String generateMessageId() {
        // symbols to randomly create from messageId
        String symbols = "ABCDEFGHIJKLMNOPQRSTUVWXYZ" +
                "abcdefghijklmnopqrstuvwqyz" +
                "1234567890";
        int symbolsLength = getRandom(MIN_SYMBOLS_NUM, MAX_SYMBOLS_NUM);
        StringBuffer result = new StringBuffer();
        for(int i = 0; i < symbolsLength; i++) {
            result.append(symbols.charAt((int) (Math.random() * symbols.length())));
        }
        Log.d(LOG_TAG, "Generated msgid is: " + result.toString());
        return result.toString();
    }

    public void stopService() {
        try {
            mSocket.disconnect();
            mSocket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
