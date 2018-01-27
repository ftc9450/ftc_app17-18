package org.firstinspires.ftc.team9450.teleop;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.team9450.sensors.Gyroscope;
import org.firstinspires.ftc.team9450.sensors.Vuforia;
import org.firstinspires.ftc.team9450.subsystems.Drivetrain;
import org.firstinspires.ftc.team9450.subsystems.Intake;
import org.firstinspires.ftc.team9450.subsystems.Ramp;
import org.firstinspires.ftc.team9450.subsystems.Rudder;
import org.firstinspires.ftc.team9450.util.Constants;
import org.firstinspires.ftc.teamcode.control.ControlBoard;
import org.firstinspires.ftc.teamcode.subsystems.Drivetrain;
import org.firstinspires.ftc.teamcode.subsystems.Elevator;
import org.firstinspires.ftc.teamcode.subsystems.Grabber;
import org.firstinspires.ftc.teamcode.subsystems.RelicArm;
import org.firstinspires.ftc.teamcode.subsystems.Rudder;
import org.firstinspires.ftc.teamcode.subsystems.SubsystemManager;
import org.firstinspires.ftc.teamcode.util.Constants;
import org.firstinspires.ftc.teamcode.util.DriveSignal;

/**
 * Created by Grace on 12/26/2017.
 */
@TeleOp
public class TwoPersonTeleOp extends OpMode{
    ControlBoard controlBoard1;
    ControlBoard controlBoard2;
    Drivetrain drivetrain;
    RelicArm relicArm;
    Intake intake;
    Rudder rudder;
    Ramp ramp;
    SubsystemManager subsystemManager;

    public void init() {
        subsystemManager=new SubsystemManager();
        controlBoard1=new ControlBoard(gamepad1);
        controlBoard2=new ControlBoard(gamepad2);

        intake = new Intake(hardwareMap.dcMotor.get(Constants.Intake.LEFT), hardwareMap.dcMotor.get(Constants.Intake.RIGHT));
        manager.add(intake);
        drivetrain = new Drivetrain(hardwareMap.dcMotor.get(Constants.Drivetrain.LF), hardwareMap.dcMotor.get(Constants.Drivetrain.LB), hardwareMap.dcMotor.get(Constants.Drivetrain.RF), hardwareMap.dcMotor.get(Constants.Drivetrain.RB));
        ramp = new Ramp(hardwareMap.servo.get(Constants.Ramp.RAMP), hardwareMap.dcMotor.get(Constants.Ramp.LIFT));
        manager.add(ramp);

        //drivetrain=new Drivetrain(hardwareMap.dcMotor.get(Constants.Drivetrain.LF), hardwareMap.dcMotor.get(Constants.Drivetrain.LB), hardwareMap.dcMotor.get(Constants.Drivetrain.RF), hardwareMap.dcMotor.get(Constants.Drivetrain.RB));
        rudder = new Rudder(hardwareMap.servo.get(Constants.Rudder.RUDDERTOP), hardwareMap.servo.get(Constants.Rudder.RUDDERBOTTOM),hardwareMap.colorSensor.get(Constants.Rudder.COLOR));
        //ramp=new Ramp(hardwareMap.servo.get(Constants.Ramp.RAMP));
        //vuforia=new Vuforia(hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId","id",hardwareMap.appContext.getPackageName()));
        //rudder.setRudderState(Rudder.RudderState.START);rudder.loop();
        //intake=new Intake(hardwareMap.dcMotor.get(Constants.Intake.LEFT), hardwareMap.dcMotor.get(Constants.Intake.RIGHT));
        imu=new Gyroscope(hardwareMap.get(BNO055IMU.class, "imu"));
        drivetrain.enableAndResetEncoders();

        //drivetrain=new Drivetrain(hardwareMap.dcMotor.get(Constants.Drivetrain.LF), hardwareMap.dcMotor.get(Constants.Drivetrain.LB), hardwareMap.dcMotor.get(Constants.Drivetrain.RF), hardwareMap.dcMotor.get(Constants.Drivetrain.RB));
        //drivetrain.disconnectEncoders();
        //rudder=new Rudder(hardwareMap.servo.get("rudder_servo"), hardwareMap.colorSensor.get("color"));
        manager.add(rudder);


        //relicArm=new RelicArm(hardwareMap.dcMotor.get(Constants.RelicArm.ARM),hardwareMap.crservo.get(Constants.RelicArm.LEFTPIVOT), hardwareMap.crservo.get(Constants.RelicArm.RIGHTPIVOT),hardwareMap.servo.get(Constants.RelicArm.HAND));
    }

    public void loop() {

        //if(controlBoard2.moveDownSixInches()){elevator.moveDownSixInches();}
        //if(controlBoard2.moveUpSixInches()){elevator.moveUpSixInches();}
        elevator.setState(controlBoard2.elevatorCommand());
        topGrabber.setState(controlBoard2.topGrabberCommand());
        bottomGrabber.setState(controlBoard2.bottomGrabberCommand());
        DriveSignal d;
        /*DriveSignal translate=controlBoard1.translate();
        DriveSignal turn=controlBoard1.turn();
        if(controlBoard1.flip()){
            drivetrain.pivot(Constants.Drivetrain.INCH*12,0.5);
            drivetrain.disconnectEncoders();
        }
        if(turn.isZero()){
            d=translate;
        }else if(translate.isZero()){
            d=turn;
        }else{
            d=DriveSignal.BRAKE;
        }
        d.scale(controlBoard1.reduceDriveSpeed());*/
        double x = gamepad1.left_stick_x + (gamepad1.dpad_left?-0.5:0) + (gamepad1.dpad_right?0.5:0);
        double y = gamepad1.left_stick_y + (gamepad1.dpad_down?0.5:0) + (gamepad1.dpad_up?-0.5:0);
        double z = gamepad1.right_stick_x + gamepad1.right_trigger/2 - gamepad1.left_trigger/2;

        d = new DriveSignal(x - y + z, -x - y + z, -x - y - z, x - y - z);
        drivetrain.setOpenLoop(d);
        rudder.setState(Rudder.RudderState.IN);
        relicArm.setHumerus(controlBoard2.relicCommand());
        relicArm.setPollex(controlBoard2.handCommand());
        relicArm.setCarpals(controlBoard2.pivotCommand());
        telemetry.addData("glypht position: ",elevator);
        telemetry.addData("relic arm position: ",relicArm);
        subsystemManager.loopSystems();
    }
}
