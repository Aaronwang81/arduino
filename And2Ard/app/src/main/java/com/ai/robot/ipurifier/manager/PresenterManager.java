package com.ai.robot.ipurifier.manager;

import android.content.Context;

import com.ai.robot.ipurifier.feature.mainpage.IMainPagePresenter;
import com.ai.robot.ipurifier.feature.mainpage.IMainPageView;
import com.ai.robot.ipurifier.feature.mainpage.MainPagePresenterImp;
import com.ai.robot.ipurifier.feature.move.IMoveControlView;
import com.ai.robot.ipurifier.feature.move.IMovePresenter;
import com.ai.robot.ipurifier.feature.move.MovePresenterImp;

/**
 * Created by aaronwang on 2018/3/22.
 */

public class PresenterManager {
    private static PresenterManager g_manager = null;

    private Context _context = null;
    private IMovePresenter _movePresenter = null;
    private IMainPagePresenter _mainPresenter = null;


    public static void createInstance(Context context){
        if(null == g_manager){
            g_manager = new PresenterManager(context);
        }
    }

    public static PresenterManager getInstance(){

        return g_manager;
    }

    public PresenterManager(Context context){
        _context = context;
    }

    public IMovePresenter getMovePresenter(){
        if(null == _movePresenter){
            _movePresenter = new MovePresenterImp(_context);
        }
        return _movePresenter;
    }

    public IMainPagePresenter getMainPagePresenter(){
        if(null == _mainPresenter){
            _mainPresenter = new MainPagePresenterImp(_context);
        }
        return _mainPresenter;
    }
}
