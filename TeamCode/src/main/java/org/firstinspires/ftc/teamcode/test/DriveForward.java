package org.firstinspires.ftc.teamcode.test;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.hardware.bosch.JustLoggingAccelerationIntegrator;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;
import org.firstinspires.ftc.teamcode.sensors.Gyroscope;
import org.firstinspires.ftc.teamcode.subsystems.Drivetrain;
import org.firstinspires.ftc.teamcode.subsystems.SubsystemManager;
import org.firstinspires.ftc.teamcode.util.Constants;
import org.firstinspires.ftc.teamcode.util.DriveSignal;

/**
 * Created by dhruv on 12/16/17.
 */

@TeleOp
@Disabled
@Deprecated
public class DriveForward extends OpMode {
    private Drivetrain drive;
    //private BNO055IMU imu;
    //Orientation angles;
    private Gyroscope imu;
    SubsystemManager manager = new SubsystemManager();

    @Override
    public void init() {
        /*BNO055IMU.Parameters parameters = new BNO055IMU.Parameters();
        parameters.angleUnit    = BNO055IMU.AngleUnit.DEGREES;
        parameters.calibrationDataFile  = "IMUCalibration.json";
        parameters.loggingEnabled       = true;
        parameters.loggingTag           = "IMU";

        imu = hardwareMap.get(BNO055IMU.class, "imu");
        imu.initialize(parameters);*/
        imu = new Gyroscope(hardwareMap.get(BNO055IMU.class, "imu"));

        drive = new Drivetrain(hardwareMap.dcMotor.get(Constants.Drivetrain.LF), hardwareMap.dcMotor.get(Constants.Drivetrain.LB), hardwareMap.dcMotor.get(Constants.Drivetrain.RF), hardwareMap.dcMotor.get(Constants.Drivetrain.RB));
        manager.add(drive);
    }

    @Override
    public void loop() {
        DriveSignal signal = new DriveSignal(0, 0,0 ,0);
        double angle;

        float L[] = {gamepad1.left_stick_x, gamepad1.left_stick_y};
        float R[] = {gamepad1.right_stick_x, gamepad1.right_stick_y};

        //angles = imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES);
        //angle = AngleUnit.DEGREES.normalize(AngleUnit.DEGREES.fromUnit(angles.angleUnit, angles.firstAngle));
        angle = imu.getAngle();

        if (R[0] != 0) {
            if (R[0] > 0) signal = new DriveSignal(1, 1, -1, -1);
            else signal = new DriveSignal(-1, -1, 1, 1);
        } else if (L[1] < 0){
            double a = Math.cos(angle) + Math.sin(angle);
            double b = Math.cos(angle) - Math.sin(angle);
            signal = new DriveSignal(a, b, b, a);
            telemetry.addData("a", a);
            telemetry.addData("b", b);
        }
        drive.setOpenLoop(signal);
        telemetry.addData("heading", angle);
        manager.loopSystems();
    }
}
