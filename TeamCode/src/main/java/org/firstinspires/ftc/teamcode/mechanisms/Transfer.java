package org.firstinspires.ftc.teamcode.mechanisms;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.mechanisms.tests.QQ_Test;

import java.util.List;

class Transfer implements QQ_Mechanism {
    /**
     * initializes the transfer
     * @param hwMap forces the init to take a Hardware Map from the configuration
     */
    @Override
    public void init(HardwareMap hwMap) {

    }

    /**
     * get tests for the transfer mechanism
     * @return list of tests for the mechanism
     */
    @Override
    public List<QQ_Test> getTests() {
        return null;
    }

    /**
     * get name
     * @return returns name as string
     */
    @Override
    public String getName() {
        return "Transfer";
    }
}
