package org.firstinspires.ftc.team9450.test;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * Created by dhruv on 1/20/18.
 */
@TeleOp
@Deprecated
@Disabled
public class RampTest extends OpMode{
    Servo servo;
    DcMotor lift;

    @Override
    public void init() {
        servo = hardwareMap.servo.get("ramp");
        servo.setDirection(Servo.Direction.FORWARD);

        lift = hardwareMap.dcMotor.get("ramp_lifter");
        lift.setDirection(DcMotorSimple.Direction.FORWARD);

    }

    @Override
    public void loop() {
        if(gamepad1.a){
            servo.setPosition(1);
        }
        else if(gamepad1.b){
            servo.setPosition(0);
        }
        if(gamepad1.x){
            lift.setPower(1.00);
        }
        else if(gamepad1.y){
            lift.setPower(-1.00);
        }
        else{
            lift.setPower(0.00);
        }

    }
}
