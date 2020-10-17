package org.firstinspires.ftc.teamcode.utils;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

public class NavigationPose extends RobotPose {
    double xToleranceCm;
    double yToleranceCm;
    double angleTolerance;

    public NavigationPose(double x, double xTolerance, double y, double yTolerance, DistanceUnit du, double angle, AngleUnit au){
        super(x, y, du, angle, au);
        xToleranceCm = du.toCm(xTolerance);
        yToleranceCm = du.toCm(yTolerance);
    }

    public boolean inDistanceTolerance(RobotPose otherPose){
        boolean xin = Math.abs(otherPose.getX(DistanceUnit.CM) - x_cm) <= xToleranceCm;
        boolean yin = Math.abs(otherPose.getY(DistanceUnit.CM) - y_cm) <= yToleranceCm;
        return xin && yin;
    }

}
