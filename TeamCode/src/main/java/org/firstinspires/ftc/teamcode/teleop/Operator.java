package org.firstinspires.ftc.teamcode.teleop;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.subsystems.Elevator;
import org.firstinspires.ftc.teamcode.subsystems.Grabber;
import org.firstinspires.ftc.teamcode.subsystems.RelicArm;
import org.firstinspires.ftc.teamcode.subsystems.SubsystemManager;

/**
 * Created by dhruv on 12/24/17.
 */

public class Operator extends LinearOpMode {
    private Elevator elevator;
    private Grabber grabber;
    private RelicArm arm;
    private SubsystemManager manager;

    @Override
    public void runOpMode() throws InterruptedException {
        elevator = new Elevator(hardwareMap.dcMotor.get("elevator"));
        grabber = new Grabber(hardwareMap.servo.get(""), hardwareMap.servo.get(""));
        arm = new RelicArm(hardwareMap.dcMotor.get("arm"), hardwareMap.servo.get("hand"));

        waitForStart();

        while (opModeIsActive()) {
            if (gamepad2.left_stick_y > 0) {
                elevator.setState(Elevator.ElevatorState.UP);
            } else if (gamepad2.left_stick_y < 0) {
                elevator.setState(Elevator.ElevatorState.DOWN);
            } else {
                elevator.setState(Elevator.ElevatorState.OFF);
            }

            if (gamepad2.right_stick_y > 0) {
                arm.setState(RelicArm.RelicArmState.OUT);
            } else if (gamepad2.right_stick_y < 0) {
                arm.setState(RelicArm.RelicArmState.IN);
            } else {
                arm.setState(RelicArm.RelicArmState.OFF);
            }

            if (gamepad2.right_trigger > 0.1) {
                grabber.setState(Grabber.GrabberState.CLOSED);
            } else if (gamepad2.left_trigger > 0.1) {
                grabber.setState(Grabber.GrabberState.OPEN);
            }

            if (gamepad2.right_bumper) {
                arm.setHand(RelicArm.HandState.CLOSED);
            } else if (gamepad2.left_bumper) {
                arm.setHand(RelicArm.HandState.OPEN);
            }
        }
    }
}
