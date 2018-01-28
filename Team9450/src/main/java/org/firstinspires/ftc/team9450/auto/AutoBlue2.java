package org.firstinspires.ftc.team9450.auto;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

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
public class AutoBlue2 extends LinearOpMode {
    Vuforia vuforia;
    RelicRecoveryVuMark detectedVuMark;
    Drivetrain drivetrain;
    Rudder rudder;
    Ramp ramp;
    Gyroscope imu;
    Intake intake;
    int center=19;
    int glyphPit=10;
    CRServo release;
    @Override
    public void runOpMode() throws InterruptedException {
        waitForStart();

        //initialize subsystems
        drivetrain = new Drivetrain(hardwareMap.dcMotor.get(Constants.Drivetrain.LF), hardwareMap.dcMotor.get(Constants.Drivetrain.LB), hardwareMap.dcMotor.get(Constants.Drivetrain.RF), hardwareMap.dcMotor.get(Constants.Drivetrain.RB));
        rudder = new Rudder(hardwareMap.servo.get(Constants.Rudder.RUDDERTOP), hardwareMap.servo.get(Constants.Rudder.RUDDERBOTTOM), hardwareMap.colorSensor.get(Constants.Rudder.COLOR));
        //ramp=new Ramp(hardwareMap.servo.get(Constants.Ramp.RAMP));
        vuforia = new Vuforia(hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName()));
        imu = new Gyroscope(hardwareMap.get(BNO055IMU.class, "imu"));
        rudder.setRudderState(Rudder.RudderState.START);
        rudder.loop();
        intake = new Intake(hardwareMap.dcMotor.get(Constants.Intake.LEFT), hardwareMap.dcMotor.get(Constants.Intake.RIGHT));
        release = hardwareMap.crservo.get("intake_release");
        release.setDirection(DcMotorSimple.Direction.REVERSE);
        telemetry.addData("step", 0);
        telemetry.update();

        //detect vumark
        telemetry.addData("step", 1);
        telemetry.update();
        detectedVuMark = vuforia.getVuMark();
        telemetry.addData("vumark", detectedVuMark);
        telemetry.update();
        drivetrain.enableAndResetEncoders();
        Thread.sleep(500);

        // knock jewel off
        rudder.setRudderState(Rudder.RudderState.OUT);
        rudder.loop();
        Thread.sleep(1000);
        int color = rudder.getColor();
        if (color == Constants.Color.BLUE) {
            rudder.setLateralState(Rudder.LateralState.BACKWARDS);
            rudder.loop();
            Thread.sleep(2000);
        } else if (color == Constants.Color.RED) {
            rudder.setLateralState(Rudder.LateralState.FORWARDS);
            rudder.loop();
            Thread.sleep(2000);
        }
        rudder.setRudderState(Rudder.RudderState.IN);
        rudder.setLateralState(Rudder.LateralState.NEUTRAL);
        rudder.loop();
        Thread.sleep(500);
        release.setPower(1);
        Thread.sleep(5000);
        release.setPower(-1);
        Thread.sleep(1500);

        //move to position and drop intake
        telemetry.addData("step", 3);
        telemetry.update();
        drivetrain.moveFB(-42, 1);//???
        telemetry.update();
        drivetrain.disconnectEncoders();
        drivetrain.pivot(90,1);
//        while (imu.getAngle() < Math.PI/2) {
//            drivetrain.setPower(new double[]{0.3, 0.3, -0.3, -0.3});
//            telemetry.addData("angle", 180*imu.getAngle()/Math.PI);
//            telemetry.update();
//        }
        drivetrain.enableAndResetEncoders();
        //drivetrain.pivotTo(Math.PI/2, imu);

        // deposit glyph
        telemetry.addData("step", 4);
        telemetry.update();
        if (detectedVuMark.equals(RelicRecoveryVuMark.RIGHT)) {
            drivetrain.moveFB(3, 1);
        } else if (detectedVuMark.equals(RelicRecoveryVuMark.LEFT)) {
            drivetrain.moveFB(9, 1);
        } else {
            drivetrain.moveFB(6, 1);
        }
        //drivetrain.pivotTo(3.0*Math.PI/4,imu);
        imu = new Gyroscope(hardwareMap.get(BNO055IMU.class, "imu"));
        drivetrain.disconnectEncoders();
//        while (imu.getAngle() > -Math.PI/4) {
//            drivetrain.setPower (new double[]{-0.3, -0.3, 0.3, 0.3});
//        }
        drivetrain.pivot(-45,-1);
        drivetrain.enableAndResetEncoders();
        //do some kind of intake deploying
        //drive forward if necessary
        intake.setState(Intake.IntakeState.OUT);
        intake.loop();
        Thread.sleep(1000);
        drivetrain.moveFB(-5, 1);
        intake.setState(Intake.IntakeState.OFF);

        /*
        dropGlyphs();
        if(detectedVuMark.equals(RelicRecoveryVuMark.RIGHT)){
            goToPitLeft(7,0);
            dropGlyphs();
            goToPitLeft(7,7);
            dropGlyphs();
        }else if(detectedVuMark.equals(RelicRecoveryVuMark.LEFT)){
            goToPitRight(7,14);
            dropGlyphs();
            goToPitRight(7,7);
            dropGlyphs();
        }else{
            goToPitLeft(7,7);
            dropGlyphs();
            goToPitRight(14,14);
            dropGlyphs();
        }
        drivetrain.moveFB(-3, .5);
        */
    }
    public void goToPitLeft(int distance, int distanceToRight){
        drivetrain.pivot(-90,-1);
        drivetrain.moveFB(6+distanceToRight,1);
        drivetrain.pivot(90,1);
        intake.setState(Intake.IntakeState.IN);intake.loop();
        drivetrain.moveFB(glyphPit, 1);
        drivetrain.moveFB(-1*glyphPit,1);
        intake.setState(Intake.IntakeState.OFF);
        drivetrain.pivot(-90,-1);
        drivetrain.moveFB(-6-(distanceToRight+distance),-1);
    }
    public void goToPitRight(int distance, int distanceToRight){
        drivetrain.pivot(-90,-1);
        drivetrain.moveFB(6+distanceToRight,1);
        drivetrain.pivot(90,1);
        intake.setState(Intake.IntakeState.IN);intake.loop();
        drivetrain.moveFB(glyphPit, 1);
        drivetrain.moveFB(-1*glyphPit,1);
        intake.setState(Intake.IntakeState.OFF);
        drivetrain.pivot(-90,-1);
        drivetrain.moveFB(-6-(distanceToRight-distance),-1);
    }
    public void dropGlyphs()throws InterruptedException{
        drivetrain.pivot(-45,1);
        drivetrain.moveFB(-12,-1);
        //ramp.setState(Ramp.RampState.OUT);ramp.loop();Thread.sleep(500);
        //ramp.setState(Ramp.RampState.IN);ramp.loop();Thread.sleep(500);
        drivetrain.moveFB(12,1);
        drivetrain.pivot(-45,-1);
    }
}