package org.firstinspires.ftc.team9450.auto;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.robotcore.external.navigation.RelicRecoveryVuMark;
import org.firstinspires.ftc.team9450.sensors.Gyroscope;
import org.firstinspires.ftc.team9450.sensors.Vuforia;
import org.firstinspires.ftc.team9450.subsystems.Drivetrain;
import org.firstinspires.ftc.team9450.subsystems.Intake;
import org.firstinspires.ftc.team9450.subsystems.Ramp;
import org.firstinspires.ftc.team9450.subsystems.Rudder;
import org.firstinspires.ftc.team9450.util.Constants;

/**
 * Created by Grace on 1/24/2018.
 */
@Autonomous
public class RecalAutoRed2 extends LinearOpMode {
    Vuforia vuforia;
    RelicRecoveryVuMark detectedVuMark;
    Drivetrain drivetrain;
    Rudder rudder;
    Ramp ramp;
    Gyroscope imu;
    Intake intake;
    int toBox=1;
    int center=1;
    int glyphPit=10;
    @Override
    public void runOpMode() throws InterruptedException {
        waitForStart();

        //initialize subsystems
        drivetrain = new Drivetrain(hardwareMap.dcMotor.get(Constants.Drivetrain.LF), hardwareMap.dcMotor.get(Constants.Drivetrain.LB), hardwareMap.dcMotor.get(Constants.Drivetrain.RF), hardwareMap.dcMotor.get(Constants.Drivetrain.RB));
        rudder = new Rudder(hardwareMap.servo.get(Constants.Rudder.RUDDERTOP), hardwareMap.servo.get(Constants.Rudder.RUDDERBOTTOM), hardwareMap.colorSensor.get(Constants.Rudder.COLOR));
        ramp=new Ramp(hardwareMap.servo.get(Constants.Ramp.RAMP),hardwareMap.dcMotor.get(Constants.Ramp.LIFT),hardwareMap.digitalChannel.get("touch"));
        vuforia = new Vuforia(hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName()));
        imu = new Gyroscope(hardwareMap.get(BNO055IMU.class, "imu"));
        intake = new Intake(hardwareMap.dcMotor.get(Constants.Intake.LEFT), hardwareMap.dcMotor.get(Constants.Intake.RIGHT));
        //detect vumark

        detectedVuMark = vuforia.getVuMark();
        telemetry.addData("vumark", detectedVuMark);
        telemetry.update();
        drivetrain.enableAndResetEncoders();
        Thread.sleep(500);

        // knock jewel off

        rudder.setRudderState(Rudder.RudderState.IN);
        rudder.loop();
        Thread.sleep(1000);
        rudder.setLateralState(Rudder.LateralState.NEUTRAL);
        rudder.loop();
        Thread.sleep(1000);
        rudder.setRudderState(Rudder.RudderState.OUT);
        rudder.loop();
        Thread.sleep(1000);
        int color = rudder.getColor();
        if (color == Constants.Color.RED) {
            rudder.setLateralState(Rudder.LateralState.BACKWARDS);
            rudder.loop();
            Thread.sleep(500);
        } else if (color == Constants.Color.BLUE) {
            rudder.setLateralState(Rudder.LateralState.FORWARDS);
            rudder.loop();
            Thread.sleep(500);
        }
        rudder.setRudderState(Rudder.RudderState.IN);
        rudder.setLateralState(Rudder.LateralState.NEUTRAL);
        rudder.loop();

        Thread.sleep(500);


        drivetrain.moveFB(-12,-0.6);
        straighten();
        drivetrain.moveFB(toBox,0.6);
        pivot(Math.PI/2, false);

        // deposit glyph
        if (detectedVuMark.equals(RelicRecoveryVuMark.LEFT)) {
            drivetrain.moveFB(center+3, 0.6);
            pivot(Math.PI/4, false);
        } else if (detectedVuMark.equals(RelicRecoveryVuMark.RIGHT)) {
            drivetrain.moveFB(center-3, -0.6);
            pivot(Math.PI/4, false);
        } else {
            drivetrain.moveFB(center, 0.6);
            pivot(Math.PI/4, false);
        }
        //drivetrain.moveFB(1, 1);
        // pivot(Math.PI/4, true);

        //drive forward if necessary
        drivetrain.moveFB(1.5*Math.sqrt(2),0.6);
        Thread.sleep(1000);
        intake.setState(Intake.IntakeState.OUT);
        intake.loop();
        Thread.sleep(1000);
        if (detectedVuMark.equals(RelicRecoveryVuMark.LEFT)){
            drivetrain.moveFB(-.5, -0.6);
        }else{
            drivetrain.moveFB(-1, -0.6);
        }
        //drivetrain.moveFB(-1, -1);
        intake.setState(Intake.IntakeState.OFF);
        intake.loop();


    }
    public void pivot(double angle, boolean cc) {
        double Q = Math.PI/25;
        imu = new Gyroscope(hardwareMap.get(BNO055IMU.class, "imu"));
        if (cc) {
            drivetrain.setPower(new double[]{-0.3, -0.3, 0.3, 0.3});
        } else {
            drivetrain.setPower(new double[]{0.3, 0.3, -0.3, -0.3});
        }
        while (opModeIsActive() && Math.abs(imu.getAngle()) < angle - Q) {}
        drivetrain.setPower(0);
    }
    public void straighten() {
        if(imu.getAngle() > 0){
            drivetrain.setPower(new double[]{0.3,0.3,-0.3,-0.3});
            while(opModeIsActive() && imu.getAngle() > 0.05){}
        }else if(opModeIsActive() && imu.getAngle() < 0){
            drivetrain.setPower(new double[]{-0.3,-0.3,0.3,0.3});
            while(imu.getAngle() < -0.05){}
        }
        drivetrain.setPower(new double[]{0,0,0,0});
    }
}