package org.firstinspires.ftc.teamcode.subsystems;

import android.graphics.Color;

import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.SwitchableLight;

import org.firstinspires.ftc.teamcode.util.*;

/**
 * Created by dhruv on 11/9/17.
 * Rudder to knock over opponent jewel
 */

public class Rudder extends Subsystem {

    private RudderState state;
    private Servo rudderServo;
    private ColorSensor colorSensor;

    public enum RudderState {
        OUT, IN
    }

    /**
     * Rudder initialization
     * @param rudderServo   servo attached to rudder
     * @param colorSensor   color sensor for checking color of jewel
     */
    public Rudder(Servo rudderServo, ColorSensor colorSensor) {
        this.rudderServo = rudderServo;
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
    public void stop() {

    }

    /**
     * Check color of jewel
     * @return RED if red jewel detected, BLUE if blue jewel detected, UNDECIDED if not sure
     */
    public int getColor() {
        if(colorSensor instanceof SwitchableLight) ((SwitchableLight) colorSensor).enableLight(true);
        int r = colorSensor.red(), g = colorSensor.green(), b = colorSensor.blue(), a = colorSensor.alpha();
        if(a < 20 || a > 200) return Constants.Color.UNDECIDED; //???? Check projected alpha values
        if(r > g*2 && r > b*2) return Constants.Color.RED;
        if(b > g*2 && b > r*2) return Constants.Color.BLUE;
        return Constants.Color.UNDECIDED;
    }

    @Override
    public void zeroSensors() {

    }

    @Override
    public void loop() {
        switch (state) {
            case IN:
                rudderServo.setPosition(Constants.Rudder.RUDDER_IN);
                break;
            case OUT:
                rudderServo.setPosition(Constants.Rudder.RUDDER_OUT);
                break;
        }
    }
}
