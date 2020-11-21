package org.firstinspires.ftc.teamcode.utils;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

public class NavigationPose extends RobotPose {
    double xToleranceCm;
    double yToleranceCm;
    double angleTolerance;

    public NavigationPose(double x, double xTolerance, double y, double yTolerance, DistanceUnit du, double angle, AngleUnit au){
        super(x, y, du, angle, au);
        /**
         * figuring out x and y position of the robot
         */
        xToleranceCm = du.toCm(xTolerance);
        yToleranceCm = du.toCm(yTolerance);
    }

    public boolean inDistanceTolerance(RobotPose otherPose){
        /**
         * figuring out the tolerance in order to navigate the robot
         */
        boolean xin = Math.abs(otherPose.getX(DistanceUnit.CM) - x_cm) <= xToleranceCm;
        boolean yin = Math.abs(otherPose.getY(DistanceUnit.CM) - y_cm) <= yToleranceCm;
        return xin && yin;
    }

}
