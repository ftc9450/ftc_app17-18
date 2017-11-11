package org.firstinspires.ftc.teamcode.auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.SwitchableLight;

import org.firstinspires.ftc.teamcode.subsystems.Drivetrain;
import org.firstinspires.ftc.teamcode.subsystems.Rudder;
import org.firstinspires.ftc.teamcode.util.Constants;

/**
 * @author Grace
 */
@Autonomous
public class StateBaseAutoRed extends LinearOpMode{
    Drivetrain driveTrain=new Drivetrain(hardwareMap.dcMotor.get("leftFront"), hardwareMap.dcMotor.get("leftBack"), hardwareMap.dcMotor.get("rightFront"), hardwareMap.dcMotor.get("rightBack"));//1120 counts per revolution, wheel travels 12.56 in per revolution
    Rudder jewelRudder = new Rudder(hardwareMap.servo.get("jewelRudder"));
    ColorSensor color = hardwareMap.colorSensor.get("colorSensor");
    public void runOpMode() throws InterruptedException {
        driveTrain.moveFB(0,0.5);
        //move jewel rudder down
        jewelRudder.setState(Rudder.RudderState.OUT);
        driveTrain.moveFB(-840,0.5);//move 18 inches backwards
        // color detection-assumes that color sensor is mounted on left
        if (isRed()==Constants.Color.RED) {
            driveTrain.moveLR(100,0.5);//calibrate
        }else if(isRed()==Constants.Color.BLUE){
            driveTrain.moveLR(-100,0.5);//calibrate
        }
        jewelRudder.setState(Rudder.RudderState.IN);
        driveTrain.moveFB(1680,0.5);//move 36 inches forwards
        driveTrain.pivot(-90,0.5);//CALIBRATE ASAP!!!!!!! Supposed to be 90 degrees left
        driveTrain.moveFB(1600,0.5);//move 30 inches
    }

    public int isRed() {
        if(color instanceof SwitchableLight){((SwitchableLight) color).enableLight(true);}
        int r=color.red();int g=color.green();int b=color.blue(); int a=color.alpha();
        if(a<20||a>200){return Constants.Color.UNDECIDED;}//???? Check projected alpha values
        if(r>g*2&&r>b*2){return Constants.Color.RED;}
        if(b>g*2&&b>r*2){return Constants.Color.BLUE;}return Constants.Color.UNDECIDED;
    }
}
