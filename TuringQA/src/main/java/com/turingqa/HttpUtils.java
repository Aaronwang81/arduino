package com.turingqa;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpUtils {

    //  params填写的url参数，encode字节编码
    public static String sendPostMessage(String postUrl, String postData, String encode){
//      作为StringBuffer初始化字符串
        //StringBuffer buffer=new StringBuffer();//请求体就在缓冲区中
        try {

            //System.out.println("--> " + postData);
//          有时的bug就是按提示敲出来的一个类，多一个s就不行啦。注意按提示敲出来的类的书写细节。
            URL url = new URL(postUrl);
            HttpURLConnection urlConnection=(HttpURLConnection)url.openConnection();
            urlConnection.setConnectTimeout(3000);
            urlConnection.setRequestMethod("POST");
            urlConnection.setDoInput(true);//表示从服务器获取数据
            urlConnection.setDoOutput(true);//表示向服务器写数据
//          获得上传信息的字节大小以及长度
            byte[] mydata = postData.getBytes();
//          表示设置请求体的类型是文本类型、设置请求体的长度
            urlConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            urlConnection.setRequestProperty("Content-Length", String.valueOf(mydata.length));
//          获得输出流，向服务器输出数据
            OutputStream outputStream=urlConnection.getOutputStream();
            outputStream.write(mydata,0,mydata.length);
            outputStream.close();
//          获得服务器响应的结果和状态码
            int responseCode=urlConnection.getResponseCode();
            if(responseCode==200){
                return changeInputStream(urlConnection.getInputStream(),encode);
            }
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return "";
    }

    //将一个输入流转换成指定编码的字符串
    private static String changeInputStream(InputStream inputStream, String encode) {
        ByteArrayOutputStream outputStream=new ByteArrayOutputStream();
        byte[] data=new byte[1024];
        int len=0;
        String result="";
        if(inputStream!=null){
            try {
                while ((len=inputStream.read(data))!=-1) {
                    outputStream.write(data, 0, len);
                }
                result=new String(outputStream.toByteArray(),encode);
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return result;
    }
}
