package com.example.aaronwang.and2ard.manager;


import android.content.Context;

import com.example.aaronwang.and2ard.view.MainActivity;

/**
 * Created by aaronwang on 2018/3/22.
 */

public class UIManager {

    private static UIManager g_UIManager = null;

    private Context _context = null;
    private MainActivity _mainActivity = null;

    public static void createInstance(Context context){
        if(null == g_UIManager){
            g_UIManager = new UIManager(context);
        }
    }

    public static UIManager getInstance(){

        return g_UIManager;
    }

    public UIManager(Context context){
        _context = context;
    }

    public void showMovementController(){
        _mainActivity.showMovementController();
    }

    public void showMainPage(){
        _mainActivity.showMainPage();
    }

    public void setMainActivity(MainActivity mainActivity){
        _mainActivity = mainActivity;
    }
}
