package org.firstinspires.ftc.team9450.test;

import com.qualcomm.hardware.bosch.BNO055IMU;
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
public class PathTest extends LinearOpMode {
    Drivetrain drive;
    Gyroscope imu;
    @Override
    public void runOpMode() throws InterruptedException {
        drive = new Drivetrain(hardwareMap.dcMotor.get(Constants.Drivetrain.LF), hardwareMap.dcMotor.get(Constants.Drivetrain.LB), hardwareMap.dcMotor.get(Constants.Drivetrain.RF), hardwareMap.dcMotor.get(Constants.Drivetrain.RB));
        imu = new Gyroscope(hardwareMap.get(BNO055IMU.class, "imu"));
        drive.enableAndResetEncoders();
        waitForStart();
        drive.moveFB(36, 0.3);
        drive.disconnectEncoders();
//        while (imu.getAngle() < Math.PI/2) {
//            telemetry.addData("imu",imu.getAngle());telemetry.update();
//            drive.setOpenLoop(new DriveSignal(1, 1, -1, -1));drive.loop();
//        }
        drive.enableAndResetEncoders();
        drive.moveFB(10, 0.3);
    }
}
