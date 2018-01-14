package org.firstinspires.ftc.teamcode.teleop;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.util.ReadWriteFile;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;
import org.firstinspires.ftc.robotcore.internal.system.AppUtil;
import org.firstinspires.ftc.teamcode.control.ControlBoard;
import org.firstinspires.ftc.teamcode.sensors.Gyroscope;
import org.firstinspires.ftc.teamcode.subsystems.*;
import org.firstinspires.ftc.teamcode.util.Constants;
import org.firstinspires.ftc.teamcode.util.DriveSignal;

import java.io.File;

/**
 * @author Grace
 */
@com.qualcomm.robotcore.eventloop.opmode.TeleOp
public class TeleOp extends LinearOpMode {
    Drivetrain drive;
    Elevator elevator;
    //Grabber grabber[];
    Grabber topGrabber;
    Grabber bottomGrabber;
    RelicArm arm;
    Gyroscope imu;
    Gyroscope imu2;
    Rudder rudder;
    SubsystemManager manager;

    @Override
    public void runOpMode() throws InterruptedException {
        drive = new Drivetrain(hardwareMap.dcMotor.get(Constants.Drivetrain.LF), hardwareMap.dcMotor.get(Constants.Drivetrain.LB), hardwareMap.dcMotor.get(Constants.Drivetrain.RF), hardwareMap.dcMotor.get(Constants.Drivetrain.RB));
        imu = new Gyroscope(hardwareMap.get(BNO055IMU.class, "imu"));
        elevator = new Elevator(hardwareMap.dcMotor.get(Constants.Elevator.ELEVATOR));
        rudder=new Rudder(hardwareMap.servo.get("rudder_servo"), hardwareMap.colorSensor.get("color"));

        /*grabber = new Grabber[]{new Grabber(hardwareMap.servo.get(Constants.Grabber.LT),hardwareMap.servo.get(Constants.Grabber.RT)),
                new Grabber(hardwareMap.servo.get(Constants.Grabber.LB),hardwareMap.servo.get(Constants.Grabber.RB))};*/
        topGrabber=new Grabber(hardwareMap.servo.get(Constants.Grabber.LT),hardwareMap.servo.get(Constants.Grabber.RT));
        bottomGrabber=new Grabber(hardwareMap.servo.get(Constants.Grabber.LB),hardwareMap.servo.get(Constants.Grabber.RB));
        bottomGrabber.setState(Grabber.GrabberState.OPEN);
        topGrabber.setState(Grabber.GrabberState.OPEN);
        arm = new RelicArm(hardwareMap.dcMotor.get("relic"), hardwareMap.crservo.get("l_pivot"), hardwareMap.crservo.get("r_pivot"), hardwareMap.servo.get("hand"));
        manager = new SubsystemManager();
        manager.add(drive);
        manager.add(elevator);
        manager.add(arm);
        manager.add(rudder);

        ControlBoard driver = new ControlBoard(gamepad1);
        ControlBoard operator = new ControlBoard(gamepad2);

        waitForStart();

        DriveSignal signal;

        boolean flip = false;
        boolean right = false;
        boolean left = false;

        while (opModeIsActive()) {
            if (gamepad1.y) imu = new Gyroscope(hardwareMap.get(BNO055IMU.class, "imu"));

            double x = gamepad1.left_stick_x + (gamepad1.dpad_left?-0.5:0) + (gamepad1.dpad_right?0.5:0);
            double y = gamepad1.left_stick_y + (gamepad1.dpad_down?0.5:0) + (gamepad1.dpad_up?-0.5:0);
            double z = gamepad1.right_stick_x + gamepad1.right_trigger/2 - gamepad1.left_trigger/2;
            /*double angle = Math.PI * imu.getAngle()/180.0;

            double temp = x;

            x = x*Math.cos(angle) - y*Math.sin(angle);
            y = temp*Math.sin(angle) + y*Math.cos(angle);

            if (gamepad1.left_bumper) {
                flip = false;
                left = false;
                right = false;
            }
            else if (gamepad1.right_bumper) {
                flip = true;
                right = false;
                left = false;
                imu2 = new Gyroscope(hardwareMap.get(BNO055IMU.class, "imu2"));
            }
            else if (gamepad1.x) {
                left = true;
                right = false;
                flip = false;
                imu2 = new Gyroscope(hardwareMap.get(BNO055IMU.class, "imu2"));
            }
            else if (gamepad2.y) {
                right = true;
                left = false;
                flip = false;
                imu2 = new Gyroscope(hardwareMap.get(BNO055IMU.class, "imu2"));
            }

            if (flip && imu2.getAngle() > 0) {
                z = 1;
            } else if (right && imu2.getAngle() > 90) {
                z = 1;
            } else if (left && imu2.getAngle() < -90) {
                z = -1;
            } else {
                flip = false;
                left = false;
                right = false;
            }*/

            signal = new DriveSignal(x - y + z, -x - y + z, -x - y - z, x - y - z);

            drive.setOpenLoop(signal);

            if(operator.moveDownSixInches()){elevator.moveDownSixInches();}
            if(operator.moveUpSixInches()){elevator.moveUpSixInches();}
            elevator.setState(operator.elevatorCommand());
            topGrabber.setState(operator.topGrabberCommand());
            bottomGrabber.setState(operator.bottomGrabberCommand());
            rudder.setState(Rudder.RudderState.IN);
            arm.setHumerus(operator.relicCommand());
            arm.setPollex(operator.handCommand());
            arm.setCarpals(operator.pivotCommand());
            //telemetry.addData("glypht position: ",elevator);
            //telemetry.addData("relic arm position: ",arm);
            telemetry.addData("top grabber", topGrabber.getState());
            telemetry.addData("bottom grabber", bottomGrabber.getState());
            /*if (gamepad2.dpad_up) {
                topGrabber.setState(Grabber.GrabberState.CLOSED);
                elevator.moveUpSixInches();
            }
            else if (gamepad2.dpad_down) {
                topGrabber.setState(Grabber.GrabberState.CLOSED);
                elevator.moveDownSixInches();
            }
            if(gamepad2.left_stick_y<0){
                elevator.setState(Elevator.ElevatorState.UP);
            }else if(gamepad2.left_stick_y>0){
                elevator.setState(Elevator.ElevatorState.DOWN);
            }else{
                elevator.setState(Elevator.ElevatorState.OFF);
            }

            if (gamepad2.right_bumper) topGrabber.close();
            else if (gamepad2.right_trigger > 0.1) topGrabber.open();

            if (gamepad2.left_bumper) bottomGrabber.close();
            else if (gamepad2.left_trigger > 0.1) bottomGrabber.open();

            if (gamepad2.right_stick_y < 0) arm.setHumerus(RelicArm.HumerusState.OUT);
            else if (gamepad2.right_stick_y > 0) arm.setHumerus(RelicArm.HumerusState.IN);
            else arm.setHumerus(RelicArm.HumerusState.OFF);

            if (gamepad2.x) arm.setCarpals(RelicArm.CarpalState.OUT);
            else if (gamepad2.y) arm.setCarpals(RelicArm.CarpalState.IN);
            else arm.setCarpals(RelicArm.CarpalState.OFF);

            if (gamepad2.a) arm.setPollex(RelicArm.PollexState.OPEN);
            else if (gamepad2.b) arm.setPollex(RelicArm.PollexState.CLOSED);
            rudder.setState(Rudder.RudderState.IN);
            telemetry.addData("Top", topGrabber.getPosition());
            telemetry.addData("Bottom", bottomGrabber.getPosition());
            telemetry.update();*/

            manager.loopSystems();
        }
    }
}
