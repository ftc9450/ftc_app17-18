package org.firstinspires.ftc.teamcode.test;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.control.GameBoard;
import org.firstinspires.ftc.teamcode.subsystems.Drivetrain;
import org.firstinspires.ftc.teamcode.subsystems.SubsystemManager;
import org.firstinspires.ftc.teamcode.util.Constants;
import org.firstinspires.ftc.teamcode.util.DriveSignal;

/**
 * Created by Grace on 11/22/2017.
 */
@TeleOp
public class DriverControllerStupidity extends OpMode {
    GameBoard gb = new GameBoard(gamepad1);
    Drivetrain drivetrain;
    SubsystemManager subsystemManager;
    public void init() {
        drivetrain=new Drivetrain(hardwareMap.dcMotor.get(Constants.Drivetrain.LF), hardwareMap.dcMotor.get(Constants.Drivetrain.LB), hardwareMap.dcMotor.get(Constants.Drivetrain.RF), hardwareMap.dcMotor.get(Constants.Drivetrain.RB));
        //subsystemManager.add(drivetrain);
    }

    @Override
    public void loop() {
        //telemetry.addData("gamepad", gb.getLeftX());
        drivetrain.setOpenLoop(new DriveSignal(1,1,1,1));
        drivetrain.loop();
    }
}
