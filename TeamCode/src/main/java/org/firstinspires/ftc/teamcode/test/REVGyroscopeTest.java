package org.firstinspires.ftc.teamcode.test;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.hardware.bosch.JustLoggingAccelerationIntegrator;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.robotcore.external.navigation.Acceleration;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;
import org.firstinspires.ftc.robotcore.external.navigation.Position;
import org.firstinspires.ftc.robotcore.external.navigation.Velocity;
import org.firstinspires.ftc.teamcode.sensors.Gyroscope;

/**
 * Created by dhruv on 11/29/17.
 */

@Autonomous
public class REVGyroscopeTest extends OpMode {
    //BNO055IMU imu;
    //Orientation angles;
    Gyroscope imu;

    @Override
    public void init() {
        /*BNO055IMU.Parameters parameters = new BNO055IMU.Parameters();
        parameters.angleUnit            = BNO055IMU.AngleUnit.DEGREES;
        parameters.accelUnit            = BNO055IMU.AccelUnit.METERS_PERSEC_PERSEC;
        parameters.calibrationDataFile  = "IMUCalibration.json";
        parameters.loggingEnabled       = true;
        parameters.loggingTag           = "IMU";
        parameters.accelerationIntegrationAlgorithm = new JustLoggingAccelerationIntegrator();

        imu = hardwareMap.get(BNO055IMU.class, "imu");
        imu.initialize(parameters);*/

        imu = new Gyroscope(hardwareMap.get(BNO055IMU.class, "imu"));
    }

    @Override
    public void loop() {
        /*angles = imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES);*/

        telemetry.addData("status", imu.getSystemStatus());
        telemetry.addData("calibration", imu.getCalibrationStatus().toString());
        telemetry.addData("heading", imu.getAngle());
    }
}
