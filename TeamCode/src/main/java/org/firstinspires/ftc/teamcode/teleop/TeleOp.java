package org.firstinspires.ftc.teamcode.teleop;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.control.ControlBoard;
import org.firstinspires.ftc.teamcode.subsystems.Drivetrain;
import org.firstinspires.ftc.teamcode.subsystems.Elevator;
import org.firstinspires.ftc.teamcode.subsystems.Grabber;
import org.firstinspires.ftc.teamcode.subsystems.Subsystem;
import org.firstinspires.ftc.teamcode.subsystems.SubsystemManager;

/**
 * @author Grace
 */
@com.qualcomm.robotcore.eventloop.opmode.TeleOp
public class TeleOp extends OpMode{
    SubsystemManager subsystemManager=new SubsystemManager();
    ControlBoard controlBoard=new ControlBoard(gamepad1);
    Drivetrain drivetrain;
    Elevator elevator;
    Grabber grabber;
    @Override
    public void init() {
        drivetrain=new Drivetrain(hardwareMap.dcMotor.get("leftFront"), hardwareMap.dcMotor.get("leftBack"), hardwareMap.dcMotor.get("rightFront"), hardwareMap.dcMotor.get("rightBack"));
        subsystemManager.add(drivetrain);
    }

    @Override
    public void loop() {

        subsystemManager.loopSystems();
    }
}
