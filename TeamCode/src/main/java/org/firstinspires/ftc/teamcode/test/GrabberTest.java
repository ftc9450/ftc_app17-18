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
    public void init() {
        controlBoard = new ControlBoard(gamepad1);
        grabber=new Grabber(hardwareMap.servo.get(Constants.Grabber.LT), hardwareMap.servo.get(Constants.Grabber.RT));
    }

    @Override
    public void loop() {
        if(gamepad1.a){
            grabber.autoClose();
        }
        if(gamepad1.b){
            grabber.autoOpen();
        }
    }
}
