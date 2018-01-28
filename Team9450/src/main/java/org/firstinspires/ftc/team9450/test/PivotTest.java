package org.firstinspires.ftc.team9450.test;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.team9450.sensors.Gyroscope;
import org.firstinspires.ftc.team9450.subsystems.Drivetrain;
import org.firstinspires.ftc.team9450.util.Constants;

/**
 * Created by Grace on 1/28/2018.
 */
@Autonomous
public class PivotTest extends LinearOpMode {
    Drivetrain drivetrain;
    Gyroscope gyroscope;
    @Override
    public void runOpMode() throws InterruptedException {
        drivetrain=new Drivetrain(hardwareMap.dcMotor.get(Constants.Drivetrain.LF), hardwareMap.dcMotor.get(Constants.Drivetrain.LB), hardwareMap.dcMotor.get(Constants.Drivetrain.RF), hardwareMap.dcMotor.get(Constants.Drivetrain.RB));
        gyroscope=new Gyroscope(hardwareMap.get(BNO055IMU.class,"imu"));
        drivetrain.pivotTo(Math.PI/2,gyroscope);
        while(opModeIsActive()){
            telemetry.addData("pos",gyroscope.getAngle());
            telemetry.update();
        }
    }


}
