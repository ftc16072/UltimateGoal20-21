package org.firstinspires.ftc.teamcode.mechanisms;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.mechanisms.tests.QQ_Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Navigation {
    public BNO055IMU imu;
    Robot robot;

    Navigation(Robot robot){
        this.robot = robot;
    }

    void init(HardwareMap hwmap){
        imu = hwmap.get(BNO055IMU.class, "imu");
    }

}
