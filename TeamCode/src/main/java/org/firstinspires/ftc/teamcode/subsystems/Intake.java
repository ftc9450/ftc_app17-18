package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

/**
 * Created by prave on 1/20/2018.
 */

public class Intake extends Subsystem{
    private DcMotor intakeLeft;
    private DcMotor intakeRight;
    public enum GrabberState{
        IN,OUT,OFF
    }
    private GrabberState grabberState;
    public Intake(DcMotor iL, DcMotor iR){
        intakeLeft = iL;
        intakeRight = iR;

        intakeLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        intakeLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        intakeRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        intakeRight.setDirection(DcMotorSimple.Direction.FORWARD);
    }

    @Override
    public void stop() {

    }
    public void setGrabberState(GrabberState state){
        grabberState=state;
    }
    @Override
    public void zeroSensors() {

    }

    @Override
    public void loop() {
        switch (grabberState){
            case IN:
                intakeLeft.setPower(-1);
                intakeRight.setPower(-1);
                break;
            case OUT:
                intakeLeft.setPower(1);
                intakeRight.setPower(1);
                break;
            case OFF:
            default:
                break;
        }
    }
}
