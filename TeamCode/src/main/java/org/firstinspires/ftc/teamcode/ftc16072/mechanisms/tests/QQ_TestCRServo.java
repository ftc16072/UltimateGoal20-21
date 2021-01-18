package org.firstinspires.ftc.teamcode.ftc16072.mechanisms.tests;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class QQ_TestCRServo extends QQ_Test {
    private double power;
    private CRServo servo;

    /**
     * @param description a human readable description of the test
     * @param power the power to run the servo at
     * @param servo       the servo to test
     */
    public QQ_TestCRServo(String description,  double power, CRServo servo) {
        super(description);
        this.servo = servo;
        this.power = power;
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
            servo.setPower(power);
        } else {
            servo.setPower(0);
        }
        telemetry.addData("Speed", servo.getPower());
    }
}
