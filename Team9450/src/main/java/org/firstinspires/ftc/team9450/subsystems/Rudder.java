package org.firstinspires.ftc.team9450.subsystems;

import android.graphics.Color;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.team9450.util.Constants;

/**
 * Created by dhruv on 11/9/17.
 * Rudder to knock over opponent jewel
 */

public class Rudder extends Subsystem {

    private RudderState state;
    private Servo topServo;
    private CRServo bottomServo;
    private ColorSensor colorSensor;

    public enum RudderState {
        OUT, IN,START
    }

    /**
     * Rudder initialization
     * @param top   servo attached to top
     * @param colorSensor   color sensor for checking color of jewel
     */
    public Rudder(Servo top, CRServo bottom, ColorSensor colorSensor) {
        this.topServo = top;
        this.bottomServo=bottom;
        this.topServo.setDirection(Servo.Direction.FORWARD);
        this.colorSensor = colorSensor;
        this.setState(RudderState.IN);
    }

    /**
     * Changes state
     * @param state new state
     */
    public void setState(RudderState state) {
        this.state = state;
    }
    @Override
    public void stop() {}
    public RudderState getState(){return state;}
    public String toString(){
        return String.valueOf(topServo.getPosition());
    }
    /**
     * Check color of jewel
     * @return RED if red jewel detected, BLUE if blue jewel detected, UNDECIDED if not sure
     */
    public int getColor() {
        float[] colors= new float[3];
        Color.RGBToHSV(colorSensor.red(),colorSensor.green(),colorSensor.blue(),colors);
        if (colors[1] > 0.1 && colors[2] <2) {
            if (180 < colors[0] && colors[0] < 260) {
                return Constants.Color.BLUE;
            } else if ((300 < colors[0] && colors[0] < 359) || colors[0] < 30) {
                return Constants.Color.RED;
            }
        }
        return Constants.Color.UNDECIDED;
    }
    public void knockRed() throws InterruptedException {
        topServo.setPosition(Constants.Rudder.RUDDER_OUT);
        Thread.sleep(500);
        int color=getColor();
        if(color==Constants.Color.RED){
            bottomServo.setPower(1);
            Thread.sleep(250);
            bottomServo.setPower(0);
            Thread.sleep(250);
            bottomServo.setPower(-1);
            Thread.sleep(250);
        }else if(color==Constants.Color.BLUE){
            bottomServo.setPower(-1);
            Thread.sleep(250);
            bottomServo.setPower(0);
            Thread.sleep(250);
            bottomServo.setPower(1);
            Thread.sleep(250);
        }
        bottomServo.setPower(0);
        topServo.setPosition(Constants.Rudder.RUDDER_IN);Thread.sleep(1000);
    }
    public void knockBlue() throws InterruptedException {
        topServo.setPosition(Constants.Rudder.RUDDER_OUT);
        Thread.sleep(500);
        int color=getColor();
        if(color==Constants.Color.BLUE){
            bottomServo.setPower(1);
            Thread.sleep(250);
            bottomServo.setPower(0);
            Thread.sleep(250);
            bottomServo.setPower(-1);
            Thread.sleep(250);
        }else if(color==Constants.Color.RED){
            bottomServo.setPower(-1);
            Thread.sleep(250);
            bottomServo.setPower(0);
            Thread.sleep(250);
            bottomServo.setPower(1);
            Thread.sleep(250);
        }
        bottomServo.setPower(0);
        topServo.setPosition(Constants.Rudder.RUDDER_IN);Thread.sleep(1000);
    }
    public void zeroSensors() {

    }
    public double rudderServoPos(){
        return topServo.getPosition();
    }
    @Override
    public void loop() {
        switch (state) {
            case IN:
                topServo.setPosition(Constants.Rudder.RUDDER_IN);
                break;
            case OUT:
                topServo.setPosition(Constants.Rudder.RUDDER_OUT);
                break;
            case START:
                topServo.setPosition(Constants.Rudder.RUDDER_START);
                break;

        }
    }
}
