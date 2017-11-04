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

    public static DriveSignal NEUTRAL = new DriveSignal(0, 0,0,0);
    public static DriveSignal BRAKE = new DriveSignal(0, 0, 0,0,true);

    @Override
    public String toString() {
        return "LF: " + leftFrontMotor + ",LB: " +", RF: " + rightFrontMotor+", RB: "+rightBackMotor;
    }

}
