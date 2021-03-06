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
@Disabled
@Deprecated
public class RudderTest extends OpMode {
    Rudder rudder;
    Drivetrain drivetrain;
    public void init() {
        rudder=new Rudder(hardwareMap.servo.get("rudder_servo"),hardwareMap.colorSensor.get("sensor_color_distance"));
        drivetrain=new Drivetrain(hardwareMap.dcMotor.get(Constants.Drivetrain.LF), hardwareMap.dcMotor.get(Constants.Drivetrain.LB), hardwareMap.dcMotor.get(Constants.Drivetrain.RF), hardwareMap.dcMotor.get(Constants.Drivetrain.RB));

    }

    @Override
    public void loop() {//knock out red
        if(gamepad1.a){
            rudder.setState(Rudder.RudderState.OUT);
            rudder.loop();
            try {Thread.sleep(1000);} catch (InterruptedException e) {}
            if(rudder.getColor()==1){
                drivetrain.pivot(1000,1);
            }else{
                drivetrain.pivot(-1000,-1);
            }
        }else{
            rudder.setState(Rudder.RudderState.START);
            rudder.loop();
        }
        telemetry.addData("rudder pos",rudder.rudderServoPos());
        telemetry.addData("state",rudder.getState());
        telemetry.addData("color",rudder.getColor());
    }
}
