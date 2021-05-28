package org.firstinspires.ftc.teamcode.ftc16072.mechanisms.tests;

import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class QQ_TestMotorEncoder extends QQ_Test {
    private DcMotor dcMotor;
    /**
     * Constructor
     *
     * @param description human readable of the description
     * @param motor       instance of DcMotor class to run the test on
     */
    public QQ_TestMotorEncoder(String description, DcMotor motor) {
        super(description);
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
        telemetry.addData("Encoder", dcMotor.getCurrentPosition());
    }
}
