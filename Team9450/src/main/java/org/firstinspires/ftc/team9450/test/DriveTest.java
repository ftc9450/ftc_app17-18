package org.firstinspires.ftc.team9450.test;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.team9450.sensors.Gyroscope;
import org.firstinspires.ftc.team9450.subsystems.Drivetrain;
import org.firstinspires.ftc.team9450.subsystems.Intake;
import org.firstinspires.ftc.team9450.subsystems.Ramp;
import org.firstinspires.ftc.team9450.subsystems.RampLifter;
import org.firstinspires.ftc.team9450.subsystems.Rudder;
import org.firstinspires.ftc.team9450.subsystems.SubsystemManager;
import org.firstinspires.ftc.team9450.util.Constants;
import org.firstinspires.ftc.team9450.util.Vector2D;

/**
 * Created by dhruv on 1/20/18.
 */

@TeleOp
public class DriveTest extends OpMode {
    private Drivetrain drive;
    private Gyroscope imu;
    @Override
    public void init() {

        drive = new Drivetrain(hardwareMap.dcMotor.get(Constants.Drivetrain.LF), hardwareMap.dcMotor.get(Constants.Drivetrain.LB), hardwareMap.dcMotor.get(Constants.Drivetrain.RF), hardwareMap.dcMotor.get(Constants.Drivetrain.RB));
        imu = new Gyroscope(hardwareMap.get(BNO055IMU.class, "imu"));
    }

    @Override
    public void loop() {
        Vector2D v = new Vector2D();
        v.x = gamepad1.left_stick_x;
        v.y = -gamepad1.left_stick_y;
        float z = gamepad1.right_stick_x;

        if (gamepad1.right_stick_button) imu = new Gyroscope(hardwareMap.get(BNO055IMU.class, "imu"));

        if (gamepad1.dpad_up || gamepad1.dpad_down || gamepad1.dpad_left || gamepad1.dpad_right) {
            v.x = gamepad1.dpad_right?0.5: gamepad1.dpad_left?-0.5:0;
            v.y = gamepad1.dpad_up?0.5: gamepad1.dpad_down?-0.5:0;
            v.rotate(imu.getAngle());
        }

        drive.setPower(new double[]{v.x + v.y + z, -v.x + v.y + z, -v.x + v.y - z, v.x + v.y - z});
    }
}
