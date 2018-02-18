package org.firstinspires.ftc.team9450.test;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.team9450.util.Constants;

/**
 * Created by Grace on 1/20/2018.
 */
@TeleOp
public class RampCalibration extends OpMode{
    Servo ramp;
    DcMotor lift;
    DigitalChannel touch;
    public void init() {
        ramp=hardwareMap.servo.get(Constants.Ramp.RAMP);
        lift = hardwareMap.dcMotor.get(Constants.Ramp.LIFT);
        touch = hardwareMap.digitalChannel.get("touch");
        //lift.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        //lift.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    @Override
    public void loop() {
        double a=ramp.getPosition();
        if(gamepad1.start){lift.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);lift.setMode(DcMotor.RunMode.RUN_USING_ENCODER);}
        if(gamepad1.b){ramp.setPosition(a+0.01);}
        else if(gamepad1.a && touch.getState()){ramp.setPosition(a-0.01);}
        if (gamepad1.x) {
            lift.setPower(0.5);
        }
        else if (gamepad1.y) {
            lift.setPower(-0.5);
        }
        else lift.setPower(0);
        if(gamepad1.left_bumper){ramp.setDirection(Servo.Direction.FORWARD);}
        if(gamepad1.right_bumper){ramp.setDirection(Servo.Direction.REVERSE);}
        if (gamepad1.left_trigger > 0.1) {
            lift.setDirection(DcMotorSimple.Direction.FORWARD);
        }
        else if (gamepad1.right_trigger > 0.1) {
            lift.setDirection(DcMotorSimple.Direction.REVERSE);
        }
        telemetry.addData("ramp direction", ramp.getDirection());
        telemetry.addData("ramp position",ramp.getPosition());
        telemetry.addData("lift direction", lift.getDirection());
        telemetry.addData("lift position", lift.getCurrentPosition());
        telemetry.addData("touch", touch.getState());
    }
}
