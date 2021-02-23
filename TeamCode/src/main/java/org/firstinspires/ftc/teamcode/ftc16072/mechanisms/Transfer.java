package org.firstinspires.ftc.teamcode.ftc16072.mechanisms;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.TouchSensor;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.teamcode.ftc16072.mechanisms.tests.QQ_DualTest;
import org.firstinspires.ftc.teamcode.ftc16072.mechanisms.tests.QQ_Test;
import org.firstinspires.ftc.teamcode.ftc16072.mechanisms.tests.QQ_TestCRServo;
import org.firstinspires.ftc.teamcode.ftc16072.mechanisms.tests.QQ_TestDigitalSensor;
import org.firstinspires.ftc.teamcode.ftc16072.mechanisms.tests.QQ_TestDistance;
import org.firstinspires.ftc.teamcode.ftc16072.mechanisms.tests.QQ_TestMotor;
import org.firstinspires.ftc.teamcode.ftc16072.mechanisms.tests.QQ_TestServo;
import org.firstinspires.ftc.teamcode.ftc16072.mechanisms.tests.QQ_TestTouchSensor;

import java.util.Arrays;
import java.util.List;

public class Transfer implements QQ_Mechanism {
    public enum elevatorState {
        UP,
        DOWN,
        BUSY
    }

    public enum transferState {
        START,
        REVERSE,
        STOP
    }

    /**
     * initializes the transfer
     *
     * @param hwMap forces the init to take a Hardware Map from the configuration
     */

    private CRServo leftBelts;
    private CRServo rightBelts;
    private DistanceSensor distanceSensor;
    private Servo tilt;
    private DigitalChannel up;
    private DigitalChannel down;

    private final double transferSpeed = .7;
    private final double reverseTransferSpeed = -0.7;

    public static double upLocation;
    public static double downLocation;


    private final double beltSpeed = 1.0;
    private final double reverseBeltSpeed = -1.0;

    private double normalDistance;
    private double distanceTolerance = 1.5;

    private boolean wasRing;
    private double ringsSeen;





    @Override
    public void init(HardwareMap hwMap) {
        leftBelts = hwMap.get(CRServo.class, "leftBelts");
        leftBelts.setDirection(DcMotorSimple.Direction.REVERSE);
        rightBelts = hwMap.get(CRServo.class, "rightBelts");
        distanceSensor = hwMap.get(DistanceSensor.class, "transfer_distance");
        normalDistance = distanceSensor.getDistance(DistanceUnit.CM);
        tilt = hwMap.get(Servo.class, "tilt_servo");
        up = hwMap.get(DigitalChannel.class, "high_sensor");
        up.setMode(DigitalChannel.Mode.INPUT);
        down = hwMap.get(DigitalChannel.class, "low_sensor");
        down.setMode(DigitalChannel.Mode.INPUT);

    }

    /**
     * get tests for the transfer mechanism
     * @return list of tests for the mechanism
     */
    @Override
    public List<QQ_Test> getTests() {
        return Arrays.asList(
                new QQ_TestServo("tilt servo", upLocation, downLocation, tilt),
                new QQ_TestCRServo("left belts", beltSpeed, leftBelts),
                new QQ_TestCRServo("right belts", beltSpeed, rightBelts),
                new QQ_DualTest(
                        new QQ_TestDigitalSensor("Up", up),
                        new QQ_TestDigitalSensor("down", down)),
                new QQ_TestDistance("Distance", distanceSensor));
    }

    /**
     *
     * @param desiredState change transfer state
     */
    public void changeTransfer(Transfer.transferState desiredState) {
        if (desiredState == transferState.START) {
            rightBelts.setPower(beltSpeed);
            leftBelts.setPower(beltSpeed);
            checkForRing(true);

        } else if (desiredState == transferState.REVERSE) {
            rightBelts.setPower(reverseBeltSpeed);
            leftBelts.setPower(reverseBeltSpeed);
            checkForRing(false);

        } else {
            rightBelts.setPower(0);
            leftBelts.setPower(0);

        }
    }

    public elevatorState currentState(){
        if (up.getState()){
           return elevatorState.UP;
        } else if (down.getState()){
            return elevatorState.DOWN;
        }
        return elevatorState.BUSY;
    }

    public void setState(elevatorState state){
        if(state == elevatorState.UP){
            tilt.setPosition(upLocation);
        }
        if(state == elevatorState.DOWN){
            tilt.setPosition(downLocation);
        }
    }

    /**
     *
     * @return distance sensor
     */
     private boolean seeRing(){
        return distanceSensor.getDistance(DistanceUnit.CM) <= normalDistance - distanceTolerance;
     }

    /**
     *
     * @param forward change wasRing to seeRing
     */
     private void checkForRing(boolean forward){
        if(seeRing() & !wasRing) {
            ringsSeen += forward ? 0.5 : -0.5;
        }
        wasRing = seeRing();
     }

    /**
     *
     * @param reset rings seen
     * @return ring count
     */
     public double getRingsSeen(boolean reset){
         double ringCount = ringsSeen;
         if(reset){
            ringsSeen = 0;
        }
        return ringCount;
     }


        /**
         * get name
         * @return returns name as string
         */
        @Override
        public String getName () {
            return "Transfer";
        }
    }
