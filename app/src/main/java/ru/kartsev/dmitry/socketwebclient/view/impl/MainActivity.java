package ru.kartsev.dmitry.socketwebclient.view.impl;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import com.crashlytics.android.Crashlytics;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.fabric.sdk.android.Fabric;
import ru.kartsev.dmitry.socketwebclient.R;
import ru.kartsev.dmitry.socketwebclient.presenter.IPresenter;
import ru.kartsev.dmitry.socketwebclient.presenter.impl.SportMapImpl;
import ru.kartsev.dmitry.socketwebclient.view.IView;

public class MainActivity extends AppCompatActivity implements IView{

    public static final String TAG = "TAG";
    private IPresenter sportMap;
    private FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Fabric.with(this, new Crashlytics());
        ButterKnife.bind(this);

        sportMap = new SportMapImpl(this, this.getBaseContext(), this);

        /*fragmentManager = getSupportFragmentManager();
        Fragment fragment = fragmentManager.findFragmentByTag(TAG);*/
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        sportMap.onDestroy();
    }

    @Override
    public void displayError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }
}
