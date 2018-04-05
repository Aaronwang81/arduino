package com.ai.robot.ipurifier.feature.mainpage;

import com.ai.robot.ipurifier.manager.IDeviceModel;

/**
 * Created by aaronwang on 2018/3/23.
 */

public interface IMainPageView {
    void onNewDeviceCome(IDeviceModel.DeviceInfo deviceInfo);
    void onDeviceRemoved(IDeviceModel.DeviceInfo deviceInfo);
}
