package com.turingqa;

import com.google.gson.Gson;
import com.turingqa.JsonClass.Constant;
import com.turingqa.JsonClass.request.Request;
import com.turingqa.JsonClass.response.Response;
import com.turingqa.JsonClass.response.result.News;
import com.turingqa.JsonClass.response.result.Result;
import okhttp3.Call;
import okhttp3.Callback;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class AppEntry {

    public static void main(String[] args) {

        Request request = Request.makeRequest();
        Gson gson = new Gson();

        request.perception.inputImage.url = "http://test.com";
        request.perception.inputMedia.url = "http://media";
        request.perception.selfInfo.location.city = "广州";
        request.perception.selfInfo.location.province = "广东";
        request.perception.selfInfo.location.street = "万惠一路";
        request.userInfo.apiKey = "51a8fac0ecc3470fa9237cfd71a7b6ed";
        request.userInfo.userId = "123456";

        Callback httpCallback = new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, okhttp3.Response response) throws IOException {
                String result = response.body().string();
                System.out.println(result);
                Response res = gson.fromJson(result, Response.class);
                String  resultType = Constant.CODEMAP.get(res.intent.code);
                System.out.println("Result date type is : " + resultType);
                showResults(res.results);
                //System.out.println(result);
            }
        };

        while(true){
            request.perception.inputText.text = JOptionPane.showInputDialog("输入文字：");
            if(null == request.perception.inputText.text || request.perception.inputText.text.isEmpty()){
                break;
            }
            String temp = gson.toJson(request);
            System.out.println(temp);
            String result = HttpUtils.sendPostMessage("http://openapi.tuling123.com/openapi/api/v2", temp,  "UTF-8", httpCallback);

        }
        //System.out.println(response.intent.parameters.toString());
    }

    static void showResults(Result[] results){
        for (Result result : results) {
            switch (result.resultType){
                case Constant.TYPE_TEXT:
                    System.out.println(result.values.text);
                    break;
                case Constant.TYPE_URL:
                    System.out.println(result.values.url);
                    if(Desktop.isDesktopSupported()){
                        try {
                            Desktop.getDesktop().browse(new URI(result.values.url));
                        } catch (IOException e) {
                            e.printStackTrace();
                        } catch (URISyntaxException e) {
                            e.printStackTrace();
                        }
                    }
                    break;
                case Constant.TYPE_VOICE:
                    System.out.println(result.values.url);
                    break;
                case Constant.TYPE_VIDEO:
                    System.out.println(result.values.url);
                    break;
                case Constant.TYPE_IMAGE:
                    System.out.println(result.values.url);
                    break;
                case Constant.TYPE_NEWS:
                    showNews(result.values.news);
                    break;
            }

        }

    }

    static void showNews(News news[]){
        for(News n : news){
            System.out.println("标题：" + n.name);
            System.out.println("来源：" + n.info);
            System.out.println("图标：" + n.icon);
            System.out.println("详情：" + n.detailurl);
        }
    }
}
