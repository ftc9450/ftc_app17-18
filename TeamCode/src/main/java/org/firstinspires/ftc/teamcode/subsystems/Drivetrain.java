package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.util.Constants;
import org.firstinspires.ftc.teamcode.util.DriveSignal;
import org.firstinspires.ftc.teamcode.util.Util;

import static android.R.attr.left;
import static android.R.attr.right;

/**
 * Created by O on 10/28/2017.
 */

public class Drivetrain extends Subsystem {
    private DcMotor leftFront;
    private DcMotor rightFront;
    private DcMotor leftBack;
    private DcMotor rightBack;

    private float maxPower;

    private DriveControlState controlState;

    public enum DriveControlState {
        OPEN_LOOP
    }

    public Drivetrain(DcMotor lf, DcMotor lb, DcMotor rf, DcMotor rb) {
        this.leftFront = lf;
        this.leftBack=lb;
        this.rightFront=rf;
        this.rightBack = rb;
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
        rightFront.setPower(signal.rightFrontMotor * maxPower);
        rightBack.setPower(signal.rightBackMotor*maxPower);
        leftFront.setPower(-signal.leftFrontMotor * maxPower);
        leftBack.setPower(-signal.leftBackMotor*maxPower);
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
