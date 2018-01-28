package org.firstinspires.ftc.team9450.test;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.team9450.subsystems.Ramp;

/**
 * Created by Grace on 1/27/2018.
 */
@TeleOp
public class RampAndTriggerTest extends OpMode{
    Ramp ramp;
    public void init() {
        ramp = new Ramp(hardwareMap.servo.get("ramp"), hardwareMap.dcMotor.get("ramp_lifter"));

    }

    @Override
    public void loop() {
        if (gamepad2.left_trigger > 0.25 && gamepad2.right_trigger > 0.25) {
            ramp.setRampState(Ramp.RampState.LEVEL);
        } else if (gamepad2.left_trigger > 0.5) {
            ramp.setRampState(Ramp.RampState.IN);
        }  else if (gamepad1.right_trigger > 0.5) {
            ramp.setRampState(Ramp.RampState.OUT);
        }
        telemetry.addData("state",ramp);
    }
}
