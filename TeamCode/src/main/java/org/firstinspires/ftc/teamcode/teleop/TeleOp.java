package org.firstinspires.ftc.teamcode.teleop;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.util.ReadWriteFile;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;
import org.firstinspires.ftc.robotcore.internal.system.AppUtil;
import org.firstinspires.ftc.teamcode.control.ControlBoard;
import org.firstinspires.ftc.teamcode.subsystems.*;
import org.firstinspires.ftc.teamcode.util.Constants;
import org.firstinspires.ftc.teamcode.util.DriveSignal;

import java.io.File;

/**
 * @author Grace
 */
@com.qualcomm.robotcore.eventloop.opmode.TeleOp
public class TeleOp extends OpMode{
    SubsystemManager subsystemManager=new SubsystemManager();
    Drivetrain drivetrain;
    ControlBoard controlBoard;
    RelicArm elevator;
    Grabber grabber;

    private BNO055IMU imu;
    Orientation angles;

    @Override
    public void init() {
        // Get the calibration data
        BNO055IMU.CalibrationData calibrationData = imu.readCalibrationData();

        // Save the calibration data to a file. You can choose whatever file
        // name you wish here, but you'll want to indicate the same file name
        // when you initialize the IMU in an opmode in which it is used. If you
        // have more than one IMU on your robot, you'll of course want to use
        // different configuration file names for each.
        String filename = "IMUCalibration.json";
        File file = AppUtil.getInstance().getSettingsFile(filename);
        ReadWriteFile.writeFile(file, calibrationData.serialize());
        telemetry.log().add("saved to '%s'", filename);
        telemetry.log().add(imu.getCalibrationStatus().toString());

        float normal = AngleUnit.DEGREES.fromUnit(angles.angleUnit, angles.firstAngle);

        BNO055IMU.Parameters parameters = new BNO055IMU.Parameters();
        parameters.angleUnit    = BNO055IMU.AngleUnit.DEGREES;
        parameters.calibrationDataFile  = "IMUCalibration.json";
        parameters.loggingEnabled       = true;
        parameters.loggingTag           = "IMU";

        controlBoard=new ControlBoard(gamepad1);
        drivetrain=new Drivetrain(hardwareMap.dcMotor.get(Constants.Drivetrain.LF), hardwareMap.dcMotor.get(Constants.Drivetrain.LB), hardwareMap.dcMotor.get(Constants.Drivetrain.RF), hardwareMap.dcMotor.get(Constants.Drivetrain.RB));
        drivetrain.disconnectEncoders();
        subsystemManager.add(drivetrain);
//        grabber=new Grabber(hardwareMap.servo.get("servoLeft"),hardwareMap.servo.get("servoRight"));
//        subsystemManager.add(grabber);

    }

    @Override
    public void loop() {
        telemetry.addData("heading", imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES));
        DriveSignal d;
        DriveSignal translate=controlBoard.translate();
        DriveSignal turn=controlBoard.turn();
        if(controlBoard.flip()){
            drivetrain.pivot(Constants.Drivetrain.INCH*12,0.5);
            drivetrain.disconnectEncoders();
        }
        if(turn.isZero()){
            d=translate;
        }else if(translate.isZero()){
            d=turn;
        }else{
            d=DriveSignal.BRAKE;
        }
        d.scale(controlBoard.reduceDriveSpeed());
        drivetrain.setOpenLoop(d);
        elevator.setState(controlBoard.elevatorCommand());
        subsystemManager.loopSystems();
    }
}
