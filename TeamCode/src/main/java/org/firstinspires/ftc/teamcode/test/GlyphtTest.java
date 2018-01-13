package org.firstinspires.ftc.teamcode.test;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
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
@Disabled
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
      /*  if(controlBoard.moveDownSixInches()){elevator.moveDownSixInches();}
        if(controlBoard.moveUpSixInches()){elevator.moveUpSixInches();}
        elevator.setState(controlBoard.elevatorCommand());
        Grabber.GrabberState top=controlBoard.topGrabberCommand(null);
        Grabber.GrabberState bottom=controlBoard.bottomGrabberCommand(null);
        if(top!=null){
            topGrabber.setState(top);
        }else{
            topGrabber.setState(topGrabber.getState());
        }
        if(bottom!=null){
            bottomGrabber.setState(bottom);
        }else{
            bottomGrabber.setState(bottomGrabber.getState());
        }
        subsystemManager.loopSystems();
*/
    }
}
