package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

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
        this.elevatorMotor.setDirection(DcMotorSimple.Direction.REVERSE);
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

    @Override
    public void loop() {
        switch (state) {
        case UP:
            if(elevatorMotor.getCurrentPosition()<Constants.Elevator.maxEncoder) {
                elevatorMotor.setPower(speed);
            }else{stop();}
            break;
        case DOWN:
            if(elevatorMotor.getCurrentPosition()>0) {
                elevatorMotor.setPower(-1 * speed);
            }else{stop();}
            break;
        case OFF:
        default:
            stop();
        }
    }
}
