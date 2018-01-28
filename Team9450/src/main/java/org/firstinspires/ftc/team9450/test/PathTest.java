package org.firstinspires.ftc.team9450.test;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.team9450.subsystems.Drivetrain;
import org.firstinspires.ftc.team9450.util.Constants;

/**
 * Created by dhruv on 1/28/18.
 */

public class PathTest extends LinearOpMode {
    Drivetrain drive;
    @Override
    public void runOpMode() throws InterruptedException {
        drive = new Drivetrain(hardwareMap.dcMotor.get(Constants.Drivetrain.LF), hardwareMap.dcMotor.get(Constants.Drivetrain.LB), hardwareMap.dcMotor.get(Constants.Drivetrain.RF), hardwareMap.dcMotor.get(Constants.Drivetrain.RB));
        drive.enableAndResetEncoders();
        waitForStart();
        drive.moveFB(10, 1);
        drive.pivot(45, 1);
        drive.moveFB(10, 1);
    }
}
