package org.firstinspires.ftc.teamcode.ftc16072.utils;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

public class Polar {
    private double x_cm;
    private double y_cm;
    private double theta;
    private double r;
    private boolean mathDone; //default is false

    /**
     * Constructor using polar coordinates
     *
     * @param angle angle component
     * @param au    angle unit the angle is in
     * @param r     distance from center
     * @param du distance unit of polar
     */
    public Polar(double angle, AngleUnit au, double r, DistanceUnit du) {
        this.r = du.toCm(r);
        this.theta = au.toRadians(angle);
        x_cm = r * Math.cos(theta);
        y_cm = r * Math.sin(theta);
    }

    /**
     * Constructor using cartesian coordinates
     *
     * @param x distance from the origin in the x axis
     * @param y distance from the origin in the y axis
     * @param du distance from polar
     */
    public Polar(double x, double y, DistanceUnit du) {
        this.x_cm = du.toCm(x);
        this.y_cm = du.toCm(y);
        theta = Math.atan2(y_cm, x_cm);
        r = Math.hypot(x_cm, y_cm);
    }

    /**
     * get the x component of the polar
     *
     * @param du distance unit you want the component in
     * @return x in the distance unit requested
     */
    public double getX(DistanceUnit du) {

        return du.fromCm(x_cm);
    }

    /**
     * get the y component of the polar
     *
     * @param du distance unit you want the component in
     * @return y in the distance unit requested
     */
    public double getY(DistanceUnit du) {

        return du.fromCm(y_cm);
    }


    /**
     * get the r component of the polar
     *
     * @param du distance unit you want the component in
     * @return r in the distance unit provided
     */
    public double getR(DistanceUnit du) {

        return du.fromCm(r);
    }

    /**
     * get the theta component of the polar
     *
     * @param au angle unit you want the component in
     * @return theta in the angle unit provided
     */
    public double getTheta(AngleUnit au) {

        return au.fromRadians(theta);
    }

    /**
     * rotates the polar component by a certain angle counter clockwise
     *
     * @param heading   angle to subtract
     * @param angleUnit angle unit that angle is in
     * @return
     */
    public Polar rotateCCW(double heading, AngleUnit angleUnit) {
        return new Polar(theta - angleUnit.toRadians(heading), AngleUnit.RADIANS, r, DistanceUnit.CM);
    }

    /**
     * rotates the polar component by a certain angle clockwise
     *
     * @param heading   angle to add
     * @param angleUnit angle unit that angle is in
     * @return
     */
    public Polar rotateCW(double heading, AngleUnit angleUnit) {
        return new Polar(theta + angleUnit.toRadians(heading), AngleUnit.RADIANS, r, DistanceUnit.CM);
    }

    public void scaleR(double scale){
        r = r * scale;
    }

}
