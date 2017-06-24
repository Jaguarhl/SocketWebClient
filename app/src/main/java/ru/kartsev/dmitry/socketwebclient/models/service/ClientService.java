package ru.kartsev.dmitry.socketwebclient.models.service;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

import io.socket.client.IO;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;
import ru.kartsev.dmitry.socketwebclient.R;
import ru.kartsev.dmitry.socketwebclient.presenter.ISportMapPresenter;
import ru.kartsev.dmitry.socketwebclient.presenter.impl.SportMapImpl;
import ru.kartsev.dmitry.socketwebclient.view.impl.MainActivity;


public class ClientService {
    public static final String LOG_TAG = "ClientService";
    public static final String KEY_SEND = "d";
    public static final String ASK_SPORT_MAP = "sportMap";
    public static final String JSON_SPORT_MAP = "sportMap";
    private final int MIN_SYMBOLS_NUM = 12;
    private final int MAX_SYMBOLS_NUM = 16;
    private Socket mSocket;
    private Context mContext;
    private MainActivity activity;
    private ISportMapPresenter sportsMapImpl;
    private boolean isConnected = false;
    // we will store requests types for server here (key is uniq msgid)
    private Map<String, String> requests = new HashMap<>();
    // server port
    private static final int SERVER_PORT = 18500;
    // server IP address
    private static final String SERVER_IP = /*"91.121.64.108";*/"81.176.228.82";


    public ClientService(Context context, MainActivity activity, SportMapImpl sportMap) {
        this.mContext = context;
        this.activity = activity;
        this.sportsMapImpl = sportMap;
        try {
            mSocket = IO.socket("http://" + SERVER_IP + ":" + SERVER_PORT);
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
//                            mSocket.emit("add user", mUsername);
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

//                    if (!status.isEmpty() && status.equals("404")) {
//                        Toast.makeText(mContext, "Error 404 (wrong request)", Toast.LENGTH_LONG).show();
//                    }
                }
            });
        }
    };

    private void parseAnswer(JSONObject data) {
        try {
            JSONObject dataObj = data.getJSONObject("data");
            if (dataObj.get("status").toString().equals("200")) {
                switch (requests.get(data.get("msgid").toString())) {
                    case ASK_SPORT_MAP:
                        parseSportMapAnswer(dataObj);
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
        sportsMapImpl.clearSportsList();
        try {
            JSONObject sportMap = dataObj.getJSONObject(JSON_SPORT_MAP);
            Log.d(LOG_TAG, "sportMap: " + sportMap.toString());
            JSONArray sportMapArray = sportMap.toJSONArray(sportMap.names());
            Log.d(LOG_TAG, "sportMapArray: " + sportMapArray.toString());
            for (int i = 0; i < sportMapArray.length(); i++) {
                JSONObject sportName = sportMapArray.getJSONObject(i);
                Log.d(LOG_TAG, "sportName: " + sportName.toString() + " " + sportName.names());
                JSONArray sportNameLabels = sportName.toJSONArray(sportName.names());
                Log.d(LOG_TAG, "sportNameLabels: " + sportNameLabels.toString());
                JSONObject spNamesObj = sportNameLabels.getJSONObject(0);
                JSONArray spNamesArr = spNamesObj.toJSONArray(spNamesObj.names());
                Map<String, String> names = new HashMap<>();
                for (int k = 0; k < spNamesArr.length(); k++) {
                    names.put(spNamesObj.names().getString(k), spNamesArr.getString(k));
                }
                sportsMapImpl.addSportToList(Integer.parseInt(sportNameLabels.get(1).toString()),
                        names);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void sendMessage(JSONObject message, String msgid) {
        if (mSocket.connected()) {
            Log.d(LOG_TAG, "Request to server: " + message);
            requests.put(msgid, ASK_SPORT_MAP);
            mSocket.emit(KEY_SEND, message);
        }

    }

    private int getRandom(int min, int max)
    {
        max -= min;
        return (int) (Math.random() * ++max) + min;
    }

    public String generateMessageId() {
        // набор символов, из которых генерируется случайным образом msgId
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
