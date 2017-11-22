package org.firstinspires.ftc.teamcode.teleop;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.control.ControlBoard;
import org.firstinspires.ftc.teamcode.subsystems.*;
import org.firstinspires.ftc.teamcode.util.Constants;
import org.firstinspires.ftc.teamcode.util.DriveSignal;

/**
 * @author Grace
 */
@com.qualcomm.robotcore.eventloop.opmode.TeleOp
public class TeleOp extends OpMode{
    SubsystemManager subsystemManager=new SubsystemManager();
    ControlBoard controlBoard=new ControlBoard(gamepad1);
    Drivetrain drivetrain;
    Elevator elevator;
    Grabber grabber;
    @Override
    public void init() {
        drivetrain=new Drivetrain(hardwareMap.dcMotor.get(Constants.Drivetrain.LF), hardwareMap.dcMotor.get(Constants.Drivetrain.LB), hardwareMap.dcMotor.get(Constants.Drivetrain.RF), hardwareMap.dcMotor.get(Constants.Drivetrain.RB));
        subsystemManager.add(drivetrain);
        elevator=new Elevator(hardwareMap.dcMotor.get(Constants.Elevator.ELEVATOR));
        subsystemManager.add(elevator);
        grabber=new Grabber(hardwareMap.servo.get("servoLeft"),hardwareMap.servo.get("servoRight"));
        subsystemManager.add(grabber);

    }

    @Override
    public void loop() {
        DriveSignal translate=controlBoard.translate();
        DriveSignal turn=controlBoard.turn();
        DriveSignal d=null;
        if(turn.isZero()){
            d=translate;
        }else if(translate.isZero()){
            d=turn;
        }else{
            d=DriveSignal.average(controlBoard.translate(),controlBoard.turn());
        }
        if(controlBoard.reduceDriveSpeed()){
            d.scale(Constants.Drivetrain.LOW_POWER);
        }
        drivetrain.setOpenLoop(d);
        elevator.setState(controlBoard.elevatorCommand());
        grabber.setState(controlBoard.grabberCommand());
        subsystemManager.loopSystems();
    }
}
