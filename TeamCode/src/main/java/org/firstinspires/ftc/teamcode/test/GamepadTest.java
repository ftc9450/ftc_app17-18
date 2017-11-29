package org.firstinspires.ftc.teamcode.test;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.control.ControlBoard;
import org.firstinspires.ftc.teamcode.control.GameBoard;
import org.firstinspires.ftc.teamcode.util.DriveSignal;

/**
 * Created by dhruv on 11/27/17.
 */
@TeleOp
public class GamepadTest extends OpMode {
    private ControlBoard gameBoard;

    @Override
    public void init() {
        gameBoard = new ControlBoard(gamepad1);
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
        DriveSignal translate=gameBoard.translate();
        DriveSignal turn=gameBoard.turn();
        DriveSignal d;
        if(turn.isZero()){
            d=translate;
        }else if(translate.isZero()){
            d=turn;
        }else{
            d=DriveSignal.BRAKE;
            //d=DriveSignal.average(translate,turn);
        }
        telemetry.addData("left stick x", d);
    }
}
