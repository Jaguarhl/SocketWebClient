package ru.kartsev.dmitry.socketwebclient.view.impl;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.crashlytics.android.Crashlytics;

import butterknife.ButterKnife;
import io.fabric.sdk.android.Fabric;
import ru.kartsev.dmitry.socketwebclient.R;
import ru.kartsev.dmitry.socketwebclient.view.IView;
import ru.kartsev.dmitry.socketwebclient.view.impl.fragments.FrameItemsList;

public class MainActivity extends AppCompatActivity implements IView{

    public static final String TAG = "TAG";
//    private IPresenter sportMap;
    private static MainActivity instance;
    private FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Fabric.with(this, new Crashlytics());
        ButterKnife.bind(this);
        instance = this;

//        sportMap = new SportMapImpl(this, getContext(), this);

        fragmentManager = getSupportFragmentManager();
        Fragment fragment = fragmentManager.findFragmentByTag(TAG);
        if (fragment == null) replaceFragment(new FrameItemsList(), false);
    }

    private void replaceFragment(Fragment fragment, boolean addBackStack) {
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.container, fragment, TAG);
        if (addBackStack) transaction.addToBackStack(null);
        transaction.commit();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        sportMap.onDestroy();
    }

    @Override
    public void displayError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    public static MainActivity getInstance() {
        return instance;
    }

    public static Context getContext() {
        return instance.getBaseContext();
    }
}
