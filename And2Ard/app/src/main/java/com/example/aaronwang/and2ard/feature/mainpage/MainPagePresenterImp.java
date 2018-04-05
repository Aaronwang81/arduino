package com.example.aaronwang.and2ard.feature.mainpage;

import android.content.Context;

import com.example.aaronwang.and2ard.manager.IDeviceModel;
import com.example.aaronwang.and2ard.manager.ModelManager;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by aaronwang on 2018/3/23.
 */

public class MainPagePresenterImp implements IMainPagePresenter, IDeviceModel.IConnectDeviceCallback {

    private Context _context = null;
    private Set<IMainPageView> _viewSet = new HashSet<>();

    public MainPagePresenterImp(Context context){
        _context = context;
        ModelManager.getInstance().getDeviceMode().registerCalback(this);
    }

    @Override
    public void registerView(IMainPageView view) {
        _viewSet.add(view);
    }

    @Override
    public void unregisterView(IMainPageView view) {
        _viewSet.remove(view);
    }

    @Override
    public List<IDeviceModel.DeviceInfo> getDeviceList() {
        return ModelManager.getInstance().getDeviceMode().getDeviceList();
    }

    @Override
    public void onConnect(IDeviceModel.DeviceInfo deviceInfo, boolean success) {
        for (IMainPageView view : _viewSet) {
            view.onNewDeviceCome(deviceInfo);
        }
    }

    @Override
    public void onDisconnect(IDeviceModel.DeviceInfo deviceInfo) {
        for (IMainPageView view : _viewSet) {
            view.onDeviceRemoved(deviceInfo);
        }
    }
}
