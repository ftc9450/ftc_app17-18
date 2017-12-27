package org.firstinspires.ftc.teamcode.test;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.control.ControlBoard;
import org.firstinspires.ftc.teamcode.subsystems.Elevator;
import org.firstinspires.ftc.teamcode.subsystems.Grabber;
import org.firstinspires.ftc.teamcode.subsystems.SubsystemManager;
import org.firstinspires.ftc.teamcode.util.Constants;

import java.util.ResourceBundle;

/**
 * Created by Grace on 12/27/2017.
 */
@TeleOp
public class GlyphtTest extends OpMode{
    Elevator elevator;
    Grabber topGrabber;
    Grabber bottomGrabber;
    ControlBoard controlBoard;
    SubsystemManager subsystemManager;
    public void init() {
        controlBoard=new ControlBoard(gamepad1);
        elevator=new Elevator(hardwareMap.dcMotor.get(Constants.Elevator.ELEVATOR));
        subsystemManager.add(elevator);
        topGrabber=new Grabber(hardwareMap.servo.get(Constants.Grabber.LT),hardwareMap.servo.get(Constants.Grabber.RT));
        subsystemManager.add(topGrabber);
        bottomGrabber=new Grabber(hardwareMap.servo.get(Constants.Grabber.LB),hardwareMap.servo.get(Constants.Grabber.RB));
        subsystemManager.add(bottomGrabber);
    }

    @Override
    public void loop() {

        elevator.setState(controlBoard.elevatorCommand());
        topGrabber.setState(controlBoard.topGrabberCommand());
        bottomGrabber.setState(controlBoard.bottomGrabberCommand());
        subsystemManager.loopSystems();

    }
}
