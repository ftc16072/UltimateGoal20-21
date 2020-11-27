package org.firstinspires.ftc.teamcode.ftc16072.actions;

import org.firstinspires.ftc.teamcode.ftc16072.opModes.QQ_Opmode;
import org.firstinspires.ftc.teamcode.ftc16072.utils.NavigationPose;

public class DriveToWobblyGoalZone extends QQ_Action{
    double ZONE_A_X = 10;
    double ZONE_A_Y = 80;
    double ZONE_B_X = 35;
    double ZONE_B_Y = 105;
    double ZONE_C_X = 10;
    double ZONE_C_Y = 140;

    @Override
    public QQ_Action run(QQ_Opmode opmode) {
        if (opmode.numberRingsSeen == 0){
            return new DriveToAction("moving to Zone A", new NavigationPose(ZONE_A_X, ZONE_A_Y)).setNext(nextAction);
        }
        if (opmode.numberRingsSeen == 1){
            return new DriveToAction("moving to Zone B", new NavigationPose(ZONE_B_X, ZONE_B_Y)).setNext(nextAction);
        }
        if (opmode.numberRingsSeen == 4){
            return new DriveToAction("moving to Zone C", new NavigationPose(ZONE_C_X, ZONE_C_Y)).setNext(nextAction);
        }
        return nextAction;
    }
}