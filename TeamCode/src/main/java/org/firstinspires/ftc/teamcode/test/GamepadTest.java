package org.firstinspires.ftc.teamcode.test;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.control.GameBoard;

/**
 * Created by dhruv on 11/27/17.
 */

public class GamepadTest extends OpMode {
    private GameBoard gameBoard;

    @Override
    public void init() {
        gameBoard = new GameBoard(gamepad1);
    }

    @Override
    public void loop() {
        if (gamepad1.a) testPad();
        else if (gamepad1.b) testBoard();
    }

    public void testPad() {
        telemetry.addData("left stick x", gamepad1.left_stick_x);
    }

    public void testBoard() {
        telemetry.addData("left stick x", gameBoard.getLeftX());
    }
}
