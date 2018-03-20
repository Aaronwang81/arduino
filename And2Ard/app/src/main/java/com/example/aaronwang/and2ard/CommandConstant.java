package com.example.aaronwang.and2ard;

/**
 * Created by aaronwang on 2018/3/20.
 */

public class CommandConstant {

    //命令构成：共4个字节，第一个字节是一级命令分类，第二个字节是子命令类型，第三，第四个字节是命令参数

    //单个命令长度,8个字节
    public static int COMMAND_LENGTH = 4;

    //一级命令分类
    public static byte COMMAND_ACTION = 1; //命令：做动作
    public static byte COMMAND_GET_INFO = 2;//命令：取信息
    public static byte COMMAND_UPDATE_FIREWARE = 3;//命令：更新固件
    public static byte COMMAND_RESET = 4;//命令：重启

    //8,4,6,2对应小键盘上的上左右下
    public static byte ACTION_FORWARD = 8;//第三字节表示要前进的距离，单位CM
    public static byte ACTION_LEFT = 4;//第三个字节表示转向角度（时间？），第4个字节表示是否为原地旋转（即另一个轮反向旋转）
    public static byte ACTION_RIGHT = 6;//第三个字节表示转向角度（时间？），第4个字节表示是否为原地旋转（即另一个轮反向旋转）
    public static byte ACTION_BACK = 2;//第三字节表示要后退的距离，单位CM
}
