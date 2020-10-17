package org.firstinspires.ftc.teamcode.mechanisms;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.mechanisms.tests.QQ_Test;
import org.firstinspires.ftc.teamcode.mechanisms.tests.QQ_TestServo;

import java.util.Arrays;
import java.util.List;

class WobblyGoal implements QQ_Mechanism {
    private Servo rotator;
    private Servo grabber;
    private final double ROTATOR_UP = 0.5;
    private final double ROTATOR_DOWN = 0;
    private final double GRABBER_OPEN = 0;
    private final double GRABBER_CLOSED = 0.2;


    @Override
    public void init(HardwareMap hwMap) {
        rotator = hwMap.get(Servo.class, "rotator");
        grabber = hwMap.get(Servo.class, "grabber");
    }

    @Override
    public List<QQ_Test> getTests() {
        return Arrays.asList((QQ_Test) new QQ_TestServo("rotator", ROTATOR_UP, ROTATOR_DOWN, rotator),
                new QQ_TestServo("grabber", GRABBER_CLOSED, GRABBER_OPEN, grabber));
    }

    public void openGrabber() {
        grabber.setPosition(GRABBER_OPEN);
    }

    public void closeGrabber() {
        grabber.setPosition(GRABBER_CLOSED);
    }

    public void raiseRotator() {
        rotator.setPosition(ROTATOR_UP);
    }

    public void lowerRotator() {
        rotator.setPosition(ROTATOR_DOWN);
    }

    @Override
    public String getName() {
        return "Wobbly Goal";
    }
}
