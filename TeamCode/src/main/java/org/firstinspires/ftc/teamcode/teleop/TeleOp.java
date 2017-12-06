package org.firstinspires.ftc.teamcode.teleop;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpModeManager;

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
    Drivetrain drivetrain;
    ControlBoard controlBoard;
    Elevator elevator;
    Grabber grabber;
    @Override
    public void init() {
        controlBoard=new ControlBoard(gamepad1);
        drivetrain=new Drivetrain(hardwareMap.dcMotor.get(Constants.Drivetrain.LF), hardwareMap.dcMotor.get(Constants.Drivetrain.LB), hardwareMap.dcMotor.get(Constants.Drivetrain.RF), hardwareMap.dcMotor.get(Constants.Drivetrain.RB));
        drivetrain.disconnectEncoders();
        subsystemManager.add(drivetrain);
//        elevator=new Elevator(hardwareMap.dcMotor.get(Constants.Elevator.ELEVATOR));
//        subsystemManager.add(elevator);
//        grabber=new Grabber(hardwareMap.servo.get("servoLeft"),hardwareMap.servo.get("servoRight"));
//        subsystemManager.add(grabber);

    }

    @Override
    public void loop() {
        DriveSignal d;
        DriveSignal translate=controlBoard.translate();
        DriveSignal turn=controlBoard.turn();
        if(controlBoard.flip()){
            drivetrain.pivot(Constants.Drivetrain.INCH*12,0.5);
            drivetrain.disconnectEncoders();
            while(controlBoard.flip()){}
        }
        if(turn.isZero()){
            d=translate;
        }else if(translate.isZero()){
            d=turn;
        }else{
            d=DriveSignal.average(translate,turn);
        }
        telemetry.addData("dpad",gamepad1.dpad_down);
        d.scale(controlBoard.reduceDriveSpeed());
        telemetry.addData("power",gamepad1.left_stick_y);
        telemetry.addData("motor power",drivetrain.toString());
        drivetrain.setOpenLoop(d);
        subsystemManager.loopSystems();
    }
}
