package org.firstinspires.ftc.teamcode.mechanisms;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.mechanisms.tests.QQ_Test;

import java.util.List;

public interface QQ_Mechanism {

    /**
     * forces the mechanism to have a init
     * @param hwMap forces the init to take a Hardware Map
     */
    void init(HardwareMap hwMap);

    /**
     * Forces the Mechanism to have a get tests
     * @return the getTests will return a list of tests
     */
    List<QQ_Test> getTests();

    /**
     * forces all the mechanisms to have a getName
     * @return a String that is its name
     */
    String getName();

}
