package com.example.aaronwang.and2ard.feature.move;

import com.example.aaronwang.and2ard.communication.UsbTransit;

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
