package org.firstinspires.ftc.team9450.teleop;

import com.qualcomm.robotcore.eventloop.opmode.*;
import com.qualcomm.robotcore.hardware.CRServo;

import org.firstinspires.ftc.team9450.sensors.Gyroscope;
import org.firstinspires.ftc.team9450.subsystems.Drivetrain;
import org.firstinspires.ftc.team9450.subsystems.Intake;
import org.firstinspires.ftc.team9450.subsystems.Ramp;
import org.firstinspires.ftc.team9450.subsystems.RelicArm;
import org.firstinspires.ftc.team9450.subsystems.Rudder;
import org.firstinspires.ftc.team9450.subsystems.SubsystemManager;
import org.firstinspires.ftc.team9450.util.Constants;
import org.firstinspires.ftc.team9450.util.DriveSignal;
import org.firstinspires.ftc.team9450.util.Vector2D;

/**
 * Created by dhruv on 2/3/18.
 */

@com.qualcomm.robotcore.eventloop.opmode.TeleOp
public class Minimalistic extends OpMode {
    Drivetrain drive;
    Intake intake;
    Ramp ramp;
    SubsystemManager manager;

    @Override
    public void init() {
        drive = new Drivetrain(hardwareMap.dcMotor.get(Constants.Drivetrain.LF), hardwareMap.dcMotor.get(Constants.Drivetrain.LB), hardwareMap.dcMotor.get(Constants.Drivetrain.RF), hardwareMap.dcMotor.get(Constants.Drivetrain.RB));
        intake = new Intake(hardwareMap.dcMotor.get("intake_left"), hardwareMap.dcMotor.get("intake_right"));
        ramp = new Ramp(hardwareMap.servo.get("ramp"), hardwareMap.dcMotor.get("lift"));
        manager = new SubsystemManager();
        manager.add(drive).add(intake).add(ramp);
    }

    @Override
    public void loop() {
        double x = Constants.floatToDouble(gamepad1.left_stick_x); double y=-1.0*Constants.floatToDouble(gamepad1.left_stick_y);
        telemetry.addData("xpos",x);telemetry.addData("ypos", y);
        DriveSignal driveSignal = new DriveSignal(0, 0, 0, 0);

        Vector2D v = new Vector2D();
        v.x = gamepad1.left_stick_x + (gamepad1.dpad_left? -0.5: gamepad1.dpad_right? 0.5:0);
        v.y = -gamepad1.left_stick_y + (gamepad1.dpad_down? -0.5: gamepad1.dpad_up? 0.5:0);
        float z = gamepad1.right_stick_x + (gamepad1.right_trigger - gamepad1.left_trigger)/2;
        driveSignal = new DriveSignal(v.x + v.y + z, -v.x + v.y + z, -v.x + v.y - z, v.x + v.y - z);
        drive.setOpenLoop(driveSignal);

        if (gamepad1.right_bumper || gamepad2.right_bumper) intake.setState(Intake.IntakeState.IN);
        else if (gamepad1.left_bumper || gamepad2.left_bumper) intake.setState(Intake.IntakeState.OUT);
        else intake.setState(Intake.IntakeState.OFF);

        //ramp.setPower(gamepad2.right_trigger);

        if (gamepad2.left_trigger > 0.25 && gamepad2.right_trigger > 0.25) {
            ramp.setRampState(Ramp.RampState.LEVEL);
        } else if (gamepad2.left_trigger > 0.5) {
            ramp.setRampState(Ramp.RampState.IN);
        }  else if (gamepad2.right_trigger > 0.5) {
            ramp.setRampState(Ramp.RampState.OUT);
        }

        if (gamepad2.dpad_up) {
            ramp.setLiftState(Ramp.LiftState.UP);
        } else if (gamepad2.dpad_down) {
            ramp.setLiftState(Ramp.LiftState.DOWN);
        } else {
            ramp.setLiftState(Ramp.LiftState.OFF);
        }

        manager.loop();
    }
}
