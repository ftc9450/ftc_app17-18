package org.firstinspires.ftc.team9450.sensors;

import com.qualcomm.hardware.bosch.BNO055IMU;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;

/**
 * Created by dhruv on 1/22/18.
 */

public class Gyroscope {
    private BNO055IMU imu;
    Orientation angles;

    public Gyroscope(BNO055IMU imu) {
        BNO055IMU.Parameters parameters = new BNO055IMU.Parameters();
        parameters.angleUnit    = BNO055IMU.AngleUnit.RADIANS;
        parameters.calibrationDataFile  = "IMUCalibration.json";
        parameters.loggingEnabled       = true;
        parameters.loggingTag           = "IMU";

        this.imu = imu;

        this.imu.initialize(parameters);
    }

    public float getAngle() {
        angles = imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.RADIANS);
        return AngleUnit.RADIANS.normalize(AngleUnit.RADIANS.fromUnit(angles.angleUnit, angles.firstAngle));
    }

    public String getSystemStatus() {
        return imu.getSystemStatus().toString();
    }

    public String getCalibrationStatus() {
        return imu.getCalibrationStatus().toString();
    }
}