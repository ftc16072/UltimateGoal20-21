package org.firstinspires.ftc.teamcode.ftc16072.mechanisms;

import com.qualcomm.robotcore.hardware.HardwareMap;

import java.util.Arrays;
import java.util.List;

public class Robot {
    public double ringCount;
    public MecanumDrive mecanumDrive = new MecanumDrive();
    public Navigation nav = new Navigation(mecanumDrive);
    /*public Shooter shooter = new Shooter();
    public Intake intake = new Intake();
    public Transfer transfer = new Transfer();
    public WobblyGoal wobblyGoal = new WobblyGoal();
    public Lights lights = new Lights();
    private List<QQ_Mechanism> mechanisms = Arrays.asList(
            mecanumDrive,
            shooter,
            intake,
            transfer,
            wobblyGoal,
            lights
    );

     */
    private List<QQ_Mechanism> mechanisms = Arrays.asList(mecanumDrive);

    /**
     * initializing hardware map
     * @param hwMap returning the mechanism that it calls for
     */
    public void init(HardwareMap hwMap){
        for (QQ_Mechanism mechanism : mechanisms){
            mechanism.init(hwMap);
        }
        nav.init(hwMap);
   //     lights.normal();
    }

    /**
     * gets the mechanisms the robot has
     * @return a list of QQ_mechanism
     */
    public List<QQ_Mechanism> getMechanisms(){
        return mechanisms;
    }
}
