package org.firstinspires.ftc.team9450.auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.team9450.sensors.Vuforia;


/**
 * Created by dhruv on 1/3/18.
 */

@Autonomous
public class VuforiaTest extends LinearOpMode{
    Vuforia vuforia;

    @Override
    public void runOpMode() throws InterruptedException {
        vuforia=new Vuforia(hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId","id",hardwareMap.appContext.getPackageName()));

        waitForStart();

        while (opModeIsActive()) {
            telemetry.addData("vumark", vuforia.getVuMark());
            telemetry.update();
        }
    }
}
