package org.firstinspires.ftc.team9450.test;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import org.firstinspires.ftc.team9450.util.Constants;

/**
 * Created by dhruv on 1/22/18.
 */

@TeleOp
public class RelicArmTest extends OpMode {
    public DcMotor motor;

    @Override
    public void init() {
        motor = hardwareMap.dcMotor.get("arm");
        motor.setDirection(DcMotorSimple.Direction.FORWARD);
        motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    @Override
    public void loop() {
        if (gamepad1.dpad_up) motor.setPower(0.5);
        else if (gamepad1.dpad_down) motor.setPower(-0.5);
        else motor.setPower(0);

        telemetry.addData("pos", motor.getCurrentPosition());
        telemetry.update();
    }
}
