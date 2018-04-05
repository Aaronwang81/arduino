package com.example.aaronwang.and2ard.feature.mainpage;

import com.example.aaronwang.and2ard.manager.IDeviceModel;

import java.util.List;

/**
 * Created by aaronwang on 2018/3/23.
 */

public interface IMainPagePresenter {
    void registerView(IMainPageView view);
    void unregisterView(IMainPageView view);

    List<IDeviceModel.DeviceInfo> getDeviceList();


}
