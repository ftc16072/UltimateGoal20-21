package org.firstinspires.ftc.teamcode.ftc16072.mechanisms;

import com.qualcomm.robotcore.hardware.HardwareMap;

import java.util.Arrays;
import java.util.List;

public class OdoOnlyRobot {
    public OdoMecanumDrive mecanumDrive = new OdoMecanumDrive();
    public Navigation nav = new Navigation(mecanumDrive);
    private List<QQ_Mechanism> mechanisms = Arrays.asList(
            mecanumDrive
    );

    /**
     * initializing hardware map
     * @param hwMap returning the mechanism that it calls for
     */
    public void init(HardwareMap hwMap){
        for (QQ_Mechanism mechanism : mechanisms){
            mechanism.init(hwMap);
        }
        nav.init(hwMap);
    }

    /**
     * gets the mechanisms the robot has
     * @return a list of QQ_mechanism
     */
    public List<QQ_Mechanism> getMechanisms(){
        return mechanisms;
    }
}
