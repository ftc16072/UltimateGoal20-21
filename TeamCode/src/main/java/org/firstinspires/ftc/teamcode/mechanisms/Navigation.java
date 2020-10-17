package org.firstinspires.ftc.teamcode.mechanisms;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.teamcode.utils.RobotPose;


public class Navigation {
    // Public Classes
    public RobotPose currentPosition;
    public BNO055IMU imu;
    // Private Classes
    MecanumDrive mecanumDrive;

    // Private Values
    private double angleTolerance = AngleUnit.RADIANS.fromDegrees(2);


    Navigation(MecanumDrive mecanumDrive){
        this.mecanumDrive = mecanumDrive;
    }

    void init(HardwareMap hwmap){
        imu = hwmap.get(BNO055IMU.class, "imu");
    }

    public RobotPose getCurrentPosition(){
        return currentPosition;
    }


    public boolean translateTo(RobotPose desiredPose, double distanceTolerance, DistanceUnit du){

        //todo write drive code here

        return currentPosition.getTransDistance(desiredPose, du).getR() < distanceTolerance;

    }

    public boolean turnTo(RobotPose desiredPose, double angleTolerance, AngleUnit au){

        //todo write turn code here

        return currentPosition.getAngleDistance(desiredPose, au) < angleTolerance;
    }

    public boolean driveTo(RobotPose desiredPose){
        return translateTo(desiredPose, 2, DistanceUnit.CM) && turnTo(desiredPose, 2, AngleUnit.DEGREES);
    }





}
