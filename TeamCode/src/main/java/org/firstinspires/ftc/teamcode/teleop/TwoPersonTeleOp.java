package org.firstinspires.ftc.teamcode.teleop;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.eventloop.opmode.*;

import org.firstinspires.ftc.robotcore.external.Const;
import org.firstinspires.ftc.teamcode.control.ControlBoard;
import org.firstinspires.ftc.teamcode.sensors.Gyroscope;
import org.firstinspires.ftc.teamcode.subsystems.Drivetrain;
import org.firstinspires.ftc.teamcode.subsystems.Elevator;
import org.firstinspires.ftc.teamcode.subsystems.Grabber;
import org.firstinspires.ftc.teamcode.subsystems.RelicArm;
import org.firstinspires.ftc.teamcode.subsystems.SubsystemManager;
import org.firstinspires.ftc.teamcode.util.Constants;
import org.firstinspires.ftc.teamcode.util.DriveSignal;

/**
 * Created by Grace on 12/26/2017.
 */
@com.qualcomm.robotcore.eventloop.opmode.TeleOp
public class TwoPersonTeleOp extends OpMode{
    ControlBoard controlBoard1;
    ControlBoard controlBoard2;
    Drivetrain drivetrain;
    //RelicArm relicArm;
    Elevator elevator;
    Grabber topGrabber;
    Grabber bottomGrabber;
    SubsystemManager subsystemManager;
    public void init() {
        subsystemManager=new SubsystemManager();
        controlBoard1=new ControlBoard(gamepad1);
        controlBoard2=new ControlBoard(gamepad2);
        drivetrain=new Drivetrain(hardwareMap.dcMotor.get(Constants.Drivetrain.LF), hardwareMap.dcMotor.get(Constants.Drivetrain.LB), hardwareMap.dcMotor.get(Constants.Drivetrain.RF), hardwareMap.dcMotor.get(Constants.Drivetrain.RB));
        drivetrain.disconnectEncoders();
        topGrabber=new Grabber(hardwareMap.servo.get(Constants.Grabber.LT),hardwareMap.servo.get(Constants.Grabber.RT));
        subsystemManager.add(topGrabber);
        bottomGrabber=new Grabber(hardwareMap.servo.get(Constants.Grabber.LB),hardwareMap.servo.get(Constants.Grabber.RB));
        subsystemManager.add(bottomGrabber);
        //relicArm=new RelicArm(hardwareMap.dcMotor.get(Constants.RelicArm.ARM),hardwareMap.servo.get(Constants.RelicArm.HAND));
        //subsystemManager.add(relicArm);
        elevator=new Elevator(hardwareMap.dcMotor.get(Constants.Elevator.ELEVATOR));
        subsystemManager.add(elevator);
    }

    public void loop() {

        if(controlBoard2.moveDownSixInches()){elevator.moveDownSixInches();}
        if(controlBoard2.moveUpSixInches()){elevator.moveUpSixInches();}
        elevator.setState(controlBoard2.elevatorCommand());
        Grabber.GrabberState currentTop=topGrabber.getState();
        Grabber.GrabberState currentBottom=bottomGrabber.getState();
        topGrabber.setState(controlBoard2.topGrabberCommand(currentTop));
        bottomGrabber.setState(controlBoard2.bottomGrabberCommand(currentBottom));
        DriveSignal d;
        DriveSignal translate=controlBoard1.translate();
        DriveSignal turn=controlBoard1.turn();
        if(controlBoard1.flip()){
            drivetrain.pivot(Constants.Drivetrain.INCH*12,0.5);
            drivetrain.disconnectEncoders();
        }
        if(turn.isZero()){
            d=translate;
        }else if(translate.isZero()){
            d=turn;
        }else{
            d=DriveSignal.BRAKE;
        }
        d.scale(controlBoard1.reduceDriveSpeed());
        drivetrain.setOpenLoop(d);
        subsystemManager.loopSystems();
    }
}
