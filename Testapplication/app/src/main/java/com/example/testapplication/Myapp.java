package com.example.testapplication;

import android.app.Application;

import com.example.testapplication.Ble.BleManager;


public class Myapp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
       // DbManager.init(this);
       // SharedPreferenceUtils.init(this);
        BleManager.init(this);
    }
}
