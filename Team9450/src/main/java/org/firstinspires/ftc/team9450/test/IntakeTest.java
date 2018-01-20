package org.firstinspires.ftc.team9450.test;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

/**
 * Created by prave on 1/20/2018.
 */

@TeleOp
public class IntakeTest extends OpMode{
    private DcMotor intakeRight;
    private DcMotor intakeLeft;


    @Override
    public void init() {
        intakeLeft = hardwareMap.dcMotor.get("left");
        intakeLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        intakeLeft.setDirection(DcMotorSimple.Direction.REVERSE);

        intakeRight = hardwareMap.dcMotor.get("right");
        intakeRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        intakeRight.setDirection(DcMotorSimple.Direction.FORWARD);
    }

    @Override
    public void loop() {
        if(gamepad1.y){
            intakeLeft.setPower(1);
            intakeRight.setPower(1);
        }
        else {
            intakeLeft.setPower(0);
            intakeRight.setPower(0);
        }
        telemetry.addData("intakeRight", intakeRight.getPower());
        telemetry.addData("intakeLeft", intakeLeft.getPower());
        }
}
