package org.firstinspires.ftc.teamcode.ftc16072.mechanisms;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;
import org.firstinspires.ftc.teamcode.ftc16072.utils.Joystick;
import org.firstinspires.ftc.teamcode.ftc16072.utils.NavigationPose;
import org.firstinspires.ftc.teamcode.ftc16072.utils.Polar;
import org.firstinspires.ftc.teamcode.ftc16072.utils.RobotPose;
import org.opencv.core.Mat;


public class Navigation {
    // Public Classe
    public static RobotPose currentPosition;

    // Private Classes
    private static BNO055IMU imu;
    MecanumDrive mecanumDrive;

    // Private Values
    private double angleTolerance = AngleUnit.RADIANS.fromDegrees(2);
    private static double imuOffset;
    double TRANSLATE_KP = 0.1;
    double MIN_R = 0.14;
    final double ROTATE_KP = 2;
    final double MAX_ROTATE_SPEED = 0.8;
    final double MIN_ROTATE_SPEED = 0.1;
    public boolean flipDriving = false;

    public enum DriverPerspective {
        BLUE, BACK, RED
    }
    DriverPerspective perspective = DriverPerspective.BACK;

    /**
     *
     * @param driver polar driver
     * @return pi/2, angle unit in radians
     */
    Polar fieldFromDriver(Polar driver){
        switch(perspective){
            case BLUE:
                return driver.rotateCCW(Math.PI/2, AngleUnit.RADIANS);
            case RED:
                return driver.rotateCW(Math.PI/2, AngleUnit.RADIANS);
            case BACK:
                break;  // no translation needed
        }
        return driver;
    }
    /**
     * Constructor
     *
     * @param mecanumDrive needs a mecanum drive to be able to drive
     */
    Navigation(MecanumDrive mecanumDrive) {
        this.mecanumDrive = mecanumDrive;
    }

    /**
     *
     * @param perspective set perspective
     */
    public void setPerspective(DriverPerspective perspective){
        this.perspective = perspective;
    }

    /**
     * Initialize the IMU
     *
     * @param hwmap hardware map from the configuration
     */
    void init(HardwareMap hwmap) {
        initializeImu(hwmap, 0);
    }


    //IMU Stuff

    /**
     * Initialize the imu with an offset
     *
     * @param hwMap  hardware map used from the configuration
     * @param offset offset for the imu
     */
    public void initializeImu(HardwareMap hwMap, double offset) {
        imu = hwMap.get(BNO055IMU.class, "imu");
        BNO055IMU.Parameters params = new BNO055IMU.Parameters();
        params.calibrationDataFile = "BNO055IMUCalibration.json";
        imu.initialize(params);
        imuOffset = offset;
    }

    /**
     *
     * @param pose set current position
     */
    public void setCurrentPosition(RobotPose pose) {
        currentPosition = pose;
        mecanumDrive.setOffsets();
    }

    /**
     *
     * @param angle reset IMU
     * @param au reset IMU
     */
    public void resetIMU(double angle, AngleUnit au) {
        double supposedHeading = au.toRadians(angle);
        double currentHeading = imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.RADIANS).firstAngle; //if we use our get method it would double the offset if we set it again

