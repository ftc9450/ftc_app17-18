package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.util.Constants;

/**
 * Created by O on 10/28/2017.
 */

public class Grabber extends Subsystem{

    private Servo leftServo;
    private Servo rightServo;

    private double openPosition = Constants.Grabber.openPos;
    private double closedPosition = Constants.Grabber.closePos;

    public enum GrabberState{
        OPEN,CLOSED
    }

    private GrabberState grabberState;

    public Grabber(Servo left, Servo right){
        this.leftServo = left;
        this.rightServo = right;
        leftServo.setDirection(Servo.Direction.REVERSE);
        rightServo.setDirection(Servo.Direction.FORWARD);
        leftServo.setPosition(openPosition);
        rightServo.setPosition(openPosition);
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
                double stor=leftServo.getPosition();
                for(double i=stor;i<closedPosition;i+=0.05){
                    leftServo.setPosition(i);
                    rightServo.setPosition(i);
                }
                break;
            case OPEN:
            default:
                double stor1=leftServo.getPosition();
                for(double i=stor1;i>openPosition;i-=0.05){
                    leftServo.setPosition(i);
                    rightServo.setPosition(i);
                }
                break;
        }
    }

}
