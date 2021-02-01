package org.firstinspires.ftc.teamcode.ftc16072.mechanisms.tests;

import com.qualcomm.robotcore.hardware.DistanceSensor;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

public class QQ_TestDistance extends QQ_Test {
    private DistanceSensor distanceSensor;

    /**
     * Constructor
     *
     * @param description    a human readable description of the test
     * @param distanceSensor an instance of the distance sensor class to use for the test
     */
    public QQ_TestDistance(String description, DistanceSensor distanceSensor) {
        super(description);
        this.distanceSensor = distanceSensor;
    }

    /**
     * sends the distance in centimeters
     *
     * @param on        on does nothing for this one
     * @param telemetry telemetry for the test to send what its doing
     */
    @Override
    public void run(boolean on, Telemetry telemetry) {
        telemetry.addData("distance (centimeters)", distanceSensor.getDistance(DistanceUnit.CM));
    }
}
