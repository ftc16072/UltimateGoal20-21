package org.firstinspires.ftc.teamcode.ftc16072.mechanisms;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.ftc16072.mechanisms.tests.QQ_Test;
import org.firstinspires.ftc.teamcode.ftc16072.mechanisms.tests.QQ_TestMotor;
import org.firstinspires.ftc.teamcode.ftc16072.mechanisms.tests.QQ_TestServo;

import java.util.Arrays;
import java.util.List;

public class WobblyGoal implements QQ_Mechanism {
    private DcMotor rotator;
    private Servo grabber;
    private final double ROTATOR_UP = 0.0;
    private final double ROTATOR_DOWN = 0.6;
    private final double GRABBER_OPEN = 0.65;
    private final double GRABBER_CLOSED = 0.85;

    /**
     * initialize hardware map
     * @param hwMap Hardware map from configuration
     */
    @Override
    public void init(HardwareMap hwMap) {
        rotator = hwMap.get(DcMotor.class, "rotator");
        grabber = hwMap.get(Servo.class, "grabber");
    }

    /**
     * Get wobbly goal tests
     * @return List of Tests
     */
    @Override
    public List<QQ_Test> getTests() {
        return Arrays.asList((QQ_Test) new QQ_TestMotor("rotator", .2, rotator ),
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

    }
    /**
     * rotator mechanism goes down
     */
    public void lowerRotator() {

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
