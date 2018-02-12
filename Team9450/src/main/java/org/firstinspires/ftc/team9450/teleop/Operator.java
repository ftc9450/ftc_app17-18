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
        //ramp = new Ramp(hardwareMap.servo.get("ramp"), hardwareMap.dcMotor.get("ramp_lifter"));
        arm = new RelicArm(hardwareMap.dcMotor.get("arm"), hardwareMap.servo.get("pivot"), hardwareMap.crservo.get("hand"));
        rudder = new Rudder(hardwareMap.servo.get(Constants.Rudder.RUDDERTOP), hardwareMap.servo.get(Constants.Rudder.RUDDERBOTTOM),hardwareMap.colorSensor.get(Constants.Rudder.COLOR));
        manager = new SubsystemManager();
        manager.add(ramp).add(arm).add(rudder);
    }

    @Override
    public void loop() {

    }
}
