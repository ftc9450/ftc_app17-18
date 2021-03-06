package org.firstinspires.ftc.teamcode.teleop;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.util.ReadWriteFile;

import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
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
public class LinearTeleOp extends LinearOpMode {
    SubsystemManager subsystemManager=new SubsystemManager();
    Drivetrain drivetrain;
    ControlBoard controlBoard;
    //RelicArm elevator;
    Grabber grabber;

    //private BNO055IMU imu;
    //Orientation angles;
    float normal;

    private Gyroscope gyro;

    VuforiaLocalizer vuforia;

    public void runOpMode() {
        telemetry.addData("heading", 9450);

        BNO055IMU.Parameters parameters = new BNO055IMU.Parameters();
        parameters.angleUnit    = BNO055IMU.AngleUnit.DEGREES;
        parameters.calibrationDataFile  = "IMUCalibration.json";
        parameters.loggingEnabled       = true;
        parameters.loggingTag           = "IMU";

        /*int camID = hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());
        VuforiaLocalizer.Parameters vuparam = new VuforiaLocalizer.Parameters(camID);
        vuparam.vuforiaLicenseKey = Constants.Setup.VUFORIAKEY;
        vuparam.cameraDirection = VuforiaLocalizer.CameraDirection.BACK;
        this.vuforia = ClassFactory.createVuforiaLocalizer(vuparam);*/

        //imu = hardwareMap.get(BNO055IMU.class, "imu");

        gyro = new Gyroscope(hardwareMap.get(BNO055IMU.class, "imu"));

        controlBoard=new ControlBoard(gamepad1);
        drivetrain=new Drivetrain(hardwareMap.dcMotor.get(Constants.Drivetrain.LF), hardwareMap.dcMotor.get(Constants.Drivetrain.LB), hardwareMap.dcMotor.get(Constants.Drivetrain.RF), hardwareMap.dcMotor.get(Constants.Drivetrain.RB));
        drivetrain.disconnectEncoders();
        subsystemManager.add(drivetrain);
//        grabber=new Grabber(hardwareMap.servo.get("servoLeft"),hardwareMap.servo.get("servoRight"));
//        subsystemManager.add(grabber);

        waitForStart();

        //angles = imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES);
        normal = gyro.getAngle();
        while (opModeIsActive()) {
            //angles = imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES);
            telemetry.addData("heading", gyro.getAngle());
            telemetry.addData("X", gyro.getX());
            telemetry.addData("Y", gyro.getY());
            telemetry.update();
            DriveSignal d;
            //DriveSignal translate=controlBoard.translate(gyro.getAngle());
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
            //elevator.setState(controlBoard.elevatorCommand());
            subsystemManager.loopSystems();
        }
    }
}
