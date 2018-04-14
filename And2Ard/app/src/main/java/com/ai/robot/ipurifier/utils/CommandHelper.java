package com.ai.robot.ipurifier.utils;

/**
 * Created by aaronwang on 2018/3/21.
 */

public class CommandHelper {

    public static boolean isCommand(byte[] buffer, byte command, byte subCommand){
        if(buffer.length != CommandConstant.COMMAND_LENGTH){
            return false;
        }

        if(subCommand != -1){
            if(command == buffer[0] && subCommand == buffer[1]){
                return true;
            }else{
                return false;
            }
        }

        return command ==  buffer[0];
    }

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

    public static byte[] makePurifierOn(){
        byte[] buffer = new byte[CommandConstant.COMMAND_LENGTH];
        buffer[0] = CommandConstant.COMMAND_PURIFIER;
        buffer[1] = CommandConstant.PURIFIER_ON;
        buffer[2] = 0;
        buffer[3] = 0;

        return buffer;
    }
    public static byte[] makePurifierOff(){
        byte[] buffer = new byte[CommandConstant.COMMAND_LENGTH];
        buffer[0] = CommandConstant.COMMAND_PURIFIER;
        buffer[1] = CommandConstant.PURIFIER_OFF;
        buffer[2] = 0;
        buffer[3] = 0;

        return buffer;
    }

    public static byte[] makePurifierSpeedUp(){
        byte[] buffer = new byte[CommandConstant.COMMAND_LENGTH];
        buffer[0] = CommandConstant.COMMAND_PURIFIER;
        buffer[1] = CommandConstant.PURIFIER_SPEED_UP;
        buffer[2] = 0;
        buffer[3] = 0;

        return buffer;
    }

    public static byte[] makePurifierSpeedDown(){
        byte[] buffer = new byte[CommandConstant.COMMAND_LENGTH];
        buffer[0] = CommandConstant.COMMAND_PURIFIER;
        buffer[1] = CommandConstant.PURIFIER_SPEED_DOWN;
        buffer[2] = 0;
        buffer[3] = 0;

        return buffer;
    }

    public static byte[] makePurifierSetSpeed(byte speed){
        if(speed > 100) speed = 100;
        if(speed < 50 ) speed  = 50;
        byte[] buffer = new byte[CommandConstant.COMMAND_LENGTH];
        buffer[0] = CommandConstant.COMMAND_PURIFIER;
        buffer[1] = CommandConstant.PURIFIER_SET_SPEED;
        buffer[2] = speed;
        buffer[3] = 0;

        return buffer;
    }

}
