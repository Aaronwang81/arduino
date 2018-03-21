package com.example.aaronwang.and2ard.move;

import com.example.aaronwang.and2ard.CommandConstant;
import com.example.aaronwang.and2ard.communication.UsbTransit;

/**
 * Created by aaronwang on 2018/3/20.
 */

public class MoveControllerImp implements IMoveController {

    private UsbTransit _transit = null;

    @Override
    public void setUsbTransit(UsbTransit usbTransit) {
        _transit = usbTransit;
    }

    @Override
    public void forward(int cm) {
        if(null != _transit){
            byte[] buffer = new byte[1];
            buffer[0] = CommandConstant.ACTION_FORWARD;
            _transit.writeData(buffer, CommandConstant.COMMAND_LENGTH, 0);
        }

    }

    @Override
    public void back(int cm) {
        if(null != _transit){
            byte[] buffer = new byte[1];
            buffer[0] = CommandConstant.ACTION_BACK;
            _transit.writeData(buffer, CommandConstant.COMMAND_LENGTH, 0);
        }
    }

    @Override
    public void left() {
        if(null != _transit){
            byte[] buffer = new byte[1];
            buffer[0] = CommandConstant.ACTION_LEFT;
            _transit.writeData(buffer, CommandConstant.COMMAND_LENGTH, 0);
        }

    }

    @Override
    public void right() {
        if(null != _transit){
            byte[] buffer = new byte[1];
            buffer[0] = CommandConstant.ACTION_RIGHT;
            _transit.writeData(buffer, CommandConstant.COMMAND_LENGTH, 0);
        }

    }
}
