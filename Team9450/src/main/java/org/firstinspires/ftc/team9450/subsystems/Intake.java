package org.firstinspires.ftc.team9450.subsystems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import org.firstinspires.ftc.team9450.util.Constants;

/**
 * Created by prave on 1/20/2018.
 */

public class Intake extends Subsystem{
    private DcMotor intakeLeft;
    private DcMotor intakeRight;
    private double power= Constants.Intake.power;
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
                intakeLeft.setPower(power*-1);
                intakeRight.setPower(power*-1);
                break;
            case OUT:
                intakeLeft.setPower(power);
                intakeRight.setPower(power);
                break;
            case OFF:
            default:
                break;
        }
    }
}
