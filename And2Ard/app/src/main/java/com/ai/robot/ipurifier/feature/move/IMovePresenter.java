package com.ai.robot.ipurifier.feature.move;

/**
 * Created by aaronwang on 2018/3/20.
 */

public interface IMovePresenter {
    void registerView(IMoveControlView view);
    void unregisterView(IMoveControlView view);
    void forward();
    void back();
    void left();
    void right();
}
