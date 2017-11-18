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
    public DriveSignal translate(){
        double angle=Math.atan2(driverController.left_stick_y,driverController.left_stick_x)-Math.PI/4;
        return new DriveSignal(Math.sin(angle)*throttle(), Math.cos(angle)*throttle(), Math.cos(angle)*throttle(), Math.sin(angle)*throttle());
    }
    public boolean reduceSpeed() {
        return driverController.left_bumper;
    }

    public float throttle() {return Math.abs(driverController.left_stick_y);}

//    public float turn() {
//        return driverController.right_stick_x;
//    }
    public DriveSignal turn(){return DriveSignal.pivot(driverController.right_stick_x);}

}
