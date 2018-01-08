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
        telemetry.update();

    }
}
