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
public class RelicClawTest extends OpMode {
    Servo pollex;
    CRServo carpal;
    public void init() {
        pollex=hardwareMap.servo.get(Constants.RelicArm.THUMB);
        carpal=hardwareMap.crservo.get(Constants.RelicArm.WRIST);
    }

    @Override
    public void loop() {
        if(gamepad1.a){
            pollex.setPosition(pollex.getPosition()+0.01);
        }else if(gamepad1.b){
            pollex.setPosition(pollex.getPosition()-0.01);
        }else{
            pollex.setPosition(pollex.getPosition());
        }
        if(gamepad1.x){
            carpal.setPower(-1);
        }else if(gamepad1.y){
            carpal.setPower(1);
        }else{
            carpal.setPower(0);
        }
        if(gamepad1.left_bumper){
            carpal.setDirection(CRServo.Direction.REVERSE);
        }
        if(gamepad1.right_bumper){
            carpal.setDirection(CRServo.Direction.FORWARD);
        }
        if(gamepad1.right_trigger>0){
            pollex.setDirection(Servo.Direction.FORWARD);
        }
        if(gamepad1.left_trigger>0){
            pollex.setDirection(Servo.Direction.REVERSE);
        }
        telemetry.addData("pollex direction", pollex.getDirection());
        telemetry.addData("carpal direction", carpal.getDirection());
        telemetry.addData("pollex position", pollex.getPosition());
    }
}
