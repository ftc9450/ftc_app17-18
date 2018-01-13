package org.firstinspires.ftc.teamcode.auto;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.robotcore.external.navigation.RelicRecoveryVuMark;
import org.firstinspires.ftc.teamcode.sensors.Gyroscope;
import org.firstinspires.ftc.teamcode.sensors.Vuforia;
import org.firstinspires.ftc.teamcode.subsystems.Drivetrain;
import org.firstinspires.ftc.teamcode.subsystems.Grabber;
import org.firstinspires.ftc.teamcode.subsystems.Rudder;
import org.firstinspires.ftc.teamcode.util.Constants;

/**
 * Created by Grace on 12/30/2017.
 */
@Autonomous
public class AutoBlueFlip extends LinearOpMode{
    Vuforia vuforia;
    RelicRecoveryVuMark detectedVuMark;
    Drivetrain drivetrain;
    Rudder rudder;
    Grabber grabber;
    Gyroscope imu;
    int right = 19;

    @Override
    public void runOpMode() throws InterruptedException {
        waitForStart();
        drivetrain=new Drivetrain(hardwareMap.dcMotor.get(Constants.Drivetrain.LF), hardwareMap.dcMotor.get(Constants.Drivetrain.LB), hardwareMap.dcMotor.get(Constants.Drivetrain.RF), hardwareMap.dcMotor.get(Constants.Drivetrain.RB));
        rudder = new Rudder(hardwareMap.servo.get("rudder_servo"), hardwareMap.colorSensor.get("color"));
        grabber=new Grabber(hardwareMap.servo.get(Constants.Grabber.LT),hardwareMap.servo.get(Constants.Grabber.RT));
        vuforia=new Vuforia(hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId","id",hardwareMap.appContext.getPackageName()));
        grabber.autoClose();
        imu = new Gyroscope(hardwareMap.get(BNO055IMU.class, "imu"));
        rudder.setState(Rudder.RudderState.START);rudder.loop();
        drivetrain.enableAndResetEncoders();
        detectedVuMark=vuforia.getVuMark();
        /*if(detectedVuMark.equals(RelicRecoveryVuMark.UNKNOWN)){
            drivetrain.moveLR(Constants.Drivetrain.STRAFEINCH,0.3);
            detectedVuMark=vuforia.getVuMark();
            drivetrain.moveLR(-1,-0.3);
        }*/

        telemetry.addData("vumark",detectedVuMark);
        telemetry.update();
        telemetry.addData("status", "started");
        telemetry.update();
       // drivetrain.moveLR(5, 0.3); // move 3 inches right
        rudder.setState(Rudder.RudderState.OUT);rudder.loop();
        Thread.sleep(1000);
        // knock off blue
        int color=rudder.getColor();
//        //Test at USRA-The thresholds don't work in my basement -Grace
//        if(color==Constants.Color.BLUE){
//            drivetrain.moveFB(4,0.3);
//            Thread.sleep(1000);
//            rudder.setState(Rudder.RudderState.IN);rudder.loop();
//            drivetrain.moveFB(-4,-0.3);
//        }else if(color==Constants.Color.RED){
//            drivetrain.moveFB(-4,-0.3);
//            Thread.sleep(1000);
//            rudder.setState(Rudder.RudderState.IN);rudder.loop();
//            drivetrain.moveFB(4,0.3);
//        }else{
//            rudder.setState(Rudder.RudderState.IN);rudder.loop();
//        }

        //if (detectedVuMark.equals(RelicRecoveryVuMark.UNKNOWN)) detectedVuMark = RelicRecoveryVuMark.from(relicTemplate);
        Thread.sleep(1000);
        if(color==Constants.Color.RED){
            drivetrain.moveFB(-4,-0.3);
            Thread.sleep(500);
            // TODO: strafe before pulling in
            rudder.setState(Rudder.RudderState.IN);rudder.loop();
            drivetrain.moveFB(4,0.3);
        } else {
            drivetrain.moveFB(4,0.3);
            Thread.sleep(500);
            rudder.setState(Rudder.RudderState.IN);rudder.loop();
            drivetrain.moveFB(-4,-0.3);
        }

        // if rudder is stuck
        if (rudder.rudderServoPos() > Constants.Rudder.RUDDER_IN+0.1) {
            drivetrain.moveLR(-2, -0.3);
            rudder.setState(Rudder.RudderState.IN);
            drivetrain.moveLR(2, 0.3);
        }

        telemetry.update();
        drivetrain.moveFB(-7,-1);
        drivetrain.pivot(-90,-1);
 /*       if (detectedVuMark == RelicRecoveryVuMark.UNKNOWN) detectedVuMark = vuforia.getVuMark();
        drivetrain.moveFB(right, 1);
        drivetrain.pivot(-90,-1); */
        if(detectedVuMark.equals(RelicRecoveryVuMark.RIGHT)){
            drivetrain.moveFB(right,1);
        }else if(detectedVuMark.equals(RelicRecoveryVuMark.LEFT)){
            drivetrain.moveFB(right+14,1);
        }else{
            drivetrain.moveFB(right+7,1);
        }
        telemetry.update();
        drivetrain.pivot(90,1);
        drivetrain.moveFB(9,1);
        grabber.autoOpen();
        drivetrain.moveFB(-10, -1);
        grabber.autoClose();
        drivetrain.moveFB(10,0.5);
        drivetrain.moveFB(-5, .5);
    }
}
