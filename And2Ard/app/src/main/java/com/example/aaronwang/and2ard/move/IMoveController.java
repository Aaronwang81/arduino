package com.example.aaronwang.and2ard.move;

import com.example.aaronwang.and2ard.communication.UsbTransit;

/**
 * Created by aaronwang on 2018/3/20.
 */

public interface IMoveController {

    public void setUsbTransit(UsbTransit usbTransit);
    public void forward(int cm);
    public void back(int cm);
    public void left();
    public void right();
}
