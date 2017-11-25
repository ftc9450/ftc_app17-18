package org.firstinspires.ftc.teamcode.control;

import com.qualcomm.robotcore.hardware.Gamepad;

/**
 * Created by dhruv on 11/25/17.
 */

public class GameBoard {
    private Gamepad gamepad;

    public GameBoard(Gamepad gamepad) {
        this.gamepad = gamepad;
    }

    public float getX() {
        return gamepad.left_stick_x;
    }

}
