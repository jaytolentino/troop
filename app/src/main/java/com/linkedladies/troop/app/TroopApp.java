package com.linkedladies.troop.app;

import android.app.Application;

public class TroopApp extends Application {

    private static TroopApp instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }

    public static TroopApp getInstance() {
        return instance;
    }
}
