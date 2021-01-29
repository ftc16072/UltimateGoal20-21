package org.firstinspires.ftc.teamcode.ftc16072.mechanisms;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.ftc16072.mechanisms.tests.QQ_Test;
import org.firstinspires.ftc.teamcode.ftc16072.mechanisms.tests.QQ_TestMotor;
import org.firstinspires.ftc.teamcode.ftc16072.mechanisms.tests.QQ_TestMotorPosition;
import org.firstinspires.ftc.teamcode.ftc16072.mechanisms.tests.QQ_TestServo;

import java.util.Arrays;
import java.util.List;

public class WobblyGoal implements QQ_Mechanism {
    private DcMotor rotator;
    private Servo grabber;
    private final int ROTATOR_IN = 0;
    private final int ROTATOR_UP = -240;
    private final int ROTATOR_DOWN = -720;
    private final double ROTATOR_MAX_SPEED = 0.5;
    private final double GRABBER_OPEN = 1.0;
    private final double GRABBER_CLOSED = 0.65;

    /**
     * initialize hardware map
     * @param hwMap Hardware map from configuration
     */
    @Override
    public void init(HardwareMap hwMap) {
        rotator = hwMap.get(DcMotor.class, "rotator");
        rotator.setTargetPosition(0);
        rotator.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        grabber = hwMap.get(Servo.class, "grabber");
    }

    /**
     * Get wobbly goal tests
     * @return List of Tests
     */
    @Override
    public List<QQ_Test> getTests() {
        return Arrays.asList((QQ_Test) new QQ_TestMotorPosition("rotator", ROTATOR_DOWN, ROTATOR_IN, ROTATOR_MAX_SPEED, rotator ),
                new QQ_TestMotor("rotator - motor only", 0.2, rotator),
                new QQ_TestServo("grabber", GRABBER_CLOSED, GRABBER_OPEN, grabber));
    }

    /**
     * grabber mechanism will open
     */
    public void openGrabber() {
        grabber.setPosition(GRABBER_OPEN);
    }

    /**
     * grabber mechanism will close
     */
    public void closeGrabber() {
        grabber.setPosition(GRABBER_CLOSED);
    }


    /**
     * rotator mechanism goes up
     */
    public void raiseRotator() {
        rotator.setTargetPosition(ROTATOR_UP);
        rotator.setPower(ROTATOR_MAX_SPEED);
    }
    /**
     * rotator mechanism goes down
     */
    public void lowerRotator() {
        rotator.setTargetPosition(ROTATOR_DOWN);
        rotator.setPower(ROTATOR_MAX_SPEED);
    }

    public boolean isRotatorBusy(){
        return rotator.isBusy();
    }


    /**
     * get name
     * @return title as string
     */
    @Override
    public String getName() {
        return "Wobbly Goal";
    }
}
