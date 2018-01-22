package org.firstinspires.ftc.team9450.subsystems;

import com.qualcomm.robotcore.hardware.Servo;

/**
 * Created by dhruv on 1/20/18.
 */

public class Ramp extends Subsystem {
    private Servo servo;

    public enum RampState {
        IN, OUT
    }

    private RampState state;

    public Ramp(Servo ramp) {
        this.servo = ramp;
        servo.setDirection(Servo.Direction.REVERSE);
        this.setState(RampState.OUT);
    }

    @Override
    public void stop() {
        servo.setPosition(servo.getPosition());
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
    }
}
