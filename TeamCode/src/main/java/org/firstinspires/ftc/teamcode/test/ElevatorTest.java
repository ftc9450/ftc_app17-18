package org.firstinspires.ftc.teamcode.test;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

/**
 * Created by dhruv on 12/9/17.
 */

@TeleOp
public class ElevatorTest extends OpMode {
    private DcMotor elevator;

    @Override
    public void init() {
        elevator = hardwareMap.dcMotor.get("glyph");
    }

    @Override
    public void loop() {
        telemetry.addData("Left stick", gamepad1.left_stick_y);
        switch (Math.round(gamepad1.left_stick_y)) {
            case -1:
                elevator.setDirection(DcMotorSimple.Direction.FORWARD);
                elevator.setPower(1.0f);
                break;
            case 1:
                elevator.setDirection(DcMotorSimple.Direction.REVERSE);
                elevator.setPower(1.0f);
                break;
            default:
                break;
        }
    }
}
