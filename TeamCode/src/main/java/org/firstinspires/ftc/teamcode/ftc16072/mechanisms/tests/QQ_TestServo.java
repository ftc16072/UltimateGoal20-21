package org.firstinspires.ftc.teamcode.ftc16072.mechanisms.tests;

import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class QQ_TestServo extends QQ_Test {
    private double offLocation;
    private double onLocation;
    private Servo servo;

    /**
     * @param description a human readable description of the test
     * @param onLocation  the location the servo goes to when on is pressed
     * @param offLocation the rest location of the servo
     * @param servo       the servo to test
     */
    public QQ_TestServo(String description, double onLocation, double offLocation, Servo servo) {
        super(description);
        this.servo = servo;
        this.offLocation = offLocation;
        this.onLocation = onLocation;
    }
    /**
     * if on run at set speed, off turn off the motor
     *
     * @param on        servo to the on or off location
     * @param telemetry telemetry for the test to send servo position
     */
    @Override
    public void run(boolean on, Telemetry telemetry) {
        if (on) {
            servo.setPosition(onLocation);
        } else {
            servo.setPosition(offLocation);
        }
        telemetry.addData("Position", servo.getPosition());
    }
}
