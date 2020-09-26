package org.firstinspires.ftc.teamcode.mechanisms;

import com.qualcomm.robotcore.hardware.HardwareMap;

import java.util.List;

public interface QQ_Mechanism {

     void init(HardwareMap hwMap);

    List<QQ_Test> getTest();

}
