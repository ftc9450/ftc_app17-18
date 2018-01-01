package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

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
        this.leftBack = lb;
        this.rightFront = rf;
        this.rightBack = rb;
        this.leftFront.setDirection(DcMotorSimple.Direction.REVERSE);
        this.leftBack.setDirection(DcMotorSimple.Direction.REVERSE);
        this.rightFront.setDirection(DcMotorSimple.Direction.FORWARD);
        this.rightBack.setDirection(DcMotorSimple.Direction.FORWARD);
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
        setPower(signal);
    }
    public boolean isBusy(){
        return !(isClose(leftFront) && isClose(leftBack) && isClose(rightFront) && isClose(rightBack));
    }
    public boolean isClose(DcMotor dcMotor){
        return dcMotor.getCurrentPosition()>=dcMotor.getTargetPosition()-10&&dcMotor.getCurrentPosition()<=dcMotor.getTargetPosition()+10;
    }
    public String toString(){
        return leftFront.getPower()+" "+leftBack.getPower()+" "+rightFront.getPower()+" "+rightBack.getPower();
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
        leftFront.setPower(signal.leftFrontMotor * maxPower);
        leftBack.setPower(signal.leftBackMotor * maxPower);
    }

    public void enableAndResetEncoders(){
        leftFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftBack.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightBack.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftFront.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        leftBack.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightFront.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightBack.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }
    public void disconnectEncoders(){
        leftFront.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        rightFront.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        leftBack.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        rightBack.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    }
    public void moveFB(int distance, double power){ //positive power and distance is move forward
        enableAndResetEncoders();
        leftFront.setTargetPosition(distance);
        leftBack.setTargetPosition(distance);
        rightFront.setTargetPosition(distance);
        rightBack.setTargetPosition(distance);
        setPower(new DriveSignal(power, power, power,power));
        try{while(isBusy());}catch (Exception e){}
    }

    public void pivot(int distance, double power){
        enableAndResetEncoders();
        leftFront.setTargetPosition(distance);
        leftBack.setTargetPosition(distance);
        rightFront.setTargetPosition(distance*-1);
        rightBack.setTargetPosition(distance*-1);
        setPower(DriveSignal.pivot(Constants.doubleToFloat(power)));
        while(isBusy());
    }

    public void moveLR(int distance, double power){//positive power and distance is move to right
        enableAndResetEncoders();
        leftFront.setTargetPosition(distance);
        leftBack.setTargetPosition(-1*distance);
        rightFront.setTargetPosition(-1*distance);
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
