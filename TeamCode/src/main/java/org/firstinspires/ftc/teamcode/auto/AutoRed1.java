package org.firstinspires.ftc.teamcode.auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.robotcore.external.navigation.RelicRecoveryVuMark;
import org.firstinspires.ftc.teamcode.sensors.Vuforia;
import org.firstinspires.ftc.teamcode.subsystems.Drivetrain;
import org.firstinspires.ftc.teamcode.subsystems.Grabber;
import org.firstinspires.ftc.teamcode.subsystems.Rudder;
import org.firstinspires.ftc.teamcode.util.Constants;

/**
 * Created by Grace on 12/30/2017.
 */
@Autonomous
public class AutoRed1 extends LinearOpMode{
    Vuforia vuforia;
    RelicRecoveryVuMark detectedVuMark;
    Drivetrain drivetrain;
    Rudder rudder;
    Grabber grabber;
    @Override
    public void runOpMode() throws InterruptedException {
        waitForStart();
        drivetrain=new Drivetrain(hardwareMap.dcMotor.get(Constants.Drivetrain.LF), hardwareMap.dcMotor.get(Constants.Drivetrain.LB), hardwareMap.dcMotor.get(Constants.Drivetrain.RF), hardwareMap.dcMotor.get(Constants.Drivetrain.RB));
        rudder = new Rudder(hardwareMap.servo.get(Constants.Rudder.RUDDER), hardwareMap.colorSensor.get(Constants.Rudder.COLOR));
        grabber=new Grabber(hardwareMap.servo.get(Constants.Grabber.LT),hardwareMap.servo.get(Constants.Grabber.RT));
        //get vumark
        vuforia=new Vuforia(hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId","id",hardwareMap.appContext.getPackageName()));
        grabber.autoClose();
        rudder.setState(Rudder.RudderState.START);rudder.loop();
        detectedVuMark=vuforia.getVuMark();
        /*if(detectedVuMark.equals(RelicRecoveryVuMark.UNKNOWN)){
            drivetrain.moveLR(Constants.Drivetrain.STRAFEINCH,0.3);
            detectedVuMark=vuforia.getVuMark();
            drivetrain.moveLR(-1,-0.3);
        }*/
        telemetry.addData("vumark",detectedVuMark);telemetry.update();
        drivetrain.enableAndResetEncoders();
       // drivetrain.moveLR(5, 0.3); // move 3 inches right
        Thread.sleep(500);
        rudder.setState(Rudder.RudderState.OUT);rudder.loop();
        Thread.sleep(1000);
        // knock off blue
        int color=rudder.getColor();
        //Test at USRA-The thresholds don't work in my basement -Grace
        /*if(color==Constants.Color.BLUE){
            drivetrain.moveFB(4,1);
            Thread.sleep(1000);
            rudder.setState(Rudder.RudderState.IN);rudder.loop();
            drivetrain.moveFB(-4,-1);
        }else if(color==Constants.Color.RED){
            drivetrain.moveFB(-4,-1);
            Thread.sleep(1000);
            rudder.setState(Rudder.RudderState.IN);rudder.loop();
            drivetrain.moveFB(4,1);
        }else{
            rudder.setState(Rudder.RudderState.IN);rudder.loop();
        }
        */
        //TODO:Strafe before pulling in
        if(color==Constants.Color.RED){
            drivetrain.moveFB(-4,-1);
            Thread.sleep(1000);
            rudder.setState(Rudder.RudderState.IN);rudder.loop();
            drivetrain.moveFB(4,1);
        }else {
            drivetrain.moveFB(4,1);
            Thread.sleep(1000);
            rudder.setState(Rudder.RudderState.IN);rudder.loop();
            drivetrain.moveFB(-4,-1);
        }
        // if rudder is stuck
        if (rudder.rudderServoPos() > Constants.Rudder.RUDDER_IN+0.1) {
            drivetrain.moveLR(-2, 0.3);
            rudder.setState(Rudder.RudderState.IN);
            drivetrain.moveLR(2, 0.3);
        }
        if(detectedVuMark.equals(RelicRecoveryVuMark.RIGHT)){
            drivetrain.moveFB(28,1);
        }else if(detectedVuMark.equals(RelicRecoveryVuMark.LEFT)){
            drivetrain.moveFB(40,1);
        }else{
            drivetrain.moveFB(34,1);
        }
        drivetrain.pivot(90,1);
        drivetrain.moveFB(5,1);
        grabber.autoOpen();
        drivetrain.moveFB(-10, -1);
        drivetrain.moveFB(10,0.5);
    }
}
