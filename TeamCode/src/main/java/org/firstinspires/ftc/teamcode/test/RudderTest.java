package org.firstinspires.ftc.teamcode.test;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.subsystems.Drivetrain;
import org.firstinspires.ftc.teamcode.subsystems.Rudder;
import org.firstinspires.ftc.teamcode.util.Constants;

/**
 * Created by Grace on 12/16/2017.
 */
@TeleOp
public class RudderTest extends OpMode {
    Rudder rudder;
    Drivetrain drivetrain;
    public void init() {
        rudder=new Rudder(hardwareMap.servo.get("rudder_servo"),hardwareMap.colorSensor.get("sensor_color_distance"));
        drivetrain=new Drivetrain(hardwareMap.dcMotor.get(Constants.Drivetrain.LF), hardwareMap.dcMotor.get(Constants.Drivetrain.LB), hardwareMap.dcMotor.get(Constants.Drivetrain.RF), hardwareMap.dcMotor.get(Constants.Drivetrain.RB));

    }

    @Override
    public void loop() {
        if(gamepad1.a){
            rudder.setState(Rudder.RudderState.OUT);
            if(rudder.getColor()==1){
                drivetrain.pivot(10,1);
            }else{
                drivetrain.pivot(-10,-1);
            }
        }else{
            rudder.setState(Rudder.RudderState.IN);}
        rudder.loop();
        telemetry.addData("state",rudder);
        telemetry.addData("color",rudder.getColor());
    }
}
