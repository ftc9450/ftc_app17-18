package org.firstinspires.ftc.team9450.test;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.team9450.util.Constants;

/**
 * Created by dhruv on 1/22/18.
 */

@TeleOp
public class RelicArmTest extends OpMode {
    private DcMotor motor;
    private Servo pivot;
    private Servo hand;

    @Override
    public void init() {
        motor = hardwareMap.dcMotor.get("arm");
        motor.setDirection(DcMotorSimple.Direction.FORWARD);
        motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        pivot = hardwareMap.servo.get("pivot");

        hand = hardwareMap.servo.get("hand");
        hand.setDirection(Servo.Direction.FORWARD);
        pivot.setDirection(Servo.Direction.FORWARD);
    }

    @Override
    public void loop() {
        motor.setPower(gamepad1.left_stick_y);

        if (gamepad1.x) pivot.setPosition(pivot.getPosition()+0.01);
        else if (gamepad1.y) pivot.setPosition(pivot.getPosition()-0.01);

        if (gamepad1.a) hand.setPosition(hand.getPosition()+0.1);
        else if (gamepad1.b) hand.setPosition(hand.getPosition()-0.1);

        telemetry.addData("arm", motor.getCurrentPosition());
        telemetry.addData("pivot", pivot.getPosition());
        telemetry.addData("hand", hand.getPosition());
    }
}
