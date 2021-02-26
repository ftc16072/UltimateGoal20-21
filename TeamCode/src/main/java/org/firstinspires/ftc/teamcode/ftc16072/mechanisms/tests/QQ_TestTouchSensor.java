package org.firstinspires.ftc.teamcode.ftc16072.mechanisms.tests;

import com.qualcomm.robotcore.hardware.AnalogInput;
import com.qualcomm.robotcore.hardware.TouchSensor;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class QQ_TestTouchSensor extends QQ_Test {
    private TouchSensor touch;

    /**
     * Constructor
     *
     * @param description a human readable description of the test
     * @param  touchSensor an instance of a analog input sensor the test is using
     */
    public QQ_TestTouchSensor(String description, TouchSensor touchSensor) {
        super(description);
        this.touch = touchSensor;
    }

    /**
     * run method sends the voltage of the sensor to telemetry
     *
     * @param on        does nothing for this test
     * @param telemetry telemetry for the test to send the voltage of the sensor
     */
    @Override
    public void run(boolean on, Telemetry telemetry) {
        telemetry.addData("Pressed?", touch.isPressed());
    }
}
