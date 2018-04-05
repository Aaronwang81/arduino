package com.ai.robot.ipurifier.manager;

import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.hardware.usb.UsbConstants;
import android.hardware.usb.UsbDevice;
import android.hardware.usb.UsbDeviceConnection;
import android.hardware.usb.UsbEndpoint;
import android.hardware.usb.UsbInterface;
import android.hardware.usb.UsbManager;
import android.util.Log;
import android.widget.Toast;

import com.ai.robot.ipurifier.R;
import com.ai.robot.ipurifier.communication.UsbTransit;
import com.ai.robot.ipurifier.utils.CommandConstant;
import com.ai.robot.ipurifier.utils.CommandHelper;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by aaronwang on 2018/3/23.
 */

public class DeviceModelImp implements IDeviceModel, TransitManager.ITransitCallback {

    private Context _context = null;
    private List<DeviceInfo> _deviceList = new LinkedList<>();

    //private UsbDeviceConnection _deviceConnection = null;
    //private UsbEndpoint _usbOut = null;
    //private UsbEndpoint _usbIn = null;
    private PendingIntent _permissionIntent = null;
    private Timer _timer = null;
    private Set<IConnectDeviceCallback> _callbackSet = new HashSet<>();

    private final TimerTask _timerTask = new TimerTask() {
        @Override
        public void run() {
//            //_view.updateStatus("Begin read data.");
//            String result = "";
//            byte[] temp = null;
//            if(null != _transit){
//                temp = _transit.readByte();
//                result = temp.length + " : " + new String(temp);
//
//                updateStatus(result);
//
//            }

        }
    };



