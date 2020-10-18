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

    /**
     * Constructor
     * @param mecanumDrive needs a mecanum drive to be able to drive
     */
    Navigation(MecanumDrive mecanumDrive){
        this.mecanumDrive = mecanumDrive;
    }

    /**
     * Initialize the IMU
     * @param hwmap hardware map from the configuration
     */
    void init(HardwareMap hwmap){
        imu = hwmap.get(BNO055IMU.class, "imu");
    }

    /**
     * an exposed method to allow other people to get the current position
     * @return A robot pose that is the current position
     */
    public RobotPose getCurrentPosition(){
        return currentPosition;
    }

    //todo make this javadoc link to the drive to method
    /**
     * A method that will only translate to the desired position if you want both call driveTo()
     * @param desiredPose desired postition to translate to (IGNORES ANGLE)
     * @return true if done
     */
    public boolean translateTo(NavigationPose desiredPose){

        //todo write drive code here

        return desiredPose.inDistanceTolerance(currentPosition);

    }

    //todo make this javadoc link to the drive to method
    /**
     * Rotates to face the angle portion of a desired pose
     * @param desiredPose pose containing the desired angle for rotation DOES NOT TRANSLATE
     * @return true if done
     */
    public boolean turnTo(NavigationPose desiredPose){

        //todo write turn code here

        return false;
    }

    /**
     * Translates and rotates to desired pose
     * @param desiredPose desired pose for your robot
     * @return true if done
     */
    public boolean driveTo(NavigationPose desiredPose) {
        return translateTo(desiredPose) && turnTo(desiredPose);
    }





}
