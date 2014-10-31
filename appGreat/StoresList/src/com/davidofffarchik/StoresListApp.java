package com.davidofffarchik;

import android.app.Application;

public class StoresListApp extends Application {

    private static StoresListApp singleton;

    // Returns the application instance
    public static StoresListApp getInstance() {
        return singleton;
    }

    public final void onCreate() {
        super.onCreate();
        singleton = this;
    }
}