package org.firstinspires.ftc.teamcode.ftc16072.mechanisms;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.matrices.GeneralMatrixF;
import org.firstinspires.ftc.robotcore.external.matrices.MatrixF;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.teamcode.ftc16072.mechanisms.tests.QQ_Test;
import org.firstinspires.ftc.teamcode.ftc16072.mechanisms.tests.QQ_TestMotor;

import java.util.Arrays;
import java.util.List;

public class MecanumDrive implements QQ_Mechanism {
    public class MoveDeltas{
        double x_cm;
        double y_cm;
        double theta;

        /**
         *
         * @param forward distance from origin on the x axis
         * @param strafe distance from origina on the y axis
         * @param du distance unit
         * @param angle radians of theta
         * @param au angle unit
         */
        public MoveDeltas(double forward, double strafe, DistanceUnit du, double angle, AngleUnit au){
            x_cm = du.toCm(forward);
            y_cm = du.toCm(strafe);
            theta = au.toRadians(angle);
        }

        /**
         *
         * @param du forward distance
         * @return return x cm from distance
         */
        public double getForward(DistanceUnit du) {
            return du.fromCm(x_cm);
        }

        /**
         *
          * @param du strafe distance
         * @return return y cm from distance
         */
        public double getStrafe(DistanceUnit du) {
            return du.fromCm(y_cm);
        }

        /**
         *
         * @param au angle distance
         * @return radians of theta
         */
        public double getAngle(AngleUnit au) { return au.fromRadians(theta); }

        /**
         *
         * @param angle set angle
         * @param au theta degrees to angle
         */
        public void setAngle(double angle, AngleUnit au) {theta = au.toRadians(angle);}

    }


    //declaring mecanum drive motors
    private DcMotorEx frontLeft;
    private DcMotorEx frontRight;
    private DcMotorEx backRight;
    private DcMotorEx backLeft;

    private final double GEAR_RATIO = 4.0/6.0;
    private final double WHEEL_RADIUS = 5.0; //5 cm
    private final double TICKS_PER_ROTATION = 383.6;
    //cm per rotation/ticks per rotation
    private final double CM_PER_TICK = (2 * Math.PI * GEAR_RATIO * WHEEL_RADIUS) / TICKS_PER_ROTATION;

    //created so we can easily change code to lower the speed in the setSpeeds method
    private double maxSpeed = 1.0;

    private MatrixF conversion;
    private GeneralMatrixF encoderMatrix = new GeneralMatrixF(3, 1);


    private int frontLeftOffset;
    private int frontRightOffset;
    private int backRightOffset;
    private int backLeftOffset;

    /**
     * Constructor
     * sets up the encoder matrix
     */
    MecanumDrive() {
        float[] data = {1.0f, 1.0f,1.0f,
                1.0f, -1.0f, -1.0f,
                1.0f, -1.0f, 1.0f};
        conversion = new GeneralMatrixF(3, 3, data);
        conversion = conversion.inverted();

    }


    /**
     * initializes macanum drive
     * @param hwMap Hardware Map from the configuration
     */
    @Override
    public void init(HardwareMap hwMap) {
        frontLeft = hwMap.get(DcMotorEx.class, "front_left_motor");
        frontLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        frontRight = hwMap.get(DcMotorEx.class, "front_right_motor");
        frontRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        backLeft = hwMap.get(DcMotorEx.class, "back_left_motor");
        backLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        backRight = hwMap.get(DcMotorEx.class, "back_right_motor");
        backRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
//back left and front left motors move backwards
        backLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        frontLeft.setDirection(DcMotorSimple.Direction.REVERSE);

    }


    /**
     * get tests
     * @return arrays as list
     */
    @Override
    public List<QQ_Test> getTests() {
        return Arrays.asList(
                (QQ_Test) new QQ_TestMotor("Front Left", 0.3, frontLeft),
                new QQ_TestMotor("Front Right", 0.3, frontRight),
                new QQ_TestMotor("Back Left", 0.3, backLeft),
                new QQ_TestMotor("Back Right", 0.3, backRight));
    }


