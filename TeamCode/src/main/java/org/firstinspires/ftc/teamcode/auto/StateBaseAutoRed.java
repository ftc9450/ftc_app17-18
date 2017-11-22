package org.firstinspires.ftc.teamcode.auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.SwitchableLight;

import org.firstinspires.ftc.teamcode.subsystems.Drivetrain;
import org.firstinspires.ftc.teamcode.subsystems.Rudder;
import org.firstinspires.ftc.teamcode.util.*;

/**
 * @author Grace
 */
@Autonomous
public class StateBaseAutoRed extends LinearOpMode{
    Drivetrain driveTrain=new Drivetrain(hardwareMap.dcMotor.get(Constants.Drivetrain.LF), hardwareMap.dcMotor.get(Constants.Drivetrain.LB), hardwareMap.dcMotor.get(Constants.Drivetrain.RF), hardwareMap.dcMotor.get(Constants.Drivetrain.RB));
    Rudder jewelRudder = new Rudder(hardwareMap.servo.get("jewelRudder"), hardwareMap.colorSensor.get("colorSensor"));

    public void runOpMode() throws InterruptedException {
        driveTrain.moveFB(0,0.5);

        // move jewel rudder down
        jewelRudder.setState(Rudder.RudderState.OUT);
        driveTrain.moveFB(-840, 0.5); // move 18 inches backwards

        // color detection-assumes that color sensor is mounted on left
        if (jewelRudder.getColor() == Constants.Color.RED) {
            driveTrain.moveLR(2*Constants.Drivetrain.INCH,0.5); // calibrate
        } else if(jewelRudder.getColor() == Constants.Color.BLUE){
            driveTrain.moveLR(-2*Constants.Drivetrain.INCH,0.5); // calibrate
        }
        jewelRudder.setState(Rudder.RudderState.IN);
        driveTrain.moveFB(36*Constants.Drivetrain.INCH,0.5); // move 36 inches forwards
        driveTrain.pivot(90*Constants.Drivetrain.DEGREE,0.5); // CALIBRATE ASAP!!!!!!! Supposed to be 90 degrees left
        driveTrain.moveFB(30*Constants.Drivetrain.INCH,0.5); // move 30 inches
    }
}
