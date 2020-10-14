package org.firstinspires.ftc.teamcode.mechanisms;

import com.qualcomm.robotcore.hardware.HardwareMap;

import java.util.Arrays;
import java.util.List;

public class Robot {
    public MecanumDrive mecanumDrive = new MecanumDrive();
    Shooter shooter = new Shooter();
    Intake intake = new Intake();
    Transfer transfer = new Transfer();
    WobblyGoal wobblyGoal = new WobblyGoal();
    private List<QQ_Mechanism> mechanisms = Arrays.asList(
            mecanumDrive,
            shooter,
            intake,
            transfer,
            wobblyGoal
    );


    public void init(HardwareMap hwMap){
        for (QQ_Mechanism mechanism : mechanisms){
            mechanism.init(hwMap);
        }
    }
    public List<QQ_Mechanism> getMechanisms(){
        return mechanisms;
    }
}
