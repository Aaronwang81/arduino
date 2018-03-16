package com.turingqa.JsonClass.response;

import com.turingqa.JsonClass.response.emotion.EmotionPair;
import com.turingqa.JsonClass.response.intent.Intent;
import com.turingqa.JsonClass.response.result.Result;

public class Response {
    public EmotionPair emotion;
    public Intent intent;
    public Result results[];
}
