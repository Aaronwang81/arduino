package com.example.aaronwang.and2ard;

import android.hardware.usb.UsbDevice;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.aaronwang.and2ard.move.IMoveControlView;
import com.example.aaronwang.and2ard.move.IMovePresenter;
import com.example.aaronwang.and2ard.move.MovePresenterImp;

public class MainActivity extends AppCompatActivity implements IMoveControlView {

    private IMovePresenter _presenter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        _presenter = MovePresenterImp.getPresenter(this, this);

        Button btnForward = findViewById(R.id.btnForward);
        Button btnLeft = findViewById(R.id.btnLeft);
        Button btnRight = findViewById(R.id.btnRight);
        Button btnBack = findViewById(R.id.btnBack);
        Button btnListDevice = findViewById(R.id.btnListDevice);
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(null == _presenter){
                    return;
                }
                switch (v.getId()){
                    case R.id.btnForward:
                        _presenter.forward();
                        break;
                    case R.id.btnLeft:
                        _presenter.left();
                        break;
                    case R.id.btnRight:
                        _presenter.right();
                        break;
                    case R.id.btnBack:
                        _presenter.back();
                        break;
                    case R.id.btnListDevice:
                        _presenter.listDevice();
                        break;
                    default:
                        //nothing
                }

            }
        };

        btnForward.setOnClickListener(listener);
        btnLeft.setOnClickListener(listener);
        btnRight.setOnClickListener(listener);
        btnBack.setOnClickListener(listener);
        btnListDevice.setOnClickListener(listener);

    }





    @Override
    public void setPresenter(IMovePresenter presenter) {
        _presenter = presenter;

    }

    @Override
    public void updateDeviceInfo(UsbDevice device) {
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1);

        //注释掉的代码需要API 21 或者 23

        if(null != device) {
            adapter.add(getString(R.string.device_name) + device.getDeviceName());
            //adapter.add(getString(R.string.man_name) + device.getManufacturerName());
            //adapter.add(getString(R.string.product_name) + device.getProductName());
            //adapter.add(getString(R.string.usb_version) + device.getVersion());
            //adapter.add(getString(R.string.serial_number) + device.getSerialNumber());
            adapter.add(getString(R.string.device_id) + device.getDeviceId());
            adapter.add(getString(R.string.vendor_id) + device.getVendorId());
            adapter.add(getString(R.string.product_id) + device.getProductId());
            adapter.add(getString(R.string.device_class) + device.getDeviceClass());
            adapter.add(getString(R.string.device_subclass) + device.getDeviceSubclass());
            adapter.add(getString(R.string.device_protocol) + device.getDeviceProtocol());
            //adapter.add(getString(R.string.config_count) + device.getConfigurationCount());
        }else{
            adapter.add(getString(R.string.device_name) + "null");
            //adapter.add(getString(R.string.man_name) + device.getManufacturerName());
            //adapter.add(getString(R.string.product_name) + device.getProductName());
            //adapter.add(getString(R.string.usb_version) + device.getVersion());
            //adapter.add(getString(R.string.serial_number) + device.getSerialNumber());
            adapter.add(getString(R.string.device_id) + "null");
            adapter.add(getString(R.string.vendor_id) + "null");
            adapter.add(getString(R.string.product_id) + "null");
            adapter.add(getString(R.string.device_class) + "null");
            adapter.add(getString(R.string.device_subclass) + "null");
            adapter.add(getString(R.string.device_protocol) + "null");
            //adapter.add(getString(R.string.config_count) + device.getConfigurationCount());
        }

        ListView listView = findViewById(R.id.listDeviceInfo);
        listView.setAdapter(adapter);
        adapter.notifyDataSetChanged();


    }

    @Override
    public void updateStatus(final String status) {
        if(Looper.getMainLooper().getThread() == Thread.currentThread()){
            TextView textStatus = findViewById(R.id.textStatus);
            textStatus.setText(status);
        }else{
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    TextView textStatus = findViewById(R.id.textStatus);
                    textStatus.setText(status);
                }
            });
        }

    }
}
