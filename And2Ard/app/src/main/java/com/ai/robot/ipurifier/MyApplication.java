package com.ai.robot.ipurifier;

import android.app.Application;

import com.ai.robot.ipurifier.manager.ModelManager;
import com.ai.robot.ipurifier.manager.PresenterManager;
import com.ai.robot.ipurifier.manager.TransitManager;
import com.ai.robot.ipurifier.manager.UIManager;

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
