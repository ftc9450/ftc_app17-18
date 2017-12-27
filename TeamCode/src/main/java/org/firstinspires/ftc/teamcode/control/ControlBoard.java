package org.firstinspires.ftc.teamcode.control;

import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.teamcode.subsystems.Elevator;
import org.firstinspires.ftc.teamcode.subsystems.RelicArm;
import org.firstinspires.ftc.teamcode.subsystems.Grabber;
import org.firstinspires.ftc.teamcode.util.*;

/**
 * Created by O on 10/28/2017.
 */

public class ControlBoard {
    private Gamepad driverController;

    public ControlBoard(Gamepad controller) {
        driverController = controller;
    }

    /**
     * Use with mecanum wheels to move in multiple directions
     * @return DriveSignal to power translational movement corresponding to the position of the joystick
     */
    public DriveSignal translate(float offset){
        float a=driverController.left_stick_x;float b=-driverController.left_stick_y;
        boolean u=driverController.dpad_up;boolean d=driverController.dpad_down;boolean l=driverController.dpad_left;boolean r=driverController.dpad_right;
        double x = Constants.floatToDouble(a);
        double y = Constants.floatToDouble(b);
        if(u||d||l||r){
            if(u){y=0.1;}
            if(d){y=-0.1;}
            if(l){x=-0.1;}
            if(r){x=0.1;}
        }
        double angle;
        if (x != 0) {
            angle = Math.atan2(y, x) - Math.PI / 4;
            angle-=offset;
            return DriveSignal.translate(angle).scale(throttle(a, b));
        } else if (y > 0) {
            angle=((Math.PI/4)-offset);
            return DriveSignal.translate(angle).scale(throttle(a, b));
        } else if (y < 0) {
            angle=-1.0*((Math.PI/4)-offset);
            return DriveSignal.translate(angle).scale(throttle(a, b));
        }
        return new DriveSignal(0, 0, 0, 0);

    }
    public DriveSignal translate(){
        float a=driverController.left_stick_x;float b=-driverController.left_stick_y;
        boolean u=driverController.dpad_up;boolean d=driverController.dpad_down;boolean l=driverController.dpad_left;boolean r=driverController.dpad_right;
        double x = Constants.floatToDouble(a);
        double y = Constants.floatToDouble(b);
        if(u||d||l||r){
            if(u){y=0.1;}
            if(d){y=-0.1;}
            if(l){x=-0.1;}
            if(r){x=0.1;}
        }
        double angle;
        if (x != 0) {
            angle = Math.atan2(y, x) - Math.PI / 4;
            return DriveSignal.translate(angle).scale(throttle(a, b));
        } else if (y > 0) {
            return new DriveSignal(1,1,1,1).scale(throttle(a,b));
        } else if (y < 0) {
            return new DriveSignal(-1,-1,-1,-1).scale(throttle(a, b));
        }
        return new DriveSignal(0, 0, 0, 0);

    }
    public Grabber.GrabberState topGrabberCommand(){
        if(driverController.left_bumper){
            return Grabber.GrabberState.CLOSED;
        }return Grabber.GrabberState.OPEN;
    }
    public Grabber.GrabberState bottomGrabberCommand(){
        if(driverController.right_bumper){
            return Grabber.GrabberState.CLOSED;
        }return Grabber.GrabberState.OPEN;
    }
    public Grabber.GrabberState grabberCommand(){
        if(driverController.x){
            return Grabber.GrabberState.CLOSED;
        }return Grabber.GrabberState.OPEN;
    }
    public RelicArm.RelicArmState relicCommand(){
        if(driverController.b){
            return RelicArm.RelicArmState.OUT;
        }else if(driverController.x){
            return RelicArm.RelicArmState.IN;
        }return RelicArm.RelicArmState.OFF;
    }
    public RelicArm.HandState handCommand(){
        if(driverController.dpad_left) {
            return RelicArm.HandState.OPEN;
        }return RelicArm.HandState.CLOSED;
    }
    public Elevator.ElevatorState elevatorCommand(){
        if(driverController.y){
            return Elevator.ElevatorState.UP;
        }else if(driverController.a){
            return Elevator.ElevatorState.DOWN;
        }return Elevator.ElevatorState.OFF;
    }
    public float reduceDriveSpeed() {
        if(driverController.left_trigger>0){return Constants.doubleToFloat(Constants.Drivetrain.HIGH_POWER-(driverController.left_trigger*(Constants.Drivetrain.HIGH_POWER-Constants.Drivetrain.LOW_POWER)));}
        return Constants.doubleToFloat(Constants.Drivetrain.HIGH_POWER-(driverController.right_trigger*Constants.Drivetrain.HIGH_POWER-Constants.Drivetrain.LOW_POWER));
    }
    public boolean flip(){
        return driverController.left_bumper||driverController.right_bumper;
    }
    /**
     * Use for scaling movements for a joystick
     * @return distance given an x and y coordinate
     */
    public double throttle(float a, float b){
        double x=Constants.floatToDouble(a); double y=Constants.floatToDouble(b);
        return 0.5+(Math.pow(Math.pow(x,2)+Math.pow(y,2),0.5)/2.0);}
//    public double throttle() {return Math.pow(Math.pow(driverController.left_stick_y,2)+Math.pow(driverController.left_stick_x,2),0.5);}

//    public float turn() {
//        return driverController.right_stick_x;
//    }
    public DriveSignal turn(){
        return DriveSignal.pivot(driverController.right_stick_x);
    }

}
