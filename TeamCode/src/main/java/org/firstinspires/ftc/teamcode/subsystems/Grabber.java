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
        OPEN,CLOSED,OFF
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

    public void close() {
        double stor=leftServo.getPosition();
        leftServo.setPosition(stor+0.05);
        rightServo.setPosition(stor+0.05);
    }

    public void open() {
        double stor=leftServo.getPosition();
        if(stor>openPosition) {
            leftServo.setPosition(stor - 0.05);
            rightServo.setPosition(stor - 0.05);
        }
    }
    public void autoClose(){
        double stor=leftServo.getPosition();
        while(stor<closedPosition) {
            stor=leftServo.getPosition();
            leftServo.setPosition(stor + 0.05);
            rightServo.setPosition(stor + 0.05);
        }
    }
    public String getPosition() {
        return leftServo.getPosition() + " " + rightServo.getPosition();
    }

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
                close();
                break;
            case OPEN:
                open();
                break;
            case OFF:
            default:
        }
    }

}
