package org.firstinspires.ftc.team9450.subsystems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.team9450.util.Constants;

/**
 * Created by dhruv on 1/20/18.
 */

public class Ramp extends Subsystem {
    private Servo servo;
    DcMotor rampMotor;
    private double speed= Constants.RampLifter.power;
    public enum RampState {
        IN, LEVEL, OUT
    }
    public enum LiftState{
        UP, DOWN, OFF
    }

    private RampState rampState;
    private LiftState liftState;

    public void setRampState(RampState state){
        this.rampState = state;
    }

    public void setLiftState(LiftState state){
        this.liftState = state;
    }
    public String toString(){
        return rampState.toString();
    }
    public Ramp(Servo ramp, DcMotor motor) {
        this.servo = ramp;
        servo.setDirection(Servo.Direction.FORWARD);
        this.setRampState(RampState.OUT);
        rampMotor = motor;
        rampMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rampMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rampMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        this.setLiftState(LiftState.OFF);
    }

    @Override
    public void stop() {
        servo.setPosition(servo.getPosition());
        rampMotor.setPower(0);
    }

    @Override
    public void loop() {
        switch (rampState) {
            case IN:
                servo.setPosition(Constants.RampLifter.INPOS);
                break;
            case LEVEL:
                servo.setPosition(Constants.RampLifter.LEVELPOS);
                break;
            case OUT:
                servo.setPosition(Constants.RampLifter.OUTPOS);
                break;
            default:
                stop();
        }
        switch (liftState) {
            case UP:
                if(rampMotor.getCurrentPosition()< Constants.RampLifter.maxPos) {
                    rampMotor.setPower(speed);
                }else{stop();}
                break;
            case DOWN:
                if(rampMotor.getCurrentPosition()>=Constants.RampLifter.minPos) {
                    rampMotor.setPower(-1 * speed);
                }else{stop();}
                break;
            case OFF:
                rampMotor.setPower(0);
        }

    }
}
