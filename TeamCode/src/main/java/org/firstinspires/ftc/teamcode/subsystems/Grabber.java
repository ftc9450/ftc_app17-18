package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.hardware.Servo;

/**
 * Created by O on 10/28/2017.
 */

public class Grabber extends Subsystem{

    private Servo leftServo;
    private Servo rightServo;

    private double openPosition = Servo.MIN_POSITION;
    private double closedPosition = Servo.MAX_POSITION;

    public enum GrabberState{
        OPEN,CLOSED
    }

    private GrabberState grabberState;

    public Grabber(Servo left, Servo right){
        this.leftServo = left;
        this.rightServo = right;
        leftServo.setDirection(Servo.Direction.FORWARD);
        rightServo.setDirection(Servo.Direction.REVERSE);

    }
    public void setState(GrabberState state){this.grabberState = state;}
    public GrabberState getState(){return this.grabberState;}


    @Override
    public void stop() {
        leftServo.setPosition(openPosition);
        rightServo.setPosition(openPosition);
    }

    @Override
    public void zeroSensors() {

    }

    @Override
    public void loop() {
        switch(grabberState){
            case CLOSED:
                leftServo.setPosition(closedPosition);
                rightServo.setPosition(closedPosition);
                break;
            case OPEN:
            default:
                leftServo.setPosition(openPosition);
                rightServo.setPosition(openPosition);
                break;
        }
    }

}
