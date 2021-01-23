package org.firstinspires.ftc.teamcode.ftc16072.mechanisms;

import com.qualcomm.hardware.rev.RevBlinkinLedDriver;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.ftc16072.mechanisms.tests.QQ_Test;
import org.firstinspires.ftc.teamcode.ftc16072.mechanisms.tests.QQ_TestBlinkin;
import org.firstinspires.ftc.teamcode.ftc16072.mechanisms.tests.QQ_TestServo;

import java.util.Arrays;
import java.util.List;

public class Lights implements QQ_Mechanism {
    RevBlinkinLedDriver blinkinLedDriver;

    public enum lightState{
        green,
        yellow,
        orange,
        red,
        blueStrobe
    }
    /**
     * initialize hardware map
     * @param hwMap Hardware map from configuration
     */
    @Override
    public void init(HardwareMap hwMap) {
        blinkinLedDriver = hwMap.get(RevBlinkinLedDriver.class, "blinkin");
    }

    /**
     * Get wobbly goal tests
     * @return List of Tests
     */
    @Override
    public List<QQ_Test> getTests() {
        return Arrays.asList(new QQ_TestBlinkin("lights", RevBlinkinLedDriver.BlinkinPattern.YELLOW, RevBlinkinLedDriver.BlinkinPattern.VIOLET, blinkinLedDriver));
    }
    /**
     * get name
     * @return title as string
     */
    @Override
    public String getName() {
        return "Lights";
    }
    public void warn(){
        blinkinLedDriver.setPattern(RevBlinkinLedDriver.BlinkinPattern.STROBE_RED);
    }
    public void normal(){
        blinkinLedDriver.setPattern(RevBlinkinLedDriver.BlinkinPattern.YELLOW);
    }
    public void endGame(){
        blinkinLedDriver.setPattern(RevBlinkinLedDriver.BlinkinPattern.CP1_2_TWINKLES);
    }
    public void off(){
        blinkinLedDriver.setPattern(RevBlinkinLedDriver.BlinkinPattern.BLACK);
    }

}
