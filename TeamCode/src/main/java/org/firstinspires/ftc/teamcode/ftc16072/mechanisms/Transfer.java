package org.firstinspires.ftc.teamcode.ftc16072.mechanisms;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.ftc16072.mechanisms.tests.QQ_Test;
import org.firstinspires.ftc.teamcode.ftc16072.mechanisms.tests.QQ_TestMotor;
import org.firstinspires.ftc.teamcode.ftc16072.mechanisms.tests.QQ_TestServo;

import java.util.Arrays;
import java.util.List;

public class Transfer implements QQ_Mechanism {
    public enum transferState {
        Start,
        Reverse,
        Stop
    }

    /**
     * initializes the transfer
     *
     * @param hwMap forces the init to take a Hardware Map from the configuration
     */

    private Servo transferServo;
    private static final double on = 0.5;
    private static final double off = -0.25;
//change 0.5 and -0.25 and test it
    @Override
    public void init(HardwareMap hwMap) {
        transferServo = hwMap.get(Servo.class, "transfer_Servo");
    }

    /**
     * get tests for the transfer mechanism
     *
     * @return list of tests for the mechanism
     */
    @Override
    public List<QQ_Test> getTests() {
        return Arrays.asList(new QQ_TestServo("Transfer Servo",0.0,0.5, transferServo));
    }

    public void changeTransfer(Transfer.transferState desiredState) {
        if (desiredState == transferState.Start) {
            transferServo.setPosition(on);
        } else if (desiredState == transferState.Reverse) {
            transferServo.setPosition(off);
        } else {
            transferServo.setPosition(0);
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
