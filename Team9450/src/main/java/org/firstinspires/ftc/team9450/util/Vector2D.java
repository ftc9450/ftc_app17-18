package org.firstinspires.ftc.team9450.util;

/**
 * Created by dhruv on 1/22/18.
 */

public class Vector2D {
    public double x, y;

    public Vector2D() {}

    public Vector2D(double x, double y){
        this.x = x;
        this.y = y;
    }

    public double[] rotate(double angle) {
        double temp = x;
        x = Math.cos(angle)*x - Math.sin(angle)*y;
        y = Math.sin(angle)*temp + Math.cos(angle)*y;
        return new double[]{x, y};
    }
}
