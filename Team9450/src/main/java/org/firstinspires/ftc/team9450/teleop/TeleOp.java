package org.firstinspires.ftc.team9450.teleop;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

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
    Servo hand;
    Servo pivot;

    @Override
    public void init() {
        drive = new Drivetrain(hardwareMap.dcMotor.get(Constants.Drivetrain.LF), hardwareMap.dcMotor.get(Constants.Drivetrain.LB), hardwareMap.dcMotor.get(Constants.Drivetrain.RF), hardwareMap.dcMotor.get(Constants.Drivetrain.RB));
        imu = new Gyroscope(hardwareMap.get(BNO055IMU.class, "imu"));
        intake = new Intake(hardwareMap.dcMotor.get("intake_left"), hardwareMap.dcMotor.get("intake_right"));
        ramp = new Ramp(hardwareMap.servo.get("ramp"), hardwareMap.dcMotor.get("lift"), hardwareMap.digitalChannel.get("touch"));
        arm = new RelicArm(hardwareMap.dcMotor.get("arm"), hardwareMap.servo.get("pivot"), hardwareMap.servo.get("hand"));
        rudder = new Rudder(hardwareMap.servo.get(Constants.Rudder.RUDDERTOP), hardwareMap.servo.get(Constants.Rudder.RUDDERBOTTOM),hardwareMap.colorSensor.get(Constants.Rudder.COLOR));
        manager = new SubsystemManager();
        manager.add(drive).add(ramp).add(rudder);//.add(arm);
        hand = hardwareMap.servo.get("hand");
        pivot = hardwareMap.servo.get("pivot");
    }

    @Override
    public void loop() {
        double x = Constants.floatToDouble(gamepad1.left_stick_x); double y=-1.0*Constants.floatToDouble(gamepad1.left_stick_y);
        DriveSignal driveSignal = new DriveSignal(0, 0, 0, 0);

        Vector2D v = new Vector2D();
        v.x = gamepad1.left_stick_x + (gamepad1.dpad_left? -0.5: gamepad1.dpad_right? 0.5:0);
        v.y = -gamepad1.left_stick_y + (gamepad1.dpad_down? -0.5: gamepad1.dpad_up? 0.5:0);
        float z = gamepad1.right_stick_x + (gamepad1.right_trigger - gamepad1.left_trigger)/2;
        driveSignal.changeSignal(new DriveSignal(v.x + v.y + z, -v.x + v.y + z, -v.x + v.y - z, v.x + v.y - z));
        drive.setOpenLoop(driveSignal.value());

        if (gamepad1.right_bumper) intake.setPower(-1);
        else if (gamepad1.left_bumper) intake.setPower(1);
        else if (gamepad2.right_bumper) intake.setPower(-0.5);
        else if (gamepad2.left_bumper) intake.setPower(0.5);
        else intake.setPower(0);

        if (gamepad2.left_trigger > 0.25 && gamepad2.right_trigger > 0.25) {
            ramp.setRampState(Ramp.RampState.LEVEL);
        } else if (gamepad2.left_trigger > 0.5) {
            ramp.setRampState(Ramp.RampState.IN);
        }  else if (gamepad2.right_trigger > 0.5) {
            ramp.setRampState(Ramp.RampState.OUT);
        }
        arm.setPower(-gamepad2.left_stick_y);

        if (gamepad2.right_stick_y < -0.5&&pivot.getPosition()<0.9) {
            pivot.setPosition(pivot.getPosition()+0.01);
            //arm.setStandardpivot(RelicArm.PivotState.OUT);
        } else if (gamepad2.right_stick_y > 0.5&&pivot.getPosition()>0.1) {
            pivot.setPosition(pivot.getPosition()-0.01);
            //arm.setStandardpivot(RelicArm.PivotState.IN);
        }

        /*if (gamepad2.a) {
            arm.setCrhand(RelicArm.HandState.OPEN);
        } else if (gamepad2.b) {
            arm.setCrhand(RelicArm.HandState.CLOSE);
        }*/

        if (gamepad2.a) hand.setPosition(hand.getPosition()+0.1);
        else if (gamepad2.b) hand.setPosition(hand.getPosition()-0.1);


        if (gamepad2.dpad_up) {
            ramp.setLiftState(Ramp.LiftState.UP);
        } else if (gamepad2.dpad_down) {
            ramp.setLiftState(Ramp.LiftState.DOWN);
        } else {
            ramp.setLiftState(Ramp.LiftState.OFF);
        }

        rudder.setLateralState(Rudder.LateralState.NEUTRAL);
        rudder.setRudderState(Rudder.RudderState.IN);
        manager.loop();
    }
}