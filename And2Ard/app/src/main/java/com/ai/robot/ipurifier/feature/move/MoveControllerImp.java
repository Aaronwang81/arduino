package com.ai.robot.ipurifier.feature.move;

import com.ai.robot.ipurifier.utils.CommandConstant;
import com.ai.robot.ipurifier.communication.UsbTransit;
import com.ai.robot.ipurifier.utils.CommandHelper;

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
            byte[] buffer = CommandHelper.makeForward((byte)cm);
            _transit.writeData(buffer, CommandConstant.COMMAND_LENGTH, 0);
        }

    }

    @Override
    public void back(int cm) {
        if(null != _transit){
            byte[] buffer = CommandHelper.makeBack((byte)cm);
            _transit.writeData(buffer, CommandConstant.COMMAND_LENGTH, 0);
        }
    }

    @Override
    public void left() {
        if(null != _transit){
            byte[] buffer = CommandHelper.makeLeft((byte)100, true);
            _transit.writeData(buffer, CommandConstant.COMMAND_LENGTH, 0);
        }

    }

    @Override
    public void right() {
        if(null != _transit){
            byte[] buffer = CommandHelper.makeRight((byte)100, true);;
            _transit.writeData(buffer, CommandConstant.COMMAND_LENGTH, 0);
        }

    }
}
