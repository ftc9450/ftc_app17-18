package org.firstinspires.ftc.team9450.teleop;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.team9450.subsystems.Ramp;
import org.firstinspires.ftc.team9450.subsystems.RelicArm;
import org.firstinspires.ftc.team9450.subsystems.Rudder;
import org.firstinspires.ftc.team9450.subsystems.SubsystemManager;
import org.firstinspires.ftc.team9450.util.Constants;

/**
 * Created by dhruv on 1/27/18.
 */

public class Operator extends OpMode {
    Ramp ramp;
    RelicArm arm;
    Rudder rudder;
    SubsystemManager manager;
    @Override
    public void init() {
        ramp = new Ramp(hardwareMap.servo.get("ramp"), hardwareMap.dcMotor.get("ramp_lifter"));
        arm = new RelicArm(hardwareMap.dcMotor.get("arm"), hardwareMap.servo.get("pivot"), hardwareMap.crservo.get("hand"));
        rudder = new Rudder(hardwareMap.servo.get(Constants.Rudder.RUDDERTOP), hardwareMap.servo.get(Constants.Rudder.RUDDERBOTTOM),hardwareMap.colorSensor.get(Constants.Rudder.COLOR));
        manager = new SubsystemManager();
        manager.add(ramp).add(arm).add(rudder);
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

        arm.setPower(-gamepad2.left_stick_y);

        if (gamepad2.right_bumper) {
            arm.setStandardpivot(RelicArm.PivotState.OUT);
        } else if (gamepad2.left_bumper) {
            arm.setStandardpivot(RelicArm.PivotState.IN);
        } else {
            arm.setStandardpivot(RelicArm.PivotState.OFF);
        }

        if (gamepad2.a) {
            arm.setCrhand(RelicArm.HandState.OPEN);
        } else if (gamepad2.b) {
            arm.setCrhand(RelicArm.HandState.CLOSE);
        }else{
            arm.setCrhand(RelicArm.HandState.OFF);
        }

        if (gamepad2.right_stick_y < 0.5) {
            ramp.setLiftState(Ramp.LiftState.UP);
        } else if (gamepad2.right_stick_y > 0.5) {
            ramp.setLiftState(Ramp.LiftState.DOWN);
        } else {
            ramp.setLiftState(Ramp.LiftState.OFF);
        }

        rudder.setLateralState(Rudder.LateralState.NEUTRAL);
        rudder.setRudderState(Rudder.RudderState.START);

        manager.loop();
    }
}
