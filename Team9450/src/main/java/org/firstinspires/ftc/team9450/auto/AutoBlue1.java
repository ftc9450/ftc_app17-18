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
import org.firstinspires.ftc.team9450.util.DriveSignal;

/**
 * Created by Grace on 1/24/2018.
 */
@Autonomous
public class AutoBlue1 extends LinearOpMode {
    //Vuforia vuforia;
    //RelicRecoveryVuMark detectedVuMark;
    Drivetrain drivetrain;
    Rudder rudder;
    Gyroscope imu;
    Ramp ramp;
    Intake intake;
    int center=24;
    int glyphPit=10;
    @Override
    public void runOpMode() throws InterruptedException {
        waitForStart();

        //initialize subsystems
        drivetrain = new Drivetrain(hardwareMap.dcMotor.get(Constants.Drivetrain.LF), hardwareMap.dcMotor.get(Constants.Drivetrain.LB), hardwareMap.dcMotor.get(Constants.Drivetrain.RF), hardwareMap.dcMotor.get(Constants.Drivetrain.RB));
        rudder = new Rudder(hardwareMap.servo.get(Constants.Rudder.RUDDERTOP), hardwareMap.servo.get(Constants.Rudder.RUDDERBOTTOM), hardwareMap.colorSensor.get(Constants.Rudder.COLOR));
        ramp=new Ramp(hardwareMap.servo.get(Constants.Ramp.RAMP),hardwareMap.dcMotor.get(Constants.Ramp.LIFT),hardwareMap.digitalChannel.get("touch"));
        //vuforia = new Vuforia(hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName()));
        imu = new Gyroscope(hardwareMap.get(BNO055IMU.class, "imu"));
        intake = new Intake(hardwareMap.dcMotor.get(Constants.Intake.LEFT), hardwareMap.dcMotor.get(Constants.Intake.RIGHT));

        //detect vumark
        //detect vumark
        //detectedVuMark = vuforia.getVuMark();
        /*telemetry.addData("vumark", detectedVuMark);
        telemetry.update();
        drivetrain.enableAndResetEncoders();
        Thread.sleep(500);

        // knock jewel off
        /*
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
        if (color == Constants.Color.BLUE) {
            rudder.setLateralState(Rudder.LateralState.BACKWARDS);
            rudder.loop();
            Thread.sleep(500);
        } else if (color == Constants.Color.RED) {
            rudder.setLateralState(Rudder.LateralState.FORWARDS);
            rudder.loop();
            Thread.sleep(500);
        }
        rudder.setRudderState(Rudder.RudderState.IN);
        rudder.setLateralState(Rudder.LateralState.NEUTRAL);
        rudder.loop();
        Thread.sleep(500);
*/

        //deposit glyph
        drivetrain.moveFB(12,0.3);
        straighten();
        /*
        if(detectedVuMark.equals(RelicRecoveryVuMark.RIGHT)){
            drivetrain.moveFB(center+7,1);
        }else if(detectedVuMark.equals(RelicRecoveryVuMark.LEFT)){
            drivetrain.moveFB(center-7,1);
        }else{
            drivetrain.moveFB(center,1);
        }
        */
        drivetrain.moveFB(center,1);
        pivot(Math.PI/4,true);
        drivetrain.moveFB(1.5*Math.sqrt(2),1);

        intake.setState(Intake.IntakeState.OUT);intake.loop();Thread.sleep(1000);
        drivetrain.moveFB(-5, -1);
        intake.setState(Intake.IntakeState.OFF);

        /*
        dropGlyphs();
        if(detectedVuMark.equals(RelicRecoveryVuMark.RIGHT)){
            goToPitLeft(7);
            dropGlyphs();
            goToPitLeft(7);
            dropGlyphs();
        }else if(detectedVuMark.equals(RelicRecoveryVuMark.LEFT)){
            goToPitRight(7);
            dropGlyphs();
            goToPitRight(7);
            dropGlyphs();
        }else{
            goToPitLeft(7);
            dropGlyphs();
            goToPitRight(14);
            dropGlyphs();
        }
        drivetrain.moveFB(-3, .5);
        */
    }
    public void goToPitLeft(int distance){
        intake.setState(Intake.IntakeState.IN);intake.loop();
        drivetrain.moveFB(glyphPit, 1);
        drivetrain.moveFB(-1*glyphPit,1);
        intake.setState(Intake.IntakeState.OFF);
        drivetrain.pivot(-90,-1);
        drivetrain.moveFB(distance,1);
    }
    public void goToPitRight(int distance){
        intake.setState(Intake.IntakeState.IN);intake.loop();
        drivetrain.moveFB(glyphPit, 1);
        drivetrain.moveFB(-1*glyphPit,1);
        intake.setState(Intake.IntakeState.OFF);
        drivetrain.pivot(-90,-1);
        drivetrain.moveFB(-1*distance,-1);
    }
    public void dropGlyphs()throws InterruptedException{
        drivetrain.pivot(-45,-1);
        drivetrain.moveFB(-12,-1);
        //ramp.setState(Ramp.RampState.OUT);ramp.loop();Thread.sleep(500);
        //ramp.setState(Ramp.RampState.IN);ramp.loop();Thread.sleep(500);
        drivetrain.moveFB(12,1);
        drivetrain.pivot(-45,-1);
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
        while(imu.getAngle()!=0){
            if(imu.getAngle() > 0){
                drivetrain.setPower(new double[]{0.3,0.3,-0.3,-0.3});
            }else if(imu.getAngle() < 0){
                drivetrain.setPower(new double[]{-0.3,-0.3,0.3,0.3});
            }else{
                drivetrain.setPower(new double[]{0,0,0,0});
            }
        }
        drivetrain.setPower(new double[]{0,0,0,0});
    }
}