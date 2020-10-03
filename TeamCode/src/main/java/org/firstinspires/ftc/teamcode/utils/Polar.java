package org.firstinspires.ftc.teamcode.utils;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;

class Polar {
    private double x;
    private double y;
    private double theta;
    private double r;
    private boolean mathDone; //default is false

    Polar(double theta, double r, AngleUnit thetaAngleUnit){
        this.r = r;
        this.theta = thetaAngleUnit.toRadians(theta);
        mathDone = false;
    }

    Polar(double x, double y){
        this.x = x;
        this.y = y;
        mathDone = false;
    }

    private void doMath(){
        if(!mathDone) {
            if (x == 0 && y == 0) {
                x = r * Math.cos(theta);
                y = r * Math.sin(theta);
            }
            if (theta == 0 && r == 0) {
                theta = Math.atan2(y, x);
                r = Math.hypot(x, y);
            }
            mathDone = true;
        }
    }
    public double getX(){
        doMath();
        return x;
    }
    public double getY(){
        doMath();
        return y;
    }
    public double getR(){
        doMath();
        return r;
    }

    public double getTheta(AngleUnit angleUnit) {
        doMath();
        return angleUnit.fromRadians(theta);
    }

    public void subtractAngle(double heading, AngleUnit angleUnit){
        theta = theta - angleUnit.toRadians(heading);
        mathDone = false;
    }
}
