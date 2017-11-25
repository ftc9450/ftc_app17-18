package org.firstinspires.ftc.teamcode.control;

import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.teamcode.util.DriveSignal;

/**
 * Created by dhruv on 11/25/17.
 */

public class GameBoard {
    private Gamepad gamepad;

    public GameBoard(Gamepad gamepad) {
        this.gamepad = gamepad;
    }
    public DriveSignal translate(){
        float a=gamepad.left_stick_x;float b=gamepad.left_stick_y;
        double x=DriveSignal.floatToDouble(a);double y=DriveSignal.floatToDouble(b);
        if(x!=0) {
            double angle = Math.atan2(y, x) - Math.PI / 4;
            return new DriveSignal(Math.sin(angle), Math.cos(angle), Math.cos(angle), Math.sin(angle));
        }else{
            if(y>0){
                return new DriveSignal(1,1,1,1);
            }else if(y<0){
                return new DriveSignal(-1,-1,-1,-1);

            }return new DriveSignal(0,0,0,0);
        }
    }
    public float getLeftX() {
        return gamepad.left_stick_x;
    }
    public float getLeftY(){return gamepad.left_stick_y;}
    public float getRightX(){return gamepad.right_stick_x;}
    public float getRightY(){return gamepad.right_stick_y;}

}
