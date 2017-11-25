package org.firstinspires.ftc.teamcode.test;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.control.ControlBoard;
import org.firstinspires.ftc.teamcode.control.GameBoard;

/**
 * Created by Grace on 11/22/2017.
 */
@TeleOp
public class DriverControllerStupidity extends OpMode {
    GameBoard gb = new GameBoard(gamepad1);
    public void init() {

    }

    @Override
    public void loop() {
        telemetry.addData("gamepad", gb.getX());
    }
}
