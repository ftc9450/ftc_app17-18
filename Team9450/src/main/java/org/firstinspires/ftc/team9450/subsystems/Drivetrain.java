package org.firstinspires.ftc.team9450.subsystems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import org.firstinspires.ftc.team9450.sensors.Gyroscope;
import org.firstinspires.ftc.team9450.util.Constants;
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
    private double maxPower;
    DriveSignal driveSignal;
    public Drivetrain(DcMotor lf, DcMotor lb, DcMotor rf, DcMotor rb) {
        leftFront = lf;
        leftBack = lb;
        rightFront = rf;
        rightBack = rb;
        maxPower=Constants.Drivetrain.HIGH_POWER;
        leftFront.setDirection(DcMotorSimple.Direction.REVERSE);
        leftBack.setDirection(DcMotorSimple.Direction.REVERSE);
        rightFront.setDirection(DcMotorSimple.Direction.FORWARD);
        rightBack.setDirection(DcMotorSimple.Direction.REVERSE);
        rightBack.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    }

    public void setPower(double power) {
        leftFront.setPower(power);
        leftBack.setPower(power);
        rightFront.setPower(power);
        rightBack.setPower(power);
    }
    public void setPower(double driveSignal[]) {
        leftFront.setPower(driveSignal[0]);
        leftBack.setPower(driveSignal[1]);
        rightFront.setPower(driveSignal[2]);
        rightBack.setPower(driveSignal[3]);
    }
    public void setFWPosition(double pos) {
        leftFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }
    public void setOpenLoop(DriveSignal d){
        driveSignal=d;
    }
    public void setPower(DriveSignal signal) {
        //spaces to make dhruv happy
        double scale=maxPower;
        double diff=Math.abs(rightFront.getPower() - (signal.rightBackMotor * maxPower));
        if(diff > 1){
            scale *= 1 - (diff / 4.0);
        }
        rightFront.setPower(signal.rightFrontMotor * scale);
        rightBack.setPower(signal.rightBackMotor * scale);
        leftFront.setPower(signal.leftFrontMotor * scale);
        leftBack.setPower(signal.leftBackMotor * scale);

    }
    public boolean isClose(DcMotor dcMotor){return Math.abs(dcMotor.getCurrentPosition()-dcMotor.getTargetPosition())<10;}
    public boolean isBusy(){return !(isClose(leftFront) || isClose(leftBack) || isClose(rightFront) || isClose(rightBack));}
    public void enableAndResetEncoders(){
        leftFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftBack.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        leftBack.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        //rightBack.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
//        leftFront.setMode(DcMotor.RunMode.RUN_TO_POSITION);
//        leftBack.setMode(DcMotor.RunMode.RUN_TO_POSITION);
//        rightFront.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        //rightBack.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }
    public void disconnectEncoders(){
        leftFront.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        rightFront.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        leftBack.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        rightBack.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    }
    public void moveFB(double distance, double power){ //positive power and distance is move forward
        enableAndResetEncoders();
        int sig = (int) distance * Constants.Drivetrain.INCH;
        //leftFront.setTargetPosition(sig);
        //leftBack.setTargetPosition(sig);
        //rightFront.setTargetPosition(sig);
        //rightBack.setTargetPosition(sig);
        setPower(new DriveSignal(power*0.8, power*0.8, power, power));
        try{while(Math.abs(leftFront.getCurrentPosition()+rightFront.getCurrentPosition())/2 < Math.abs(sig));}catch (Exception e){}
        setPower(new DriveSignal(0,0,0,0));
    }
    public String toString(){
        return String.valueOf((leftFront.getCurrentPosition()+rightFront.getCurrentPosition())/2 );
    }
    public void pivotTo(double pos, Gyroscope imu){
        disconnectEncoders();
        while(imu.getAngle()!=pos){
            if(imu.getAngle() > pos && Math.abs(imu.getAngle()-pos) > 0.1){
                setPower(new double[]{0.3,0.3,-0.3,-0.3});
            }else if(imu.getAngle() < pos && Math.abs(imu.getAngle()-pos) > 0.1){
                setPower(new double[]{-0.3,-0.3,0.3,0.3});
            }else{
                setPower(new double[]{0,0,0,0});
            }
        }
    }
    public void pivot(int distance, double power){
        enableAndResetEncoders();
        distance*=Constants.Drivetrain.DEGREE;
        leftFront.setTargetPosition(distance);
        leftBack.setTargetPosition(distance);
        rightFront.setTargetPosition(distance*-1);
        rightBack.setTargetPosition(distance*-1);
        setPower(DriveSignal.pivot(Constants.doubleToFloat(power)));
        while(isBusy());
    }

    public void moveLR(int distance, double power){//positive power and distance is move to right
        enableAndResetEncoders();
        distance*=Constants.Drivetrain.STRAFEINCH;
        leftFront.setTargetPosition(distance);
        leftBack.setTargetPosition(-1*distance);
        rightFront.setTargetPosition(-1*distance);
        rightBack.setTargetPosition(distance);
        setPower(DriveSignal.lateralMove(Constants.doubleToFloat(power)));
        while(isBusy());
    }
    @Override
    public void stop() {
        leftFront.setPower(0);
        leftBack.setPower(0);
        rightFront.setPower(0);
        rightBack.setPower(0);
    }
    public int getPosition() {
        return (leftFront.getCurrentPosition() + rightFront.getCurrentPosition())/2;
    }

    @Override
    public void loop() {
        setPower(driveSignal);
    }
}
