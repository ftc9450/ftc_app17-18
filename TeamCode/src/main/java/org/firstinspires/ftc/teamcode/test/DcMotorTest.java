package org.firstinspires.ftc.teamcode.test;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.control.ControlBoard;

/**
 * @author Grace
 */
@TeleOp
public class DcMotorTest extends OpMode {
    DcMotor testMe = null;
    @Override
    public void init() {
        testMe=hardwareMap.dcMotor.get("name");
        testMe.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        testMe.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    @Override
    public void loop() {
        if(gamepad1.y){
            testMe.setPower(1);
        }else{testMe.setPower(0);}
        telemetry.addData("encoder counts",testMe.getCurrentPosition());
    }
}
