package org.firstinspires.ftc.teamcode.test;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * Created by dhruv on 12/20/17.
 */

@Autonomous
public class RelicTest extends OpMode {
    private Servo pivot;
    private Servo hand;

    @Override
    public void init() {
        pivot = hardwareMap.servo.get("pivot");
        hand = hardwareMap.servo.get("hand");

        pivot.setDirection(Servo.Direction.FORWARD);
        hand.setDirection(Servo.Direction.FORWARD);
    }

    @Override
    public void loop() {
        pivot.setPosition(Servo.MAX_POSITION);
        hand.setPosition(Servo.MAX_POSITION);
    }
}
