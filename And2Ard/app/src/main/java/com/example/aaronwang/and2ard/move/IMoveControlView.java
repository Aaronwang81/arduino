package com.example.aaronwang.and2ard.move;

import android.hardware.usb.UsbDevice;

/**
 * Created by aaronwang on 2018/3/20.
 */

public interface IMoveControlView {
    public void setPresenter(IMovePresenter presenter);
    public void updateDeviceInfo(UsbDevice device);
    public void updateStatus(String status);
}
