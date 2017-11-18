package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcontroller.external.samples.SensorREVColorDistance;
import org.firstinspires.ftc.teamcode.util.*;

/**
 * Created by dhruv on 11/9/17.
 * Rudder to knock over opponent jewel
 */

public class Rudder extends Subsystem {

    private RudderState state;
    private Servo rudderServo;

    public enum RudderState {
        OUT, IN
    }

    public Rudder(Servo rudderServo) {
        this.rudderServo = rudderServo;
        this.state = RudderState.IN;
    }

    public void setState(RudderState state) {
        this.state = state;
    }

    @Override
    public void stop() {

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
