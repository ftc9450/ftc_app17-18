package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.util.Constants;
import org.firstinspires.ftc.teamcode.util.DriveSignal;
import org.firstinspires.ftc.teamcode.util.Util;

/**
 * Created by O on 10/28/2017.
 */

public class Drivetrain extends Subsystem {
    private DcMotor left;
    private DcMotor right;

    private float maxPower;

    private DriveControlState controlState;

    public enum DriveControlState {
        OPEN_LOOP
    }

    public Drivetrain(DcMotor left, DcMotor right) {
        this.left = left;
        this.right = right;
        maxPower = Constants.Drivetrain.HIGH_POWER;
    }

    public void setMaxPower(float maxPower) {
        this.maxPower = maxPower;
        System.out.println("max power: " + maxPower);
    }

    public float getMaxPower() {
        return this.maxPower;
    }

    public void setOpenLoop(DriveSignal signal) {
        controlState = DriveControlState.OPEN_LOOP;
        left.setPower(signal.rightMotor * maxPower);
        right.setPower(-signal.leftMotor * maxPower);
    }

    @Override
    public synchronized void stop() {
        setOpenLoop(DriveSignal.NEUTRAL);
    }

    @Override
    public synchronized void zeroSensors() {

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
