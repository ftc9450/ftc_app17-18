package org.firstinspires.ftc.teamcode.test;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.Servo;

import java.util.concurrent.TimeUnit;

/**
 * Created by Grace on 11/22/2017.
 */
@TeleOp
public class ServoTest extends OpMode {
    Servo testMe;
    @Override
    public void init() {
        testMe=hardwareMap.servo.get("rudder_servo");
        testMe.setDirection(Servo.Direction.FORWARD);
    }

    @Override
    public void loop() {
        if(gamepad1.x){testMe.setPosition(1.0);}
        if(gamepad1.y){testMe.setPosition(0);}
        telemetry.addData("port", testMe.getPortNumber());
        telemetry.addData("exist",testMe.toString());
        telemetry.addData("pos",testMe.getPosition());
    }
}
