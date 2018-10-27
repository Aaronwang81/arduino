package com.turingqa.JsonClass;

import java.util.HashMap;
import java.util.Map;

public class Constant {

    static public Map<Integer, String> CODEMAP = new HashMap<Integer, String>() {
        {
            put(10003, "新闻");
            put(10004, "聊天");
            put(10005, "附近酒店");
            put(10006, "笑话");
            put(10008, "天气");
            put(10009, "数学计算");
            put(10010, "讲故事");
            put(10011, "成语接龙");
            put(10013, "生活百科");
            put(10014, "图片");
            put(10015, "菜谱");
            put(10016, "快递查询");
            put(10018, "列车、票务");
            put(10019, "日期、节日查询");
            put(10020, "中英互译");
            put(10022, "脑筋急转弯");
            put(10026, "股票");
            put(10030, "歇后语");
            put(10031, "绕口令");
            put(10032, "顺口溜");
            put(10041, "星座运势");
            put(10044, "藏头诗");
        }
    };

    public static final String TYPE_TEXT = "text";    //文本
    public static final String TYPE_URL = "url";      //链接
    public static final String TYPE_VOICE = "voice";  //音频
    public static final String TYPE_VIDEO = "video";  //视频
    public static final String TYPE_IMAGE = "image";  //图片
    public static final String TYPE_NEWS = "news";    //图文（混排 ？）
}
