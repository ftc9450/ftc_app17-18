package org.firstinspires.ftc.teamcode.util;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

/**
 * Created by dhruv on 12/30/17.
 */

@TeleOp(name = "Restring", group = "Setup")
public class Restring extends OpMode {
    private DcMotor glypht;
    private DcMotor relic;

    @Override
    public void init() {
        glypht = hardwareMap.dcMotor.get("glypht");
        glypht.setDirection(DcMotorSimple.Direction.FORWARD);
        relic = hardwareMap.dcMotor.get("relic");
        relic.setDirection(DcMotorSimple.Direction.FORWARD);
    }

    @Override
    public void loop() {
        if (gamepad1.a) glypht.setPower(0.25);
        else if (gamepad1.b) glypht.setPower(-0.25);
        else glypht.setPower(0);

        if (gamepad1.x) relic.setPower(0.25);
        else if (gamepad1.y) relic.setPower(-0.25);
        else relic.setPower(0);
    }
}
