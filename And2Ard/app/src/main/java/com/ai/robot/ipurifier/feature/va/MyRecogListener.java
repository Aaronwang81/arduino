package com.ai.robot.ipurifier.feature.va;

import android.widget.TextView;

import com.baidu.android.voicedemo.recognization.RecogResult;
import com.baidu.android.voicedemo.recognization.StatusRecogListener;
import com.baidu.tts.client.SpeechError;
import com.baidu.tts.client.SpeechSynthesizer;
import com.baidu.tts.client.SpeechSynthesizerListener;
import com.baidu.tts.client.TtsMode;

public class MyRecogListener extends StatusRecogListener {

    private TextView _statusView;
    private SpeechSynthesizer _synthesizer = SpeechSynthesizer.getInstance();

    public void setStatusView(TextView textView){
        _statusView = textView;
        _synthesizer.setContext(textView.getContext());
        _synthesizer.setAppId("11051727");
        _synthesizer.setApiKey("aeDI3voQiaAdCE9nbyl1rGtO", "eQglHoCa1yBMIlUIAyQUnw8kPwmQzx7G");
        _synthesizer.auth(TtsMode.MIX);
        _synthesizer.setParam(SpeechSynthesizer.PARAM_SPEAKER, "4");
        _synthesizer.setSpeechSynthesizerListener(new SpeechSynthesizerListener() {
            @Override
            public void onSynthesizeStart(String s) {

            }

            @Override
            public void onSynthesizeDataArrived(String s, byte[] bytes, int i) {

            }

            @Override
            public void onSynthesizeFinish(String s) {

            }

            @Override
            public void onSpeechStart(String s) {

            }

            @Override
            public void onSpeechProgressChanged(String s, int i) {

            }

            @Override
            public void onSpeechFinish(String s) {

            }

            @Override
            public void onError(String s, SpeechError speechError) {
                _statusView.setText("TTS error: " + s);
            }
        });

        _synthesizer.initTts(TtsMode.MIX);
    }

    @Override
    public void onAsrReady() {
        super.onAsrReady();
        _statusView.setText("Asr Ready.");
    }

    @Override
    public void onAsrBegin() {
        super.onAsrBegin();
        _statusView.setText("Asr Begin.");
    }

    @Override
    public void onAsrEnd() {
        super.onAsrEnd();
        //_statusView.setText("Asr End.");
    }

    @Override
    public void onAsrPartialResult(String[] results, RecogResult recogResult) {
        super.onAsrPartialResult(results, recogResult);
        _statusView.setText("Asr PartialResult.");
    }

    @Override
    public void onAsrFinalResult(String[] results, RecogResult recogResult) {
        super.onAsrFinalResult(results, recogResult);
        _statusView.setText("Asr FinalResult. Best result is: " + results[0] );
        _synthesizer.speak(results[0]);

    }

    @Override
    public void onAsrFinish(RecogResult recogResult) {
        super.onAsrFinish(recogResult);
        //_statusView.setText("Asr Finish.");
    }

    @Override
    public void onAsrFinishError(int errorCode, int subErrorCode, String errorMessage, String descMessage, RecogResult recogResult) {
        super.onAsrFinishError(errorCode, subErrorCode, errorMessage, descMessage, recogResult);
        _statusView.setText("Asr FinishError.");
    }

    @Override
    public void onAsrLongFinish() {
        super.onAsrLongFinish();
        //_statusView.setText("Asr LongFinish");
    }

    @Override
    public void onAsrVolume(int volumePercent, int volume) {
        super.onAsrVolume(volumePercent, volume);
    }

    @Override
    public void onAsrAudio(byte[] data, int offset, int length) {
        super.onAsrAudio(data, offset, length);
    }

    @Override
    public void onAsrExit() {
        super.onAsrExit();
    }

    @Override
    public void onAsrOnlineNluResult(String nluResult) {
        super.onAsrOnlineNluResult(nluResult);
    }

    @Override
    public void onOfflineLoaded() {
        super.onOfflineLoaded();
    }

    @Override
    public void onOfflineUnLoaded() {
        super.onOfflineUnLoaded();
    }
}
