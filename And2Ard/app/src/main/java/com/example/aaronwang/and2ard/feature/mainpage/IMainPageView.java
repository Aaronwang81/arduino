package com.example.aaronwang.and2ard.feature.mainpage;

import com.example.aaronwang.and2ard.manager.IDeviceModel;

/**
 * Created by aaronwang on 2018/3/23.
 */

public interface IMainPageView {
    void onNewDeviceCome(IDeviceModel.DeviceInfo deviceInfo);
    void onDeviceRemoved(IDeviceModel.DeviceInfo deviceInfo);
}
