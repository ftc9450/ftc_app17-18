package org.firstinspires.ftc.teamcode.test;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.subsystems.Rudder;

/**
 * Created by dhruv on 12/31/17.
 */

@TeleOp
public class RudderCalibration extends LinearOpMode {
    Rudder rudder;

    @Override
    public void runOpMode() throws InterruptedException {
        rudder = new Rudder(hardwareMap.servo.get("rudder_servo"),hardwareMap.colorSensor.get("sensor_color_distance"));
        waitForStart();

        while (opModeIsActive()) {
            if (gamepad1.a) {
                rudder.setState(Rudder.RudderState.OUT);
            } else if (gamepad1.b) {
                rudder.setState(Rudder.RudderState.IN);
            }
        }
    }
}
