package org.firstinspires.ftc.teamcode.mechanisms;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.matrices.GeneralMatrixF;
import org.firstinspires.ftc.robotcore.external.matrices.MatrixF;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.teamcode.mechanisms.tests.QQ_Test;
import org.firstinspires.ftc.teamcode.mechanisms.tests.QQ_TestMotor;

import java.util.Arrays;
import java.util.List;

public class MecanumDrive implements QQ_Mechanism {
    public class MoveDeltas{
        double x_cm;
        double y_cm;

        public MoveDeltas(double x, double y, DistanceUnit du){
            x_cm = du.toCm(x);
            y_cm = du.toCm(y);
        }

        public double getX(DistanceUnit du) {
            return du.fromCm(x_cm);
        }
        public double getY(DistanceUnit du) {
            return du.fromCm(y_cm);
        }

    }


    //declaring mecanum drive motors
    private DcMotor frontLeft;
    private DcMotor frontRight;
    private DcMotor backRight;
    private DcMotor backLeft;

    private final static double GEAR_RATIO = 0.5;
    private final static double WHEEL_RADIUS = 5.0; //5 cm
    private final static double TICKS_PER_ROTATION = 383.6;
    //cm per rotation/ticks per rotation
    private final static double CM_PER_TICK = (2 * Math.PI * GEAR_RATIO * WHEEL_RADIUS) / TICKS_PER_ROTATION;

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
        frontLeft = hwMap.get(DcMotor.class, "front_left_motor");
        frontLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        frontRight = hwMap.get(DcMotor.class, "front_right_motor");
        frontRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        backLeft = hwMap.get(DcMotor.class, "back_left_motor");
        backLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        backRight = hwMap.get(DcMotor.class, "back_right_motor");
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

    public void driveMecanum(double forward, double strafe, double rotate) {
        double frontLeftSpeed = forward + strafe + rotate;
        double frontRightSpeed = forward - strafe - rotate;
        double backLeftSpeed = forward - strafe + rotate;
        double backRightSpeed = forward + strafe - rotate;

        setSpeeds(frontLeftSpeed, frontRightSpeed, backLeftSpeed, backRightSpeed);
    }

    /**
     * calculating the distances we have traveled using encoders
     * @return a MoveDelta of the mecanum drive representing how far we have driven since the last reset
     */
    MoveDeltas getDistance() {

        encoderMatrix.put(0, 0, (float) ((frontLeft.getCurrentPosition() - frontLeftOffset) * CM_PER_TICK));
        encoderMatrix.put(1, 0, (float) ((frontRight.getCurrentPosition() - frontRightOffset) * CM_PER_TICK));
        encoderMatrix.put(2, 0, (float) ((backLeft.getCurrentPosition() - backLeftOffset) * CM_PER_TICK));

        MatrixF distanceMatrix = conversion.multiplied(encoderMatrix);

        return new MoveDeltas(distanceMatrix.get(0, 0),  distanceMatrix.get(0, 0), DistanceUnit.CM);
    }

    public void setMaxSpeed(double maxSpeed) {
        this.maxSpeed = maxSpeed;
    }
}