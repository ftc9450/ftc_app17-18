package org.firstinspires.ftc.teamcode.teleop;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.eventloop.opmode.*;

import org.firstinspires.ftc.robotcore.external.Const;
import org.firstinspires.ftc.teamcode.control.ControlBoard;
import org.firstinspires.ftc.teamcode.sensors.Gyroscope;
import org.firstinspires.ftc.teamcode.subsystems.Drivetrain;
import org.firstinspires.ftc.teamcode.subsystems.Elevator;
import org.firstinspires.ftc.teamcode.subsystems.Grabber;
import org.firstinspires.ftc.teamcode.subsystems.RelicArm;
import org.firstinspires.ftc.teamcode.subsystems.SubsystemManager;
import org.firstinspires.ftc.teamcode.util.Constants;

/**
 * Created by Grace on 12/26/2017.
 */
@com.qualcomm.robotcore.eventloop.opmode.TeleOp
public class TeleOpFinal extends OpMode{
    ControlBoard controlBoard1;
    ControlBoard controlBoard2;
    Drivetrain drivetrain;
    RelicArm relicArm;
    //Elevator elevator;
    Grabber top;
    Grabber bottom;
    SubsystemManager subsystemManager;
    public void init() {
        controlBoard1=new ControlBoard(gamepad1);
        controlBoard2=new ControlBoard(gamepad2);
        drivetrain=new Drivetrain(hardwareMap.dcMotor.get(Constants.Drivetrain.LF), hardwareMap.dcMotor.get(Constants.Drivetrain.LB), hardwareMap.dcMotor.get(Constants.Drivetrain.RF), hardwareMap.dcMotor.get(Constants.Drivetrain.RB));
        drivetrain.disconnectEncoders();
        top=new Grabber(hardwareMap.servo.get(Constants.Grabber.LT),hardwareMap.servo.get(Constants.Grabber.RT));
        subsystemManager.add(top);
        bottom=new Grabber(hardwareMap.servo.get(Constants.Grabber.LB),hardwareMap.servo.get(Constants.Grabber.RB));
        subsystemManager.add(bottom);
        relicArm=new RelicArm(hardwareMap.dcMotor.get(Constants.RelicArm.ARM),hardwareMap.servo.get(Constants.RelicArm.HAND));
        subsystemManager.add(relicArm);

    }

    public void loop() {

    }
}
