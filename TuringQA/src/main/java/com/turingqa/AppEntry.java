package com.turingqa;

import com.google.gson.Gson;
import com.turingqa.JsonClass.request.Request;
import com.turingqa.JsonClass.response.Response;

public class AppEntry {

    public static void main(String[] args) {

        Request request = Request.makeRequest();
        Gson gson = new Gson();


        request.perception.inputText.text = "华师附中";
        request.perception.inputText.text = "父亲亲节时间";
        request.perception.inputImage.url = "http://test.com";
        request.perception.inputMedia.url = "http://media";
        request.perception.selfInfo.location.city = "广州";
        request.perception.selfInfo.location.province = "广东";
        request.perception.selfInfo.location.street = "万惠一路";
        request.userInfo.apiKey = "51a8fac0ecc3470fa9237cfd71a7b6ed";
        request.userInfo.userId = "123456";

        String temp = gson.toJson(request);
        System.out.println(temp);
        String result = HttpUtils.sendPostMessage("http://openapi.tuling123.com/openapi/api/v2", temp,  "UTF-8");
        Response response = gson.fromJson(result, Response.class);
        System.out.println(result);
        //System.out.println(response.intent.parameters.toString());
    }
}
