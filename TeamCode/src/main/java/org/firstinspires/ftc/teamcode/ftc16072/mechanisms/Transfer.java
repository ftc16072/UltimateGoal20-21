package org.firstinspires.ftc.teamcode.ftc16072.mechanisms;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.TouchSensor;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.teamcode.ftc16072.mechanisms.tests.QQ_Test;
import org.firstinspires.ftc.teamcode.ftc16072.mechanisms.tests.QQ_TestCRServo;
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

    private DcMotor transferMotor;
    private CRServo leftBelts;
    private CRServo rightBelts;
    private DistanceSensor distanceSensor;
    private TouchSensor bottom;
    private Servo tilt;

    private final double transferSpeed = .7;
    private final double reverseTransferSpeed = -0.7;

    public static double upLocation;
    public static double downLocation;

    public static int ENCODERLOCATION = 200;


    private final double beltSpeed = 1.0;
    private final double reverseBeltSpeed = -1.0;

    private double normalDistance;
    private double distanceTolerance = 1.5;

    private boolean wasRing;
    private double ringsSeen;





    @Override
    public void init(HardwareMap hwMap) {
        transferMotor = hwMap.get(DcMotor.class, "transfer_motor");
        transferMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        leftBelts = hwMap.get(CRServo.class, "leftBelts");
        rightBelts = hwMap.get(CRServo.class, "rightBelts");
        rightBelts.setDirection(DcMotorSimple.Direction.REVERSE);
        distanceSensor = hwMap.get(DistanceSensor.class, "transfer_distance");
        normalDistance = distanceSensor.getDistance(DistanceUnit.CM);
        bottom = hwMap.get(TouchSensor.class, "bottom_limit");
        tilt = hwMap.get(Servo.class, "tilt_servo");
    }

    /**
     * get tests for the transfer mechanism
     * @return list of tests for the mechanism
     */
    @Override
    public List<QQ_Test> getTests() {
        return Arrays.asList(new QQ_TestMotor("Transfer Motor", .2, transferMotor),
                new QQ_TestMotor("Transfer Reverse", -.2, transferMotor),
                new QQ_TestServo("tilt servo", upLocation, downLocation, tilt),
                new QQ_TestCRServo("left belts", beltSpeed, leftBelts),
                new QQ_TestCRServo("right belts", beltSpeed, rightBelts),
                new QQ_TestTouchSensor("bottom", bottom),
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
            transferMotor.setPower(0);
            rightBelts.setPower(0);
            leftBelts.setPower(0);

        }
    }

    public elevatorState currentState(){
        if(transferMotor.getCurrentPosition() <=10){
            return elevatorState.DOWN;
        }
        if(transferMotor.getCurrentPosition() >= ENCODERLOCATION + 10){
            return elevatorState.UP;
        }
        return elevatorState.BUSY;
    }

    public void setState(elevatorState state){
        if(state == elevatorState.UP){
            goUP();
            tilt.setPosition(upLocation);
        }
        if(state == elevatorState.DOWN){
            goDOWN();
            tilt.setPosition(downLocation);
        }
    }

    private void goUP(){
        transferMotor.setTargetPosition(ENCODERLOCATION);
        transferMotor.setPower(transferSpeed);
    }

    private void goDOWN(){
        transferMotor.setTargetPosition(0);
        transferMotor.setPower(reverseTransferSpeed);
    }


    public void checkEncoder(){
        if(bottom.isPressed()){
            transferMotor.setPower(0);
            transferMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        } else {
            transferMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            transferMotor.setPower(-0.2);
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
