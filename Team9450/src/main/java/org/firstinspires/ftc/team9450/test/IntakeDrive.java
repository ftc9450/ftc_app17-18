package org.firstinspires.ftc.team9450.test;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.team9450.sensors.Gyroscope;
import org.firstinspires.ftc.team9450.subsystems.Drivetrain;
import org.firstinspires.ftc.team9450.subsystems.Intake;
import org.firstinspires.ftc.team9450.subsystems.Ramp;
import org.firstinspires.ftc.team9450.util.Vector2D;

/**
 * Created by dhruv on 1/20/18.
 */

@TeleOp
public class IntakeDrive extends OpMode {
    private Intake intake;
    private Drivetrain drive;
    private Ramp ramp;
    private Gyroscope imu;
    @Override
    public void init() {
        intake = new Intake(hardwareMap.dcMotor.get("intake_left"), hardwareMap.dcMotor.get("intake_right"));
        drive = new Drivetrain(hardwareMap.dcMotor.get("left_front"), hardwareMap.dcMotor.get("left_back"), hardwareMap.dcMotor.get("right_front"), hardwareMap.dcMotor.get("right_back"));
        ramp = new Ramp(hardwareMap.servo.get("ramp_servo"));
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

        if (gamepad1.left_bumper) intake.setState(Intake.IntakeState.OFF);
        else if (gamepad1.right_bumper) intake.setState(Intake.IntakeState.IN);
        else if (gamepad1.right_trigger > 0.1) intake.setState(Intake.IntakeState.OUT);

        if (gamepad1.a) ramp.setState(Ramp.RampState.IN);
        else if (gamepad1.b) ramp.setState(Ramp.RampState.OUT);

        intake.loop();
        ramp.loop();
    }
}
