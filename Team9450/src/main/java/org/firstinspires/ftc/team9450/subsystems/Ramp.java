package org.firstinspires.ftc.team9450.subsystems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.team9450.util.Constants;

/**
 * Created by dhruv on 1/20/18.
 */

public class Ramp extends Subsystem {
    private Servo servo;
    DcMotor lift;
    DigitalChannel touch;
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
    public double getPosition() {
        return lift.getCurrentPosition();
    }
    public String toString(){
        return rampState.toString();
    }
    public Ramp(Servo ramp, DcMotor motor, DigitalChannel touch) {
        this.servo = ramp;
        servo.setDirection(Servo.Direction.FORWARD);
        this.setRampState(RampState.IN);
        lift = motor;
        lift.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        lift.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        lift.setDirection(DcMotorSimple.Direction.REVERSE);
        this.setLiftState(LiftState.OFF);
        this.touch = touch;
        touch.setMode(DigitalChannel.Mode.INPUT);
    }

    @Override
    public void stop() {
        servo.setPosition(servo.getPosition());
        lift.setPower(0);
    }

    @Override
    public void loop() {
        switch (rampState) {
            case LEVEL:
                if (!touch.getState()) {
                    servo.setPosition(servo.getPosition());
                } else {
                    servo.setPosition(Constants.Ramp.LEVEL);
                }
                break;
            case IN:
                if (!touch.getState()) {
                    servo.setPosition(servo.getPosition());
                } else {
                    servo.setPosition(Constants.Ramp.IN);
                }
                break;
            case OUT:
                servo.setPosition(Constants.Ramp.OUT);
                break;
        }
        switch (liftState) {
            case UP:
                if(Math.abs(lift.getCurrentPosition()) < Constants.Ramp.MAX) {
                    lift.setPower(speed);
                }else{lift.setPower(0);}
                break;
            case DOWN:
                if(Math.abs(lift.getCurrentPosition()) >= 0) {
                    lift.setPower(-1 * speed);
                }else{lift.setPower(0);}
                break;
            case OFF:
                lift.setPower(0);
        }

    }
}
