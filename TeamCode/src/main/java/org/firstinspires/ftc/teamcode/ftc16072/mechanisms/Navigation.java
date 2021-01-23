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
    final static double ROTATE_KP = 2;
    final static double MAX_ROTATE_SPEED = 0.8;
    final static double MIN_ROTATE_SPEED = 0.1;
    public boolean flipDriving = false;

    public enum DriverPerspective {
        BLUE, BACK, RED
    }
    DriverPerspective perspective = DriverPerspective.BACK;

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

    public void setCurrentPosition(RobotPose pose) {
        currentPosition = pose;
        mecanumDrive.setOffsets();
    }

    public void resetIMU(double angle, AngleUnit au) {
        double supposedHeading = au.toRadians(angle);
        double currentHeading = imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.RADIANS).firstAngle; //if we use our get method it would double the offset if we set it again

        imuOffset = supposedHeading - currentHeading;
    }

    public double getHeading(AngleUnit au) {
        Orientation angles;

        angles = imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.RADIANS);

        return au.fromRadians(angles.firstAngle + imuOffset);
    }

    private void driveFieldRelative(Polar translate, double rotateSpeed) {
        Polar drive = translate.rotateCCW(getHeading(AngleUnit.RADIANS), AngleUnit.RADIANS);
        mecanumDrive.driveMecanum(drive.getY(DistanceUnit.CM), drive.getX(DistanceUnit.CM), rotateSpeed);
    }

    //Teleop Stuff
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

            double newR = Math.max((distance.getR(DistanceUnit.CM) * TRANSLATE_KP), MIN_R);

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

    public void updatePose() {
        MecanumDrive.MoveDeltas movement = mecanumDrive.getDistance(true);
        currentPosition.setAngle(getHeading(AngleUnit.RADIANS), AngleUnit.RADIANS);
        currentPosition.updatePose(movement);
    }


}
