package com.ai.robot.ipurifier.device.controller;

import com.ai.robot.ipurifier.manager.IDeviceModel;
import com.ai.robot.ipurifier.manager.ModelManager;
import com.ai.robot.ipurifier.manager.TransitManager;
import com.ai.robot.ipurifier.utils.CommandConstant;
import com.ai.robot.ipurifier.utils.CommandHelper;

import static com.ai.robot.ipurifier.utils.CommandConstant.TYPE_PURIFIER;

public class PurifierControllerImp implements IPurifierController {
    @Override
    public void turnOn() {
        IDeviceModel.DeviceInfo deviceInfo = ModelManager.getInstance().getDeviceMode().getDeviceByType(TYPE_PURIFIER);
        if(null != deviceInfo){
            byte[] buffer = CommandHelper.makePurifierOn();
            TransitManager.getInstance().writeData(deviceInfo._device.getDeviceId(), buffer, CommandConstant.COMMAND_LENGTH);
        }
    }

    @Override
    public void turnOff() {
        IDeviceModel.DeviceInfo deviceInfo = ModelManager.getInstance().getDeviceMode().getDeviceByType(TYPE_PURIFIER);
        if(null != deviceInfo){
            byte[] buffer = CommandHelper.makePurifierOff();
            TransitManager.getInstance().writeData(deviceInfo._device.getDeviceId(), buffer, CommandConstant.COMMAND_LENGTH);
        }
    }

    @Override
    public void speedUp() {
        IDeviceModel.DeviceInfo deviceInfo = ModelManager.getInstance().getDeviceMode().getDeviceByType(TYPE_PURIFIER);
        if(null != deviceInfo){
            byte[] buffer = CommandHelper.makePurifierSpeedUp();
            TransitManager.getInstance().writeData(deviceInfo._device.getDeviceId(), buffer, CommandConstant.COMMAND_LENGTH);
        }
    }

    @Override
    public void speedDown() {
        IDeviceModel.DeviceInfo deviceInfo = ModelManager.getInstance().getDeviceMode().getDeviceByType(TYPE_PURIFIER);
        if(null != deviceInfo){
            byte[] buffer = CommandHelper.makePurifierSpeedDown();
            TransitManager.getInstance().writeData(deviceInfo._device.getDeviceId(), buffer, CommandConstant.COMMAND_LENGTH);
        }
    }
}
