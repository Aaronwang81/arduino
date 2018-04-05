package com.ai.robot.ipurifier.feature.move;

import android.content.Context;
import android.hardware.usb.UsbDevice;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by aaronwang on 2018/3/20.
 */

public class MovePresenterImp implements IMovePresenter {

    private IMoveController _controller = null;
    private Context _context = null;
    private Set<IMoveControlView> _viewSet = new HashSet<>();


    public MovePresenterImp(Context context){
        _context = context;
        _controller = new MoveControllerImp();
    }


    private void printDeviceInfo(UsbDevice usbDevice){

    }


    private void updateStatus(String status){
        for (IMoveControlView view : _viewSet) {
            view.updateStatus(status);
        }
    }

    @Override
    public void registerView(IMoveControlView view) {

        _viewSet.add(view);
    }

    @Override
    public void unregisterView(IMoveControlView view) {
        _viewSet.remove(view);
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
