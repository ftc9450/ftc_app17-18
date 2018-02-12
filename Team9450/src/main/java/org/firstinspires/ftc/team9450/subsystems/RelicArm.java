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
    private Servo standardpivot=null;
    private CRServo crpivot=null;
    private CRServo crhand=null;
    private Servo standardhand=null;

    private double speed = Constants.RelicArm.power;

    public enum ArmState {
        OUT, IN, OFF
    }
    public enum PivotState {
        OUT, IN, OFF
    }
    public enum HandState {
        OPEN, CLOSE,OFF
    }

    private ArmState armState;
    private PivotState pivotState;
    private HandState handState;

    public RelicArm(DcMotor relicArmMotor) {
        this.arm = relicArmMotor;
        this.arm.setDirection(DcMotorSimple.Direction.FORWARD);
        this.arm.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        this.arm.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        this.armState = ArmState.OFF;
    }

    public RelicArm(DcMotor relicArmMotor, Servo LCarpal, CRServo pollex) {
        this(relicArmMotor);

        this.standardpivot = LCarpal;
        this.standardpivot.setDirection(Servo.Direction.REVERSE);
        pivotState = PivotState.OFF;

        this.crhand = pollex;
        this.crhand.setDirection(CRServo.Direction.FORWARD);
        this.handState = HandState.OPEN;
    }

    public RelicArm(DcMotor relicArmMotor, CRServo LCarpal, Servo pollex){
        this(relicArmMotor);

        this.crpivot = LCarpal;
        this.crpivot.setDirection(CRServo.Direction.REVERSE);
        pivotState = PivotState.OFF;

        this.standardhand = pollex;
        this.standardhand.setDirection(Servo.Direction.FORWARD);
        this.handState = HandState.OPEN;
    }

    public RelicArm(DcMotor relicArmMotor, Servo LCarpal, Servo pollex) {
        this(relicArmMotor);

        this.standardpivot = LCarpal;
        this.standardpivot.setDirection(Servo.Direction.REVERSE);
        pivotState = PivotState.OFF;

        this.standardhand = pollex;
        this.standardhand.setDirection(Servo.Direction.FORWARD);
        this.handState = HandState.OPEN;
    }

    public RelicArm(DcMotor relicArmMotor, CRServo LCarpal, CRServo pollex) {
        this(relicArmMotor);

        this.crpivot = LCarpal;
        this.crpivot.setDirection(CRServo.Direction.REVERSE);
        pivotState = PivotState.OFF;

        this.crhand = pollex;
        this.crhand.setDirection(CRServo.Direction.FORWARD);
        this.handState = HandState.OPEN;
    }
    public String toString(){return String.valueOf(arm.getCurrentPosition());}
    public void setArm(ArmState state) {
        this.armState = state;
    }
    public void setPower(double power) {
        arm.setPower(power);
    }
    public void setStandardpivot(PivotState state) {
        this.pivotState = state;
    }
    public void setCrhand(HandState state) {
        this.handState = state;
    }
    public HandState getHandState() {
        return this.handState;
    }
    public double getPosition() {
        return arm.getCurrentPosition();
    }

    @Override
    public void stop() {
        arm.setPower(0);
        if(standardpivot == null){
            crpivot.setPower(0);
        }else{standardpivot.setPosition(standardpivot.getPosition());}
        if(standardhand == null){
            crhand.setPower(0);
        }else{standardhand.setPosition(standardhand.getPosition());}

    }

    public void zeroSensors() {
    }

    @Override
    public void loop() {
        /*switch (armState) {
            case OUT:
                if(arm.getCurrentPosition()<Constants.RelicArm.maxPos) {
                    arm.setPower(speed);
                }else{arm.setPower(0);}
                break;
            case IN:
                if(arm.getCurrentPosition()>=0) {
                    arm.setPower(-1 * speed);
                }else{arm.setPower(0);}
                break;
            case OFF:
            default:
                arm.setPower(0);
        }*/
        switch (pivotState) {
            case OUT:
                if(standardpivot == null){
                    crpivot.setPower(1);
                }else{standardpivot.setPosition(standardpivot.getPosition()+0.01);}
                break;
            case IN:
                if(standardpivot == null) {
                   crpivot.setPower(-1);
                }else{standardpivot.setPosition(standardpivot.getPosition()-0.01);}
                break;
            case OFF:
            default:
                if(standardpivot == null){
                    crpivot.setPower(0);
                }else{standardpivot.setPosition(standardpivot.getPosition());}
                break;
        }
        switch (handState) {
            case OPEN:
                if(standardhand == null){
                    crhand.setPower(1);
                }else{standardhand.setPosition(standardhand.getPosition()+0.01);}
                break;
            case CLOSE:
                if(standardhand == null) {
                    crhand.setPower(-1);
                }else{standardhand.setPosition(standardhand.getPosition()-0.01);}
                break;
            case OFF:
            default:
                if(standardhand == null){
                    crhand.setPower(0);
                }else{standardhand.setPosition(standardhand.getPosition());}
                break;
        }
    }
}