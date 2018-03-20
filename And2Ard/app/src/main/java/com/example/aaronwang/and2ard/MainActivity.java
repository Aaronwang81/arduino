package com.example.aaronwang.and2ard;

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
import android.hardware.usb.UsbRequest;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity implements Runnable {

    TextView textOut;
    UsbDevice device = null;
    UsbDeviceConnection deviceConnection = null;
    UsbInterface usbFace = null;
    UsbEndpoint usbOut = null;
    UsbEndpoint usbIn = null;
    PendingIntent mPermissionIntent = null;

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
                            //call method to set up device communication
                            connectDevice(device, (UsbManager)getSystemService(Context.USB_SERVICE));
                        }
                    }
                    else {
                        Log.d("And2Ard", "permission denied for device " + device);
                    }
                }
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textOut = findViewById(R.id.textView);

        UsbManager usbManager = (UsbManager)getSystemService(Context.USB_SERVICE);
        if(null != usbManager){

            mPermissionIntent = PendingIntent.getBroadcast(this, 0, new Intent(ACTION_USB_PERMISSION), 0);
            IntentFilter filter = new IntentFilter(ACTION_USB_PERMISSION);
            registerReceiver(mUsbReceiver, filter);


            HashMap<String,UsbDevice> deviceList = usbManager.getDeviceList();
            for (UsbDevice usbDevice : deviceList.values()) {
                printDeviceInfo(usbDevice);
                usbManager.requestPermission(usbDevice, mPermissionIntent);
                break;
            }

        }
    }

    private void printDeviceInfo(UsbDevice usbDevice){
        textOut.setText( usbDevice.getDeviceId() + usbDevice.getDeviceName() );

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
            device = usbDevice;
            usbFace = usbInterface;
            usbOut = endpointOut;
            usbIn = endpointIn;
        }else{
            textOut.setText("No match Interface found.");
        }

        if(null != device){
            UsbDeviceConnection connection = usbManager.openDevice(device);
            if(null != connection && connection.claimInterface(usbFace, true)){
                deviceConnection = connection;
                Thread thread = new Thread(this);
                thread.start();
            }else{
                textOut.setText("Open device OR claimInterface failed.");
            }
        }
    }

    @Override
    public void run() {
        byte[] buffer = new byte[12];
        for (byte b : buffer) {
            b = 65;
        }
        while(true) {
            deviceConnection.bulkTransfer(usbOut, buffer, 12, 0);
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
