package org.firstinspires.ftc.teamcode.ftc16072.mechanisms.tests;

import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class QQ_TestMotorPosition extends QQ_Test {
    private DcMotor dcMotor;
    private int onLocation;
    private int offLocation;
    private double maxSpeed;
    /**
     * Constructor
     *
     * @param description human readable of the description
     * @param onLocation integer
     * @param offLocation integer
     * @param maxSpeed maximum speed of motor
     * @param motor       instance of DcMotor class to run the test on
     */
    public QQ_TestMotorPosition(String description, int onLocation, int offLocation, double maxSpeed, DcMotor motor) {
        super(description);
        this.onLocation = onLocation;
        this.offLocation = offLocation;
        this.maxSpeed = maxSpeed;
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
            dcMotor.setTargetPosition(onLocation);
            dcMotor.setPower(maxSpeed);
        } else {
            dcMotor.setTargetPosition(offLocation);
            dcMotor.setPower(maxSpeed);
        }
        telemetry.addData("Target position", dcMotor.getTargetPosition());
        telemetry.addData("Encoder", dcMotor.getCurrentPosition());
    }
}
