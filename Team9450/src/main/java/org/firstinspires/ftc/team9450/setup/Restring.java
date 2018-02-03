package org.firstinspires.ftc.team9450.setup;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.team9450.util.Constants;

/**
 * Created by dhruv on 1/22/18.
 */

@TeleOp
public class Restring extends OpMode {
    DcMotor arm;
    DcMotor lifter;
    Servo rudder;
    Servo lateral;
    @Override
    public void init() {
        //arm = hardwareMap.dcMotor.get("arm");
        lifter = hardwareMap.dcMotor.get("lift");
        //rudder = hardwareMap.servo.get(Constants.Rudder.RUDDERTOP);
        //lateral = hardwareMap.servo.get(Constants.Rudder.RUDDERBOTTOM);
    }

    @Override
    public void loop() {
        //arm.setPower(gamepad1.right_trigger - gamepad1.left_trigger);

        lifter.setPower(gamepad1.right_bumper? 0.5: gamepad1.left_bumper? -0.5:0);

        //if (gamepad1.a) rudder.setPosition(Constants.Rudder.RUDDER_IN);
        //else if (gamepad1.b) rudder.setPosition(Constants.Rudder.RUDDER_OUT);
        //lateral.setPosition(gamepad1.right_stick_x*0.1+lateral.getPosition());
        //telemetry.addData("lateral", lateral.getPosition());
    }
}
