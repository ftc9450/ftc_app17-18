package org.firstinspires.ftc.team9450.util;

/**
 * Created by dhruv on 1/22/18.
 */

public class Constants {
    public class Drivetrain {
        public static final double HIGH_POWER = 0.75;
        public static final double LOW_POWER = 0.25;
        public static final int STRAFEINCH = 135;
        public static final int INCH = 90;//1120 cpr for neverest 40
        public static final int DEGREE = 18;
        public static final String LF="frontLeft";
        public static final String LB="backLeft";
        public static final String RF="frontRight";
        public static final String RB="backRight";
    }
    public class RelicArm {
        public static final String RELICARM="relicArm";
        public static final int maxPos = 30400;
        public static final int minPos = 0;
        public static final double power=1;
    }
    public class Intake{
        public static final String LEFT="left";
        public static final String RIGHT="right";
        public static final double power=0.75;
    }
    public class RampLifter{
        public static final String RAMPLIFTER="rampdc";
        public static final double power=1;
        public static final int maxPos=1000;
        public static final int minPos=0;
    }
    public class Ramp {
        public static final String RAMP = "ramp";
        public static final String LIFT = "lift";
        public static final double IN=0.2;
        public static final double OUT=0.7;
    }
    public class Setup {
        public static final String VUFORIA_KEY = "AYwm7lb/////AAAAGeQI9HT4B0R2unLNBq/DsId49BJr71nKGdfP8X8fnmtD0Jna47KLigPBytLYBjzOIl6uCfYWbIXHc3FqoabxIITohKJ4VvPispe5kGGFFJyQEIifEL1Bc511jOl00pyY2Tr/YOGwjGk7lSXQ0QrScHVaiwIOM3mUUlsv9Ethn1OCZB2AVhT91gnrUKryxBwfLCGjqpmYdWOVDsJTloDiowWMez0U42S9sILVevNguQXZqTr1uURaUx5Voy+2N6FVK5p4dvraac9+LD6YskUCLqWsK2XVruCpCsRWZxfrqylNyni2ll5AW3Mekw/hSSzfjx70eyKXyaLRiOj4UhHKCjeqWjFCePt0Vb59tyqd9KhS";
    }
    public class Color{
        public static final int RED=0;
        public static final int BLUE=1;
        public static final int UNDECIDED=-1;
    }
    public static double floatToDouble(float f) {
        Float d=new Float(f);
        return d.doubleValue();
    }

    public static float doubleToFloat(double d){
        Double f=new Double(d);
        return f.floatValue();
    }
    public class Rudder{
        public static final String RUDDER="rudder";
        public static final String COLOR="color";
        public static final double RUDDER_IN=0.4;
        public static final double RUDDER_OUT=1;
        public static final double RUDDER_START=0.4;
    }
}
