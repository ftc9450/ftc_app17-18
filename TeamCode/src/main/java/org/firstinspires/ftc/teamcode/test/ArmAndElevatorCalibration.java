package org.firstinspires.ftc.teamcode.test;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

/**
 * Created by Grace on 12/25/2017.
 */
@TeleOp
public class ArmAndElevatorCalibration extends OpMode{
    DcMotor arm;
    DcMotor elevator;
    double armPower=1;
    double elevatorPower=1;
    public void init() {
        arm=hardwareMap.dcMotor.get("arm");
        elevator=hardwareMap.dcMotor.get("elevator");
    }

    @Override
    public void loop() {
        if(gamepad1.y){
            elevator.setPower(elevatorPower);
        }else{elevator.setPower(0);}
        if(gamepad1.a){
            elevator.setPower(-1.0*elevatorPower);
        }else{elevator.setPower(0);}
        if(gamepad1.x){
            arm.setPower(-1.0*armPower);
        }else{arm.setPower(0);}
        if(gamepad1.b){
            arm.setPower(armPower);
        }else{arm.setPower(0);}
        if(gamepad1.dpad_up&&elevatorPower<1){elevatorPower+=0.01;}
        if(gamepad1.dpad_down&&elevatorPower>0){elevatorPower-=0.01;}
        if(gamepad1.dpad_left&&armPower>0){armPower-=0.01;}
        if(gamepad1.dpad_right&&armPower<1){armPower+=0.01;}
        if(gamepad1.start){
            arm.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            arm.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            elevator.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            elevator.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        }
        if(gamepad1.left_bumper){
            if(arm.getDirection().equals(DcMotorSimple.Direction.FORWARD)){arm.setDirection(DcMotorSimple.Direction.REVERSE);}
            else{arm.setDirection(DcMotorSimple.Direction.FORWARD);}
        }
        if(gamepad1.right_bumper){
            if(elevator.getDirection().equals(DcMotorSimple.Direction.FORWARD)){elevator.setDirection(DcMotorSimple.Direction.REVERSE);}
            else{elevator.setDirection(DcMotorSimple.Direction.FORWARD);}
        }
        telemetry.addData("elevator direction",elevator.getDirection().toString());
        telemetry.addData("arm direction",arm.getDirection().toString());
        telemetry.addData("elevator position",elevator.getCurrentPosition());
        telemetry.addData("arm position",arm.getCurrentPosition());
        telemetry.addData("elevator power",elevatorPower);
        telemetry.addData("arm power",armPower);
    }
}
