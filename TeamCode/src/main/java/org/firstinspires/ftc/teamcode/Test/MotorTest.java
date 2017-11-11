package org.firstinspires.ftc.teamcode.Test;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.robotcore.external.Telemetry;

import static com.sun.tools.javac.jvm.ByteCodes.error;

/**
 * Created by PinkUnicornRainbow on 11/9/2017.
 */
@TeleOp
public class MotorTest extends LinearOpMode {
    public void runOpMode() throws InterruptedException {
        DcMotor testMe=null;
        try{testMe=hardwareMap.dcMotor.get("motor");}catch(Exception e){telemetry.addData("errorMesssage","Definition error!");}
        testMe.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        testMe.setMode(DcMotor.RunMode.RUN_TO_POSITION);//testMe.setTargetPosition(10000000);while(testMe.isBusy()){}
    }
}
