package org.firstinspires.ftc.team9450.test;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;

import org.firstinspires.ftc.team9450.subsystems.Rudder;
import org.firstinspires.ftc.team9450.util.Constants;

/**
 * Created by Grace on 1/25/2018.
 */
@TeleOp
public class RudderTest extends OpMode{
    Rudder rudder;
    CRServo bottomServo;
    @Override
    public void init() {
        rudder = new Rudder(hardwareMap.servo.get(Constants.Rudder.RUDDERTOP), hardwareMap.servo.get(Constants.Rudder.RUDDERBOTTOM),hardwareMap.colorSensor.get(Constants.Rudder.COLOR));
        bottomServo=hardwareMap.crservo.get(Constants.Rudder.RUDDERBOTTOM);
    }

    @Override
    public void loop() {
        try{
        if(gamepad1.a){
            //rudder.knockRed();
        }
        if(gamepad1.b){
            //rudder.knockBlue();
        }
        if(gamepad1.x){
            int color=rudder.getColor();
            if(color==Constants.Color.RED){
                bottomServo.setPower(1);
                Thread.sleep(500);
                bottomServo.setPower(0);
                Thread.sleep(500);
                bottomServo.setPower(-1);
                Thread.sleep(500);
            }else if(color==Constants.Color.BLUE){
                bottomServo.setPower(-1);
                Thread.sleep(500);
                bottomServo.setPower(0);
                Thread.sleep(500);
                bottomServo.setPower(1);
                Thread.sleep(500);
            }
        }
        }catch(Exception e){}
        telemetry.addData("color", rudder.getColor());
    }
}
