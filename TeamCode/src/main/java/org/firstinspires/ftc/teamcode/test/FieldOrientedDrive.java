package org.firstinspires.ftc.teamcode.test;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ReadWriteFile;

import org.firstinspires.ftc.robotcore.internal.system.AppUtil;
import org.firstinspires.ftc.teamcode.sensors.Gyroscope;
import org.firstinspires.ftc.teamcode.subsystems.Drivetrain;
import org.firstinspires.ftc.teamcode.subsystems.SubsystemManager;
import org.firstinspires.ftc.teamcode.util.Constants;
import org.firstinspires.ftc.teamcode.util.DriveSignal;

import java.io.File;

/**
 * Created by dhruv on 12/27/17.
 */
@TeleOp
@Disabled
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

        telemetry.addData("signal", null);

        DriveSignal signal;

        File file = AppUtil.getInstance().getSettingsFile("signal_dat.txt");
        String output = "";

        while (opModeIsActive()) {
            double x = gamepad1.left_stick_x;
            double y = gamepad1.left_stick_y;
            double z = gamepad1.right_stick_x;
            double angle = Math.PI * imu.getAngle()/180.0;

            double temp = x;
            x = x*Math.cos(angle) - y*Math.sin(angle);
            y = temp*Math.sin(angle) + y*Math.cos(angle);

            signal = new DriveSignal(x - y + z, -x - y + z, -x - y - z, x - y - z);
            telemetry.addData("signal", signal.toString());
            telemetry.update();
            output += "\n" + signal.toString();
            drive.setOpenLoop(signal);
            manager.loopSystems();
        }
        ReadWriteFile.writeFile(file, output);
    }
}
