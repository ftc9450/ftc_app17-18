package org.firstinspires.ftc.teamcode.test;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;

/**
 * Created by Lan Xiang on 12/26/2017.
 */

@Autonomous
public class RGBTest extends OpMode {
    private ColorSensor color;

    @Override
    public void init() {
        color = hardwareMap.colorSensor.get("sensor_color_distance");
    }

    @Override
    public void loop() {
        telemetry.addData("RGB", color.red() + " " + color.green() + " " + color.blue());
    }
}
