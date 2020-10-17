package org.firstinspires.ftc.teamcode.mechanisms;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.mechanisms.tests.QQ_Test;

import java.util.List;

class Intake implements QQ_Mechanism {
    private DcMotor intakeMotor;

    /**
     * initialize intake
     * @param hwMap hardware map from configuration
     */
    @Override
    public void init(HardwareMap hwMap) {
        intakeMotor = hwMap.get(DcMotor.class, "intake_motor");
    }

    /**
     * Gets tests
     * @return a list of tests
     */
    @Override
    public List<QQ_Test> getTests() {
        return null;
    }

    public void ConveyorBelt(){}

    public void intake() {

    }

    /**
     * gets name
     * @return intake
     */
    @Override
    public String getName() {
        return "Intake";
    }
}
