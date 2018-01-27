package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import org.firstinspires.ftc.robotcore.external.Const;
import org.firstinspires.ftc.teamcode.control.ControlBoard;
import org.firstinspires.ftc.teamcode.util.*;

/**
 * Created by O on 10/28/2017.
 */

public class Elevator extends Subsystem{
    private DcMotor elevatorMotor;
    private double speed = Constants.Elevator.POWER;

    public enum ElevatorState{
        UP,DOWN,OFF
    }
    private ElevatorState state;

    public Elevator(DcMotor elevatorMotor){
        this.elevatorMotor = elevatorMotor;
        this.elevatorMotor.setDirection(DcMotorSimple.Direction.FORWARD);
        this.elevatorMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        this.elevatorMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        state = ElevatorState.OFF;
    }

    public void setState(ElevatorState state){this.state= state;}
    public ElevatorState getState(){return this.state;}
    @Override
    public void stop() {elevatorMotor.setPower(0);}

    @Override
    public void zeroSensors() {
    }
    public String toString(){return String.valueOf(elevatorMotor.getCurrentPosition())+" "+state;}
    public void moveUpSixInches(){
        int curr=elevatorMotor.getCurrentPosition();
        if(curr<=Constants.Elevator.maxEncoder-Constants.Elevator.sixInches){
            elevatorMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            elevatorMotor.setTargetPosition(Constants.Elevator.sixInches+curr);
            elevatorMotor.setPower(speed);
            //while(elevatorMotor.isBusy()){}
        }
    }
    public void moveDownSixInches(){
        int curr=elevatorMotor.getCurrentPosition();
        if(curr>=Constants.Elevator.sixInches){
            elevatorMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            elevatorMotor.setTargetPosition(curr-Constants.Elevator.sixInches);
            elevatorMotor.setPower(-1*speed);
            //while(elevatorMotor.isBusy()){}
        }
    }
    @Override
    public void loop() {
        //if(Math.abs(elevatorMotor.getCurrentPosition()-elevatorMotor.getTargetPosition())<50) {
            switch (state) {
                case UP:
                    elevatorMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
                    if (elevatorMotor.getCurrentPosition() < Constants.Elevator.maxEncoder) {
                        elevatorMotor.setPower(speed);
                    } else {
                        stop();
                    }
                    break;
                case DOWN:
                    elevatorMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
                    if (elevatorMotor.getCurrentPosition() >= 0) {
                        elevatorMotor.setPower(-1 * speed);
                    } else {
                        stop();
                    }
                    break;
                case OFF:
                default:
                    stop();
            }
       // }
    }
}
