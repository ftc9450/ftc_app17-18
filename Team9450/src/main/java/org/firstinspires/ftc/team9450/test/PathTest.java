package org.firstinspires.ftc.team9450.test;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.team9450.sensors.Gyroscope;
import org.firstinspires.ftc.team9450.subsystems.Drivetrain;
import org.firstinspires.ftc.team9450.util.Constants;
import org.firstinspires.ftc.team9450.util.DriveSignal;

/**
 * Created by dhruv on 1/28/18.
 */

@Autonomous
@Disabled
public class PathTest extends LinearOpMode {
    Drivetrain drive;
    Gyroscope imu;
    @Override
    public void runOpMode() throws InterruptedException {
        drive = new Drivetrain(hardwareMap.dcMotor.get(Constants.Drivetrain.LF), hardwareMap.dcMotor.get(Constants.Drivetrain.LB), hardwareMap.dcMotor.get(Constants.Drivetrain.RF), hardwareMap.dcMotor.get(Constants.Drivetrain.RB));
        drive.enableAndResetEncoders();
        waitForStart();
        drive.moveFB(10, 1);
        drive.disconnectEncoders();
        while (imu.getAngle() < Math.PI/2) {
            drive.setOpenLoop(new DriveSignal(1, 1, -1, -1));
        }
        drive.enableAndResetEncoders();
        drive.moveFB(10, 1);
    }
}
