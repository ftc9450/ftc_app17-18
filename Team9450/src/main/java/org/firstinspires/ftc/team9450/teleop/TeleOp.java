package org.firstinspires.ftc.team9450.teleop;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

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
 * Created by dhruv on 1/27/18.
 */

@com.qualcomm.robotcore.eventloop.opmode.TeleOp
public class TeleOp extends OpMode {
    Drivetrain drive;
    Gyroscope imu;
    Intake intake;
    Ramp ramp;
    RelicArm arm;
    Rudder rudder;
    SubsystemManager manager;

    @Override
    public void init() {
        drive = new Drivetrain(hardwareMap.dcMotor.get(Constants.Drivetrain.LF), hardwareMap.dcMotor.get(Constants.Drivetrain.LB), hardwareMap.dcMotor.get(Constants.Drivetrain.RF), hardwareMap.dcMotor.get(Constants.Drivetrain.RB));
        imu = new Gyroscope(hardwareMap.get(BNO055IMU.class, "imu"));
        intake = new Intake(hardwareMap.dcMotor.get("intake_left"), hardwareMap.dcMotor.get("intake_right"));
        ramp = new Ramp(hardwareMap.servo.get("ramp"), hardwareMap.dcMotor.get("ramp_lifter"));
        arm = new RelicArm(hardwareMap.dcMotor.get("arm"), hardwareMap.servo.get("pivot"), hardwareMap.crservo.get("hand"));
        rudder = new Rudder(hardwareMap.servo.get(Constants.Rudder.RUDDERTOP), hardwareMap.servo.get(Constants.Rudder.RUDDERBOTTOM),hardwareMap.colorSensor.get(Constants.Rudder.COLOR));
        manager = new SubsystemManager();
        manager.add(drive).add(intake).add(ramp).add(arm).add(rudder);
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
        /*if (gamepad1.right_stick_x != 0) {
            driveSignal = new DriveSignal(v.x + v.y + z, -v.x + v.y + z, -v.x + v.y - z, v.x + v.y - z);
        } else {
            driveSignal = DriveSignal.translate(Math.atan2(y,x),Math.sqrt(Math.pow(x,2)+Math.pow(y,2)));
        }*/

        drive.setOpenLoop(driveSignal);

        if (gamepad1.right_bumper) intake.setState(Intake.IntakeState.IN);
        else if (gamepad1.left_bumper) intake.setState(Intake.IntakeState.OUT);
        else intake.setState(Intake.IntakeState.OFF);

        if (gamepad2.left_trigger > 0.25 && gamepad2.right_trigger > 0.25) {
            ramp.setRampState(Ramp.RampState.LEVEL);
        } else if (gamepad2.left_trigger > 0.5) {
            ramp.setRampState(Ramp.RampState.IN);
        }  else if (gamepad1.right_trigger > 0.5) {
            ramp.setRampState(Ramp.RampState.OUT);
        }

        /*if (gamepad2.left_stick_y < -0.1) {
            arm.setArm(RelicArm.ArmState.OUT);
        } else if (gamepad2.left_stick_y > 0.1) {
            arm.setArm(RelicArm.ArmState.IN);
        } else {
            arm.setArm(RelicArm.ArmState.OFF);
        }*/

        arm.setPower(-gamepad2.left_stick_y);

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

        if (gamepad2.right_stick_y < -0.5) {
            ramp.setLiftState(Ramp.LiftState.UP);
        } else if (gamepad2.right_stick_y > 0.5) {
            ramp.setLiftState(Ramp.LiftState.DOWN);
        } else {
            ramp.setLiftState(Ramp.LiftState.OFF);
        }

        rudder.setLateralState(Rudder.LateralState.NEUTRAL);
        rudder.setRudderState(Rudder.RudderState.START);

        manager.loop();
    }
}