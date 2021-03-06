package com.ai.robot.ipurifier.communication;

import android.hardware.usb.UsbDeviceConnection;
import android.hardware.usb.UsbEndpoint;
import android.hardware.usb.UsbRequest;

import java.nio.ByteBuffer;

/**
 * Created by aaronwang on 2018/3/20.
 */

public class UsbTransit {

    private UsbDeviceConnection _deviceConnection = null;
    private UsbEndpoint _endPointOut = null;
    private UsbEndpoint _endPointIn = null;

    public static int writeData(UsbDeviceConnection deviceConnection, UsbEndpoint endpointOut, byte[] buffer, int lenght, int timeout){
        return deviceConnection.bulkTransfer(endpointOut, buffer, lenght, timeout);
    }

    public UsbTransit(UsbDeviceConnection deviceConnection, UsbEndpoint endPointOut, UsbEndpoint endpointIn){
        _deviceConnection = deviceConnection;
        _endPointOut = endPointOut;
        _endPointIn = endpointIn;
    }


    public int writeData(byte[] buffer, int length, int timeout){
        return _deviceConnection.bulkTransfer(_endPointOut, buffer, length, timeout);
    }

    public byte[] readByte(int length){
        ByteBuffer byteBuffer = ByteBuffer.allocate(length);
        UsbRequest request = new UsbRequest();
        request.initialize(_deviceConnection, _endPointIn);
        request.queue(byteBuffer, length);
            if (_deviceConnection.requestWait() == request) {
                return byteBuffer.array();
            }

            return null;

    }
}
