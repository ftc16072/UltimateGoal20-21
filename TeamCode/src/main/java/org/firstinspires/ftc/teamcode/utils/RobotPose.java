package org.firstinspires.ftc.teamcode.utils;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

public class RobotPose {
    double x_cm;
    double y_cm;
    double theta;

    public RobotPose(double x, double y, DistanceUnit du, double angle, AngleUnit au){
        /**
         * /distance moved on the x axis in centimeters
         */
        x_cm = du.toCm(x);
        /**
         * distance moved on the y axis in centimeters
         */
        y_cm = du.toCm(y);
        /**
         * angle of the robot
         */
        theta = au.toRadians(angle);
    }

    public RobotPose(double x, double y, DistanceUnit du){
        /**
         * distance moved on the x axis in centimeters
         */
        x_cm = du.toCm(x);
        /**
         * distance moved on the y axis in centimeters
         */
        y_cm = du.toCm(y);
    }

    /**
     * return distance moved on the x axis in centimeters
     * @param du
     * @return distance moved on the x axis in cm
     */
    public double getX(DistanceUnit du) {
        return du.fromCm(x_cm);
    }

    /**
     * return distance moved on the y axis in centimeters
     * @param du
     * @return distance moved on the y axis in centimeters
     */
    public double getY(DistanceUnit du) {
        return du.fromCm(y_cm);
    }

    /**
     * return the angle value in radians
     * @param au
     * @return the angle value in radians
     */
    public double getAngle(AngleUnit au) {
        return au.fromRadians(theta);
    }

    public Polar getTransDistance(RobotPose otherPoint, DistanceUnit du){
        return new Polar(otherPoint.getX(du) - getX(du), otherPoint.getY(du) - getY(du));
    }

    public double getAngleDistance(RobotPose otherPoint, AngleUnit au){
        return otherPoint.getAngle(au) - getAngle(au);
    }



}
