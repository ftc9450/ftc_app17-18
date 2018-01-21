package org.firstinspires.ftc.team9450.test;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.team9450.subsystems.Drivetrain;
import org.firstinspires.ftc.team9450.subsystems.Intake;
import org.firstinspires.ftc.team9450.subsystems.Ramp;

/**
 * Created by dhruv on 1/20/18.
 */

@TeleOp
public class IntakeDrive extends OpMode {
    private Intake intake;
    private Drivetrain drive;
    private Ramp ramp;
    @Override
    public void init() {
        intake = new Intake(hardwareMap.dcMotor.get("intake_left"), hardwareMap.dcMotor.get("intake_right"));
        drive = new Drivetrain(hardwareMap.dcMotor.get("left_front"), hardwareMap.dcMotor.get("left_back"), hardwareMap.dcMotor.get("right_front"), hardwareMap.dcMotor.get("right_back"));
        ramp = new Ramp(hardwareMap.servo.get("ramp_servo"));
    }

    @Override
    public void loop() {
        float x = gamepad1.left_stick_x;
        float y = gamepad1.left_stick_y;
        float z = gamepad1.right_stick_x;

        drive.setPower(new double[]{x - y + z, -x - y + z, -x - y - z, x - y - z});

        if (gamepad1.left_bumper) intake.setState(Intake.IntakeState.OFF);
        else if (gamepad1.right_bumper) intake.setState(Intake.IntakeState.IN);
        else if (gamepad1.right_trigger > 0.1) intake.setState(Intake.IntakeState.OUT);

        if (gamepad1.a) ramp.setState(Ramp.RampState.IN);
        else if (gamepad1.b) ramp.setState(Ramp.RampState.OUT);

        intake.loop();
        ramp.loop();
    }
}
