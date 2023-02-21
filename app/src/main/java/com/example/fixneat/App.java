package com.example.fixneat;

import android.app.Application;

import com.example.fixneat.Utils.SignalUser;

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        SignalUser.initInstance(this);
    }
}