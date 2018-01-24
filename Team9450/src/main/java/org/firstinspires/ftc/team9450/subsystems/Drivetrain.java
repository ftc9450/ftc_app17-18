package org.firstinspires.ftc.team9450.subsystems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import org.firstinspires.ftc.team9450.util.DriveSignal;

import java.util.HashMap;

/**
 * Created by dhruv on 1/20/18.
 */

public class Drivetrain extends Subsystem {
    private DcMotor leftFront;
    private DcMotor leftBack;
    private DcMotor rightFront;
    private DcMotor rightBack;
    DriveSignal driveSignal;
    public Drivetrain(DcMotor lf, DcMotor lb, DcMotor rf, DcMotor rb) {
        leftFront = lf;
        leftBack = lb;
        rightFront = rf;
        rightBack = rb;

        leftFront.setDirection(DcMotorSimple.Direction.REVERSE);
        leftBack.setDirection(DcMotorSimple.Direction.REVERSE);
        rightFront.setDirection(DcMotorSimple.Direction.FORWARD);
        rightBack.setDirection(DcMotorSimple.Direction.FORWARD);
    }

    public void setPower(double driveSignal[]) {
        leftFront.setPower(driveSignal[0]);
        leftBack.setPower(driveSignal[1]);
        rightFront.setPower(driveSignal[2]);
        rightBack.setPower(driveSignal[3]);
    }
    public void setPower(DriveSignal driveSignal){
        leftFront.setPower(driveSignal.leftFrontMotor);
        leftBack.setPower(driveSignal.leftBackMotor);
        rightFront.setPower(driveSignal.rightFrontMotor);
        rightBack.setPower(driveSignal.rightBackMotor);
    }
    public void setFWPosition(double pos) {
        leftFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }
    public void setOpenLoop(DriveSignal d){
        driveSignal=d;
    }
    @Override
    public void stop() {
        leftFront.setPower(0);
        leftBack.setPower(0);
        rightFront.setPower(0);
        rightBack.setPower(0);
    }

    @Override
    public void loop() {
        setPower(driveSignal);
    }
}
