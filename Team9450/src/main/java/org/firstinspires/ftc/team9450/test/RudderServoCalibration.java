package org.firstinspires.ftc.team9450.test;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.team9450.util.Constants;

/**
 * Created by Grace on 1/25/2018.
 */
@TeleOp
public class RudderServoCalibration extends OpMode{
    Servo servo;
    @Override
    public void init() {
        servo=hardwareMap.servo.get(Constants.Rudder.RUDDERTOP);
        servo.setDirection(Servo.Direction.FORWARD);
    }

    @Override
    public void loop() {
        if(gamepad1.a){
            servo.setPosition(servo.getPosition()+0.01);
        }
        if(gamepad1.b){
            servo.setPosition(servo.getPosition()-0.01);
        }
        telemetry.addData("pos", servo.getPosition());
    }
}
