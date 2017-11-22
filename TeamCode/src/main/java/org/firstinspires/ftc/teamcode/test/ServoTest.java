package org.firstinspires.ftc.teamcode.test;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.Servo;

import java.util.concurrent.TimeUnit;

/**
 * Created by Grace on 11/22/2017.
 */
@Autonomous
public class ServoTest extends LinearOpMode {

    @Override
    public void runOpMode() throws InterruptedException {
        CRServo testMe=hardwareMap.crservo.get("servo1");
        testMe.setPower(-1);

    }
}
