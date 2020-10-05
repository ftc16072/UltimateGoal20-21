package org.firstinspires.ftc.teamcode.mechanisms;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.mechanisms.tests.QQ_Test;

import java.util.List;

class Intake implements QQ_Mechanism {
    @Override
    public void init(HardwareMap hwMap) {

    }

    @Override
    public List<QQ_Test> getTests() {
        return null;
    }

    @Override
    public String getName() {
        return "Intake";
    }
}
