package org.firstinspires.ftc.team9450.test;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.team9450.util.Constants;

/**
 * Created by Grace on 1/25/2018.
 */
@TeleOp
public class RudderCalibration extends OpMode{
    Servo out;
    Servo lateral;
    @Override
    public void init() {
        out = hardwareMap.servo.get("rudder_out");
        out.setDirection(Servo.Direction.FORWARD);

        lateral = hardwareMap.servo.get("rudder_lat");
        lateral.setDirection(Servo.Direction.FORWARD);
    }

    @Override
    public void loop() {
        if (gamepad1.a) out.setPosition(out.getPosition()+0.001);
        else if (gamepad1.b) out.setPosition(out.getPosition()-0.001);

        if (gamepad1.x) lateral.setPosition(lateral.getPosition()+0.001);
        else if (gamepad1.y) lateral.setPosition(lateral.getPosition()-0.001);

        telemetry.addData("out", out.getPosition());
        telemetry.addData("lateral", lateral.getPosition());
    }
}
