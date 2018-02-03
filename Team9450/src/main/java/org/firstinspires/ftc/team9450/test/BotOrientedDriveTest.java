package org.firstinspires.ftc.team9450.test;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.team9450.sensors.Gyroscope;
import org.firstinspires.ftc.team9450.subsystems.Drivetrain;
import org.firstinspires.ftc.team9450.subsystems.SubsystemManager;
import org.firstinspires.ftc.team9450.util.Constants;
import org.firstinspires.ftc.team9450.util.DriveSignal;
import org.firstinspires.ftc.team9450.util.Vector2D;

/**
 * Created by dhruv on 1/20/18.
 */

@TeleOp
@Disabled
public class BotOrientedDriveTest extends OpMode {
    private Drivetrain drive;
    private Gyroscope imu;
    SubsystemManager subsystemManager=new SubsystemManager();
    @Override
    public void init() {
        drive = new Drivetrain(hardwareMap.dcMotor.get(Constants.Drivetrain.LF), hardwareMap.dcMotor.get(Constants.Drivetrain.LB), hardwareMap.dcMotor.get(Constants.Drivetrain.RF), hardwareMap.dcMotor.get(Constants.Drivetrain.RB));
        subsystemManager.add(drive);
        imu = new Gyroscope(hardwareMap.get(BNO055IMU.class, "imu"));
    }

    @Override
    public void loop() {
        double x=Constants.floatToDouble(gamepad1.left_stick_x); double y=-1.0*Constants.floatToDouble(gamepad1.left_stick_y);
        telemetry.addData("xpos",x);telemetry.addData("ypos", y);
        DriveSignal driveSignal=DriveSignal.translate(Math.atan2(y,x),Math.sqrt(Math.pow(x,2)+Math.pow(y,2)));
        drive.setOpenLoop(driveSignal);
        subsystemManager.loop();
    }
}
