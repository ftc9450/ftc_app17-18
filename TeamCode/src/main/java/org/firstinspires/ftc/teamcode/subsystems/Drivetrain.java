package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.util.*;

/**
 * Created by O on 10/28/2017.
 */

public class Drivetrain extends Subsystem {
    private DcMotor leftFront;
    private DcMotor rightFront;
    private DcMotor leftBack;
    private DcMotor rightBack;

    private double maxPower;

    private DriveControlState controlState;

    public enum DriveControlState {
        OPEN_LOOP
    }

    public Drivetrain(DcMotor lf, DcMotor lb, DcMotor rf, DcMotor rb) {
        this.leftFront = lf;
        //this.leftFront.setDirection(DcMotor.Direction.REVERSE);

        this.leftBack = lb;
        //this.leftBack.setDirection(DcMotor.Direction.REVERSE);

        this.rightFront = rf;
        this.rightBack = rb;

        maxPower = Constants.Drivetrain.HIGH_POWER;
    }

    public void setMaxPower(float maxPower) {
        this.maxPower = maxPower;
        System.out.println("max power: " + maxPower);
    }

    public double getMaxPower() {
        return this.maxPower;
    }

    public void setOpenLoop(DriveSignal signal) {
        controlState = DriveControlState.OPEN_LOOP;
        rightFront.setPower(signal.rightFrontMotor * maxPower);
        rightBack.setPower(signal.rightBackMotor * maxPower);
        leftFront.setPower(-signal.leftFrontMotor * maxPower);
        leftBack.setPower(-signal.leftBackMotor * maxPower);
    }
    public boolean isBusy(){
        return leftFront.isBusy() || leftBack.isBusy() || rightFront.isBusy() || rightBack.isBusy();
    }

    @Override
    public synchronized void stop() {
        setOpenLoop(DriveSignal.NEUTRAL);
    }

    @Override
    public synchronized void zeroSensors() {

    }

    // Autonomous actions
    public void setPower(DriveSignal signal) {
        rightFront.setPower(signal.rightFrontMotor * maxPower);
        rightBack.setPower(signal.rightBackMotor * maxPower);
        leftFront.setPower(-signal.leftFrontMotor * maxPower);
        leftBack.setPower(-signal.leftBackMotor * maxPower);
    }

    public void resetMotors(){
        leftFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftBack.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightBack.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftFront.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        leftBack.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightFront.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightBack.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }

    public void moveFB(int distance, double power){ //positive power is move forward
        resetMotors();
        leftFront.setTargetPosition(distance);
        leftBack.setTargetPosition(distance);
        rightFront.setTargetPosition(distance);
        rightBack.setTargetPosition(distance);
        setPower(new DriveSignal(power, power, power,power));
        while(isBusy());
    }

    public void pivot(int distance, double power){ //positive power is move to right
        resetMotors();
        leftFront.setTargetPosition(distance);
        leftBack.setTargetPosition(distance);
        rightFront.setTargetPosition(-1*distance);
        rightBack.setTargetPosition(-1*distance);
        //setPower(DriveSignal.pivot(power));
        while(isBusy());
    }

    public void moveLR(int distance, double power){//positive power is move to right
        resetMotors();
        leftFront.setTargetPosition(distance);
        leftBack.setTargetPosition(-distance);
        rightFront.setTargetPosition(-distance);
        rightBack.setTargetPosition(distance);
        setPower(DriveSignal.lateralMove(Constants.doubleToFloat(power)));
        while(isBusy());
    }

    public void loop() {
        switch(controlState) {
            case OPEN_LOOP:
                break;
            default:
                setOpenLoop(DriveSignal.NEUTRAL);
                break;
        }
    }
}
