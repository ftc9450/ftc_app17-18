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
    private DcMotor humerus;
    private CRServo carpal;
    private Servo pollex;

    private double speed = Constants.RelicArm.power;

    public enum HumerusState {
        OUT, IN, OFF
    }
    public enum CarpalState {
        OUT, IN, OFF
    }
    public enum PollexState {
        OPEN, CLOSED
    }

    private HumerusState humerusState;
    private CarpalState carpalState;
    private PollexState pollexState;

    public RelicArm(DcMotor relicArmMotor, CRServo LCarpal, Servo pollex) {
        this.humerus = relicArmMotor;
        this.humerus.setDirection(DcMotorSimple.Direction.REVERSE);
        this.humerus.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        this.humerus.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        this.humerusState = HumerusState.OFF;

        this.carpal = LCarpal;
        this.carpal.setDirection(DcMotorSimple.Direction.FORWARD);
        carpalState = CarpalState.OFF;

        this.pollex = pollex;
        this.pollex.setDirection(Servo.Direction.FORWARD);
        this.pollexState = PollexState.OPEN;
    }
    public String toString(){return String.valueOf(humerus.getCurrentPosition());}
    public void setHumerus(HumerusState state) {
        this.humerusState = state;
    }
    public void setCarpals(CarpalState state) {
        this.carpalState = state;
    }
    public void setPollex(PollexState state) {
        this.pollexState = state;
    }

    public CarpalState getCarpalState() {return this.carpalState;}
    public PollexState getPollexState() {return this.pollexState;}

    @Override
    public void stop() {
        humerus.setPower(0);
        carpal.setPower(0);
        pollex.setPosition(pollex.getPosition());
    }

    public void zeroSensors() {
    }

    @Override
    public void loop() {
        switch (humerusState) {
            case OUT:
                if(humerus.getCurrentPosition()<Constants.RelicArm.maxPos) {
                    humerus.setPower(speed);
                }else{stop();}
                break;
            case IN:
                if(humerus.getCurrentPosition()>=0) {
                    humerus.setPower(-1 * speed);
                }else{stop();}
                break;
            //case OFF:
            default:
                stop();
        }
        switch (carpalState) {
            case OUT:
                carpal.setPower(1);
                break;
            case IN:
                carpal.setPower(-1);
                break;
            case OFF:
                carpal.setPower(0);
                break;
            default:
                stop();
        }
        switch (pollexState) {
            case OPEN:
                pollex.setPosition(0);
                break;
            case CLOSED:
                pollex.setPosition(1);
                break;
            default:
                stop();
        }
    }
}