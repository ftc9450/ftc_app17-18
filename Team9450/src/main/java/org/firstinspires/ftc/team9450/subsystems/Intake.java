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
    public Intake(DcMotor i){
        intakeLeft=i;
        intakeRight=null;
    }

    @Override
    public void stop() {
        setPower(0);
    }
    public void setState(IntakeState state){
        this.state = state;
    }
    public void setPower(double p) {
        if(intakeRight.equals(null)){
            intakeLeft.setPower(p);
        }else {
            intakeLeft.setPower(p);
            intakeRight.setPower(p);
        }
    }
    //@Override
    public void zeroSensors() {

    }

    @Override
    public void loop() {
        switch (state){
            case IN:
                setPower(-1*power);
                break;
            case OUT:
                setPower(power);
                break;
            case OFF:
            default:
                setPower(0);
        }
    }
}
