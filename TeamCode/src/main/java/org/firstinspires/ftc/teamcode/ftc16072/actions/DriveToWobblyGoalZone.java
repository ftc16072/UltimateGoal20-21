package org.firstinspires.ftc.teamcode.ftc16072.actions;

import org.firstinspires.ftc.teamcode.ftc16072.opModes.QQ_Opmode;
import org.firstinspires.ftc.teamcode.ftc16072.utils.NavigationPose;
import org.firstinspires.ftc.teamcode.ftc16072.utils.StackPipeline;

public class DriveToWobblyGoalZone extends QQ_Action{
    double ZONE_A_X = 10;
    double ZONE_A_Y = 75;
    double ZONE_B_X = 35;
    double ZONE_B_Y = 105;
    double ZONE_C_X = 10;
    double ZONE_C_Y = 140;

    @Override
    public QQ_Action run(QQ_Opmode opmode) {
        if (opmode.numberRingsSeen == StackPipeline.ringNumber.zero){
            return new DriveToAction("moving to Zone A", new NavigationPose(ZONE_A_X, ZONE_A_Y, 180)).setNext(nextAction);
        }
        if (opmode.numberRingsSeen == StackPipeline.ringNumber.one){
            return new DriveToAction("moving to Zone B", new NavigationPose(ZONE_B_X, ZONE_B_Y, 180)).setNext(nextAction);
        }
        if (opmode.numberRingsSeen == StackPipeline.ringNumber.four){
            return new DriveToAction("moving to Zone C", new NavigationPose(ZONE_C_X, ZONE_C_Y, 180)).setNext(nextAction);
        }
        return nextAction;
    }

}