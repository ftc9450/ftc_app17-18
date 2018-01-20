package org.firstinspires.ftc.team9450.test;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * Created by Grace on 1/20/2018.
 */
@TeleOp
public class RampCalibration extends OpMode{
    Servo ramp;
    public void init() {
        ramp=hardwareMap.servo.get("ramp");
    }

    @Override
    public void loop() {
        if(gamepad1.a){ramp.setPosition(ramp.getPosition()+0.01);}
        if(gamepad1.b){ramp.setPosition(ramp.getPosition()-0.01);}
        if(gamepad1.left_bumper){ramp.setDirection(Servo.Direction.FORWARD);}
        if(gamepad1.right_bumper){ramp.setDirection(Servo.Direction.REVERSE);}
        telemetry.addData("direction", ramp.getDirection());
        telemetry.addData("position",ramp.getPosition());
    }
}
