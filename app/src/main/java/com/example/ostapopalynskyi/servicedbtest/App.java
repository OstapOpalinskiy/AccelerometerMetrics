package com.example.ostapopalynskyi.servicedbtest;

import android.app.Application;

import com.example.ostapopalynskyi.servicedbtest.model.database.DatabaseFacade;
import com.example.ostapopalynskyi.servicedbtest.model.managers.AccelerometerMetricsDbManager;

/**
 * Created by ostap.opalynskyi on 28.03.2017.
 */

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        DatabaseFacade.instance(this);
    }
}
