package org.firstinspires.ftc.teamcode.mechanisms;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.teamcode.utils.NavigationPose;
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
//returningn the current position of the robot
    public RobotPose getCurrentPosition(){
        return currentPosition;
    }


    public boolean translateTo(NavigationPose desiredPose){

        //todo write drive code here

        return desiredPose.inDistanceTolerance(currentPosition);

    }

    public boolean turnTo(NavigationPose desiredPose){

        //todo write turn code here

        return false;
    }

    public boolean driveTo(NavigationPose desiredPose){
        return translateTo(desiredPose) && turnTo(desiredPose);
    }





}
