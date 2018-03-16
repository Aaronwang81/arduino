package com.turingqa.JsonClass.request;

import com.turingqa.JsonClass.request.perception.*;
import com.turingqa.JsonClass.request.userinfo.UserInfo;

public class Request {
    public int reqType;
    public Perception perception;
    public UserInfo userInfo;

    private Request(){

    }

    public static Request makeRequest(){
        Request request = new Request();
        request.reqType = 0;
        request.perception = new Perception();
        request.perception.inputText = new InputText();
        request.perception.inputImage = new InputImage();
        request.perception.inputMedia = new InputMedia();
        request.perception.selfInfo = new SelfInfo();
        request.perception.selfInfo.location = new Location();
        request.userInfo = new UserInfo();

        return request;
    }
}
