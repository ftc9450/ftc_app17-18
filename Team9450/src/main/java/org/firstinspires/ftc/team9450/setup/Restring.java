package org.firstinspires.ftc.team9450.setup;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.team9450.subsystems.Ramp;
import org.firstinspires.ftc.team9450.util.Constants;

/**
 * Created by dhruv on 1/22/18.
 */

@TeleOp
public class Restring extends OpMode {
    DcMotor arm;
    Servo ramp;
    DcMotor lift;
    @Override
    public void init() {
        arm = hardwareMap.dcMotor.get("arm");
        ramp = hardwareMap.servo.get("ramp");
        lift = hardwareMap.dcMotor.get("lift");
    }

    @Override
    public void loop() {
        ramp.setPosition(Constants.Ramp.IN);
        if (gamepad1.dpad_up) lift.setPower(0.5);
        else if (gamepad1.dpad_down) lift.setPower(-0.5);
        else lift.setPower(0);
        arm.setPower(gamepad1.right_trigger - gamepad1.left_trigger);
    }
}
