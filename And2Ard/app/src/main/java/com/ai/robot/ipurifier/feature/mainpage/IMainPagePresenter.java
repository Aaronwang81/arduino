package com.ai.robot.ipurifier.feature.mainpage;

import com.ai.robot.ipurifier.manager.IDeviceModel;

import java.util.List;

/**
 * Created by aaronwang on 2018/3/23.
 */

public interface IMainPagePresenter {
    void registerView(IMainPageView view);
    void unregisterView(IMainPageView view);

    List<IDeviceModel.DeviceInfo> getDeviceList();


}