    /**
     * takes speeds for each wheel, scales them, allows us to set an artificial ceiling, and then sets the motors
     * @param f1Speed speed for the front left wheel
     * @param frSpeed speed for the front right wheel
     * @param b1Speed speed for the back left wheel
     * @param brSpeed speed for the back right wheel
     */
    private void setSpeeds(double f1Speed, double frSpeed, double b1Speed, double brSpeed) {
        double largest = 1.0;
        //takes the absolute value of the speed, returns the max value
        largest = Math.max(largest, Math.abs(f1Speed));
        largest = Math.max(largest, Math.abs(frSpeed));
        largest = Math.max(largest, Math.abs(b1Speed));
        largest = Math.max(largest, Math.abs(brSpeed));
//at the end, largest will equal the highest speed or 1
        frontLeft.setPower((maxSpeed * (f1Speed / largest)));
        frontRight.setPower((maxSpeed * (frSpeed / largest)));
        backLeft.setPower((maxSpeed * (b1Speed / largest)));
        backRight.setPower((maxSpeed * (brSpeed / largest)));
    }

    /**
     * get name
     * @return defining the variables
     */
    @Override
    public String getName() {
        return "MecanumDrive";
    }

    /**
     *
     * @param forward drive mecanum forward
     * @param strafe drive mecanum side to side
     * @param rotate rotate mecanum
     */
    public void driveMecanum(double forward, double strafe, double rotate) {
        double frontLeftSpeed = forward + strafe + rotate;
        double frontRightSpeed = forward - strafe - rotate;
        double backLeftSpeed = forward - strafe + rotate;
        double backRightSpeed = forward + strafe - rotate;

        setSpeeds(frontLeftSpeed, frontRightSpeed, backLeftSpeed, backRightSpeed);
    }

    /**
     * calculating the distances we have traveled using encoders
     * @param reset whether to reset encoders after this
     * @return a MoveDelta of the mecanum drive representing how far we have driven since the last reset
     */
    public MoveDeltas getDistance(boolean reset) {
        int backLeftPosition = backLeft.getCurrentPosition();
        int backRightPosition = backRight.getCurrentPosition();
        int frontLeftPosition = frontLeft.getCurrentPosition();
        int frontRightPosition = frontRight.getCurrentPosition();

        encoderMatrix.put(0, 0, (float) ((frontLeftPosition - frontLeftOffset) * CM_PER_TICK));
        encoderMatrix.put(1, 0, (float) ((frontRightPosition - frontRightOffset) * CM_PER_TICK));
        encoderMatrix.put(2, 0, (float) ((backLeftPosition - backLeftOffset) * CM_PER_TICK));

        MatrixF distanceMatrix = conversion.multiplied(encoderMatrix);

        double forward = distanceMatrix.get(0, 0);
        double strafe = distanceMatrix.get(0, 1);
        //double angle = distanceMatrix.get(0, 2);
        double angle = 0;
        if(reset){
            frontLeftOffset  = frontLeftPosition;
            frontRightOffset = frontRightPosition;
            backLeftOffset   = backLeftPosition;
            backRightOffset  = backRightPosition;
        }

        return new MoveDeltas(forward, strafe, DistanceUnit.CM, angle , AngleUnit.DEGREES);
    }

    /**
     *
     * @param maxSpeed set max speed
     */
    public void setMaxSpeed(double maxSpeed) {
        this.maxSpeed = maxSpeed;
    }

    /**
     * set backleft, backright, frontleft and frontright offsets
     */
    public void setOffsets(){
        backLeftOffset = backLeft.getCurrentPosition();
        backRightOffset = backRight.getCurrentPosition();
        frontLeftOffset = frontLeft.getCurrentPosition();
        frontRightOffset = frontRight.getCurrentPosition();
    }
}