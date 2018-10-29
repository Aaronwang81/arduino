package com.ai.robot.ipurifier.manager;

import android.hardware.usb.UsbDevice;

import com.ai.robot.ipurifier.communication.UsbTransit;

import java.util.HashMap;
import java.util.List;

/**
 * Created by aaronwang on 2018/3/23.
 */

public interface IDeviceModel {

    class DeviceInfo{
        public UsbDevice _device;
        public byte _type;
        public UsbTransit _transit;
    }

    interface IConnectDeviceCallback{
        void onConnect(DeviceInfo deviceInfo, boolean success);
        void onDisconnect(DeviceInfo deviceInfo);
    }

    List<DeviceInfo> getDeviceList();

    void registerCalback(IConnectDeviceCallback callback);
    void unregisterCallback(IConnectDeviceCallback callback);

    DeviceInfo getDeviceByType(byte type);

}
