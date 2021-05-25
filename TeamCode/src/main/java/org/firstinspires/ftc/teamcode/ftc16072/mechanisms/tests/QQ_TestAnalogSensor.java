package org.firstinspires.ftc.teamcode.ftc16072.mechanisms.tests;

import com.qualcomm.robotcore.hardware.AnalogInput;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class QQ_TestAnalogSensor extends QQ_Test {
    private AnalogInput analogInput;

    /**
     * Constructor
     *
     * @param description a human readable description of the test
     * @param analogInput an instance of a analog input sensor the test is using
     */
    public QQ_TestAnalogSensor(String description, AnalogInput analogInput) {
        super(description);
        this.analogInput = analogInput;
    }

    /**
     * run method sends the voltage of the sensor to telemetry
     *
     * @param on        does nothing for this test
     * @param telemetry telemetry for the test to send the voltage of the sensor
     */
    @Override
    public void run(boolean on, Telemetry telemetry) {
        telemetry.addData("Voltage (volts)", analogInput.getVoltage());
    }
}
