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
    private CRServo crservo;
    private Servo servo;

    @Override
    public void init() {
        motor = hardwareMap.dcMotor.get("arm");
        motor.setDirection(DcMotorSimple.Direction.FORWARD);
        motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        servo = hardwareMap.servo.get("pivot");

        crservo = hardwareMap.crservo.get("hand");
        servo.setDirection(Servo.Direction.FORWARD);
        crservo.setDirection(DcMotorSimple.Direction.FORWARD);
    }

    @Override
    public void loop() {

        if (gamepad1.x) servo.setPosition(servo.getPosition()+0.1);
        else if (gamepad1.y) servo.setPosition(servo.getPosition()-0.1);

        if (gamepad1.a) crservo.setPower(1);
        else if (gamepad1.b) crservo.setPower(-1);
        else crservo.setPower(0);

        telemetry.addData("hand", crservo.getPower());
        telemetry.update();
    }
}
