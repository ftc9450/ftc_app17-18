package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.util.Constants;

/**
 * Created by Grace on 12/13/2017.
 */

public class RelicArm extends Subsystem {
    private DcMotor RelicArmMotor;
    private Servo hand;
    private double speed = Constants.Elevator.POWER;

    public enum RelicArmState {
        OUT, IN, OFF
    }

    public enum HandState {
        OPEN, CLOSED
    }

    private RelicArmState state;
    private HandState handState;

    public RelicArm(DcMotor relicArmMotor, Servo hand) {
        this.RelicArmMotor = relicArmMotor;
        this.RelicArmMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        this.RelicArmMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        this.RelicArmMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        state = RelicArmState.OFF;

        this.hand = hand;
        this.hand.setDirection(Servo.Direction.FORWARD);
        this.handState = HandState.OPEN;
    }

    public void setState(RelicArmState state) {
        this.state = state;
    }

    public void setHand(HandState state) {this.handState = state;}

    public RelicArmState getState() {
        return this.state;
    }

    @Override
    public void stop() {
        RelicArmMotor.setPower(0);
    }

    @Override
    public void zeroSensors() {
    }

    @Override
    public void loop() {
        switch (state) {
            case OUT:
                if (RelicArmMotor.getCurrentPosition() < Constants.Elevator.maxEncoder) {
                    RelicArmMotor.setPower(speed);
                } else {
                    stop();
                }
                break;
            case IN:
                if (RelicArmMotor.getCurrentPosition() > 0) {
                    RelicArmMotor.setPower(-1 * speed);
                } else {
                    stop();
                }
                break;
            case OFF:
            default:
                stop();
        }

        switch (handState) {
            case CLOSED:
                this.hand.setPosition(0);
                break;
            case OPEN:
                this.hand.setPosition(90);
                break;
        }
    }
}