package org.firstinspires.ftc.team9450.subsystems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

/**
 * Created by prave on 1/20/2018.
 */

public class Intake extends Subsystem{
    private DcMotor intakeLeft;
    private DcMotor intakeRight;
    public enum IntakeState{
        IN,OUT,OFF
    }
    private IntakeState state;
    public Intake(DcMotor iL, DcMotor iR){
        intakeLeft = iL;
        intakeRight = iR;

        intakeLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        intakeLeft.setDirection(DcMotorSimple.Direction.FORWARD);
        intakeRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        intakeRight.setDirection(DcMotorSimple.Direction.REVERSE);

        this.setState(IntakeState.OFF);
    }

    @Override
    public void stop() {

    }
    public void setState(IntakeState state){
        this.state = state;
    }
    //@Override
    public void zeroSensors() {

    }

    @Override
    public void loop() {
        switch (state){
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
