package org.firstinspires.ftc.team9450.test;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

/**
 * @author Grace
 */
@TeleOp
@Disabled
public class DrivetrainTest extends OpMode {
    DcMotor leftFront = null;
    DcMotor leftBack=null;
    DcMotor rightFront=null;
    DcMotor rightBack=null;
    @Override
    public void init() {
        leftFront=hardwareMap.dcMotor.get("frontLeft");
        leftFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightFront=hardwareMap.dcMotor.get("frontRight");
        rightFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        leftBack=hardwareMap.dcMotor.get("backLeft");
        leftBack.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftBack.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightBack=hardwareMap.dcMotor.get("backRight");
        rightBack.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightBack.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    @Override
    public void loop() {
        if(gamepad1.y){
            leftFront.setPower(1);
        }else{leftFront.setPower(0);}
        if(gamepad1.b){
            rightFront.setPower(1);
        }else{rightFront.setPower(0);}
        if(gamepad1.x){
            leftBack.setPower(1);
        }else{leftBack.setPower(0);}
        if(gamepad1.a){
            rightBack.setPower(1);
        }else{rightBack.setPower(0);}
        telemetry.addData("lf encoder counts",leftFront.getCurrentPosition());
        telemetry.addData("rf encoder counts",rightFront.getCurrentPosition());
        telemetry.addData("lb encoder counts",leftBack.getCurrentPosition());
        telemetry.addData("rb encoder counts",rightBack.getCurrentPosition());
    }
}
