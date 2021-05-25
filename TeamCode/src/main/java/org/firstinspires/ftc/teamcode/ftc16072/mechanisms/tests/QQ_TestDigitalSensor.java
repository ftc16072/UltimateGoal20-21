package org.firstinspires.ftc.teamcode.ftc16072.mechanisms.tests;

import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.TouchSensor;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class QQ_TestDigitalSensor extends QQ_Test {
    private DigitalChannel sensor;

    /**
     * Constructor
     *
     * @param description a human readable description of the test
     * @param  sensor an instance of a digital chanel sensor the test is using
     */
    public QQ_TestDigitalSensor(String description, DigitalChannel sensor) {
        super(description);
        this.sensor = sensor;
        sensor.setMode(DigitalChannel.Mode.INPUT);
    }

    /**
     * run method sends the voltage of the sensor to telemetry
     *
     * @param on        does nothing for this test
     * @param telemetry telemetry for the test to send the voltage of the sensor
     */
    @Override
    public void run(boolean on, Telemetry telemetry) {
        telemetry.addData("state:", sensor.getState());
    }
}
