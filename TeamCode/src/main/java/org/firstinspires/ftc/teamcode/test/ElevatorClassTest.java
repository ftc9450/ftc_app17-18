package org.firstinspires.ftc.teamcode.test;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.control.ControlBoard;
import org.firstinspires.ftc.teamcode.subsystems.RelicArm;
import org.firstinspires.ftc.teamcode.util.Constants;

/**
 * Created by Grace on 12/13/2017.
 */
@TeleOp
public class ElevatorClassTest extends OpMode {
    RelicArm elevator;
    ControlBoard controlBoard;
    public void init() {
        elevator=new RelicArm(hardwareMap.dcMotor.get(Constants.Elevator.ELEVATOR));
        controlBoard=new ControlBoard(gamepad1);
    }

    @Override
    public void loop() {
        elevator.setState(controlBoard.elevatorCommand());
        telemetry.addData("elevator",elevator.getState());
        telemetry.addData("y",gamepad1.y);
        elevator.loop();
    }
}
