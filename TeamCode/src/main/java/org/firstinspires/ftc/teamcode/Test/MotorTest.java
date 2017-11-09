package org.firstinspires.ftc.teamcode.Test;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

/**
 * Created by PinkUnicornRainbow on 11/9/2017.
 */

public class MotorTest extends LinearOpMode {
    DcMotor testMe=hardwareMap.dcMotor.get("1");
    public void runOpMode() throws InterruptedException {
        testMe.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        testMe.setMode(DcMotor.RunMode.RUN_TO_POSITION);testMe.setTargetPosition(10000000);while(testMe.isBusy()){}
    }
}
