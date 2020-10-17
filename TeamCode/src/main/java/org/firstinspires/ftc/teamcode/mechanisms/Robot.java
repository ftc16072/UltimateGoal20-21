package org.firstinspires.ftc.teamcode.mechanisms;

import com.qualcomm.robotcore.hardware.HardwareMap;

import java.util.Arrays;
import java.util.List;

public class Robot {
    public MecanumDrive mecanumDrive = new MecanumDrive();
    public Navigation nav = new Navigation(mecanumDrive);
    public Shooter shooter = new Shooter();
    public Intake intake = new Intake();
    public Transfer transfer = new Transfer();
    public WobblyGoal wobblyGoal = new WobblyGoal();
    private List<QQ_Mechanism> mechanisms = Arrays.asList(
            mecanumDrive,
            shooter,
            intake,
            transfer,
            wobblyGoal
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
    public List<QQ_Mechanism> getMechanisms(){
        return mechanisms;
    }
}
