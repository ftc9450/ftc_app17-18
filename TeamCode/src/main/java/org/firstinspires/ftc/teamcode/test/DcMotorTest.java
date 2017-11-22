package org.firstinspires.ftc.teamcode.test;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

/**
 * @author Grace
 */
@Autonomous
public class DcMotorTest extends LinearOpMode {
    DcMotor testMe = null;

    @Override
    public void runOpMode() throws InterruptedException {
        try {
            testMe=hardwareMap.dcMotor.get("frontLeft");
            testMe.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            testMe.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            testMe.setPower(0.5);
        } catch(Exception e) {
            telemetry.addData("errorMesssage","Definition error!");
        }

        testMe.setTargetPosition(1000);
        while(testMe.isBusy()){}
        testMe.setTargetPosition(0);while(testMe.isBusy()){}
    }


}
