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
        driveTrain.moveFB(0,0.5);
        //move jewel rudder down
        driveTrain.moveFB(-840,0.5);//move 18 inches backwards
        //Take out jewels-check with mechanical to see which way they put the servo
        //Do jewel rudder stuff
        driveTrain.moveFB(1680,0.5);//move 36 inches forwards
        driveTrain.pivot(-90,0.5);//CALIBRATE ASAP!!!!!!! Supposed to be 90 degrees left
        driveTrain.moveFB(1600,0.5);//move 30 inches
    }
}
