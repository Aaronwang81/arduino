package com.turingqa;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.turingqa.JsonClass.request.Request;
import com.turingqa.JsonClass.request.perception.*;
import com.turingqa.JsonClass.request.userinfo.UserInfo;

public class AppEntry {

    public static void main(String[] args) {

        Request request = new Request();
        Gson gson = new Gson();

        request.reqType = 0;
        request.perception = new Perception();
        request.perception.inputText = new InputText();
        request.perception.inputImage = new InputImage();
        request.perception.inputMedia = new InputMedia();
        request.perception.inputText.text = "纪纲人伦";
        request.perception.inputImage.url = "http://test.com";
        request.perception.inputMedia.url = "http://media";
        request.perception.selfInfo = new SelfInfo();
        request.perception.selfInfo.location = new Location();
        request.perception.selfInfo.location.city = "广州";
        request.perception.selfInfo.location.province = "广东";
        request.perception.selfInfo.location.street = "";
        request.userInfo = new UserInfo();
        request.userInfo.apiKey = "51a8fac0ecc3470fa9237cfd71a7b6ed";
        request.userInfo.userId = "123456";

        String temp = gson.toJson(request);
        System.out.println(temp);
        String result = HttpUtils.sendPostMessage("http://openapi.tuling123.com/openapi/api/v2", temp,  "UTF-8");
        System.out.println(result);

    }
}
