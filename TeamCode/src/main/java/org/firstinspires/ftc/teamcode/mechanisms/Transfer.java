package org.firstinspires.ftc.teamcode.mechanisms;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.mechanisms.tests.QQ_Test;

import java.util.List;

class Transfer implements QQ_Mechanism {
    @Override
    public void init(HardwareMap hwMap) {

    }

    /**
     * transfer mechanism
     * @return nothing
     */
    @Override
    public List<QQ_Test> getTests() {
        return null;
    }

    /**
     * get name
     * @return transfer
     */
    @Override
    public String getName() {
        return "Transfer";
    }
}
