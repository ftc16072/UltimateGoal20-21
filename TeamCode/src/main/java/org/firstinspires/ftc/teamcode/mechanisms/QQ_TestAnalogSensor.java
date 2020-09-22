package org.firstinspires.ftc.teamcode.mechanisms;

import com.qualcomm.robotcore.hardware.AnalogInput;
import com.qualcomm.robotcore.hardware.DistanceSensor;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

public class QQ_TestAnalogSensor extends QQ_Test{
        private AnalogInput analogInput;

    QQ_TestAnalogSensor(String description, AnalogInput analogInput){
        super(description);
        this.analogInput = analogInput;
    }

    @Override
    void run(boolean on, Telemetry telemetry) {
        telemetry.addData("distance (centimeters)", analogInput.getVoltage());
    }
}
