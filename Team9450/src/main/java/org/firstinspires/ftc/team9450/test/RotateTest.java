package org.firstinspires.ftc.team9450.test;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.team9450.subsystems.Drivetrain;
import org.firstinspires.ftc.team9450.util.Constants;

/**
 * Created by dhruv on 1/28/18.
 */

@Autonomous
@Disabled
public class RotateTest extends LinearOpMode {
    Drivetrain drive;
    @Override
    public void runOpMode() throws InterruptedException {
        drive = new Drivetrain(hardwareMap.dcMotor.get(Constants.Drivetrain.LF), hardwareMap.dcMotor.get(Constants.Drivetrain.LB), hardwareMap.dcMotor.get(Constants.Drivetrain.RF), hardwareMap.dcMotor.get(Constants.Drivetrain.RB));
        waitForStart();
        drive.enableAndResetEncoders();
        drive.disconnectEncoders();
        while (opModeIsActive()) {
            drive.setPower(new double[]{0.3, 0.3, -0.3, -0.3});
        }
        drive.enableAndResetEncoders();
    }
}
