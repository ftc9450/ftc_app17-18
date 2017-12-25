package org.firstinspires.ftc.teamcode.test;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.subsystems.Rudder;
import org.firstinspires.ftc.teamcode.subsystems.SubsystemManager;
import org.firstinspires.ftc.teamcode.util.Constants;

/**
 * Created by dhruv on 11/22/17.
 */

@Autonomous
@Disabled
public class ColorSensorTest extends OpMode {
    private Rudder rudder;

    @Override
    public void init() {
        try {
            rudder = new Rudder(hardwareMap.servo.get("rudder_servo"), hardwareMap.colorSensor.get("sensor_color_distance"));
        } catch (Exception e) {
            telemetry.addData("errorMesssage","Definition error!");
        }
    }

    @Override
    public void loop() {
        String color = "def";
        switch (rudder.getColor()) {
            case Constants.Color.RED:
                color = "RED";
                break;
            case Constants.Color.BLUE:
                color = "BLUE";
                break;
            case Constants.Color.UNDECIDED:
                color = "???";
                break;
        }
        telemetry.addData("colorValue", color);
    }
}
