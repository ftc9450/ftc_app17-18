package org.firstinspires.ftc.team9450.test;

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

        crservo = hardwareMap.crservo.get("pivot");
        crservo.setDirection(DcMotorSimple.Direction.FORWARD);

        servo = hardwareMap.servo.get("hand");
        servo.setDirection(Servo.Direction.FORWARD);
    }

    @Override
    public void loop() {
        if (gamepad1.dpad_up) motor.setPower(0.5);
        else if (gamepad1.dpad_down) motor.setPower(-0.5);
        else motor.setPower(0);

        if (gamepad1.a) servo.setPosition(1);
        else if (gamepad1.b) servo.setPosition(0);

        if (gamepad1.x) crservo.setPower(0.5);
        else if (gamepad1.y) crservo.setPower(-0.5);
        else crservo.setPower(0);

        telemetry.addData("arm", motor.getCurrentPosition());
        telemetry.addData("hand", servo.getPosition());
        telemetry.update();
    }
}
