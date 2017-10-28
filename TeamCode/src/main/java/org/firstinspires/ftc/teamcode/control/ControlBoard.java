package org.firstinspires.ftc.teamcode.control;

import com.qualcomm.robotcore.hardware.Gamepad;

/**
 * Created by O on 10/28/2017.
 */

public class ControlBoard {
    private Gamepad driverController;

    public ControlBoard(Gamepad controller) {
        driverController = controller;
    }

    public boolean moveSliderLeft() {
        return driverController.dpad_left;
    }

    public boolean moveSliderRight() {
        return driverController.dpad_right;
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

    public boolean toggleShooterOn() {
        return driverController.x;
    }

    public boolean toggleShooterOff() {
        return driverController.b;
    }
}
