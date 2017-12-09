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
        public static final float POWER = 0.1f;
        public static final String ELEVATOR="elevator";
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
}
