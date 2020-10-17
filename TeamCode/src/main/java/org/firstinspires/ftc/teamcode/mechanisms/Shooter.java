package org.firstinspires.ftc.teamcode.mechanisms;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.mechanisms.tests.QQ_Test;

import java.util.List;

class Shooter implements QQ_Mechanism {
    private DcMotor ShooterLeft;
    private DcMotor ShooterRight;

    /**
     * initializes hardware map
     * @param hwMap
     */
    @Override
    public void init(HardwareMap hwMap) {
//ShooterLeft = hwMap.get(DcMotor.class, "LeftMotor");
//ShooterRight = hwMap.get(DcMotor.class, "RightMotor");
    }

    /**
     * gets tests
     * @return nothing
     */
    @Override
    public List<QQ_Test> getTests() {
        return null;
    }

    //create methods to set angle up and down, left, right
    //mmethod for moving the shooter up
    public void ShooterUp() {

    }
//method for moving the shooter to the left
    public void ShooterLeft() {

    }
//method for moving the shooter to the right
    public void ShooterRight() {

    }
//method for moving the shooter down
    public void ShooterDown() {

    }

    /**
     * get name
     * @return name
     */
    @Override
    public String getName() {
        return "Shooter";
    }
}
