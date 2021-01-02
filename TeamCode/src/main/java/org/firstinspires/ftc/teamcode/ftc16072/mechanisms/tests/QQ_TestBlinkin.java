package org.firstinspires.ftc.teamcode.ftc16072.mechanisms.tests;

import com.qualcomm.hardware.rev.RevBlinkinLedDriver;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class QQ_TestBlinkin extends QQ_Test {
    private RevBlinkinLedDriver.BlinkinPattern offPattern;
    private RevBlinkinLedDriver.BlinkinPattern onPattern;
    private RevBlinkinLedDriver blinkin;

    /**
     * @param description a human readable description of the test
     * @param onPattern  the pattern displayed when on is pressed
     * @param offPattern the rest pattern of the blinkin
     * @param blinkin       the blinkin to test
     */
    public QQ_TestBlinkin(String description, RevBlinkinLedDriver.BlinkinPattern offPattern,
                          RevBlinkinLedDriver.BlinkinPattern onPattern,
                          RevBlinkinLedDriver blinkin) {
        super(description);
        this.blinkin = blinkin;
        this.offPattern = offPattern;
        this.onPattern = onPattern;
    }
    /**
     * if on show the on pattern, off show the off pattern
     *
     * @param on        witch pattern to show
     * @param telemetry n/a
     */
    @Override
    public void run(boolean on, Telemetry telemetry) {
        if (on) {
            blinkin.setPattern(onPattern);
        } else {
            blinkin.setPattern(offPattern);
        }
    }
}
