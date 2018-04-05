package com.example.aaronwang.and2ard.manager;

import android.content.Context;

/**
 * Created by aaronwang on 2018/3/22.
 */

public class ModelManager {
    private static ModelManager g_modelManager = null;

    private DeviceModelImp _deviceModel = null;
    private Context _context = null;

    public static void createInstance(Context context){
        if(null == g_modelManager){
            g_modelManager = new ModelManager(context);
        }
    }

    public static ModelManager getInstance(){
        return g_modelManager;
    }

    public ModelManager(Context context){
        _context = context;
    }

    public IDeviceModel getDeviceMode(){
        if(null == _deviceModel){
            _deviceModel = new DeviceModelImp(_context);
        }
        return _deviceModel;
    }
}
