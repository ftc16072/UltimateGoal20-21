package org.firstinspires.ftc.teamcode.utils;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

public class RobotPose {
    double x_cm;
    double y_cm;
    double theta;

    public RobotPose(double x, double y, DistanceUnit du, double angle, AngleUnit au){
        x_cm = du.toCm(x);
        y_cm = du.toCm(y);
        theta = au.toRadians(angle);
    }

    public RobotPose(double x, double y, DistanceUnit du){
        x_cm = du.toCm(x);
        y_cm = du.toCm(y);
    }



}
