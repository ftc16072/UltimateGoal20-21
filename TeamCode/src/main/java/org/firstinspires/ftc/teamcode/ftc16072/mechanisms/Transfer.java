package org.firstinspires.ftc.teamcode.ftc16072.mechanisms;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.teamcode.ftc16072.mechanisms.tests.QQ_DualTest;
import org.firstinspires.ftc.teamcode.ftc16072.mechanisms.tests.QQ_Test;
import org.firstinspires.ftc.teamcode.ftc16072.mechanisms.tests.QQ_TestCRServo;
import org.firstinspires.ftc.teamcode.ftc16072.mechanisms.tests.QQ_TestDigitalSensor;
import org.firstinspires.ftc.teamcode.ftc16072.mechanisms.tests.QQ_TestDistance;
import org.firstinspires.ftc.teamcode.ftc16072.mechanisms.tests.QQ_TestMotor;
import org.firstinspires.ftc.teamcode.ftc16072.mechanisms.tests.QQ_TestServo;

import java.util.Arrays;
import java.util.List;

@Config
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


    private DcMotor transfer;
    private Servo tilt;


    private final double transferSpeed = -1;
    private final double reverseTransferSpeed = 0.7;

    public static double UP_LOCATION = .64;
    public static double DOWN_LOCATION = .40;

    private elevatorState stateGlobal;



    /**
     * initializes the transfer
     *
     * @param hwMap forces the init to take a Hardware Map from the configuration
     */
    @Override
    public void init(HardwareMap hwMap) {
        tilt = hwMap.get(Servo.class, "tilt_servo");
        transfer = hwMap.get(DcMotor.class, "transfer");
    }

    /**
     * get tests for the transfer mechanism
     * @return list of tests for the mechanism
     */
    @Override
    public List<QQ_Test> getTests() {
        return Arrays.asList(
                new QQ_TestServo("tilt servo", UP_LOCATION, DOWN_LOCATION, tilt),
                new QQ_TestMotor("transfer", transferSpeed, transfer));
    }

    /**
     *
     * @param desiredState change transfer state
     */
    public void changeTransfer(Transfer.transferState desiredState) {
        if (desiredState == transferState.START) {
            transfer.setPower(transferSpeed);
        } else if (desiredState == transferState.REVERSE) {
            transfer.setPower(reverseTransferSpeed);
        } else {
            transfer.setPower(0.0);
        }
    }

    public elevatorState currentState(){
        return stateGlobal;
    }

    public void setState(elevatorState state){
        if(state == elevatorState.UP){
            tilt.setPosition(UP_LOCATION);
            stateGlobal = elevatorState.UP;
        }
        if(state == elevatorState.DOWN){
            tilt.setPosition(DOWN_LOCATION);
            stateGlobal = elevatorState.DOWN;
        }
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
