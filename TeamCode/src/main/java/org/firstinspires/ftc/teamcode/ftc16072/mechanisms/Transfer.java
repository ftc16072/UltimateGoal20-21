package org.firstinspires.ftc.teamcode.ftc16072.mechanisms;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.ftc16072.mechanisms.tests.QQ_Test;
import org.firstinspires.ftc.teamcode.ftc16072.mechanisms.tests.QQ_TestCRServo;
import org.firstinspires.ftc.teamcode.ftc16072.mechanisms.tests.QQ_TestMotor;

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

    private DcMotor transferMotor;
    private CRServo leftBelts;
    private CRServo rightBelts;
    private CRServo middleTransferRight;
    private CRServo middleTransferLeft;

    private static final double transferSpeed = 1.0;
    private static final double reverseTransferSpeed = -0.5;
    private static final double beltSpeed = 1.0;
    private static final double reverseBeltSpeed = -1.0;
    private static final double middleTransferSpeed = 1;
    private static final double reverseMiddleTransferSpeed = -0.5;




    @Override
    public void init(HardwareMap hwMap) {
        transferMotor = hwMap.get(DcMotor.class, "transfer_motor");
        leftBelts = hwMap.get(CRServo.class, "leftBelts");
        rightBelts = hwMap.get(CRServo.class, "rightBelts");
        rightBelts.setDirection(DcMotorSimple.Direction.REVERSE);
        middleTransferRight = hwMap.get(CRServo.class, "middleTransferRight");
        middleTransferLeft = hwMap.get(CRServo.class, "middleTransferLeft");
        middleTransferLeft.setDirection(DcMotorSimple.Direction.REVERSE);
    }

    /**
     * get tests for the transfer mechanism
     * @return list of tests for the mechanism
     */
    @Override
    public List<QQ_Test> getTests() {
        return Arrays.asList(new QQ_TestMotor("Transfer Motor", transferSpeed, transferMotor),
                new QQ_TestCRServo("left belts", beltSpeed, leftBelts),
                new QQ_TestCRServo("right belts", beltSpeed, rightBelts),
                new QQ_TestCRServo("middle Transfer Right", middleTransferSpeed, middleTransferRight),
                new QQ_TestCRServo("middle Transfer Left", middleTransferSpeed, middleTransferLeft));

    }

    public void changeTransfer(Transfer.transferState desiredState) {
        if (desiredState == transferState.Start) {
            transferMotor.setPower(transferSpeed);
            rightBelts.setPower(beltSpeed);
            leftBelts.setPower(beltSpeed);
            middleTransferRight.setPower(middleTransferSpeed);
            middleTransferLeft.setPower(middleTransferSpeed);

        } else if (desiredState == transferState.Reverse) {
            transferMotor.setPower(reverseTransferSpeed);
            rightBelts.setPower(reverseBeltSpeed);
            leftBelts.setPower(reverseBeltSpeed);
            middleTransferRight.setPower(reverseMiddleTransferSpeed);
            middleTransferLeft.setPower(reverseMiddleTransferSpeed);

        } else {
            transferMotor.setPower(0);
            rightBelts.setPower(0);
            leftBelts.setPower(0);
            middleTransferRight.setPower(0);
            middleTransferLeft.setPower(0);

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
