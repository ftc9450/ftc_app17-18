package org.firstinspires.ftc.teamcode.control;

import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.teamcode.util.DriveSignal;

/**
 * Created by O on 10/28/2017.
 */

public class ControlBoard {
    private Gamepad driverController;

    public ControlBoard(Gamepad controller) {
        driverController = controller;
    }
    public DriveSignal pivot(){
        double power=Math.sqrt(Math.pow(driverController.left_stick_x,2)+Math.pow(driverController.left_stick_y,2));
        if(driverController.left_stick_y>0){return DriveSignal.pivot(Math.sqrt(power));}
        return DriveSignal.pivot(power);//Not done. This method is utter garbage
    }
    public boolean reduceSpeed() {
        return driverController.left_bumper;
    }

    public float throttle() {
        return driverController.left_stick_y;
    }

    public float turn() {
        return driverController.right_stick_x;
    }

}
