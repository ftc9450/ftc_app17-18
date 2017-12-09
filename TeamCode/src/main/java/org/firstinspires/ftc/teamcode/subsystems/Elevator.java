package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import org.firstinspires.ftc.teamcode.util.*;

/**
 * Created by O on 10/28/2017.
 */

@TeleOp
public class Elevator extends Subsystem{
    private DcMotor elevatorMotor;
    private double speed = Constants.Elevator.POWER;

    public enum ElevatorState{
        UP,DOWN,OFF
    }
    private ElevatorState state;

    public Elevator(DcMotor elevatorMotor){
        this.elevatorMotor = elevatorMotor;
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
        switch(state){
            case UP:
                elevatorMotor.setDirection(DcMotorSimple.Direction.FORWARD);
                elevatorMotor.setPower(speed);
                break;
            case DOWN:
                elevatorMotor.setDirection(DcMotorSimple.Direction.REVERSE);
                elevatorMotor.setPower(speed);
                break;
            case OFF:
            default:
                stop();
        }
    }
}
