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
public class GrabberTest extends OpMode {
    ControlBoard controlBoard;
    Grabber grabber;
    SubsystemManager subsystemManager;
    public void init() {
        controlBoard = new ControlBoard(gamepad1);
        subsystemManager = new SubsystemManager();
        grabber=new Grabber(hardwareMap.servo.get(Constants.Grabber.LT), hardwareMap.servo.get(Constants.Grabber.RT));
        subsystemManager.add(grabber);
    }

    @Override
    public void loop() {
        grabber.setState(controlBoard.grabberCommand());
        subsystemManager.loopSystems();
    }
}
