package org.firstinspires.ftc.teamcode.ftc16072.utils;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.teamcode.ftc16072.mechanisms.Navigation;

public class NavigationPose extends RobotPose {
    double xToleranceCm;
    double yToleranceCm;
    double angleTolerance;

    /**
     * Main Constructor for NavigationPose
     * @param x x component
     * @param xTolerance tolerance on the x component
     * @param y y component
     * @param yTolerance tolerance on the y component
     * @param du distance unit those distances are in
     * @param angle angle component
     * @param au angle unit that angle is in
     */
    public NavigationPose(double x, double xTolerance, double y, double yTolerance, DistanceUnit du, double angle, double angleTolerance, AngleUnit au){
        super(x, y, du, angle, au);
        //figuring out x and y position of the robot
        xToleranceCm = du.toCm(xTolerance);
        yToleranceCm = du.toCm(yTolerance);
        this.angleTolerance = au.toRadians(angleTolerance);
    }

    public NavigationPose(double x, double y, double distanceTolerance, double angle, double angleTolerance){
        this(x, distanceTolerance, y, distanceTolerance, DistanceUnit.INCH, angle, angleTolerance, AngleUnit.DEGREES);
    }

    /**
     * default tolerance is 0.5 and angle is 0
     * @param x in inches
     * @param y in inches
     */
    public NavigationPose(double x, double y){
        this(x, 0.5, y, 0.5, DistanceUnit.INCH, 0, 0, AngleUnit.DEGREES);
    }

    public NavigationPose(double x, double y, double theta){
        this(x, 0.5, y, 0.5, DistanceUnit.INCH, theta, 2, AngleUnit.DEGREES);
    }
    /**
     * checks to see if another pose is in tolerance
     * @param otherPose pose to check
     * @return true if it is
     */
    public boolean inDistanceTolerance(RobotPose otherPose){
        //figuring out the tolerance in order to navigate the robot
        boolean xin = Math.abs(otherPose.getX(DistanceUnit.CM) - x_cm) <= xToleranceCm;
        boolean yin = Math.abs(otherPose.getY(DistanceUnit.CM) - y_cm) <= yToleranceCm;
        return xin && yin;
    }

    /**
     * checks to see if another pose is in tolerance
     * @param otherPose pose to check
     * @return true if it is
     */
    public boolean inAngleTolerance(RobotPose otherPose){
        //figuring out the tolerance in order to navigate the robot
        return Math.abs(otherPose.getAngle(AngleUnit.RADIANS) - theta) <= angleTolerance;
    }

}
