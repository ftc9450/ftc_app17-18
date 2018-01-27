package org.firstinspires.ftc.team9450.test;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.navigation.RelicRecoveryVuMark;
import org.firstinspires.ftc.team9450.sensors.Gyroscope;
import org.firstinspires.ftc.team9450.sensors.Vuforia;
import org.firstinspires.ftc.team9450.subsystems.Drivetrain;
import org.firstinspires.ftc.team9450.subsystems.Intake;
import org.firstinspires.ftc.team9450.subsystems.Rudder;
import org.firstinspires.ftc.team9450.subsystems.SubsystemManager;
import org.firstinspires.ftc.team9450.util.Constants;

import static org.firstinspires.ftc.team9450.util.Constants.Color.BLUE;
import static org.firstinspires.ftc.team9450.util.Constants.Color.RED;

/**
 * Created by dhruv on 1/24/18.
 */

public class ConceptAutonomous extends LinearOpMode {
    Drivetrain drive;
    Rudder rudder;
    Intake intake;
    Gyroscope imu;
    Vuforia vuforia;
    SubsystemManager manager;
    @Override
    //red
    public void runOpMode() throws InterruptedException {
        //initialize subsystems
        drive = new Drivetrain(hardwareMap.dcMotor.get(Constants.Drivetrain.LF), hardwareMap.dcMotor.get(Constants.Drivetrain.LB), hardwareMap.dcMotor.get(Constants.Drivetrain.RF), hardwareMap.dcMotor.get(Constants.Drivetrain.RB));
        intake = new Intake(hardwareMap.dcMotor.get("intake_left"), hardwareMap.dcMotor.get("intake_right"));
        rudder = new Rudder(hardwareMap.servo.get("rudder"), hardwareMap.crservo.get(""), hardwareMap.colorSensor.get("color"));
        vuforia = new Vuforia(hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId","id",hardwareMap.appContext.getPackageName()));
        manager.add(drive).add(intake).add(rudder);
        waitForStart();

        //pull in glyph
        intake.setPower(0.3);
        Thread.sleep(1000);
        intake.stop();

        //detect vumark
        RelicRecoveryVuMark vumark = vuforia.getVuMark();

        //drop down rudder
        rudder.setState(Rudder.RudderState.OUT);
        rudder.loop();
        int color = Constants.Color.UNDECIDED;
        switch (color) {
            case RED:
                break;
            case BLUE:
                break;
            default:
        }
        rudder.setState(Rudder.RudderState.IN);
        //move to position
        drive.moveFB(36.5, 1);
        drive.disconnectEncoders();
        switch (vumark) {
            case LEFT:
                while (imu.getAngle() > -Math.atan(3/11.5)) {
                    drive.setPower(new double[]{-0.5, -0.5, 0.5, 0.5});
                }
                drive.enableAndResetEncoders();
                drive.moveFB(Math.sqrt(11.5*11.5 + 9), 1);
                break;
            case CENTER:
                while (imu.getAngle() > -Math.atan(6/11.5)) {
                    drive.setPower(new double[]{-0.5, -0.5, 0.5, 0.5});
                }
                drive.enableAndResetEncoders();
                drive.moveFB(Math.sqrt(11.5*11.5 + 36), 1);
                break;
            default:
                while (imu.getAngle() > -Math.atan(9/11.5)) {
                    drive.setPower(new double[]{-0.5, -0.5, 0.5, 0.5});
                }
                drive.enableAndResetEncoders();
                drive.moveFB(Math.sqrt(11.5*11.5 + 81), 1);
        }
        intake.setPower(-0.5);
        drive.moveFB(-2, 1);
        intake.stop();
        Thread.sleep(2000);
        manager.stop();
    }
}
