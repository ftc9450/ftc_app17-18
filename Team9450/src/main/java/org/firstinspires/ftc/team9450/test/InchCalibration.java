package org.firstinspires.ftc.team9450.test;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import org.firstinspires.ftc.team9450.util.Constants;

/**
 * Created by Grace on 1/17/2018.
 */
@TeleOp
public class InchCalibration extends OpMode{
    DcMotor lf;
    DcMotor lb;
    DcMotor rf;
    DcMotor rb;
    public void init() {
        lf=hardwareMap.dcMotor.get(Constants.Drivetrain.LF);
        lb=hardwareMap.dcMotor.get(Constants.Drivetrain.LB);
        rf=hardwareMap.dcMotor.get(Constants.Drivetrain.RF);
        rb=hardwareMap.dcMotor.get(Constants.Drivetrain.RB);
        lf.setDirection(DcMotorSimple.Direction.REVERSE);
        lb.setDirection(DcMotorSimple.Direction.REVERSE);
        rf.setDirection(DcMotorSimple.Direction.FORWARD);
        rb.setDirection(DcMotorSimple.Direction.REVERSE);
        lf.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        lb.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rf.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        //rb.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rb.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    }

    @Override
    public void loop() {
        if(gamepad1.y){
            lf.setPower(0.5);
            lb.setPower(0.5);
            rf.setPower(0.5);
            rb.setPower(0.5);
        }else if(gamepad1.x) {
            lf.setPower(-0.5);
            lb.setPower(-0.5);
            rf.setPower(-0.5);
            rb.setPower(-0.5);
        }else{
            lf.setPower(0);
            lb.setPower(0);
            rf.setPower(0);
            rb.setPower(0);
        }
        if(gamepad1.a){
            lf.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            lb.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            rf.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            //rb.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            lf.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            lb.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            rf.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            //rb.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        }
        telemetry.addData("left front: ",lf.getCurrentPosition());
        telemetry.addData("left back: ",lb.getCurrentPosition());
        telemetry.addData("right front: ",rf.getCurrentPosition());
        //telemetry.addData("right back: ", rb.getCurrentPosition());
    }
}
