package org.firstinspires.ftc.teamcode.mechanisms;

import android.view.accessibility.AccessibilityNodeInfo;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;
import org.firstinspires.ftc.teamcode.utils.Joystick;
import org.firstinspires.ftc.teamcode.utils.NavigationPose;
import org.firstinspires.ftc.teamcode.utils.Polar;
import org.firstinspires.ftc.teamcode.utils.RobotPose;


public class Navigation {
    // Public Classe
    public RobotPose currentPosition;
    public BNO055IMU imu;
    private double imuOffset;
    // Private Classes
    MecanumDrive mecanumDrive;
    double TRANSLATE_KP = 0.1;
    double MIN_R = 0.14;



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
        initializeImu(hwmap, 0);

    }


    //IMU Stuff

    /**
     * Initialize the imu with an offset
     *
     * @param hwMap hardware map used from the configuration
     * @param offset offset for the imu
     */
    public void initializeImu(HardwareMap hwMap, double offset) {
        imu = hwMap.get(BNO055IMU.class, "imu");
        BNO055IMU.Parameters params = new BNO055IMU.Parameters();
        params.calibrationDataFile = "BNO055IMUCalibration.json";
        imu.initialize(params);
        imuOffset = offset;
    }

    public void setCurrentPosition(RobotPose pose){
        currentPosition = pose;
    }

    public void setImuOffset(double imuOffset, AngleUnit au) {
        this.imuOffset = au.toRadians(imuOffset);
    }

    private double getHeading(AngleUnit au){
        Orientation angles;

        angles = imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.RADIANS);

        return au.fromRadians(angles.firstAngle + imuOffset);
    }



    //Teleop Stuff

    public void driveFieldRel(Joystick translateJoystick, double rotateSpeed){
        driveFieldRelative(translateJoystick.getPolar(), rotateSpeed);
    }


    public void driveFieldRelative(Polar translate, double rotateSpeed){
        translate.subtractAngle(getHeading(AngleUnit.RADIANS), AngleUnit.RADIANS);

        mecanumDrive.driveMecanum(translate.getY(DistanceUnit.CM), translate.getX(DistanceUnit.CM), rotateSpeed);
    }


    public void driveFieldRelativeAngle(Polar translate, double angle, AngleUnit au){
        translate.subtractAngle(getHeading(AngleUnit.RADIANS), AngleUnit.RADIANS);
        double desiredRobotAngle = au.toRadians(angle) - Math.PI/2;

        double deltaAngle = AngleUnit.normalizeRadians(getHeading(AngleUnit.RADIANS) - desiredRobotAngle);

        double MAX_ROTATE = 0.7;

        deltaAngle = Range.clip(deltaAngle,-MAX_ROTATE, MAX_ROTATE);

        mecanumDrive.driveMecanum(translate.getY(DistanceUnit.CM), translate.getX(DistanceUnit.CM), deltaAngle);
    }

    public void driveRotate(double rotateSpeed){
        mecanumDrive.driveMecanum(0.0, 0.0, rotateSpeed);
    }






    //Auto Stuff

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
        Polar distance = currentPosition.getTransDistance(desiredPose);

        double newR = Math.max((distance.getR(DistanceUnit.CM) * TRANSLATE_KP), MIN_R);

        Polar drive = new Polar(distance.getTheta(AngleUnit.RADIANS), AngleUnit.RADIANS, newR, DistanceUnit.CM);

        driveFieldRelative(drive, 0.0);

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

        return true;
    }

    /**
     * Translates and rotates to desired pose
     * @param desiredPose desired pose for your robot
     * @return true if done
     */
    public boolean driveTo(NavigationPose desiredPose) {
        boolean translate = translateTo(desiredPose);
        boolean rotate = turnTo(desiredPose);
        if(translate && rotate){
            mecanumDrive.driveMecanum(0,0,0);
            return true;
        } else {
            return false;
        }
    }

    public void updatePose(){
        MecanumDrive.MoveDeltas movement = mecanumDrive.getDistance();
        currentPosition.setAngle(getHeading(AngleUnit.RADIANS), AngleUnit.RADIANS);
        currentPosition.updatePose(movement);
        mecanumDrive.setOffsets();
    }





}
