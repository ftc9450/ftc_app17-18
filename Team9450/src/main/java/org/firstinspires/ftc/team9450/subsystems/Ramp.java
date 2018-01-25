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
    public enum RampLifterState{
        UP, DOWN, OFF
    }

    private RampState state;
    private RampLifterState liftState;

    public void setRampState(RampState state){
        this.state = state;
    }
    public void setRampLifterState(RampLifterState state){
        this.liftState = state;
    }
    public Ramp(Servo ramp, DcMotor motor) {
        this.servo = ramp;
        servo.setDirection(Servo.Direction.REVERSE);
        this.setState(RampState.OUT);
        rampMotor = motor;
        this.setRampLifterState(RampLifterState.DOWN);
    }

    @Override
    public void stop() {
        servo.setPosition(servo.getPosition());
        rampMotor.setPower(0);
    }

    public void setState(RampState state) {
        this.state = state;
    }

    @Override
    public void loop() {
        switch (state) {
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
