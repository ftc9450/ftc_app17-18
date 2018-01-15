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
    private CRServo pivot1;
    private CRServo pivot2;
    private Servo hand;

    @Override
    public void init() {
        arm = hardwareMap.dcMotor.get("relic");
        pivot1 = hardwareMap.crservo.get("pivot");
        //pivot2 = hardwareMap.crservo.get("r_pivot");

        hand = hardwareMap.servo.get("hand");

        arm.setDirection(DcMotorSimple.Direction.FORWARD);
        pivot1.setDirection(CRServo.Direction.FORWARD);
        //pivot2.setDirection(DcMotorSimple.Direction.REVERSE);
        hand.setDirection(Servo.Direction.FORWARD);
    }

    @Override
    public void loop() {
        if(gamepad1.y){
            pivot1.setPower(1);
            //pivot2.setPower(1);
        }else if(gamepad1.a){
            pivot1.setPower(-1);
            //pivot2.setPower(-1);
        }else{
            pivot1.setPower(0);
            //pivot2.setPower(0);
        }
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
