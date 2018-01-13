package org.firstinspires.ftc.teamcode.test;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.subsystems.Drivetrain;
import org.firstinspires.ftc.teamcode.util.Constants;

/**
 * Created by Grace on 1/13/2018.
 */
@TeleOp
public class DriveTrainCalibration extends OpMode{
    Drivetrain drivetrain;
    int FBCt=0;
    int LRCt=0;
    @Override
    public void init() {
        drivetrain=new Drivetrain(hardwareMap.dcMotor.get(Constants.Drivetrain.LF), hardwareMap.dcMotor.get(Constants.Drivetrain.LB), hardwareMap.dcMotor.get(Constants.Drivetrain.RF), hardwareMap.dcMotor.get(Constants.Drivetrain.RB));
    }

    @Override
    public void loop() {
        if(gamepad1.left_bumper){
            FBCt=0;
            LRCt=0;
        }
        if(gamepad1.y){
            FBCt+=10;
            drivetrain.moveFB(10,0.5);
        }
        if(gamepad1.x){
            LRCt+=10;
            drivetrain.moveLR(10,0.5);
        }
        telemetry.addData("forward/backwards encoders",FBCt);
        telemetry.addData("left/right encoders",LRCt);

    }
}
