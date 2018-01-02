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
public class AutoRed2 extends LinearOpMode{
    Vuforia vuforia;
    RelicRecoveryVuMark detectedVuMark;
    Drivetrain drivetrain;
    Rudder rudder;
    Grabber grabber;
    @Override
    public void runOpMode() throws InterruptedException {
        drivetrain=new Drivetrain(hardwareMap.dcMotor.get(Constants.Drivetrain.LF), hardwareMap.dcMotor.get(Constants.Drivetrain.LB), hardwareMap.dcMotor.get(Constants.Drivetrain.RF), hardwareMap.dcMotor.get(Constants.Drivetrain.RB));
        rudder = new Rudder(hardwareMap.servo.get("rudder_servo"), hardwareMap.colorSensor.get("sensor_color_distance"));
        grabber=new Grabber(hardwareMap.servo.get(Constants.Grabber.LT),hardwareMap.servo.get(Constants.Grabber.RT));
        vuforia=new Vuforia(hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId","id",hardwareMap.appContext.getPackageName()));
        detectedVuMark=vuforia.getVuMark();
        telemetry.addData("vumark",detectedVuMark);telemetry.update();
        grabber.setState(Grabber.GrabberState.CLOSED);grabber.loop();
        rudder.setState(Rudder.RudderState.START);rudder.loop();
        drivetrain.enableAndResetEncoders();
        telemetry.addData("status", "started"); telemetry.update();
        drivetrain.moveLR(5*Constants.Drivetrain.INCH, 1); // move 3 inches right
        rudder.setState(Rudder.RudderState.OUT);rudder.loop();
        Thread.sleep(1000);
        // knock off blue
        int color=rudder.getColor();
        //Test at USRA-The thresholds don't work in my basement -Grace
        /*if(color==Constants.Color.BLUE){
            drivetrain.moveFB(4*Constants.Drivetrain.INCH,1);
            Thread.sleep(1000);
            rudder.setState(Rudder.RudderState.IN);rudder.loop();
            drivetrain.moveFB(-4*Constants.Drivetrain.INCH,-1);
        }else if(color==Constants.Color.RED){
            drivetrain.moveFB(-4*Constants.Drivetrain.INCH,-1);
            Thread.sleep(1000);
            rudder.setState(Rudder.RudderState.IN);rudder.loop();
            drivetrain.moveFB(4*Constants.Drivetrain.INCH,1);
        }else{
            rudder.setState(Rudder.RudderState.IN);rudder.loop();
        }
        */
        if(color==Constants.Color.RED){
            drivetrain.moveFB(-4*Constants.Drivetrain.INCH,-1);
            Thread.sleep(1000);
            rudder.setState(Rudder.RudderState.IN);rudder.loop();
            drivetrain.moveFB(4*Constants.Drivetrain.INCH,1);
        }else {
            drivetrain.moveFB(4*Constants.Drivetrain.INCH,1);
            Thread.sleep(1000);
            rudder.setState(Rudder.RudderState.IN);rudder.loop();
            drivetrain.moveFB(-4*Constants.Drivetrain.INCH,-1);
        }
        drivetrain.moveFB(30*Constants.Drivetrain.INCH,1);
        drivetrain.pivot(-90*Constants.Drivetrain.DEGREE,-1);
        if(detectedVuMark.equals(RelicRecoveryVuMark.RIGHT)){
            drivetrain.moveFB(12*Constants.Drivetrain.INCH,1);
        }else if(detectedVuMark.equals(RelicRecoveryVuMark.LEFT)){
            drivetrain.moveFB(25*Constants.Drivetrain.INCH,1);
        }else{
            drivetrain.moveFB(19*Constants.Drivetrain.INCH,1);
        }
        drivetrain.pivot(90*Constants.Drivetrain.DEGREE,1);
        drivetrain.moveFB(9*Constants.Drivetrain.INCH,1);
    }
}
