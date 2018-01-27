package org.firstinspires.ftc.team9450.subsystems;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.team9450.util.Constants;

/**
 * Created by Grace on 12/13/2017.
 */

public class RelicArm extends Subsystem {
    private DcMotor arm;
    private CRServo pivot;
    private Servo hand;

    private double speed = Constants.RelicArm.power;

    public enum ArmState {
        OUT, IN, OFF
    }
    public enum PivotState {
        OUT, IN, OFF
    }
    public enum HandState {
        OPEN, CLOSED
    }

    private ArmState armState;
    private PivotState pivotState;
    private HandState handState;

    public RelicArm(DcMotor relicArmMotor, CRServo LCarpal, Servo pollex) {
        this.arm = relicArmMotor;
        this.arm.setDirection(DcMotorSimple.Direction.REVERSE);
        this.arm.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        this.arm.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        this.armState = ArmState.OFF;

        this.pivot = LCarpal;
        this.pivot.setDirection(DcMotorSimple.Direction.FORWARD);
        pivotState = PivotState.OFF;

        this.hand = pollex;
        this.hand.setDirection(Servo.Direction.FORWARD);
        this.handState = HandState.OPEN;
    }
    public String toString(){return String.valueOf(arm.getCurrentPosition());}
    public void setArm(ArmState state) {
        this.armState = state;
    }
    public void setPivot(PivotState state) {
        this.pivotState = state;
    }
    public void setHand(HandState state) {
        this.handState = state;
    }

    @Override
    public void stop() {
        arm.setPower(0);
        pivot.setPower(0);
        hand.setPosition(hand.getPosition());
    }

    public void zeroSensors() {
    }

    @Override
    public void loop() {
        switch (armState) {
            case OUT:
                if(arm.getCurrentPosition()<Constants.RelicArm.maxPos) {
                    arm.setPower(speed);
                }else{stop();}
                break;
            case IN:
                if(arm.getCurrentPosition()>=0) {
                    arm.setPower(-1 * speed);
                }else{stop();}
                break;
            //case OFF:
            default:
                stop();
        }
        switch (pivotState) {
            case OUT:
                pivot.setPower(1);
                break;
            case IN:
                pivot.setPower(-1);
                break;
            case OFF:
                pivot.setPower(0);
                break;
            default:
                stop();
        }
        switch (handState) {
            case OPEN:
                hand.setPosition(0);
                break;
            case CLOSED:
                hand.setPosition(1);
                break;
            default:
                stop();
        }
    }
}