        imuOffset = supposedHeading - currentHeading;
    }

    /**
     *
     * @param au heading of angle unit
     * @return radians from angles
     */
    public double getHeading(AngleUnit au) {
        Orientation angles;

        angles = imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.RADIANS);

        return au.fromRadians(angles.firstAngle + imuOffset);
    }

    /**
     *
     * @param translate rotate CCW
     * @param rotateSpeed drive field relative
     */
    private void driveFieldRelative(Polar translate, double rotateSpeed) {
        Polar drive = translate.rotateCCW(getHeading(AngleUnit.RADIANS), AngleUnit.RADIANS);
        mecanumDrive.driveMecanum(drive.getY(DistanceUnit.CM), drive.getX(DistanceUnit.CM), rotateSpeed);
    }

    //Teleop Stuff

    /**
     *
     * @param translateJoystick driveFieldRelativeAngle
     * @param angleJoystick if angleJoystick >=0.8, set fieldRelative to ...
     */
    public void driveFieldRelativeAngle(Joystick translateJoystick, Joystick angleJoystick) {
        double rotateSpeed = 0;

        if (angleJoystick.getR() >= 0.8) {
            Polar fieldRelative = fieldFromDriver(angleJoystick.getPolar().rotateCCW(Math.PI/2, AngleUnit.RADIANS));
            fieldRelative = flipDriving ? fieldRelative.rotateCCW(180, AngleUnit.DEGREES) : fieldRelative;
            double deltaAngle = AngleUnit.normalizeRadians(getHeading(AngleUnit.RADIANS) - fieldRelative.getTheta(AngleUnit.RADIANS));
            rotateSpeed = Range.clip(deltaAngle, -MAX_ROTATE_SPEED, MAX_ROTATE_SPEED);
        }

        Polar translate = flipDriving ? translateJoystick.getPolar() : fieldFromDriver(translateJoystick.getPolar()) ;

        driveFieldRelative(translate, rotateSpeed);
    }

    /**
     *
     * @param translateJoystick driveAngle
     * @param angle rotateSpeed
     */
    public void driveAngle(Joystick translateJoystick, double angle){
        double rotateSpeed = 0;

        double deltaAngle = AngleUnit.normalizeRadians(getHeading(AngleUnit.RADIANS) - AngleUnit.RADIANS.fromDegrees(angle));
        rotateSpeed = Range.clip(deltaAngle * 2, -MAX_ROTATE_SPEED, MAX_ROTATE_SPEED);

        Polar translate = flipDriving ? translateJoystick.getPolar() : fieldFromDriver(translateJoystick.getPolar()) ;

        driveFieldRelative(translate, rotateSpeed);
    }

    /**
     *
     * @param rotateSpeed driveMecanum (forward 0.0, strafe 0.0, rotateSpeed)
     */
    public void driveRotate(double rotateSpeed) {
        mecanumDrive.driveMecanum(0.0, 0.0, rotateSpeed);
    }


    //Auto Stuff

    /**
     * an exposed method to allow other people to get the current position
     *
     * @return A robot pose that is the current position
     */
    public RobotPose getCurrentPosition() {
        return currentPosition;
    }


    /**
     * Translates and rotates to desired pose
     *
     * @param desiredPose desired pose for your robot
     * @return true if done
     */
    public boolean driveTo(NavigationPose desiredPose) {
        Polar drive;
        double rotateSpeed;
        boolean distancebln;
        boolean angle;

        if (desiredPose.inDistanceTolerance(currentPosition)){
            drive = new Polar(0, AngleUnit.RADIANS, 0, DistanceUnit.CM);
            distancebln = true;
        } else {
            Polar distance = currentPosition.getTransDistance(desiredPose);

            double newR = Math.min(Math.max((distance.getR(DistanceUnit.CM) * TRANSLATE_KP), desiredPose.getMinSpeed()), desiredPose.getMaxSpeed());

            System.out.println(newR);

            drive = new Polar(distance.getTheta(AngleUnit.RADIANS), AngleUnit.RADIANS, newR, DistanceUnit.CM);
            distancebln = false;
        }

        if(desiredPose.inAngleTolerance(currentPosition)){
            rotateSpeed = 0.0;
            angle = true;
        } else {
            double angleDelta = desiredPose.getAngleDistance(currentPosition, AngleUnit.RADIANS);
            rotateSpeed = Math.signum(angleDelta) * Math.max(Math.min(Math.abs(angleDelta * ROTATE_KP), MAX_ROTATE_SPEED), MIN_ROTATE_SPEED);
            angle = false;

        }

        if(distancebln && angle){
            mecanumDrive.driveMecanum(0, 0, 0);
            return true;
        }
        driveFieldRelative(drive, rotateSpeed);

        return false;
    }

    /**
     * update position of mecanum drive
     */
    public void updatePose() {
        MecanumDrive.MoveDeltas movement = mecanumDrive.getDistance(true);
        currentPosition.setAngle(getHeading(AngleUnit.RADIANS), AngleUnit.RADIANS);
        currentPosition.updatePose(movement);
    }


}
