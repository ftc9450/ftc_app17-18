package org.firstinspires.ftc.teamcode.control;

import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.teamcode.subsystems.Elevator;
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
    public DriveSignal translate(){
        float a=driverController.left_stick_x;float b=driverController.left_stick_y;
        boolean u=driverController.dpad_up;boolean d=driverController.dpad_down;boolean l=driverController.dpad_left;boolean r=driverController.dpad_right;
        if(u||d||l||r){
            if(u){b=0.3f;}
            if(d){b=-0.3f;}
            if(l){a=-0.3f;}
            if(r){a=0.3f;}
        }
        double x = Constants.floatToDouble(a);
        double y = Constants.floatToDouble(b);
        if (x != 0) {
            double angle = Math.atan2(y, x) - Math.PI / 4;
            return new DriveSignal(Math.cos(angle), Math.sin(angle), Math.sin(angle), Math.cos(angle)).scale(throttle(a, b));
        } else {
            if (y > 0) {
                return new DriveSignal(1, 1, 1, 1).scale(throttle(a, b));
            } else if (y < 0) {
                return new DriveSignal(-1, -1, -1, -1).scale(throttle(a, b));

            }
            return new DriveSignal(0, 0, 0, 0);
        }

    }
    public Grabber.GrabberState grabberCommand(){
        if(driverController.x){
            return Grabber.GrabberState.CLOSED;
        }return Grabber.GrabberState.OPEN;
    }
    public Elevator.ElevatorState elevatorCommand(){
        if(driverController.dpad_up){
            return Elevator.ElevatorState.UP;
        }else if(driverController.dpad_down){
            return Elevator.ElevatorState.DOWN;
        }return Elevator.ElevatorState.OFF;
    }
    public float reduceDriveSpeed() {
        if(driverController.left_trigger>0){return Constants.doubleToFloat(Constants.Drivetrain.HIGH_POWER-(driverController.left_trigger/2));}
        return Constants.doubleToFloat(Constants.Drivetrain.HIGH_POWER-(driverController.right_trigger/2));
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
