package org.firstinspires.ftc.teamcode.util;

/**
 * Created by O on 10/28/2017.
 */
public class Constants {
    public class Drivetrain {
        public static final float HIGH_POWER = 1.0f;
        public static final float LOW_POWER = 0.3f;
        public static final int INCH=90;
        public static final int DEGREE=1;
        public static final String LF="leftFront";
        public static final String LB="leftBack";
        public static final String RF="rightFront";
        public static final String RB="rightBack";
    }

    public class Elevator {
        public static final float POWER = 0.3f;
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

    public class HardwareMap {
        public static final String DCTEST = "frontLeft";
        public static final String RUDDER = "rudder_servo";
        public static final String COLOR = "sensor_color_distance";
    }
}
