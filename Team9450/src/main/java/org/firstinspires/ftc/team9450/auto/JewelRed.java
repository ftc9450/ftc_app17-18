package org.firstinspires.ftc.team9450.auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import org.firstinspires.ftc.team9450.subsystems.Rudder;
import org.firstinspires.ftc.team9450.util.Constants;

/**
 * Created by dhruv on 1/27/18.
 */

@Autonomous
public class JewelRed extends LinearOpMode {
    Rudder rudder;
    @Override
    public void runOpMode() throws InterruptedException {
        rudder = new Rudder(hardwareMap.servo.get(Constants.Rudder.RUDDERTOP), hardwareMap.servo.get(Constants.Rudder.RUDDERBOTTOM), hardwareMap.colorSensor.get(Constants.Rudder.COLOR));

        waitForStart();
        rudder.setRudderState(Rudder.RudderState.IN);
        rudder.loop();
        Thread.sleep(1000);
        rudder.setLateralState(Rudder.LateralState.NEUTRAL);
        rudder.loop();
        Thread.sleep(1000);
        rudder.setRudderState(Rudder.RudderState.OUT);
        rudder.loop();
        Thread.sleep(1000);
        int color = rudder.getColor();
        if (color == Constants.Color.BLUE) {
            rudder.setLateralState(Rudder.LateralState.FORWARDS);
            rudder.loop();
            Thread.sleep(500);
        } else if (color == Constants.Color.RED) {
            rudder.setLateralState(Rudder.LateralState.BACKWARDS);
            rudder.loop();
            Thread.sleep(500);
        }
        rudder.setRudderState(Rudder.RudderState.IN);
        rudder.setLateralState(Rudder.LateralState.NEUTRAL);
        rudder.loop();
        Thread.sleep(500);

    }
}
