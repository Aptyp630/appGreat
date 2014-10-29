package com.davidofffarchik.webclient;

import android.app.Application;

public class GetOwnContext extends Application {
    private static GetOwnContext singleton;

    // Returns the application instance
    public static GetOwnContext getInstance() {
        return singleton;
    }

    public final void onCreate() {
        super.onCreate();
        singleton = this;
    }
}