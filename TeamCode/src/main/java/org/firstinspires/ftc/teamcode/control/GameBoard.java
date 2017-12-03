package org.firstinspires.ftc.teamcode.control;

import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.teamcode.util.DriveSignal;

/**
 * Created by dhruv on 11/25/17.
 */
@Deprecated
public class GameBoard {
    private Gamepad gamepad;
    public GameBoard(Gamepad gamepad) {
        this.gamepad = gamepad;
    }
    public float getLeftX() {
        return gamepad.left_stick_x;
    }
    public float getLeftY(){return gamepad.left_stick_y;}
    public float getRightX(){return gamepad.right_stick_x;}
    public float getRightY(){return gamepad.right_stick_y;}

}
