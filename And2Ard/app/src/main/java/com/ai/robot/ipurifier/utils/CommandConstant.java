package com.ai.robot.ipurifier.utils;

/**
 * Created by aaronwang on 2018/3/20.
 */

public class CommandConstant {

    //命令构成：共4个字节，第一个字节是一级命令分类，第二个字节是子命令类型，第三，第四个字节是命令参数
    //TODO:扩展命令到8个字节,增加返回码支持

    //单个命令长度,8个字节
    public static int COMMAND_LENGTH = 4;

    //一级命令分类
    public static byte COMMAND_MOVE = 0x10; //命令：做动作
    public static byte COMMAND_GET_INFO = 0x20;//命令：取信息
    public static byte COMMAND_UPDATE_FIREWARE = 0x30;//命令：更新固件
    public static byte COMMAND_RESET = 0x40;//命令：重启
    public static byte COMMAND_REPORT = 0x50;//命令：控制器上报信息

    //WASD控制移动
    public static byte MOVE_FORWARD = 'W';//第三字节表示要前进的距离，单位CM
    public static byte MOVE_LEFT = 'A';//第三个字节表示转向角度（时间？），第4个字节表示是否为原地旋转（即另一个轮反向旋转）
    public static byte MOVE_RIGHT = 'D';//第三个字节表示转向角度（时间？），第4个字节表示是否为原地旋转（即另一个轮反向旋转）
    public static byte MOVE_BACK = 'S';//第三字节表示要后退的距离，单位CM

    //获取控制器信息子命令
    public static byte GET_TYPE = 0x21;//获取控制器类型,无参数

    //控制器类型
    public static byte TYPE_MOVE = 0x01;//控制行走
    public static byte TYPE_PURIFIER = 0x02;//控制净化器
    public static byte TYPE_NOISE = 0x03;//监听声源
    public static byte TYPE_HEAD_ACTION = 0x04;//控制头部活动
}
