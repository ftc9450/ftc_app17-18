package org.firstinspires.ftc.teamcode.teleop;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.eventloop.opmode.*;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.sensors.Gyroscope;
import org.firstinspires.ftc.teamcode.subsystems.Drivetrain;
import org.firstinspires.ftc.teamcode.subsystems.Elevator;
import org.firstinspires.ftc.teamcode.subsystems.Grabber;
import org.firstinspires.ftc.teamcode.subsystems.RelicArm;
import org.firstinspires.ftc.teamcode.subsystems.SubsystemManager;
import org.firstinspires.ftc.teamcode.util.Constants;
import org.firstinspires.ftc.teamcode.util.DriveSignal;

/**
 * Created by dhruv on 12/28/17.
 */

@com.qualcomm.robotcore.eventloop.opmode.TeleOp
public class TeleOp2 extends LinearOpMode{
    Drivetrain drive;
    Elevator elevator;
    Grabber grabber[];
    RelicArm arm;
    Gyroscope imu;
    SubsystemManager manager;

    @Override
    public void runOpMode() throws InterruptedException {
        drive = new Drivetrain(hardwareMap.dcMotor.get(Constants.Drivetrain.LF), hardwareMap.dcMotor.get(Constants.Drivetrain.LB), hardwareMap.dcMotor.get(Constants.Drivetrain.RF), hardwareMap.dcMotor.get(Constants.Drivetrain.RB));
        imu = new Gyroscope(hardwareMap.get(BNO055IMU.class, "imu"));
        elevator = new Elevator(hardwareMap.dcMotor.get(Constants.Elevator.ELEVATOR));
        grabber = new Grabber[]{new Grabber(hardwareMap.servo.get(Constants.Grabber.LT),hardwareMap.servo.get(Constants.Grabber.RT)),
                new Grabber(hardwareMap.servo.get(Constants.Grabber.LB),hardwareMap.servo.get(Constants.Grabber.RB))};
        manager = new SubsystemManager();
        manager.add(drive);
        for (Grabber g: grabber) manager.add(g);

        waitForStart();

        DriveSignal signal;

        boolean flip = false;
        double flipAngle = 0;

        while (opModeIsActive()) {
            if (gamepad1.y) imu = new Gyroscope(hardwareMap.get(BNO055IMU.class, "imu"));

            double x = gamepad1.left_stick_x + (gamepad1.dpad_left?-0.5:0) + (gamepad1.dpad_right?0.5:0);
            double y = gamepad1.left_stick_y + (gamepad1.dpad_down?0.5:0) + (gamepad1.dpad_up?-0.5:0);
            double z = gamepad1.right_stick_x + gamepad1.right_trigger/2 - gamepad1.left_trigger/2;
            double angle = Math.PI * imu.getAngle()/180.0;

            double temp = x;

            x = x*Math.cos(angle) - y*Math.sin(angle);
            y = temp*Math.sin(angle) + y*Math.cos(angle);

            double vals[] = {x - y + z, -x - y + z, -x - y - z, x - y - z};
            double norm = 0;
            for (double val:vals) norm += Math.pow(val, 2);
            norm = Math.sqrt(norm);

            signal = new DriveSignal(vals[0]/norm, vals[1]/norm, vals[2]/norm, vals[3]/norm);

            /*if (gamepad1.right_bumper) {
                flip = true;
                flipAngle = imu.getAngle();
                if (flipAngle < 0) flipAngle += 360;
            }

            if (gamepad1.left_bumper) flip = false;

            if (flip) {
                double current = (imu.getAngle()+360 - flipAngle)%360;
                //if (current < 0) current += 360;
                if (current < 180) {
                    signal = new DriveSignal(1, 1, -1, -1);
                } else {
                    flip = false;
                }
            }*/

            drive.setOpenLoop(signal);

            if (gamepad2.dpad_up) {
                grabber[0].setState(Grabber.GrabberState.CLOSED);
                elevator.moveUpSixInches();
            }
            else if (gamepad2.dpad_down) {
                grabber[0].setState(Grabber.GrabberState.CLOSED);
                elevator.moveDownSixInches();
            }

            if (gamepad2.right_bumper) grabber[0].open();
            else if (gamepad2.right_trigger > 0.1) grabber[0].close();

            if (gamepad2.left_bumper) grabber[1].open();
            else if (gamepad2.left_trigger > 0.1) grabber[1].close();

            telemetry.addData("Top", grabber[0].getPosition());
            telemetry.addData("Bottom", grabber[1].getPosition());
            telemetry.update();

            manager.loopSystems();
        }
    }
}