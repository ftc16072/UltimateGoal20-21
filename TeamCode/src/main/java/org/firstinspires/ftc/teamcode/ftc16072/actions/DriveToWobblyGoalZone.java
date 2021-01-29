package org.firstinspires.ftc.teamcode.ftc16072.actions;

import org.firstinspires.ftc.teamcode.ftc16072.opModes.QQ_Opmode;
import org.firstinspires.ftc.teamcode.ftc16072.utils.NavigationPose;
import org.firstinspires.ftc.teamcode.ftc16072.utils.StackPipeline;

public class DriveToWobblyGoalZone extends QQ_Action{
    double ZONE_A_X = 12;
    double ZONE_A_Y = 68;
    double ZONE_B_X = 40;
    double ZONE_B_Y = 95;
    double ZONE_C_X = 12;
    double ZONE_C_Y = 110;

    @Override
    public QQ_Action run(QQ_Opmode opmode) {
        if (opmode.numberRingsSeen == StackPipeline.ringNumber.ZERO){
            return new DriveToAction("moving to Zone A", new NavigationPose(ZONE_A_X, ZONE_A_Y, 0.5, -130, .5)).setNext(nextAction);
        } else if (opmode.numberRingsSeen == StackPipeline.ringNumber.ONE){
            return new DriveToAction("moving to Zone B", new NavigationPose(ZONE_B_X, ZONE_B_Y, 0.5, -150, .5)).setNext(nextAction);
        } else {
            return new DriveToAction("moving to Zone C", new NavigationPose(ZONE_C_X, ZONE_C_Y, 0.5, -130, .5)).setNext(nextAction);
        }

    }
}