package org.firstinspires.ftc.teamcode.test;

import android.graphics.Color;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.NormalizedColorSensor;

/**
 * Created by Grace on 1/6/2018.
 */
@Autonomous
public class ColorSensorHSVTest extends OpMode {
    ColorSensor colorSensor;


    @Override
    public void init() {
        colorSensor=hardwareMap.colorSensor.get("sensor_color_distance");
    }

    @Override
    public void loop() {
        float[] colors= new float[3];
        Color.RGBToHSV(colorSensor.red(),colorSensor.green(),colorSensor.blue(),colors);
        telemetry.addData("red",colorSensor.red());
        telemetry.addData("green", colorSensor.green());
        telemetry.addData("blue",colorSensor.blue());
        telemetry.addData("hue",colors[0]);
        telemetry.addData("saturation",colors[1]);
        telemetry.addData("value",colors[2]);
        if (colors[1] > 50 && colors[2] > 45) {
            if (180 < colors[0] && colors[0] < 260) {
                telemetry.addData("jewel", "BLUE");
            } else if ((300 < colors[0] && colors[0] < 359) || colors[0] < 30) {
                telemetry.addData("jewel", "RED");
            } else {
                telemetry.addData("jewel", "???");
            }
        } else {
            telemetry.addData("jewel", "???");
        }
        telemetry.update();

    }
}
