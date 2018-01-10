package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.util.Constants;

/**
 * Created by Grace on 12/13/2017.
 */

public class RelicArm extends Subsystem {
    private DcMotor humerus;
    private CRServo[] carpals;
    private Servo pollex;

    private double speed = Constants.Elevator.POWER;

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

    public RelicArm(DcMotor relicArmMotor, CRServo LCarpal, CRServo RCarpal, Servo pollex) {
        this.humerus = relicArmMotor;
        this.humerus.setDirection(DcMotorSimple.Direction.REVERSE);
        this.humerus.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        this.humerus.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        this.humerusState = HumerusState.OFF;

        this.carpals = new CRServo[]{LCarpal, RCarpal};
        this.carpals[0].setDirection(DcMotorSimple.Direction.FORWARD);
        this.carpals[1].setDirection(DcMotorSimple.Direction.REVERSE);
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

    @Override
    public void stop() {
        humerus.setPower(0);
        carpals[0].setPower(0);
        carpals[1].setPower(0);
        pollex.setPosition(pollex.getPosition());
    }

    @Override
    public void zeroSensors() {
    }

    @Override
    public void loop() {
        switch (humerusState) {
            case OUT:
                if(humerus.getCurrentPosition()<Constants.RelicArm.maxEncoder) {
                    humerus.setPower(speed);
                }else{stop();}
                break;
            case IN:
                //if(1==1 || humerus.getCurrentPosition()>0) {
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
                carpals[0].setPower(1);
                carpals[1].setPower(1);
                break;
            case IN:
                carpals[0].setPower(-1);
                carpals[1].setPower(-1);
                break;
            case OFF:
                carpals[0].setPower(0);
                carpals[0].setPower(0);
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