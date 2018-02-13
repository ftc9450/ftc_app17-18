package org.firstinspires.ftc.team9450.setup;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.team9450.subsystems.Drivetrain;
import org.firstinspires.ftc.team9450.util.Constants;

/**
 * Created by dhruv on 1/22/18.
 */

public class InchCalibration extends OpMode {
    public Drivetrain drive;

    @Override
    public void init() {
        drive = new Drivetrain(hardwareMap.dcMotor.get(Constants.Drivetrain.LF), hardwareMap.dcMotor.get(Constants.Drivetrain.LB), hardwareMap.dcMotor.get(Constants.Drivetrain.RF), hardwareMap.dcMotor.get(Constants.Drivetrain.RB));
        drive.enableAndResetEncoders();
    }

    @Override
    public void loop() {
        if (gamepad1.a) {
            drive.moveFB(10, 0.5);
        } else if (gamepad1.b) {
            drive.moveFB(-10, 0.5);
        } else if (gamepad1.x) {
            drive.moveFB(100, 0.5);
        } else if (gamepad1.y) {
            drive.moveFB(-100, 0.5);
        }
        String vals = "";
        vals+=drive.getPosition();
        telemetry.addData("pos", vals);
    }
}
