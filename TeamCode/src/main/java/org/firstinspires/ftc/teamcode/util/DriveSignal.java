package org.firstinspires.ftc.teamcode.util;

/**
 * Created by O on 10/28/2017.
 * A drivetrain command consisting of left, right motor settings and whether brake mode is on
 */

public class DriveSignal {
    public double leftMotor;
    public double rightMotor;
    public boolean breakMode;

    public DriveSignal(double left, double right) {
        this(left, right, false);
    }

    public DriveSignal(double left, double right, boolean breakMode) {
        this.leftMotor = left;
        this.rightMotor = right;
        this.breakMode = breakMode;
    }

    public static DriveSignal NEUTRAL = new DriveSignal(0, 0);
    public static DriveSignal BRAKE = new DriveSignal(0, 0, true);

    @Override
    public String toString() {
        return "L: " + leftMotor + ", R: " + rightMotor;
    }

}
