package org.firstinspires.ftc.teamcode.auto;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.subsystems.Drivetrain;

/**
 * Created by PinkUnicornRainbow on 11/9/2017.
 */

public class StateBaseAutoRed extends LinearOpMode{
    Drivetrain driveTrain=new Drivetrain(hardwareMap.dcMotor.get("leftFront"), hardwareMap.dcMotor.get("leftBack"), hardwareMap.dcMotor.get("rightFront"), hardwareMap.dcMotor.get("rightBack"));//1120 counts per revolution, wheel travels 12.56 in per revolution
    Servo jewelRudder=hardwareMap.servo.get("jewelRudder");
    ColorSensor color=hardwareMap.colorSensor.get("colorSensor");
    public void runOpMode() throws InterruptedException {

    }
}
