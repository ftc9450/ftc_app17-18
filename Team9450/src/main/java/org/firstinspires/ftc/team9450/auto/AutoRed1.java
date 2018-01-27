package org.firstinspires.ftc.team9450.auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.robotcore.external.navigation.RelicRecoveryVuMark;
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
public class AutoRed1 extends LinearOpMode {
    Vuforia vuforia;
    RelicRecoveryVuMark detectedVuMark;
    Drivetrain drivetrain;
    Rudder rudder;
    Ramp ramp;
    Intake intake;
    int center=28;
    int glyphPit=10;
    @Override
    public void runOpMode() throws InterruptedException {
        waitForStart();
        drivetrain=new Drivetrain(hardwareMap.dcMotor.get(Constants.Drivetrain.LF), hardwareMap.dcMotor.get(Constants.Drivetrain.LB), hardwareMap.dcMotor.get(Constants.Drivetrain.RF), hardwareMap.dcMotor.get(Constants.Drivetrain.RB));
        rudder = new Rudder(hardwareMap.servo.get(Constants.Rudder.RUDDERTOP), hardwareMap.servo.get(Constants.Rudder.RUDDERBOTTOM),hardwareMap.colorSensor.get(Constants.Rudder.COLOR));
        //ramp=new Ramp(hardwareMap.servo.get(Constants.Ramp.RAMP));
        vuforia=new Vuforia(hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId","id",hardwareMap.appContext.getPackageName()));
        rudder.setRudderState(Rudder.RudderState.START);rudder.loop();
        intake=new Intake(hardwareMap.dcMotor.get(Constants.Intake.LEFT), hardwareMap.dcMotor.get(Constants.Intake.RIGHT));
        detectedVuMark=vuforia.getVuMark();
        telemetry.addData("vumark",detectedVuMark);telemetry.update();
        drivetrain.enableAndResetEncoders();
        Thread.sleep(500);
        rudder.setRudderState(Rudder.RudderState.OUT);rudder.loop();
        Thread.sleep(1000);

        //rudder.knockBlue();

        // if rudder is stuck
        if (rudder.rudderServoPos() > Constants.Rudder.RUDDER_IN+0.1) {
            drivetrain.moveLR(-2, -0.3);
            rudder.setRudderState(Rudder.RudderState.IN);
            drivetrain.moveLR(2, 0.3);
        }

        drivetrain.moveFB(-7,-1);
        if (detectedVuMark.equals(RelicRecoveryVuMark.UNKNOWN)) detectedVuMark = vuforia.getVuMark();

        if(detectedVuMark.equals(RelicRecoveryVuMark.RIGHT)){
            drivetrain.moveFB(7-center,-1);
        }else if(detectedVuMark.equals(RelicRecoveryVuMark.LEFT)){
            drivetrain.moveFB(-7-center,-1);
        }else{
            drivetrain.moveFB(-1*center,-1);
        }
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
    }
    public void goToPitRight(int distance){
        intake.setState(Intake.IntakeState.IN);intake.loop();
        drivetrain.moveFB(glyphPit, 1);
        drivetrain.moveFB(-1*glyphPit,1);
        intake.setState(Intake.IntakeState.OFF);intake.loop();
        drivetrain.pivot(90,1);
        drivetrain.moveFB(distance*-1,-1);
    }
    public void goToPitLeft(int distance){
        intake.setState(Intake.IntakeState.IN);intake.loop();
        drivetrain.moveFB(glyphPit, 1);
        drivetrain.moveFB(-1*glyphPit,1);
        intake.setState(Intake.IntakeState.OFF);intake.loop();
        drivetrain.pivot(90,1);
        drivetrain.moveFB(distance,1);
    }
    public void dropGlyphs()throws InterruptedException{
        drivetrain.pivot(-45,1);
        drivetrain.moveFB(-12,-1);
        ramp.setRampState(Ramp.RampState.OUT);ramp.loop();Thread.sleep(500);
        ramp.setRampState(Ramp.RampState.IN);ramp.loop();Thread.sleep(500);
        drivetrain.moveFB(12,1);
        drivetrain.pivot(-45,-1);
    }
}