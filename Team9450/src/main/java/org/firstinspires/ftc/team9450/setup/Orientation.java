package org.firstinspires.ftc.team9450.setup;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.team9450.sensors.Gyroscope;

/**
 * Created by dhruv on 1/27/18.
 */

@Autonomous
public class Orientation extends OpMode {
    Gyroscope imu;
    @Override
    public void init() {
        imu = new Gyroscope(hardwareMap.get(BNO055IMU.class, "imu"));
    }

    @Override
    public void loop() {
        telemetry.addData("angle", 180*imu.getAngle()/Math.PI);
    }
}
