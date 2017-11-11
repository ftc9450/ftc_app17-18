package org.firstinspires.ftc.teamcode.test;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

/**
 * @author Grace
 */
@TeleOp
public class DcMotorTest extends LinearOpMode {
    public void runOpMode() throws InterruptedException {
        DcMotor testMe = null;
        try {
            testMe=hardwareMap.dcMotor.get("motor");
        } catch(Exception e) {
            telemetry.addData("errorMesssage","Definition error!");
        }

        testMe.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        testMe.setPower(0.5);
    }
}
