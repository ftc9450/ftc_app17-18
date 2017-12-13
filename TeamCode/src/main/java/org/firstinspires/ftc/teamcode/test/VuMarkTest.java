package org.firstinspires.ftc.teamcode.test;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.teamcode.util.Constants;

/**
 * Created by dhruv on 12/13/17.
 */

@TeleOp
public class VuMarkTest extends OpMode {
    VuforiaLocalizer vuforia;
    VuforiaLocalizer.Parameters parameters;

    @Override
    public void init() {
        parameters = new VuforiaLocalizer.Parameters();
        parameters.vuforiaLicenseKey = Constants.Setup.VUFORIAKEY;
    }

    @Override
    public void loop() {

    }
}
