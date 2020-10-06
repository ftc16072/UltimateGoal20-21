package org.firstinspires.ftc.teamcode.mechanisms.tests;

import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class QQ_TestMotor extends QQ_Test {
    private DcMotor dcMotor;
    private double speed;
    /**
     * Constructor
     *
     * @param description human readable of the description
     * @param speed       speed for the motor to run the test at
     * @param motor       instance of DcMotor class to run the test on
     */
    QQ_TestMotor(String description, double speed, DcMotor motor) {
        super(description);
        this.speed = speed;
        dcMotor = motor;
    }
    /**
     * if on run at set speed, off turn off the motor
     *
     * @param on        motor on or off
     * @param telemetry telemetry for the test to send encoder position
     */
    @Override
    public void run(boolean on, Telemetry telemetry) {
        if (on) {
            dcMotor.setPower(speed);
        } else {
            dcMotor.setPower(0.0);
        }
        telemetry.addData("Encoder", dcMotor.getCurrentPosition());
    }
}
