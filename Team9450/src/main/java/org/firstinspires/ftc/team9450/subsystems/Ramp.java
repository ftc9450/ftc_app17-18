package org.firstinspires.ftc.team9450.subsystems;

import com.qualcomm.robotcore.hardware.DcMotor;
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
        IN, OUT
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

    public Ramp(Servo ramp, DcMotor motor) {
        this.servo = ramp;
        servo.setDirection(Servo.Direction.REVERSE);
        this.setRampState(RampState.OUT);
        rampMotor = motor;
        this.setLiftState(LiftState.DOWN);
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
                servo.setPosition(0.4);
                break;
            case OUT:
                servo.setPosition(0.9);
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
                if(rampMotor.getCurrentPosition()>=0) {
                    rampMotor.setPower(-1 * speed);
                }else{stop();}
                break;
            case OFF:
        }

    }
}
