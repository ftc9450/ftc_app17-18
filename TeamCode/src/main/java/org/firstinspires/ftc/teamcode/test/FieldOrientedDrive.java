package org.firstinspires.ftc.teamcode.test;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.sensors.Gyroscope;
import org.firstinspires.ftc.teamcode.subsystems.Drivetrain;
import org.firstinspires.ftc.teamcode.subsystems.SubsystemManager;
import org.firstinspires.ftc.teamcode.util.Constants;
import org.firstinspires.ftc.teamcode.util.DriveSignal;

/**
 * Created by dhruv on 12/27/17.
 */

public class FieldOrientedDrive extends LinearOpMode {
    Drivetrain drive;
    Gyroscope imu;
    SubsystemManager manager;

    @Override
    public void runOpMode() throws InterruptedException {
        drive = new Drivetrain(hardwareMap.dcMotor.get(Constants.Drivetrain.LF), hardwareMap.dcMotor.get(Constants.Drivetrain.LB), hardwareMap.dcMotor.get(Constants.Drivetrain.RF), hardwareMap.dcMotor.get(Constants.Drivetrain.RB));
        imu = new Gyroscope(hardwareMap.get(BNO055IMU.class, "imu"));
        manager = new SubsystemManager();
        manager.add(drive);

        waitForStart();

        DriveSignal signal;

        while (opModeIsActive()) {
            double x = gamepad1.left_stick_x;
            double y = gamepad1.left_stick_y;
            double z = gamepad1.right_stick_x;
            double angle = Math.PI * imu.getAngle()/180.0;

            double temp = x;
            x = x*Math.cos(angle) - y*Math.sin(angle);
            y = temp*Math.sin(angle) + y*Math.cos(angle);

            signal = new DriveSignal(x + y + z, -x + y + z, x - y + z, -x - y + z);
            drive.setOpenLoop(signal);
            manager.loopSystems();
        }
    }
}