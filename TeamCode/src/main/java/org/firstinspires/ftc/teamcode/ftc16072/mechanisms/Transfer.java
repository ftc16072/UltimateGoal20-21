package org.firstinspires.ftc.teamcode.ftc16072.mechanisms;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.ftc16072.mechanisms.tests.QQ_Test;
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
    private static final double transferSpeed = 0.5;
    private static final double reverseTransferSpeed = -0.25;

    @Override
    public void init(HardwareMap hwMap) {
        transferMotor = hwMap.get(DcMotor.class, "transfer_motor");
    }

    /**
     * get tests for the transfer mechanism
     *
     * @return list of tests for the mechanism
     */
    @Override
    public List<QQ_Test> getTests() {
        return Arrays.asList(new QQ_TestMotor("Transfer Motor", 0.5, transferMotor));
    }

    public void changeTransfer(Transfer.transferState desiredState) {
        if (desiredState == transferState.Start) {
            transferMotor.setPower(transferSpeed);
        } else if (desiredState == transferState.Reverse) {
            transferMotor.setPower(reverseTransferSpeed);
        } else {
            transferMotor.setPower(0);
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
