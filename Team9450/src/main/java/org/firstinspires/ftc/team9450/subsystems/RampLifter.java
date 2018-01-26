package org.firstinspires.ftc.team9450.subsystems;

import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.team9450.util.Constants;

/**
 * Created by Grace on 1/22/2018.
 */
@Deprecated
public class RampLifter extends Subsystem{
    DcMotor rampMotor;
    private double speed= Constants.RampLifter.power;
    public enum RampLifterState{
        UP,DOWN,OFF
    }
    private RampLifterState state;
    public void stop() {rampMotor.setPower(0);}
    public void setRampLifterState(RampLifterState state){this.state=state;}
    @Override
    public void loop() {
        switch(state){
            case UP:
                if(rampMotor.getCurrentPosition()<Constants.RampLifter.maxPos) {
                    rampMotor.setPower(speed);
                }else{stop();}
                break;
            case DOWN:
                if(rampMotor.getCurrentPosition()>=0) {
                    rampMotor.setPower(-1 * speed);
                }else{stop();}
                break;
            case OFF:
            default:
                stop();
        }
    }
}
