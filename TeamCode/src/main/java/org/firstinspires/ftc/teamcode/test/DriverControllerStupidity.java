package org.firstinspires.ftc.teamcode.test;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.control.ControlBoard;

/**
 * Created by Grace on 11/22/2017.
 */
@Autonomous
public class DriverControllerStupidity extends OpMode {
    ControlBoard c=new ControlBoard(gamepad1);
    public void init() {

    }

    @Override
    public void loop() {
        telemetry.addData("gamepad", c.kms());
        c.elevatorCommand();
    }
}
