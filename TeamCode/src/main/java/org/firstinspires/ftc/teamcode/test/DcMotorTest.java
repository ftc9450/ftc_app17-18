package org.firstinspires.ftc.teamcode.test;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.control.ControlBoard;

/**
 * @author Grace
 */
@TeleOp
public class DcMotorTest extends OpMode {
    DcMotor testMe = null;
    @Override
    public void init() {
        testMe=hardwareMap.dcMotor.get("name");
    }

    @Override
    public void loop() {
        testMe.setPower(-1.0*gamepad1.left_stick_y);
    }
}
