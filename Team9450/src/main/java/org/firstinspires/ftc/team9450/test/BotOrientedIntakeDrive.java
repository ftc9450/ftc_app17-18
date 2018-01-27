package org.firstinspires.ftc.team9450.test;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.team9450.sensors.Gyroscope;
import org.firstinspires.ftc.team9450.subsystems.Drivetrain;
import org.firstinspires.ftc.team9450.subsystems.Intake;
import org.firstinspires.ftc.team9450.subsystems.Ramp;
import org.firstinspires.ftc.team9450.subsystems.RampLifter;
import org.firstinspires.ftc.team9450.subsystems.Rudder;
import org.firstinspires.ftc.team9450.subsystems.SubsystemManager;
import org.firstinspires.ftc.team9450.util.Constants;
import org.firstinspires.ftc.team9450.util.DriveSignal;
import org.firstinspires.ftc.team9450.util.Vector2D;

/**
 * Created by dhruv on 1/20/18.
 */

@TeleOp
public class BotOrientedIntakeDrive extends OpMode {
    private Intake intake;
    private Drivetrain drive;
    SubsystemManager subsystemManager=new SubsystemManager();
    @Override
    public void init() {
        intake = new Intake(hardwareMap.dcMotor.get(Constants.Intake.LEFT), hardwareMap.dcMotor.get(Constants.Intake.RIGHT));
        subsystemManager.add(intake);
        drive = new Drivetrain(hardwareMap.dcMotor.get(Constants.Drivetrain.LF), hardwareMap.dcMotor.get(Constants.Drivetrain.LB), hardwareMap.dcMotor.get(Constants.Drivetrain.RF), hardwareMap.dcMotor.get(Constants.Drivetrain.RB));
        subsystemManager.add(drive);
    }

    @Override
    public void loop() {
        double x=Constants.floatToDouble(gamepad1.left_stick_x); double y=-1.0*Constants.floatToDouble(gamepad1.left_stick_y);
        telemetry.addData("xpos",x);telemetry.addData("ypos", y);
        DriveSignal driveSignal=DriveSignal.translate(Math.atan2(y,x),Math.sqrt(Math.pow(x,2)+Math.pow(y,2)));
        drive.setOpenLoop(driveSignal);
        if(gamepad1.a){intake.setState(Intake.IntakeState.IN);}
        else if(gamepad1.b){intake.setState(Intake.IntakeState.OUT);}
        else{intake.setState(Intake.IntakeState.OFF);}
        subsystemManager.loop();
    }
}
