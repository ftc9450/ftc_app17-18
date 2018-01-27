package org.firstinspires.ftc.team9450.test;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * Created by dhruv on 1/20/18.
 */
@TeleOp
@Disabled
@Deprecated
public class RampTest extends OpMode{
    Servo servo;

    @Override
    public void init() {
        servo = hardwareMap.servo.get("ramp");
        servo.setDirection(Servo.Direction.FORWARD);
    }

    @Override
    public void loop() {
        if(gamepad1.a){
            servo.setPosition(1);
        }
        else if(gamepad1.b){
            servo.setPosition(0);
        }

    }
}
