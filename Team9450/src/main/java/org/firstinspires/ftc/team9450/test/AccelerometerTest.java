package org.firstinspires.ftc.team9450.test;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.robotcore.external.navigation.Acceleration;
import org.firstinspires.ftc.robotcore.external.navigation.Position;
import org.firstinspires.ftc.robotcore.external.navigation.Velocity;
import org.firstinspires.ftc.team9450.sensors.Accelerometer;
import org.firstinspires.ftc.team9450.sensors.Localizer;
import org.firstinspires.ftc.team9450.subsystems.Drivetrain;
import org.firstinspires.ftc.team9450.util.Constants;
import org.firstinspires.ftc.team9450.util.DriveSignal;
import org.firstinspires.ftc.team9450.util.Vector2D;

/**
 * Created by dhruv on 2/10/18.
 */

@Autonomous
public class AccelerometerTest extends LinearOpMode {
    private Localizer imu;
    private Drivetrain drive;

    @Override
    public void runOpMode() throws InterruptedException {
        waitForStart();
        imu = new Localizer(hardwareMap.get(BNO055IMU.class, "imu"));
        drive = new Drivetrain(hardwareMap.dcMotor.get(Constants.Drivetrain.LF), hardwareMap.dcMotor.get(Constants.Drivetrain.LB), hardwareMap.dcMotor.get(Constants.Drivetrain.RF), hardwareMap.dcMotor.get(Constants.Drivetrain.RB));
        Acceleration pos = imu.getPosition();
        telemetry.addData("start", true);
        telemetry.addData("x", pos.xAccel);
        telemetry.addData("y", pos.yAccel);
        telemetry.addData("z,", pos.zAccel);
        telemetry.update();
        drive.setPower(0.5);
        while (opModeIsActive() && drive.getPosition() < 30 * Constants.Drivetrain.INCH){}
        drive.setPower(0);
        //drive.moveFB(-20, 0.3);
        pos = imu.getPosition();
        telemetry.addData("x", pos.xAccel);
        telemetry.addData("y", pos.yAccel);
        telemetry.addData("z,", pos.zAccel);
        telemetry.update();
    }
}
