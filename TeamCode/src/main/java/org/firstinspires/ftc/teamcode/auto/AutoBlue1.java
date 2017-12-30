package org.firstinspires.ftc.teamcode.auto;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.navigation.RelicRecoveryVuMark;
import org.firstinspires.ftc.teamcode.sensors.Vuforia;
import org.firstinspires.ftc.teamcode.subsystems.Drivetrain;
import org.firstinspires.ftc.teamcode.subsystems.Rudder;
import org.firstinspires.ftc.teamcode.util.Constants;

/**
 * Created by Grace on 12/30/2017.
 */

public class AutoBlue1 extends LinearOpMode{
    Vuforia vuforia;
    RelicRecoveryVuMark detectedVuMark;
    Drivetrain drivetrain;
    Rudder rudder;
    @Override
    public void runOpMode() throws InterruptedException {
        vuforia = new Vuforia(hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName()));
        detectedVuMark = vuforia.getVuMark();
        telemetry.addData("vumark", detectedVuMark);
        telemetry.update();
        drivetrain = new Drivetrain(hardwareMap.dcMotor.get(Constants.Drivetrain.LF), hardwareMap.dcMotor.get(Constants.Drivetrain.LB), hardwareMap.dcMotor.get(Constants.Drivetrain.RF), hardwareMap.dcMotor.get(Constants.Drivetrain.RB));
        rudder = new Rudder(hardwareMap.servo.get("rudder_servo"), hardwareMap.colorSensor.get("sensor_color_distance"));
        rudder.setState(Rudder.RudderState.START);
        rudder.loop();
        drivetrain.enableAndResetEncoders();
        telemetry.addData("status", "started");
        telemetry.update();
        drivetrain.moveLR(3 * Constants.Drivetrain.INCH, 1); // move 3 inches right
        rudder.setState(Rudder.RudderState.OUT);
        rudder.loop();
        Thread.sleep(1000);
        // knock off red
        int color = rudder.getColor();
        if (color == Constants.Color.BLUE) {
            drivetrain.pivot(1000, 1);
            Thread.sleep(1000);
            rudder.setState(Rudder.RudderState.IN);
            rudder.loop();
            drivetrain.pivot(-1000, -1);
        } else if (color == Constants.Color.RED) {
            drivetrain.pivot(-1000, -1);
            Thread.sleep(1000);
            rudder.setState(Rudder.RudderState.IN);
            rudder.loop();
            drivetrain.pivot(1000, 1);
        } else {
            rudder.setState(Rudder.RudderState.IN);
            rudder.loop();
        }
        if (detectedVuMark.equals(RelicRecoveryVuMark.RIGHT)) {
            drivetrain.moveFB(-25 * Constants.Drivetrain.INCH, -1);
        } else if (detectedVuMark.equals(RelicRecoveryVuMark.LEFT)) {
            drivetrain.moveFB(-50 * Constants.Drivetrain.INCH, -1);
        } else {
            drivetrain.moveFB(-36 * Constants.Drivetrain.INCH, -1);
        }
        drivetrain.pivot(2000, 1);//Calibrate for 90 degree turn
        drivetrain.moveFB(3 * Constants.Drivetrain.INCH, 1);
    }
}
