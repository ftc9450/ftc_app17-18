package org.firstinspires.ftc.teamcode.util;

import static android.R.attr.left;
import static android.R.attr.right;

/**
 * Created by O on 10/28/2017.
 * A drivetrain command consisting of left, right motor settings and whether brake mode is on
 */

public class DriveSignal {
    public double leftFrontMotor;
    public double rightFrontMotor;
    public double leftBackMotor;
    public double rightBackMotor;
    public boolean breakMode;

    public DriveSignal(double lf, double lb, double rf, double rb) {
        this(lf,lb,rf,rb, false);
    }

    public DriveSignal(double lf, double lb,double rf,double rb, boolean breakMode) {
        this.leftFrontMotor = lf;
        this.leftBackMotor=lb;
        this.rightFrontMotor = rf;
        this.rightBackMotor=rb;
        this.breakMode = breakMode;
    }
    public static DriveSignal translate(double angle){
        return new DriveSignal(Math.cos(angle), Math.sin(angle), Math.sin(angle), Math.cos(angle));
    }
    public static DriveSignal pivot(float pow){
        // positive power is pivot to right
        double power=Constants.floatToDouble(pow);
        return new DriveSignal(power, power, -power, -power);
    }

    public static DriveSignal lateralMove(float pow){ // positive power is move to right
        double power=Constants.floatToDouble(pow);
        return new DriveSignal(power, -power, -power, power);
    }
    public static DriveSignal average(DriveSignal a, DriveSignal b){
        return new DriveSignal((a.leftFrontMotor+b.leftFrontMotor)/2.0, (a.leftBackMotor+b.leftBackMotor)/2.0, (a.rightFrontMotor+b.rightFrontMotor)/2.0, (a.rightBackMotor+b.rightBackMotor)/2.0);
    }
    public static DriveSignal NEUTRAL = new DriveSignal(0, 0, 0, 0);
    public static DriveSignal BRAKE = new DriveSignal(0, 0, 0, 0, true);

    @Override
    public String toString() {
        return "LF: " + leftFrontMotor + ",LB: " +leftBackMotor+", RF: " + rightFrontMotor+", RB: "+rightBackMotor;
    }
    public boolean isZero(){
        return leftFrontMotor==0&&leftBackMotor==0&&rightFrontMotor==0&&rightBackMotor==0;
    }
    public DriveSignal scale(double power){
        this.leftBackMotor *= power;
        this.leftFrontMotor *= power;
        this.rightBackMotor *= power;
        this.rightFrontMotor *= power;
        return this;
    }

}
