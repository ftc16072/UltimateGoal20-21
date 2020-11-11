package org.firstinspires.ftc.teamcode.mechanisms;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.mechanisms.tests.QQ_Test;
import org.firstinspires.ftc.teamcode.mechanisms.tests.QQ_TestMotor;

import java.util.Arrays;
import java.util.List;

public class Intake implements QQ_Mechanism {
    public enum intakeState{
        Start,
        Stop,
        Reverse
    };

    private DcMotor intakeMotor;
    private static final double intakeSpeed = 0.2;
    private static final double reverseIntakeSpeed = -0.2;

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
        return Arrays.asList(new QQ_TestMotor("Intake Motor",  0.25, intakeMotor));
    }

    /**
     * Sets intake to a desired state based off of the enum
     * @param desiredState state desired for the intake
     */
    public void changeState(Intake.intakeState desiredState){
        if(desiredState == intakeState.Start){
            intakeMotor.setPower(intakeSpeed);
        } else if (desiredState == intakeState.Reverse){
            intakeMotor.setPower(reverseIntakeSpeed);
        } else if (desiredState == intakeState.Stop){
            intakeMotor.setPower(0.0);
        }
    }

    /**
     * gets name
     * @return the name as a string "Intake"
     */
    @Override
    public String getName() {
        return "Intake";
    }
}
