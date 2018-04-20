package org.firstinspires.ftc.team9450.util;

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
    public static DriveSignal buffer[] = new DriveSignal[20];
    public static int n = 0;

    public DriveSignal(){
        this.leftFrontMotor = 0;
        this.leftBackMotor=0;
        this.rightFrontMotor = 0;
        this.rightBackMotor=0;
        this.breakMode = breakMode;
    }

    public DriveSignal(double lf, double lb, double rf, double rb) {
        this(lf,lb,rf,rb, false);
    }

    public DriveSignal(double lf, double lb, double rf, double rb, boolean breakMode) {
        this.leftFrontMotor = lf;
        this.leftBackMotor=lb;
        this.rightFrontMotor = rf;
        this.rightBackMotor=rb;
        this.breakMode = breakMode;
    }
    public static DriveSignal translate(double angle,double throttle){
        angle-=Math.PI/4;
        return new DriveSignal(Math.cos(angle), Math.sin(angle), Math.sin(angle), Math.cos(angle)).scale(throttle);
    }

    public static DriveSignal translate(double angle,double throttle, double rot){
        angle-=Math.PI/4;
        return new DriveSignal(Math.cos(angle)+rot, Math.sin(angle)+rot, Math.sin(angle)-rot, Math.cos(angle)-rot).scale(throttle);
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
    public void addSignal(DriveSignal signal) {
        buffer[n] = signal;
        n = (n + 1) % 20;
    }
    public void changeSignal(DriveSignal signal) {
        this.leftFrontMotor = signal.leftFrontMotor;
        this.leftBackMotor = signal.leftBackMotor;
        this.rightFrontMotor = signal.rightFrontMotor;
        this.rightBackMotor = signal.rightFrontMotor;
        addSignal(signal);
    }
    public DriveSignal value() {
        double lf = 0, lb = 0, rf = 0, rb = 0;
        for (DriveSignal sig:buffer) {
            try {
                lf += sig.leftFrontMotor;
                lb += sig.leftBackMotor;
                rf += sig.rightFrontMotor;
                rb += sig.rightBackMotor;
            }catch(Exception e){
                sig=new DriveSignal();
            }
        }
        return new DriveSignal(lf/20, lb/20, rf/20, rb/20);
    }
    @Override
    public String toString() {
        return "LF: " + leftFrontMotor + ",LB: " +leftBackMotor+", RF: " + rightFrontMotor+", RB: "+rightBackMotor;
    }
    public boolean isZero(){
        return leftFrontMotor == 0 && leftBackMotor == 0 && rightFrontMotor == 0 && rightBackMotor == 0;
    }
    public DriveSignal scale(double power){
        this.leftBackMotor *= power;
        this.leftFrontMotor *= power;
        this.rightBackMotor *= power;
        this.rightFrontMotor *= power;
        return this;
    }

}
