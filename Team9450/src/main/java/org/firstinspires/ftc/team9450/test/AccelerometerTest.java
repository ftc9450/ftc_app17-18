package org.firstinspires.ftc.team9450.test;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.team9450.sensors.Accelerometer;

/**
 * Created by dhruv on 2/10/18.
 */
@TeleOp
public class AccelerometerTest extends OpMode {
    private Accelerometer imu;
    private DriveTest dt;

    @Override
    public void init() {
        dt = new DriveTest(hardwareMap,gamepad1);//Dhruv here are your spaces. -Grace
        dt.init();
        imu = new Accelerometer(hardwareMap.get(BNO055IMU.class, "imu"));
    }

    @Override
    public void loop() {
        dt.loop();
        telemetry.addData("x", imu.getPosition().x);
        telemetry.addData("y", imu.getPosition().y);
        telemetry.addData("z", imu.getPosition().z);
    }
}
