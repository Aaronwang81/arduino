package com.example.aaronwang.and2ard.move;

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

import com.example.aaronwang.and2ard.communication.UsbTransit;

import java.util.HashMap;

/**
 * Created by aaronwang on 2018/3/20.
 */

public class MovePresenterImp implements Runnable, IMovePresenter {

    private IMoveController _controller = null;
    private IMoveControlView _view = null;


    private Context _context = null;
    private UsbDevice _device = null;
    //private UsbDeviceConnection _deviceConnection = null;
    private UsbInterface _usbFace = null;
    //private UsbEndpoint _usbOut = null;
    //private UsbEndpoint _usbIn = null;
    private UsbTransit _transit = null;
    private PendingIntent _PermissionIntent = null;
    private static IMovePresenter s_presenter = null;

    public static IMovePresenter getPresenter(Context context, IMoveControlView view){
        if(null == s_presenter){
            s_presenter = new MovePresenterImp(context, view);;
        }
        return s_presenter;
    }

    public MovePresenterImp(Context context, IMoveControlView view){
        _context = context;
        _view = view;
        _controller = new MoveControllerImp();
    }

    private static final String ACTION_USB_PERMISSION =
            "com.android.example.USB_PERMISSION";
    private final BroadcastReceiver mUsbReceiver = new BroadcastReceiver() {

        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (ACTION_USB_PERMISSION.equals(action)) {
                synchronized (this) {
                    UsbDevice device = (UsbDevice)intent.getParcelableExtra(UsbManager.EXTRA_DEVICE);

                    if (intent.getBooleanExtra(UsbManager.EXTRA_PERMISSION_GRANTED, false)) {
                        if(device != null){
                            //call method to set up _device communication
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

    protected void init() {

        UsbManager usbManager = (UsbManager)_context.getSystemService(Context.USB_SERVICE);
        if(null != usbManager){

            _PermissionIntent = PendingIntent.getBroadcast(_context, 0, new Intent(ACTION_USB_PERMISSION), 0);
            IntentFilter filter = new IntentFilter(ACTION_USB_PERMISSION);
            _context.registerReceiver(mUsbReceiver, filter);


            HashMap<String,UsbDevice> deviceList = usbManager.getDeviceList();
            if(deviceList.isEmpty()){
                updateStatus("No Device connected.");
                _view.updateDeviceInfo(null);
            }
            for (UsbDevice usbDevice : deviceList.values()) {
                printDeviceInfo(usbDevice);
                usbManager.requestPermission(usbDevice, _PermissionIntent);
                break;
            }

        }
    }

    private void printDeviceInfo(UsbDevice usbDevice){
        _view.updateDeviceInfo(usbDevice);

    }

    private void connectDevice(UsbDevice usbDevice, UsbManager usbManager){
        UsbInterface usbInterface = null;
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
                    }
                }

            }

        }

        if(null != endpointOut && null != endpointIn ){
            _device = usbDevice;
            _usbFace = usbInterface;
            //_usbOut = endpointOut;
            //_usbIn = endpointIn;
        }else{
            updateStatus("No match Interface found.");
        }

        if(null != _device){
            UsbDeviceConnection connection = usbManager.openDevice(_device);
            if(null != connection && connection.claimInterface(_usbFace, true)){
                //_deviceConnection = connection;
                if(null  == _transit){
                    _transit = new UsbTransit(connection, endpointOut, endpointIn);
                }
            }else{
                updateStatus("Open _device OR claimInterface failed.");
            }
        }
    }

    private void updateStatus(String status){
        if(null != _view){
            _view.updateStatus(status);
        }
    }

    @Override
    public void run() {

    }
    @Override
    public void setController(IMoveController controller) {
        _controller = controller;
    }

    @Override
    public void setView(IMoveControlView view) {
        _view = view;
    }

    @Override
    public void listDevice() {
        init();
    }

    @Override
    public void forward() {
        if(null != _controller){
            _controller.forward(10);
        }
    }

    @Override
    public void back() {
        if(null != _controller){
            _controller.back(10);
        }

    }

    @Override
    public void left() {
        if(null != _controller){
            _controller.left();
        }

    }

    @Override
    public void right() {
        if(null != _controller){
            _controller.right();
        }

    }
}
