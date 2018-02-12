package org.firstinspires.ftc.team9450.test;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.team9450.subsystems.Ramp;
import org.firstinspires.ftc.team9450.subsystems.RelicArm;

/**
 * Created by dhruv on 2/11/18.
 */

@TeleOp
public class EncoderCalibration extends OpMode {
    private Ramp ramp;
    private RelicArm arm;
    @Override
    public void init() {
        ramp = new Ramp(hardwareMap.servo.get("ramp"), hardwareMap.dcMotor.get("lift"), hardwareMap.digitalChannel.get("touch"));
        arm = new RelicArm(hardwareMap.dcMotor.get("arm"), hardwareMap.crservo.get("pivot"), hardwareMap.crservo.get("hand"));
    }

    @Override
    public void loop() {
        ramp.setRampState(Ramp.RampState.IN);

        if (gamepad1.dpad_up) {
            ramp.setLiftState(Ramp.LiftState.UP);
        } else if (gamepad1.dpad_down) {
            ramp.setLiftState(Ramp.LiftState.DOWN);
        } else {
            ramp.setLiftState(Ramp.LiftState.OFF);
        }

        arm.setPower(gamepad1.left_stick_y);

        telemetry.addData("lift", ramp.getPosition());
        telemetry.addData("arm", arm.getPosition());
    }
}
