package com.example.aaronwang.and2ard;

import android.app.Application;

import com.example.aaronwang.and2ard.manager.ModelManager;
import com.example.aaronwang.and2ard.manager.PresenterManager;
import com.example.aaronwang.and2ard.manager.TransitManager;
import com.example.aaronwang.and2ard.manager.UIManager;

/**
 * Created by aaronwang on 2018/3/23.
 */

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        ModelManager.createInstance(getBaseContext());
        PresenterManager.createInstance(getBaseContext());
        UIManager.createInstance(getBaseContext());
        TransitManager.createInstance(getBaseContext());
    }
}
