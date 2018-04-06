package com.ai.robot.ipurifier;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;

import com.baidu.android.voicedemo.control.MyRecognizer;
import com.baidu.android.voicedemo.recognization.MessageStatusRecogListener;
import com.baidu.android.voicedemo.recognization.StatusRecogListener;
import com.baidu.android.voicedemo.util.AuthInfo;

import java.util.HashMap;
import java.util.Map;

public class VoiceAssisant {

    private Context _context;
    private MyRecognizer _recognizer;

    public VoiceAssisant(Context context){
        _context = context;
        StatusRecogListener listener = new MessageStatusRecogListener(new MessageHandler(Looper.getMainLooper()));
        _recognizer = new MyRecognizer(context, listener);
    }

    public void start(){
        Map<String, Object> params = new HashMap<>();
        params.put("com.baidu.speech.APP_ID", 11051727);
        params.put("com.baidu.speech.API_KEY", "aeDI3voQiaAdCE9nbyl1rGtO");
        params.put("com.baidu.speech.SECRET_KEY", "eQglHoCa1yBMIlUIAyQUnw8kPwmQzx7G");
        params.put("pid", 1536);
        _recognizer.start(params);
    }

    public void stop(){
        _recognizer.stop();
    }

    public void cancel(){
        _recognizer.cancel();
    }

    public void release(){
        _recognizer.release();
    }

    private class MessageHandler extends Handler {

        public MessageHandler(Looper loop){
            super(loop);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            //TODO:处理回调消息
            Log.d("VoiceAssisant", msg.obj.toString());
        }
    }

}
