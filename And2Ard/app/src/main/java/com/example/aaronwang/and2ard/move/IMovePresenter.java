package com.example.aaronwang.and2ard.move;

/**
 * Created by aaronwang on 2018/3/20.
 */

public interface IMovePresenter {
    public void setController(IMoveController controller);
    public void setView(IMoveControlView view);
    public void listDevice();
    public void forward();
    public void back();
    public void left();
    public void right();
}
