package org.firstinspires.ftc.team9450.test;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.team9450.subsystems.Rudder;
import org.firstinspires.ftc.team9450.util.Constants;

/**
 * Created by Grace on 1/25/2018.
 */
@TeleOp
public class RudderTest extends OpMode{
    Rudder rudder;
    Servo bottomServo;
    @Override
    public void init() {
        rudder = new Rudder(hardwareMap.servo.get(Constants.Rudder.RUDDERTOP), hardwareMap.servo.get(Constants.Rudder.RUDDERBOTTOM),hardwareMap.colorSensor.get(Constants.Rudder.COLOR));
        bottomServo=hardwareMap.servo.get(Constants.Rudder.RUDDERBOTTOM);
    }

    @Override
    public void loop() {
        if(gamepad1.a){
            rudder.setLateralState(Rudder.LateralState.BACKWARDS);
        }else if(gamepad1.b){
            rudder.setLateralState(Rudder.LateralState.FORWARDS);
        }else if(gamepad1.x){
            rudder.setLateralState(Rudder.LateralState.NEUTRAL);
        }else if(gamepad1.y){
            rudder.setRudderState(Rudder.RudderState.IN);
        }else if(gamepad1.left_bumper){
            rudder.setRudderState(Rudder.RudderState.OUT);
        }else if(gamepad1.right_bumper){
            rudder.setRudderState(Rudder.RudderState.START);
        }else{
            rudder.setRudderState(Rudder.RudderState.START);
            rudder.setLateralState(Rudder.LateralState.NEUTRAL);
        }
        rudder.loop();
        try{Thread.sleep(500);}catch(Exception e){}
        telemetry.addData("color", rudder.getColor());
    }
}
