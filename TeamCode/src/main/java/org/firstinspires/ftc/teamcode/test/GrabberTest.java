package org.firstinspires.ftc.teamcode.test;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.control.ControlBoard;
import org.firstinspires.ftc.teamcode.subsystems.Grabber;
import org.firstinspires.ftc.teamcode.subsystems.SubsystemManager;
import org.firstinspires.ftc.teamcode.util.Constants;

/**
 * Created by Grace on 11/18/2017.
 */
@TeleOp
@Disabled
public class GrabberTest extends OpMode {
    ControlBoard controlBoard=new ControlBoard(gamepad1);
    Grabber grabber;
    SubsystemManager subsystemManager=new SubsystemManager();
    public void init() {
        grabber=new Grabber(hardwareMap.servo.get(Constants.Grabber.L), hardwareMap.servo.get(Constants.Grabber.R));
        subsystemManager.add(grabber);
    }

    @Override
    public void loop() {
        grabber.setState(controlBoard.grabberCommand());
        subsystemManager.loopSystems();
    }
}
