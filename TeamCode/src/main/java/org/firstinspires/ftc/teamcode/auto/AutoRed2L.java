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
public class AutoRed2L extends LinearOpMode{
    Vuforia vuforia;
    RelicRecoveryVuMark detectedVuMark;
    Drivetrain drivetrain;
    Rudder rudder;
    Grabber grabber;

    @Override
    public void runOpMode() throws InterruptedException {
//        int cameraMonitorViewId = hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());
//        VuforiaLocalizer.Parameters parameters = new VuforiaLocalizer.Parameters(cameraMonitorViewId);
//        parameters.vuforiaLicenseKey = Constants.Setup.VUFORIAKEY;
//        parameters.cameraDirection = VuforiaLocalizer.CameraDirection.BACK;
//        this.vuforia = ClassFactory.createVuforiaLocalizer(parameters);
//        VuforiaTrackables relicTrackables = this.vuforia.loadTrackablesFromAsset("RelicVuMark");
//        VuforiaTrackable relicTemplate = relicTrackables.get(0);
//        relicTemplate.setName("relicVuMarkTemplate"); // can help in debugging; otherwise not necessary

        drivetrain=new Drivetrain(hardwareMap.dcMotor.get(Constants.Drivetrain.LF), hardwareMap.dcMotor.get(Constants.Drivetrain.LB), hardwareMap.dcMotor.get(Constants.Drivetrain.RF), hardwareMap.dcMotor.get(Constants.Drivetrain.RB));
        rudder = new Rudder(hardwareMap.servo.get("rudder_servo"), hardwareMap.colorSensor.get("color"));
        grabber=new Grabber(hardwareMap.servo.get(Constants.Grabber.LT),hardwareMap.servo.get(Constants.Grabber.RT));
        vuforia=new Vuforia(hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId","id",hardwareMap.appContext.getPackageName()));
        grabber.setState(Grabber.GrabberState.CLOSED);grabber.loop();
        //rudder.setState(Rudder.RudderState.START);rudder.loop();
        waitForStart();
//
//        relicTrackables.activate();
//        RelicRecoveryVuMark detectedVuMark = RelicRecoveryVuMark.from(relicTemplate);
        detectedVuMark=vuforia.getVuMark();

        telemetry.addData("vumark",detectedVuMark);
        telemetry.update();

        drivetrain.enableAndResetEncoders();
        telemetry.addData("status", "started");
        telemetry.update();
        drivetrain.moveLR(5*Constants.Drivetrain.INCH, 1); // move 3 inches right
        //rudder.setState(Rudder.RudderState.OUT);rudder.loop();
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
        //if (detectedVuMark.equals(RelicRecoveryVuMark.UNKNOWN)) detectedVuMark = RelicRecoveryVuMark.from(relicTemplate);
        telemetry.addData("vumark", detectedVuMark);
        telemetry.update();
        if(color==Constants.Color.RED){
            drivetrain.moveFB(-4*Constants.Drivetrain.INCH,-1);
            Thread.sleep(1000);
            // TODO: strafe before pulling in
            //rudder.setState(Rudder.RudderState.IN);rudder.loop();
            drivetrain.moveFB(4*Constants.Drivetrain.INCH,1);
        } else {
            drivetrain.moveFB(4*Constants.Drivetrain.INCH,1);
            Thread.sleep(1000);
            //rudder.setState(Rudder.RudderState.IN);rudder.loop();
            drivetrain.moveFB(-4*Constants.Drivetrain.INCH,-1);
        }

        // if rudder is stuck
        /*if (rudder.rudderServoPos() > Constants.Rudder.RUDDER_IN+0.1) {
            drivetrain.moveLR(-2, 1);
            rudder.setState(Rudder.RudderState.IN);
            drivetrain.moveLR(2, 1);
        }*/

        telemetry.update();
        drivetrain.moveFB(10*Constants.Drivetrain.INCH,1);
        if (detectedVuMark == RelicRecoveryVuMark.UNKNOWN) detectedVuMark = vuforia.getVuMark();
        drivetrain.moveFB(16*Constants.Drivetrain.INCH, 1);
        drivetrain.pivot(-90*Constants.Drivetrain.DEGREE,-1);
        drivetrain.moveFB(-5, 1);
        if(detectedVuMark.equals(RelicRecoveryVuMark.RIGHT)){
            drivetrain.moveFB(12*Constants.Drivetrain.INCH,1);
        }else if(detectedVuMark.equals(RelicRecoveryVuMark.CENTER)){
            drivetrain.moveFB(22*Constants.Drivetrain.INCH,1);
        }else{
            drivetrain.moveFB(30*Constants.Drivetrain.INCH,1);
        }
        telemetry.update();
        drivetrain.pivot(90*Constants.Drivetrain.DEGREE,1);
        drivetrain.moveFB(9*Constants.Drivetrain.INCH,1);
        grabber.setState(Grabber.GrabberState.OPEN); grabber.loop();
        drivetrain.moveFB(-2*Constants.Drivetrain.INCH, 1);
    }
}
