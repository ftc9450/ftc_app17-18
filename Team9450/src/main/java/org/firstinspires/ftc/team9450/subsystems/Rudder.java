package org.firstinspires.ftc.team9450.subsystems;

import android.graphics.Color;

import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.team9450.util.Constants;

/**
 * Created by dhruv on 11/9/17.
 * Rudder to knock over opponent jewel
 */

public class Rudder extends Subsystem {

    private RudderState rudderState;
    private LateralState lateralState;
    private Servo servo;
    private Servo lateral;
    private ColorSensor color;
    private int twitchTime=600;


    public enum RudderState {
        OUT, IN,START
    }
    public enum LateralState{
        FORWARDS,BACKWARDS,NEUTRAL,START
    }
    /**
     * Rudder initialization
     * @param top   servo attached to top
     * @param colorSensor   color sensor for checking color of jewel
     */
    public Rudder(Servo top, Servo bottom, ColorSensor colorSensor) {
        this.servo = top;
        this.lateral = bottom;
        this.servo.setDirection(Servo.Direction.FORWARD);
        this.lateral.setDirection(Servo.Direction.FORWARD);
        this.color = colorSensor;
        this.setRudderState(RudderState.START);
        this.setLateralState(LateralState.START);
    }

    /**
     * Changes rudderState
     * @param state new rudderState
     */
    public void setRudderState(RudderState state) {
        this.rudderState = state;
    }
    @Override
    public void stop() {}
    public RudderState getRudderState(){return rudderState;}
    public void setLateralState(LateralState state){this.lateralState=state;}
    public String toString(){
        return String.valueOf(servo.getPosition());
    }
    /**
     * Check color of jewel
     * @return RED if red jewel detected, BLUE if blue jewel detected, UNDECIDED if not sure
     */
    public int getColor() {
        float[] colors= new float[3];
        Color.RGBToHSV(color.red(),color.green(),color.blue(),colors);

        if (colors[1] > 0.1 && colors[2] < 1) {
            if (180 < colors[0] && colors[0] < 260) {
                return Constants.Color.BLUE;
            } else if ((300 < colors[0] && colors[0] < 360) || colors[0] < 50) {
                return Constants.Color.RED;
            }
        }
        return Constants.Color.UNDECIDED;
    }

    public void zeroSensors() {

    }
    public double rudderServoPos(){
        return servo.getPosition();
    }
    @Override
    public void loop() {
        switch (rudderState) {
            case IN:
                servo.setPosition(Constants.Rudder.RUDDER_IN);
                break;
            case OUT:
                servo.setPosition(Constants.Rudder.RUDDER_OUT);
                break;
            case START:
                servo.setPosition(Constants.Rudder.RUDDER_START);
                break;
            default:
                servo.setPosition(servo.getPosition());
        }
        switch (lateralState){
            case FORWARDS:
                lateral.setPosition(Constants.Rudder.LATERALFORWARD);
                break;
            case BACKWARDS:
                lateral.setPosition(Constants.Rudder.LATERALBACKWARD);
                break;
            case START:
                lateral.setPosition(Constants.Rudder.LATERALSTART);
                break;
            case NEUTRAL:
                lateral.setPosition(Constants.Rudder.LATERALNEUTRAL);
                break;
            default:
                lateral.setPosition(lateral.getPosition());
        }
    }
}
