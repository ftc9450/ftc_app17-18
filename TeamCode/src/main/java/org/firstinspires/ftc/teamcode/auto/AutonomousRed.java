package org.firstinspires.ftc.teamcode.auto;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.SwitchableLight;

import static android.R.transition.move;

/**
 * Created by PinkUnicornRainbow on 10/28/2017.
 */

public class AutonomousRed extends LinearOpMode{
    DcMotor leftFront=hardwareMap.dcMotor.get("leftFront");
    DcMotor leftBack=hardwareMap.dcMotor.get("leftBack");
    DcMotor rightFront=hardwareMap.dcMotor.get("rightFront");
    DcMotor rightBack=hardwareMap.dcMotor.get("rightBack");//1120 counts per revolution, wheel travels 12.56 in per revolution
    Servo jewelRudder=hardwareMap.servo.get("jewelRudder");
    ColorSensor leftColor=hardwareMap.colorSensor.get("leftColor");
    ColorSensor rightColor=hardwareMap.colorSensor.get("rightColor");
    public void initBot(){
        leftFront.setDirection(DcMotor.Direction.REVERSE);leftBack.setDirection(DcMotor.Direction.REVERSE);
        leftFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);leftBack.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);rightBack.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    }
    public void resetMotors(){
        leftFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);leftBack.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);rightBack.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftFront.setMode(DcMotor.RunMode.RUN_TO_POSITION);leftBack.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightFront.setMode(DcMotor.RunMode.RUN_TO_POSITION);rightBack.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }
    public void moveFB(int distance, double power){
        resetMotors();
        leftFront.setPower(power);leftBack.setPower(power);rightFront.setPower(power);rightBack.setPower(power);
        leftFront.setTargetPosition(distance);leftBack.setTargetPosition(distance);rightFront.setTargetPosition(distance);rightBack.setTargetPosition(distance);
        while(leftFront.isBusy()||leftBack.isBusy()||rightFront.isBusy()||rightBack.isBusy()){}
    }
    public void pivot(int distance, double power){
        resetMotors();
        leftFront.setPower(power);leftBack.setPower(power);rightFront.setPower(-1*power);rightBack.setPower(-1*power);
        leftFront.setTargetPosition(distance);leftBack.setTargetPosition(distance);rightFront.setTargetPosition(distance);rightBack.setTargetPosition(distance);
        while(leftFront.isBusy()||leftBack.isBusy()||rightFront.isBusy()||rightBack.isBusy()){}
    }
    public String readJewel(ColorSensor c){
        if(c instanceof SwitchableLight){((SwitchableLight) c).enableLight(true);}
        int r=c.red();int g=c.green();int b=c.blue(); int a=c.alpha();
        if(a<20||a>200){return "BLACK";}//???? Check projected alpha values
        if(r>g+(r/2)&&r>b+(r/2)){return "RED";}
        if(b>g+(b/2)&&b>r+(b/2)){return "BLUE";}return "BLACK";
    }
    public void runOpMode() throws InterruptedException {
        moveFB(0,0.5);
        jewelRudder.setPosition(90);
        moveFB(-840,0.5);//move 18 inches backwards
        //Take out jewels-check with mechanical to see which way they put the servo
        //Conservatively designed-if the sensors aren't confident that it knocked off the correct one, it will take out the other one
        if((readJewel(leftColor).equals("RED")&&readJewel(rightColor).equals("BLUE"))||(readJewel(leftColor).equals("BLUE")&&readJewel(rightColor).equals("RED"))){
            if(readJewel(leftColor).equals("RED")){
                jewelRudder.setPosition(0);
                if(!readJewel(leftColor).equals("RED")){
                    jewelRudder.setPosition(180);
                }
            }else if(readJewel(rightColor).equals("RED")){
                jewelRudder.setPosition(180);
                if(!readJewel(rightColor).equals("RED")){
                    jewelRudder.setPosition(0);
                }
            }
        }
        moveFB(1680,0.5);//move 36 inches forwards
        pivot(-90,0.5);//CALIBRATE ASAP!!!!!!! Supposed to be 90 degrees left
        moveFB(1600,0.5);//move 30 inches
    }
}
