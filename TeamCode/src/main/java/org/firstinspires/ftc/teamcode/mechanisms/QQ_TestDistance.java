package org.firstinspires.ftc.teamcode.mechanisms;

import com.qualcomm.robotcore.hardware.DistanceSensor;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

public class QQ_TestDistance extends QQ_Test {
    private DistanceSensor distanceSensor;

    QQ_TestDistance(String description, DistanceSensor distanceSensor){
        super(description);
        this.distanceSensor = distanceSensor;
    }

    @Override
    void run(boolean on, Telemetry telemetry) {
        telemetry.addData("distance (centimeters)", distanceSensor.getDistance(DistanceUnit.CM) );
    }
}
