package com.ai.robot.ipurifier.utils;

/**
 * Created by aaronwang on 2018/3/21.
 */

public class CommandHelper {
    public static byte[] makeForward(byte cm){
        byte[] buffer = new byte[CommandConstant.COMMAND_LENGTH];
        buffer[0] = CommandConstant.COMMAND_MOVE;
        buffer[1] = CommandConstant.MOVE_FORWARD;
        buffer[2] = cm;
        buffer[3] = 0;

        return buffer;
    }

    public static byte[] makeBack(byte cm){
        byte[] buffer = new byte[CommandConstant.COMMAND_LENGTH];
        buffer[0] = CommandConstant.COMMAND_MOVE;
        buffer[1] = CommandConstant.MOVE_BACK;
        buffer[2] = cm;
        buffer[3] = 0;

        return buffer;
    }

    public static byte[] makeLeft(byte time, boolean synergy){
        byte[] buffer = new byte[CommandConstant.COMMAND_LENGTH];
        buffer[0] = CommandConstant.COMMAND_MOVE;
        buffer[1] = CommandConstant.MOVE_LEFT;
        buffer[2] = time;
        buffer[3] = (byte) (synergy ? 1 : 0);

        return buffer;
    }

    public static byte[] makeRight(byte time, boolean synergy){
        byte[] buffer = new byte[CommandConstant.COMMAND_LENGTH];
        buffer[0] = CommandConstant.COMMAND_MOVE;
        buffer[1] = CommandConstant.MOVE_RIGHT;
        buffer[2] = time;
        buffer[3] = (byte) (synergy ? 1 : 0);

        return buffer;
    }

    public static byte[] makeGetInfo(byte info){
        byte[] buffer = new byte[CommandConstant.COMMAND_LENGTH];
        buffer[0] = CommandConstant.COMMAND_GET_INFO;
        buffer[1] = CommandConstant.GET_TYPE;
        buffer[2] = 0;
        buffer[3] = 0;

        return buffer;
    }
}
