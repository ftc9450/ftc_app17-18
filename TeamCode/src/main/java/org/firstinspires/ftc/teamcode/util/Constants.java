package org.firstinspires.ftc.teamcode.util;

/**
 * Created by O on 10/28/2017.
 */
public class Constants {
    public class Drivetrain {
        public static final double HIGH_POWER = 1;
        public static final double LOW_POWER = 0.3;
        public static final int INCH=90;
        public static final int DEGREE=1;
        public static final String LF="frontLeft";
        public static final String LB="backLeft";
        public static final String RF="frontRight";
        public static final String RB="backRight";
    }

    public class Elevator {
        public static final float POWER = 1.0f;
        public static final String ELEVATOR="elevator";
        public static final double maxEncoder=10*420;//420 ppr for neverest 60
    }
    public class Grabber{
        public static final String L="leftServo";
        public static final String R="rightServo";
    }
    public class Rudder {
        public static final float RUDDER_IN = 0f;
        public static final float RUDDER_OUT = 0f;
    }

    public class Color {
        public static final int RED = 1;
        public static final int BLUE = 0;
        public static final int UNDECIDED = -1;
    }
    public static double floatToDouble(float f) {
        Float d=new Float(f);
        return d.doubleValue();
    }

    public static float doubleToFloat(double d){
        Double f=new Double(d);
        return f.floatValue();
    }
    public class HardwareMap {
        public static final String DCTEST = "frontLeft";
        public static final String RUDDER = "rudder_servo";
        public static final String COLOR = "sensor_color_distance";
    }
    public class Setup {
        public static final String VUFORIAKEY = "AYwm7lb/////AAAAGeQI9HT4B0R2unLNBq/DsId49BJr71nKGdfP8X8fnmtD0Jna47KLigPBytLYBjzOIl6uCfYWbIXHc3FqoabxIITohKJ4VvPispe5kGGFFJyQEIifEL1Bc511jOl00pyY2Tr/YOGwjGk7lSXQ0QrScHVaiwIOM3mUUlsv9Ethn1OCZB2AVhT91gnrUKryxBwfLCGjqpmYdWOVDsJTloDiowWMez0U42S9sILVevNguQXZqTr1uURaUx5Voy+2N6FVK5p4dvraac9+LD6YskUCLqWsK2XVruCpCsRWZxfrqylNyni2ll5AW3Mekw/hSSzfjx70eyKXyaLRiOj4UhHKCjeqWjFCePt0Vb59tyqd9KhS";
    }
}
