package org.firstinspires.ftc.teamcode.test;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.subsystems.Elevator;
import org.firstinspires.ftc.teamcode.util.Constants;

/**
 * Created by dhruv on 12/9/17.
 */

@TeleOp
public class ElevatorTest extends OpMode {
    private Elevator elevator;

    @Override
    public void init() {
        elevator = new Elevator(hardwareMap.dcMotor.get("glyph"));
    }

    @Override
    public void loop() {
        switch (Math.round(gamepad1.left_stick_x)) {
            case 1:
                elevator.setState(Elevator.ElevatorState.UP);
                break;
            case -1:
                elevator.setState(Elevator.ElevatorState.DOWN);
                break;
            default:
                break;
        }
    }
}