    private static final String ACTION_USB_PERMISSION = "com.android.example.USB_PERMISSION";
    private final BroadcastReceiver _usbPermissionReceiver = new BroadcastReceiver() {

        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (ACTION_USB_PERMISSION.equals(action)) {
                synchronized (this) {
                    UsbDevice device = intent.getParcelableExtra(UsbManager.EXTRA_DEVICE);

                    if (intent.getBooleanExtra(UsbManager.EXTRA_PERMISSION_GRANTED, false)) {
                        if(device != null){
                            //call method to set up _device communication
                            updateStatus("Connect Device: " + ((null != device) ? device.getDeviceId() : "null"));
                            connectDevice(device, (UsbManager)_context.getSystemService(Context.USB_SERVICE));
                        }
                    }
                    else {
                        Log.d("And2Ard", "permission denied for _device " + device);
                    }
                }
            }
        }
    };
    private final  BroadcastReceiver _usbDeviceConnectReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            UsbDevice device = (UsbDevice)intent.getExtras().get(UsbManager.EXTRA_DEVICE);
            if(action.equals(UsbManager.ACTION_USB_DEVICE_ATTACHED)){
                UsbManager usbManager = (UsbManager)_context.getSystemService(Context.USB_SERVICE);
                if(null != usbManager) {
                    usbManager.requestPermission(device, _permissionIntent);
                }
            }else if(action.equals(UsbManager.ACTION_USB_DEVICE_DETACHED)){
                DeviceInfo deviceInfo = getDeviceInfo(device);
                for(int index = 0; index < _deviceList.size(); ++index){
                    if(_deviceList.get(index)._device.getDeviceId() == device.getDeviceId()){
                        _deviceList.remove(index);
                        break;
                    }
                }
                onDeviceDisconnect(deviceInfo);
            }

        }
    };

    public DeviceModelImp(Context context){
        _context = context;
        UsbManager usbManager = (UsbManager)_context.getSystemService(Context.USB_SERVICE);
        if(null != usbManager) {

            _permissionIntent = PendingIntent.getBroadcast(_context, 0, new Intent(ACTION_USB_PERMISSION), 0);
            IntentFilter filter = new IntentFilter(ACTION_USB_PERMISSION);
            _context.registerReceiver(_usbPermissionReceiver, filter);
        }

        _context.registerReceiver(_usbDeviceConnectReceiver, new IntentFilter(UsbManager.ACTION_USB_DEVICE_ATTACHED));
        _context.registerReceiver(_usbDeviceConnectReceiver, new IntentFilter(UsbManager.ACTION_USB_DEVICE_DETACHED));
    }

    @Override
    public List<DeviceInfo> getDeviceList() {
        if(_deviceList.isEmpty()){
            getAllDevices();
        }
        return _deviceList;
    }

    @Override
    public void registerCalback(IConnectDeviceCallback callback) {
        _callbackSet.add(callback);
    }

    @Override
    public void unregisterCallback(IConnectDeviceCallback callback) {
        _callbackSet.remove(callback);
    }

    private void getAllDevices(){
        _deviceList.clear();
        UsbManager usbManager = (UsbManager)_context.getSystemService(Context.USB_SERVICE);
        if(null != usbManager){

            _permissionIntent = PendingIntent.getBroadcast(_context, 0, new Intent(ACTION_USB_PERMISSION), 0);
            IntentFilter filter = new IntentFilter(ACTION_USB_PERMISSION);
            _context.registerReceiver(_usbPermissionReceiver, filter);


            HashMap<String,UsbDevice> deviceList = usbManager.getDeviceList();
            if(deviceList.isEmpty()){
                updateStatus("No Device connected.");
                //_view.updateDeviceInfo(null);
            }
            for (UsbDevice usbDevice : deviceList.values()) {
                printDeviceInfo(usbDevice);
                usbManager.requestPermission(usbDevice, _permissionIntent);
            }

        }
    }

    private void connectDevice(UsbDevice usbDevice, UsbManager usbManager){
        UsbInterface usbInterface;
        UsbEndpoint endpointOut = null;
        UsbEndpoint endpointIn = null;
        for(int index  = 0; index < usbDevice.getInterfaceCount(); ++index){
            usbInterface = usbDevice.getInterface(index);
            if(usbInterface.getEndpointCount() >= 2){
                for(int endIndex = 0; endIndex < usbInterface.getEndpointCount(); ++endIndex){
                    if(UsbConstants.USB_ENDPOINT_XFER_BULK == usbInterface.getEndpoint(endIndex).getType()){
                        if(UsbConstants.USB_DIR_OUT == usbInterface.getEndpoint(endIndex).getDirection()){
                            endpointOut = usbInterface.getEndpoint(endIndex);
                        }else if(UsbConstants.USB_DIR_IN == usbInterface.getEndpoint(endIndex).getDirection()){
                            endpointIn = usbInterface.getEndpoint(endIndex);
                        }

                        //找到输入和输出端口
                        if(null != endpointOut && null != endpointIn ){
                            UsbDeviceConnection connection = usbManager.openDevice(usbDevice);
                            if(null != connection && connection.claimInterface(usbInterface, true)){
                                //_deviceConnection = connection;
                                DeviceInfo deviceInfo = new DeviceInfo();
                                deviceInfo._device = usbDevice;
                                deviceInfo._transit = new UsbTransit(connection, endpointOut, endpointIn);
                                _deviceList.add(deviceInfo);
                                onDeviceConnected(deviceInfo);
                                //TODO:发包查询设备类型
                                updateStatus("Find Endpoint.");
                            }else{
                                updateStatus("Open _device OR claimInterface failed.");
                            }
                            break;
                        }
                    }
                }

                endpointOut = null;
                endpointIn = null;

            }

        }

    }

    private void onDeviceConnected(DeviceInfo deviceInfo){
        //
        for (IConnectDeviceCallback callback : _callbackSet) {
            callback.onConnect(deviceInfo, true);
        }

        TransitManager.getInstance().addDevice(deviceInfo);
        TransitManager.getInstance().registerCallback(deviceInfo._device.getDeviceId(), this);
        TransitManager.getInstance().writeData(deviceInfo._device.getDeviceId()
                , CommandHelper.makeGetInfo(CommandConstant.GET_TYPE)
                , CommandConstant.COMMAND_LENGTH);

    }

    private void onDeviceDisconnect(DeviceInfo deviceInfo){
        for (IConnectDeviceCallback callback : _callbackSet) {
            callback.onDisconnect(deviceInfo);
        }

        TransitManager.getInstance().removeDevice(deviceInfo);
        TransitManager.getInstance().unregisterCallback(deviceInfo._device.getDeviceId());
    }

    private DeviceInfo getDeviceInfo(UsbDevice device){
        for (DeviceInfo deviceInfo : _deviceList) {
            if(device.getDeviceId() == deviceInfo._device.getDeviceId()){
                return deviceInfo;
            }
        }
        return null;
    }

    private void updateStatus(String status){
        Toast.makeText(_context, status, Toast.LENGTH_LONG).show();
    }

    private void printDeviceInfo(UsbDevice device){

    }

    @Override
    public void onDataReceived(Integer deviceId, byte[] buffer) {

    }
}
