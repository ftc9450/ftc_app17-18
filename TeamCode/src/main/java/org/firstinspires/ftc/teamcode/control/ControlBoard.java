package org.firstinspires.ftc.teamcode.control;

import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.teamcode.util.*;

/**
 * Created by O on 10/28/2017.
 */

public class ControlBoard {
    private Gamepad driverController;

    public ControlBoard(Gamepad controller) {
        driverController = controller;
    }

    /**
     * Use with mecanum wheels to move in multiple directions
     * @return DriveSignal to power translational movement corresponding to the position of the joystick
     */
    public DriveSignal translate(){
        double angle=Math.atan2(driverController.left_stick_y,driverController.left_stick_x)-Math.PI/4;
        double throttle=throttle();
        return new DriveSignal(Math.sin(angle)*throttle, Math.cos(angle)*throttle, Math.cos(angle)*throttle, Math.sin(angle)*throttle);
    }

    public boolean reduceSpeed() {
        return driverController.left_bumper;
    }

    /**
     * Use for scaling movements
     * @return the distance moved by the left joystick as a double from 0-1
     */
    public double throttle() {return Math.pow(Math.pow(driverController.left_stick_y,2)+Math.pow(driverController.left_stick_x,2),0.5);}

//    public float turn() {
//        return driverController.right_stick_x;
//    }
    public DriveSignal turn(){return DriveSignal.pivot(driverController.right_stick_x);}

}
