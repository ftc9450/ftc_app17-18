package org.firstinspires.ftc.team9450.test;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.team9450.sensors.Accelerometer;
import org.firstinspires.ftc.team9450.sensors.Localizer;

/**
 * Created by dhruv on 2/10/18.
 */

public class AccelerometerTest extends OpMode {
    private Localizer imu;
    private DriveTest dt;

    @Override
    public void init() {
        imu = new Localizer(hardwareMap.get(BNO055IMU.class, "imu"));
    }

    @Override
    public void loop() {
        dt.loop();
        telemetry.addData("x", imu.getPosition().x);
        telemetry.addData("y", imu.getPosition().y);
        telemetry.addData("z", imu.getPosition().z);
    }
}
