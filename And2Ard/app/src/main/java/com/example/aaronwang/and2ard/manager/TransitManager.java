package com.example.aaronwang.and2ard.manager;

import android.content.Context;
import android.hardware.usb.UsbRequest;
import android.os.Looper;

import com.example.aaronwang.and2ard.utils.CommandConstant;

import java.util.HashMap;
import java.util.Map;
import java.util.Queue;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.SynchronousQueue;

/**
 * Created by aaronwang on 2018/3/23.
 */

public class TransitManager {
    /**
     * Created by aaronwang on 2018/3/23.
     */


    public interface ITransitCallback {
        void onDataReceived(Integer deviceId, byte[] buffer);

    }

    private class BufferInfo{
        public Integer _deviceId;
        byte[] _buffer = null;
    }

    private static TransitManager g_transitManager = null;

    private Context _context = null;
    private Map<Integer, ITransitCallback> _mapCallback = new ConcurrentHashMap<>();
    private Map<Integer, IDeviceModel.DeviceInfo> _mapDevices = new ConcurrentHashMap<>();
    private SynchronousQueue<BufferInfo> _bufferQueue = new SynchronousQueue<>();

    private Thread _thread = null;
    private Runnable _runnable = new Runnable() {
        @Override
        public void run() {
            while (true) {
                //1.检查是否有数据待发送
                if (!_bufferQueue.isEmpty()) {
                    try {
                        BufferInfo bufferInfo = _bufferQueue.take();
                        IDeviceModel.DeviceInfo deviceInfo = getDeviceByID(bufferInfo._deviceId);
                        deviceInfo._transit.writeData(bufferInfo._buffer, bufferInfo._buffer.length, 0);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                //2.检查是否有数据到达
                for (IDeviceModel.DeviceInfo deviceInfo : _mapDevices.values()) {
                    byte[] buffer = deviceInfo._transit.readByte(CommandConstant.COMMAND_LENGTH);
                    if (null != buffer) {
                        dataReceived(deviceInfo._device.getDeviceId(), buffer);
                    }
                }

                //需要反复读取数据，这里也不应该用sleep。纠结中。不太熟悉java
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    };

    public static void createInstance(Context context){
        if(null == g_transitManager){
            g_transitManager = new TransitManager(context);
        }
    }

    public static TransitManager getInstance(){
        return g_transitManager;
    }

    public TransitManager(Context context){
        _context = context;
        _thread = new Thread(_runnable);
        _thread.start();

    }

    public void addDevice(IDeviceModel.DeviceInfo deviceInfo){
        _mapDevices.put(deviceInfo._device.getDeviceId(), deviceInfo);
        //添加设备后应该补一个读请求吗？
    }

    public void removeDevice(IDeviceModel.DeviceInfo deviceInfo){
        _mapDevices.remove(deviceInfo._device.getDeviceId());
    }

    public void registerCallback(Integer deviceId, ITransitCallback callback){
        _mapCallback.put(deviceId, callback);
    }

    public void unregisterCallback(Integer deviceId){
        _mapCallback.remove(deviceId);
    }


    public void writeData(Integer deviceId, byte[] buffer, int length){
        BufferInfo bufferInfo = new BufferInfo();
        bufferInfo._deviceId = deviceId;
        bufferInfo._buffer = buffer;
        try {
            _bufferQueue.put(bufferInfo);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void readData(Integer deviceId){

    }

    private IDeviceModel.DeviceInfo getDeviceByID(Integer deviceID){
            for (IDeviceModel.DeviceInfo deviceInfo : _mapDevices.values()) {
                if(deviceInfo._device.getDeviceId() == deviceID){
                    return deviceInfo;
                }
            }

        return null;
    }

    private void dataReceived(Integer deviceID, byte[] buffer){
        
            ITransitCallback callback = _mapCallback.get(deviceID);
            if(null != callback){
                callback.onDataReceived(deviceID, buffer);
            }
    }
}
