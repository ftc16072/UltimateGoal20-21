package org.firstinspires.ftc.teamcode.utils;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.teamcode.mechanisms.MecanumDrive;

public class RobotPose {
    double x_cm;
    double y_cm;
    double theta;

    /**
     * Constructor using both x, y and angle
     * @param x x position
     * @param y y position
     * @param du distance unit positions are in
     * @param angle angle position
     * @param au angle unit position is in
     */
    public RobotPose(double x, double y, DistanceUnit du, double angle, AngleUnit au){
        x_cm = du.toCm(x);
        y_cm = du.toCm(y);
        theta = au.toRadians(angle);
    }

    /**
     * constructor without angle
     * @param x x position
     * @param y y position
     * @param du distance unit positions are in
     */
    public RobotPose(double x, double y, DistanceUnit du){
        this(x, y, du, 0, AngleUnit.DEGREES);
    }

    public void setX(double x, DistanceUnit du){
        x_cm = du.toCm(x);
    }
    public void setY(double y, DistanceUnit du){
        y_cm = du.toCm(y);
    }
    public void setAngle(double angle, AngleUnit au){
        theta = au.toRadians(angle);
    }

    /**
     * get x component of pose
     * @param du distance unit for component to be in
     * @return x component in distance unit desired
     */
    public double getX(DistanceUnit du) {
        return du.fromCm(x_cm);
    }

    /**
     * get y component of pose
     * @param du distance unit for component to be in
     * @return y component in desired distance unit
     */
    public double getY(DistanceUnit du) {
        return du.fromCm(y_cm);
    }

    /**
     * get angle component of pose
     * @param au angle unit for angle to be in
     * @return angle in desired angle unit
     */
    public double getAngle(AngleUnit au) {
        return au.fromRadians(theta);
    }

    /**
     * get the distance between this point and some other point
     * @param otherPoint point to find distance to
     * @return returns a polar of the distance
     */
    public Polar getTransDistance(RobotPose otherPoint){
        DistanceUnit du = DistanceUnit.CM;
        return new Polar(otherPoint.getX(du) - getX(du), otherPoint.getY(du) - getY(du), du);
    }

    /**
     * gets the angle distance between this point and some other point
     * @param otherPoint point to find angle distance to
     * @param au angle unit to get difference in
     * @return distance in desired angle unit
     */
    public double getAngleDistance(RobotPose otherPoint, AngleUnit au){
        return otherPoint.getAngle(au) - getAngle(au);
    }

    public void updatePose(MecanumDrive.MoveDeltas moveDeltas){
        theta = AngleUnit.normalizeRadians(theta + moveDeltas.getAngle(AngleUnit.RADIANS));

        Polar translation = new Polar(moveDeltas.getStrafe(DistanceUnit.CM), moveDeltas.getForward(DistanceUnit.CM), DistanceUnit.CM);
        Polar rotated = translation.rotateCCW(theta, AngleUnit.RADIANS);

        x_cm += rotated.getX(DistanceUnit.CM);
        y_cm += rotated.getY(DistanceUnit.CM);
    }
}
