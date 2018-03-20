package com.example.aaronwang.and2ard.utils;

import com.example.aaronwang.and2ard.CommandConstant;

/**
 * Created by aaronwang on 2018/3/20.
 */

public class CommandHelper {
    public static byte[] makeForward(byte cm){
        byte[] buffer = new byte[4];
        buffer[0] = CommandConstant.COMMAND_ACTION;
        buffer[1] = CommandConstant.ACTION_FORWARD;
        buffer[2] = cm;
        buffer[3] = 0;
        return buffer;
    }

    public static byte[] makeBack(byte cm){
        byte[] buffer = new byte[4];
        buffer[0] = CommandConstant.COMMAND_ACTION;
        buffer[1] = CommandConstant.ACTION_BACK;
        buffer[2] = cm;
        buffer[3] = 0;
        return buffer;
    }

    public static byte [] makeLeft(byte time, boolean synergy){
        byte[] buffer = new byte[4];
        buffer[0] = CommandConstant.COMMAND_ACTION;
        buffer[1] = CommandConstant.ACTION_LEFT;
        buffer[2] = time;
        buffer[3] = (byte) (synergy ? 1 : 0);
        return buffer;
    }

    public static byte [] makeRight(byte time, boolean synergy){
        byte[] buffer = new byte[4];
        buffer[0] = CommandConstant.COMMAND_ACTION;
        buffer[1] = CommandConstant.ACTION_RIGHT;
        buffer[2] = time;
        buffer[3] = (byte) (synergy ? 1 : 0);
        return buffer;
    }
}
