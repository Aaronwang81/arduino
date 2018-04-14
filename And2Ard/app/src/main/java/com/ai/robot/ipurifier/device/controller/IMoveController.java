package com.ai.robot.ipurifier.device.controller;

import com.ai.robot.ipurifier.communication.UsbTransit;

/**
 * Created by aaronwang on 2018/3/20.
 */

public interface IMoveController {

    void setUsbTransit(UsbTransit usbTransit);
    void forward(int cm);
    void back(int cm);
    void left();
    void right();
}
