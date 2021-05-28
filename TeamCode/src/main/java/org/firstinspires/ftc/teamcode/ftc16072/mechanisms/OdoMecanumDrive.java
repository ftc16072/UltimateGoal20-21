package org.firstinspires.ftc.teamcode.ftc16072.mechanisms;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.teamcode.ftc16072.mechanisms.tests.QQ_Test;
import org.firstinspires.ftc.teamcode.ftc16072.mechanisms.tests.QQ_TestMotorEncoder;

import java.util.List;

class OdoMecanumDrive extends MecanumDrive{
    DcMotor leftEncoder;
    DcMotor rightEncoder;
    DcMotor sidewaysEncoder;

    int leftOffset;
    int rightOffset;
    int sidewaysOffset;


    /**
     * initializes mecanum drive
     * @param hwMap Hardware Map from the configuration
     */
    @Override
    public void init(HardwareMap hwMap) {
        super.init(hwMap);
        frontLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        frontRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        backLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        backRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        leftEncoder = frontLeft;
        rightEncoder = frontRight;
        sidewaysEncoder = backLeft;
    }

    /**
     * get tests
     * @return arrays as list
     */
    @Override
    public List<QQ_Test> getTests() {
        List<QQ_Test> listTests = super.getTests();
        listTests.add(new QQ_TestMotorEncoder("Left", leftEncoder));
        listTests.add(new QQ_TestMotorEncoder("Right", rightEncoder));
        listTests.add(new QQ_TestMotorEncoder("Sideways", sidewaysEncoder));
        return listTests;
    }
    /**
     * get name
     * @return defining the variables
     */
    @Override
    public String getName() {
        return "OdoMecanumDrive";
    }

    /**
     * calculating the distances we have traveled using encoders
     * @param reset whether to reset encoders after this
     * @return a MoveDelta of the mecanum drive representing how far we have driven since the last reset
     */
    public MoveDeltas getDistance(boolean reset) {
        final double ENCODER_WHEEL_DIAMETER = 0.38; //38 mm
        final double TICKS_PER_ENCODER_ROTATION = 8192;
        final double CM_PER_ENCODER_TICK = (Math.PI * ENCODER_WHEEL_DIAMETER) / TICKS_PER_ENCODER_ROTATION;

        final double DISTANCE_BETWEEN_ENCODERS_CM = 42;
        final double CM_PER_ROBOT_ROTATION = (Math.PI * DISTANCE_BETWEEN_ENCODERS_CM);
        final double RAD_PER_CIRCLE = 2 * Math.PI;

        int leftCount= leftEncoder.getCurrentPosition() - leftOffset;
        int rightCount = rightEncoder.getCurrentPosition() - rightOffset;
        int sidewaysCount = sidewaysEncoder.getCurrentPosition() - sidewaysOffset;

        double forward = CM_PER_ENCODER_TICK * ((leftCount + rightCount) / 2.0);
        double strafe = CM_PER_ENCODER_TICK * sidewaysCount;
        double theta = RAD_PER_CIRCLE * ((CM_PER_ENCODER_TICK * (leftCount - rightCount)) / CM_PER_ROBOT_ROTATION);

        if(reset){
            leftOffset += leftCount;
            rightOffset += rightCount;
            sidewaysOffset += sidewaysCount;
        }

        return new MoveDeltas(forward, strafe, DistanceUnit.CM, theta, AngleUnit.RADIANS);
    }

    @Override
    public void setOffsets(){
        leftOffset = leftEncoder.getCurrentPosition();
        rightOffset = rightEncoder.getCurrentPosition();
        sidewaysOffset = sidewaysEncoder.getCurrentPosition();
    }

}
