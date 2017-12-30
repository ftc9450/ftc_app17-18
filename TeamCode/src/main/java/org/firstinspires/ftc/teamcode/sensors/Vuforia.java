package org.firstinspires.ftc.teamcode.sensors;

import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.navigation.RelicRecoveryVuMark;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackable;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackables;
import org.firstinspires.ftc.teamcode.util.Constants;

/**
 * Created by Grace on 12/30/2017.
 */

public class Vuforia {
    VuforiaLocalizer.Parameters parameters;
    VuforiaLocalizer vuforia;
    VuforiaTrackables relicTrackables;
    VuforiaTrackable relicVuMarks;
    public Vuforia(int CamMonitorViewId){
        parameters=new VuforiaLocalizer.Parameters(CamMonitorViewId);
        parameters.vuforiaLicenseKey= Constants.Setup.VUFORIAKEY;
        parameters.cameraDirection= VuforiaLocalizer.CameraDirection.BACK;
        vuforia= ClassFactory.createVuforiaLocalizer(parameters);
        relicTrackables =vuforia.loadTrackablesFromAsset("RelicVuMark");
        relicVuMarks= relicTrackables.get(0);
    }
    public RelicRecoveryVuMark getVuMark(){
        return RelicRecoveryVuMark.from(relicVuMarks);
    }
}
