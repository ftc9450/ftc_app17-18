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
        float x=driverController.left_stick_x;float y=driverController.left_stick_y;
        if(x!=0) {
            double angle = Math.atan2(y, x) - Math.PI / 4;
            return new DriveSignal(Math.sin(angle), Math.cos(angle), Math.cos(angle), Math.sin(angle)).scale(throttle(x,y));
        }else{
            if(y>0){
                return new DriveSignal(1,1,1,1).scale(throttle(x,y));
            }else if(y<0){
                return new DriveSignal(-1,-1,-1,-1).scale(throttle(x,y));

            }return new DriveSignal(0,0,0,0);
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
    public boolean reduceDriveSpeed() {return driverController.left_bumper;}

    /**
     * Use for scaling movements for a joystick
     * @return distance given an x and y coordinate
     */
    public double throttle(float x, float y){return Math.pow(Math.pow(x,2)+Math.pow(y,2),0.5);}
//    public double throttle() {return Math.pow(Math.pow(driverController.left_stick_y,2)+Math.pow(driverController.left_stick_x,2),0.5);}

//    public float turn() {
//        return driverController.right_stick_x;
//    }
    public DriveSignal turn(){
        return DriveSignal.pivot(driverController.right_stick_x);
    }

}
