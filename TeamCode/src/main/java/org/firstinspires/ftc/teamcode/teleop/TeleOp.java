package org.firstinspires.ftc.teamcode.teleop;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.util.ReadWriteFile;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;
import org.firstinspires.ftc.robotcore.internal.system.AppUtil;
import org.firstinspires.ftc.teamcode.control.ControlBoard;
import org.firstinspires.ftc.teamcode.sensors.Gyroscope;
import org.firstinspires.ftc.teamcode.subsystems.*;
import org.firstinspires.ftc.teamcode.util.Constants;
import org.firstinspires.ftc.teamcode.util.DriveSignal;

import java.io.File;

/**
 * @author Grace
 */
@com.qualcomm.robotcore.eventloop.opmode.TeleOp
public class TeleOp extends OpMode{
    SubsystemManager subsystemManager=new SubsystemManager();
    Drivetrain drivetrain;
    ControlBoard controlBoard;
    Gyroscope gyroscope;
    Grabber grabber;

    private BNO055IMU imu;
    Orientation angles;

    float normal;

    @Override
    public void init() {
        controlBoard=new ControlBoard(gamepad1);
        drivetrain=new Drivetrain(hardwareMap.dcMotor.get(Constants.Drivetrain.LF), hardwareMap.dcMotor.get(Constants.Drivetrain.LB), hardwareMap.dcMotor.get(Constants.Drivetrain.RF), hardwareMap.dcMotor.get(Constants.Drivetrain.RB));
        drivetrain.disconnectEncoders();
        gyroscope=new Gyroscope(hardwareMap.get(BNO055IMU.class,"imu"));
//        grabber=new Grabber(hardwareMap.servo.get("servoLeft"),hardwareMap.servo.get("servoRight"));
//        subsystemManager.add(grabber);

        //normal = AngleUnit.DEGREES.fromUnit(angles.angleUnit, angles.firstAngle);
        angles = imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES);
        normal =gyroscope.getAngle();

    }

    @Override
    public void loop() {
       // telemetry.addData("heading", imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES).firstAngle - normal);
        angles = imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES);
        telemetry.addData("heading", angles.firstAngle);
        /**
        DriveSignal d;
        DriveSignal translate=controlBoard.translate(gyroscope.getAngle());
        DriveSignal turn=controlBoard.turn();
        if(controlBoard.flip()){
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
        d.scale(controlBoard.reduceDriveSpeed());
        drivetrain.setOpenLoop(d);
         **/
        DriveSignal translate=controlBoard.translate();
        DriveSignal turn=controlBoard.turn();
        if(controlBoard.flip()){
            drivetrain.pivot(Constants.Drivetrain.INCH*12,0.5);
            drivetrain.disconnectEncoders();
        }
        if(turn.isZero()){
            drivetrain.setOpenLoop(translate);
        }else if(translate.isZero()){
            drivetrain.setOpenLoop(turn);
        }else{
            drivetrain.setOpenLoop(translate);
            drivetrain.setOpenLoop(turn);
        }
        //elevator.setState(controlBoard.elevatorCommand());
        //subsystemManager.loopSystems();
    }
}
