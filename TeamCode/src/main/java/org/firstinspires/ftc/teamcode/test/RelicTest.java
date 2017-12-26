package org.firstinspires.ftc.teamcode.test;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * Created by dhruv on 12/20/17.
 */

@TeleOp
public class RelicTest extends OpMode {
    private DcMotor arm;
    private CRServo pivot;
    private Servo hand;

    @Override
    public void init() {
        arm = hardwareMap.dcMotor.get("arm");
        pivot = hardwareMap.crservo.get("pivot");
        hand = hardwareMap.servo.get("hand");

        arm.setDirection(DcMotorSimple.Direction.FORWARD);
        pivot.setDirection(CRServo.Direction.FORWARD);
        hand.setDirection(Servo.Direction.FORWARD);
    }

    @Override
    public void loop() {
        if(gamepad1.y){
            pivot.setPower(1);
        }else if(gamepad1.a){
            pivot.setPower(-1);
        }else{pivot.setPower(0);}
        if(gamepad1.x) {
            hand.setPosition(0);
        }else if(gamepad1.b){
            hand.setPosition(0.75);
        }

        if (gamepad1.dpad_up) {
            arm.setPower(1);
        } else if (gamepad1.dpad_down) {
            arm.setPower(-1);
        } else {
            arm.setPower(0);
        }
    }
}
