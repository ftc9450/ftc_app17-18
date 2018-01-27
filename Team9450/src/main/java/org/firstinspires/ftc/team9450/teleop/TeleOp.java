package org.firstinspires.ftc.team9450.teleop;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.team9450.sensors.Gyroscope;
import org.firstinspires.ftc.team9450.subsystems.Drivetrain;
import org.firstinspires.ftc.team9450.subsystems.Intake;
import org.firstinspires.ftc.team9450.subsystems.Ramp;
import org.firstinspires.ftc.team9450.subsystems.RelicArm;
import org.firstinspires.ftc.team9450.subsystems.SubsystemManager;
import org.firstinspires.ftc.team9450.util.Constants;
import org.firstinspires.ftc.team9450.util.DriveSignal;

/**
 * Created by dhruv on 1/27/18.
 */

@com.qualcomm.robotcore.eventloop.opmode.TeleOp
public class TeleOp extends OpMode {
    Drivetrain drive;
    Gyroscope imu;
    Intake intake;
    Ramp ramp;
    RelicArm arm;
    SubsystemManager manager;

    @Override
    public void init() {
        drive = new Drivetrain(hardwareMap.dcMotor.get(Constants.Drivetrain.LF), hardwareMap.dcMotor.get(Constants.Drivetrain.LB), hardwareMap.dcMotor.get(Constants.Drivetrain.RF), hardwareMap.dcMotor.get(Constants.Drivetrain.RB));
        imu = new Gyroscope(hardwareMap.get(BNO055IMU.class, "imu"));
        intake = new Intake(hardwareMap.dcMotor.get("intake_left"), hardwareMap.dcMotor.get("intake_right"));
        ramp = new Ramp(hardwareMap.servo.get("ramp"), hardwareMap.dcMotor.get("ramp_lifter"));
        arm = new RelicArm(hardwareMap.dcMotor.get("arm"), hardwareMap.servo.get("pivot"), hardwareMap.crservo.get("hand"));
        manager = new SubsystemManager();
        manager.add(drive).add(intake).add(ramp).add(arm);
    }

    @Override
    public void loop() {
        double x = Constants.floatToDouble(gamepad1.left_stick_x); double y=-1.0*Constants.floatToDouble(gamepad1.left_stick_y);
        telemetry.addData("xpos",x);telemetry.addData("ypos", y);
        DriveSignal driveSignal=DriveSignal.translate(Math.atan2(y,x),Math.sqrt(Math.pow(x,2)+Math.pow(y,2)));
        drive.setOpenLoop(driveSignal);

        if (gamepad1.right_bumper) intake.setState(Intake.IntakeState.IN);
        else if (gamepad1.left_bumper) intake.setState(Intake.IntakeState.OUT);
        else intake.setState(Intake.IntakeState.OFF);

        if (gamepad1.left_trigger > 0.1) ramp.setRampState(Ramp.RampState.IN);
        else if (gamepad1.right_trigger > 0.1) ramp.setRampState(Ramp.RampState.OUT);

        if (gamepad2.right_trigger > 0.1) {
            arm.setArm(RelicArm.ArmState.OUT);
        } else if (gamepad2.left_trigger > 0.1) {
            arm.setArm(RelicArm.ArmState.IN);
        } else {
            arm.setArm(RelicArm.ArmState.OFF);
        }

        if (gamepad2.right_bumper) {
            arm.setStandardpivot(RelicArm.PivotState.OUT);
        } else if (gamepad2.left_bumper) {
            arm.setStandardpivot(RelicArm.PivotState.IN);
        } else {
            arm.setStandardpivot(RelicArm.PivotState.OFF);
        }

        if (gamepad2.a) {
            arm.setCrhand(RelicArm.HandState.OPEN);
        } else if (gamepad2.b) {
            arm.setCrhand(RelicArm.HandState.CLOSE);
        }else{
            arm.setCrhand(RelicArm.HandState.OFF);
        }

        manager.loop();
    }
}
