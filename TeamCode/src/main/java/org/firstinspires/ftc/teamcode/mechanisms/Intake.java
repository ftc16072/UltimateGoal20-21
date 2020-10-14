package org.firstinspires.ftc.teamcode.mechanisms;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.mechanisms.tests.QQ_Test;

import java.util.List;

class Intake implements QQ_Mechanism {
    private DcMotor intakeMotor;

    @Override
    public void init(HardwareMap hwMap) {
        intakeMotor = hwMap.get(DcMotor.class, "intake_motor");
    }

    @Override
    public List<QQ_Test> getTests() {
        return null;
    }

    public void intake() {

    }

    @Override
    public String getName() {
        return "Intake";
    }
}
