package org.firstinspires.ftc.teamcode.auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.subsystems.Drivetrain;
import org.firstinspires.ftc.teamcode.subsystems.Rudder;
import org.firstinspires.ftc.teamcode.util.*;

/**
 * @author Grace
 */
@Autonomous
@Disabled
@Deprecated
public class StateBaseAutoRed extends LinearOpMode{
    Drivetrain drivetrain;
    Rudder rudder;

    public void runOpMode() throws InterruptedException {
        drivetrain=new Drivetrain(hardwareMap.dcMotor.get(Constants.Drivetrain.LF), hardwareMap.dcMotor.get(Constants.Drivetrain.LB), hardwareMap.dcMotor.get(Constants.Drivetrain.RF), hardwareMap.dcMotor.get(Constants.Drivetrain.RB));
        rudder = new Rudder(hardwareMap.servo.get("rudder_servo"), hardwareMap.colorSensor.get("sensor_color_distance"));
        rudder.setState(Rudder.RudderState.START);rudder.loop();
        drivetrain.enableAndResetEncoders();
        telemetry.addData("status", "started");
        drivetrain.moveLR(-3*Constants.Drivetrain.INCH, -1); // move 18 inches backwards
        rudder.setState(Rudder.RudderState.OUT);rudder.loop();
        Thread.sleep(1000);
        // knock off red
        if(rudder.getColor()==Constants.Color.RED){
            drivetrain.pivot(-1000,-1);
            rudder.setState(Rudder.RudderState.IN);rudder.loop();
            Thread.sleep(1000);
            drivetrain.pivot(1000,1);
        }else{
            drivetrain.pivot(1000,1);
            Thread.sleep(1000);
            rudder.setState(Rudder.RudderState.IN);rudder.loop();
            drivetrain.pivot(-1000,-1);
        }
        drivetrain.moveFB(-36*Constants.Drivetrain.INCH,-0.5); // move 36 inches backwards
    }
}
