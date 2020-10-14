package org.firstinspires.ftc.teamcode.mechanisms;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.mechanisms.tests.QQ_Test;

import java.util.List;

class Shooter implements QQ_Mechanism {
    private DcMotor ShooterLeft;
    private DcMotor ShooterRight;

    @Override
    public void init(HardwareMap hwMap) {
//ShooterLeft = hwMap.get(DcMotor.class, "LeftMotor");
//ShooterRight = hwMap.get(DcMotor.class, "RightMotor");
    }

    @Override
    public List<QQ_Test> getTests() {
        return null;
    }

    //create methods to set angle up and down, left, right
    public void ShooterUp() {

    }

    public void ShooterLeft() {

    }

    public void ShooterRight() {

    }

    public void ShooterDown() {

    }

    @Override
    public String getName() {
        return "Shooter";
    }
}
