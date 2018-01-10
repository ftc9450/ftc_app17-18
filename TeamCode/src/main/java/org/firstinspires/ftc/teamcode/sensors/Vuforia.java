package org.firstinspires.ftc.teamcode.sensors;

import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.matrices.OpenGLMatrix;
import org.firstinspires.ftc.robotcore.external.matrices.VectorF;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;
import org.firstinspires.ftc.robotcore.external.navigation.RelicRecoveryVuMark;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackable;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackableDefaultListener;
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
        parameters.cameraDirection= VuforiaLocalizer.CameraDirection.FRONT;
        vuforia= ClassFactory.createVuforiaLocalizer(parameters);
        relicTrackables =vuforia.loadTrackablesFromAsset("RelicVuMark");
        relicVuMarks= relicTrackables.get(0);
        relicTrackables.activate();
    }
    public RelicRecoveryVuMark getVuMark(){
        RelicRecoveryVuMark detectedVuMark=RelicRecoveryVuMark.UNKNOWN;
        for(int i=0;i<2000;i++) {
            detectedVuMark=RelicRecoveryVuMark.from(relicVuMarks);
            if(!detectedVuMark.equals(RelicRecoveryVuMark.UNKNOWN)){i=2000;}
        }
        return detectedVuMark;
    }
}
