package org.firstinspires.ftc.teamcode.test;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.robotcore.external.Const;
import org.firstinspires.ftc.teamcode.control.ControlBoard;
import org.firstinspires.ftc.teamcode.control.GameBoard;
import org.firstinspires.ftc.teamcode.subsystems.Drivetrain;
import org.firstinspires.ftc.teamcode.subsystems.SubsystemManager;
import org.firstinspires.ftc.teamcode.util.Constants;
import org.firstinspires.ftc.teamcode.util.DriveSignal;

/**
 * Created by Grace on 11/22/2017.
 */
@TeleOp
@Disabled
public class DriverControllerStupidity extends OpMode {
    GameBoard gb;
    Drivetrain drivetrain;
    SubsystemManager subsystemManager=new SubsystemManager();
    public void init() {
        gb = new GameBoard(gamepad1);
        drivetrain=new Drivetrain(hardwareMap.dcMotor.get(Constants.Drivetrain.LF), hardwareMap.dcMotor.get(Constants.Drivetrain.LB), hardwareMap.dcMotor.get(Constants.Drivetrain.RF), hardwareMap.dcMotor.get(Constants.Drivetrain.RB));
        subsystemManager.add(drivetrain);
    }
    public boolean reduceDriveSpeed() {return gamepad1.left_stick_button||gamepad1.right_stick_button;}
    @Override
    public void loop() {
        float x=gamepad1.left_stick_x;float y=gamepad1.left_stick_y;
        DriveSignal d;
        DriveSignal translate=translate(x,y).scale(throttle(x,y));
        DriveSignal turn=turn(gamepad1.right_stick_x);
        if(turn.isZero()){
            d=translate;
        }else if(translate.isZero()){
            d=turn;
        }else{
            d=DriveSignal.average(translate,turn);
        }
        if(reduceDriveSpeed()){
            d.scale(Constants.Drivetrain.LOW_POWER);
        }else{
            d.scale(Constants.Drivetrain.HIGH_POWER);
        }
        drivetrain.setOpenLoop(d);
        subsystemManager.loopSystems();
    }
    public DriveSignal turn(float a){
        return DriveSignal.pivot(a);
    }
    public double throttle(float a, float b){
        Double x=Constants.floatToDouble(a); double y=Constants.floatToDouble(b);
        return Math.pow(Math.pow(x,2)+Math.pow(y,2),0.5);}
    public DriveSignal translate(float a, float b){
        double x=Constants.floatToDouble(a);double y=Constants.floatToDouble(b);
        if(x!=0) {
            double angle = Math.atan2(y, x) - Math.PI / 4;
            return new DriveSignal(Math.sin(angle), Math.cos(angle), Math.cos(angle), Math.sin(angle));
        }else{
            if(y>0){
                return new DriveSignal(1,1,1,1);
            }else if(y<0){
                return new DriveSignal(-1,-1,-1,-1);
            }return new DriveSignal(0,0,0,0);
        }
    }
}
