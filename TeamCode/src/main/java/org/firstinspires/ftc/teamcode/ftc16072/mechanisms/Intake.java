package org.firstinspires.ftc.teamcode.ftc16072.mechanisms;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.ftc16072.mechanisms.tests.QQ_Test;
import org.firstinspires.ftc.teamcode.ftc16072.mechanisms.tests.QQ_TestMotor;
import org.firstinspires.ftc.teamcode.ftc16072.mechanisms.tests.QQ_TestServo;

import java.util.Arrays;
import java.util.List;

@Config
public class Intake implements QQ_Mechanism {
    public enum intakeState{
        Start,
        Stop,
        Reverse
    };

    private DcMotor intakeMotor;
    public Servo intakeHolder;
    private final double intakeSpeed = -0.8;
    private final double reverseIntakeSpeed = 0.4;
    public static double HOLD_POSITION = 1.0;
    public static double RELEASE_POSITION = 0.0;

    /**
     * initialize intake
     * @param hwMap hardware map from configuration
     */
    @Override
    public void init(HardwareMap hwMap) {
         intakeMotor = hwMap.get(DcMotor.class, "intake_motor");
         intakeHolder = hwMap.get(Servo.class, "intake_holder");
    }

    /**
     * Gets tests
     * @return a list of tests
     */
    @Override
    public List<QQ_Test> getTests() {
        return Arrays.asList(
                new QQ_TestMotor("Intake Motor",  intakeSpeed, intakeMotor),
                new QQ_TestMotor("motor - FULL speed", -1.0, intakeMotor),
                new QQ_TestServo("Holder", HOLD_POSITION, RELEASE_POSITION, intakeHolder));
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
     * release intake position
     */
    public void release(){
        intakeHolder.setPosition(RELEASE_POSITION);
    }

    /**
     * hold intake position
     */
    public void hold(){
        intakeHolder.setPosition(HOLD_POSITION);
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
