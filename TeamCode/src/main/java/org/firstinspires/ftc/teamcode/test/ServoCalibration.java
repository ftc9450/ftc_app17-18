package org.firstinspires.ftc.teamcode.test;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.util.Constants;

/**
 * Created by Grace on 12/26/2017.
 */
@TeleOp
public class ServoCalibration extends OpMode{
    Servo servo;
    public void init() {
        servo=hardwareMap.servo.get(Constants.Grabber.RT);
    }

    @Override
    public void loop() {
        double pos=servo.getPosition();
        if(gamepad1.y){servo.setPosition(pos+0.001);}
        if(gamepad1.a){servo.setPosition(pos-0.001);}
        if(gamepad1.x){servo.setDirection(Servo.Direction.REVERSE);}
        if(gamepad1.b){servo.setDirection(Servo.Direction.FORWARD);}
        telemetry.addData("position",pos);
        telemetry.addData("direction",servo.getDirection());
    }
}
