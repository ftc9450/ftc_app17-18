package org.firstinspires.ftc.team9450.test;

import android.graphics.Color;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;

import org.firstinspires.ftc.team9450.subsystems.Rudder;
import org.firstinspires.ftc.team9450.util.Constants;

@Autonomous
public class ColorTest extends OpMode {
    private ColorSensor color;
    public void init() {
        color = hardwareMap.colorSensor.get(Constants.Rudder.COLOR);

    }

    @Override
    public void loop() {
        float[] colors= new float[3];
        Color.RGBToHSV(color.red(),color.green(),color.blue(),colors);

        telemetry.addData("Hue", colors[0]);
        telemetry.addData("Saturation", colors[1]);
        telemetry.addData("Variance", colors[2]);
    }
}
