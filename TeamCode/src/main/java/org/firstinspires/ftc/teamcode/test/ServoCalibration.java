package org.firstinspires.ftc.teamcode.test;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * Created by Grace on 12/26/2017.
 */
@TeleOp
public class ServoCalibration extends OpMode{
    Servo servo;
    public void init() {
        servo=hardwareMap.servo.get("sensor_color_distance");
    }

    @Override
    public void loop() {
        if(gamepad1.y){servo.setPosition(servo.getPosition()+0.01);}
        if(gamepad1.a){servo.setPosition(servo.getPosition()-0.01);}
        if(gamepad1.x){servo.setDirection(Servo.Direction.REVERSE);}
        if(gamepad1.b){servo.setDirection(Servo.Direction.FORWARD);}
        telemetry.addData("position",servo.getPosition());
        telemetry.addData("direction",servo.getDirection());
    }
}
