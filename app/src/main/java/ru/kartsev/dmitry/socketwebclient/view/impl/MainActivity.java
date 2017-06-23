package ru.kartsev.dmitry.socketwebclient.view.impl;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import ru.kartsev.dmitry.socketwebclient.R;
import ru.kartsev.dmitry.socketwebclient.presenter.IPresenter;
import ru.kartsev.dmitry.socketwebclient.presenter.impl.SportMapImpl;
import ru.kartsev.dmitry.socketwebclient.view.IView;

public class MainActivity extends AppCompatActivity implements IView{

    @Bind(R.id.btn_connect_to_server)
    Button btnConnect;

    IPresenter sportMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        sportMap = new SportMapImpl(this, this.getBaseContext(), this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        sportMap.onDestroy();
    }

    @OnClick(R.id.btn_connect_to_server)
    void onSaveClick() {
        sportMap.askForSportMap();
    }

    @Override
    public void displayError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }
}
