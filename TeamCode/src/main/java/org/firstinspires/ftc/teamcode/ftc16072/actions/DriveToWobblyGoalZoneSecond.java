package org.firstinspires.ftc.teamcode.ftc16072.actions;

import org.firstinspires.ftc.teamcode.ftc16072.opModes.QQ_Opmode;
import org.firstinspires.ftc.teamcode.ftc16072.utils.NavigationPose;
import org.firstinspires.ftc.teamcode.ftc16072.utils.StackPipeline;

public class DriveToWobblyGoalZoneSecond extends QQ_Action{
    double ZONE_A_X = 22;
    double ZONE_A_Y = 78;
    double ZONE_B_X = 48;
    double ZONE_B_Y = 110;
    double ZONE_C_X = 10;
    double ZONE_C_Y = 128;

    /**
     * checks rings seen and drives to specific zone
     * @param opmode this gives the action access to our robot, gamepads, time left etc
     * @return drives to zone a, b, or c
     */
    @Override
    public QQ_Action run(QQ_Opmode opmode) {
        if (opmode.numberRingsSeen == StackPipeline.ringNumber.ZERO){
            return new DriveToAction("moving to Zone A", new NavigationPose(ZONE_A_X, ZONE_A_Y, 0.5, .3, 1, -100, .5)).setNext(nextAction);
        } else if (opmode.numberRingsSeen == StackPipeline.ringNumber.ONE){
            return new DriveToAction("navigating for Zone B", new NavigationPose(ZONE_B_X, ZONE_B_Y, 0.5, .3, 1,-90, .5)).setNext(nextAction);
        } else {
            return new DriveToAction("navigaiting for Zone C", new NavigationPose(56, ZONE_C_Y-48, 7, .8, 1, -90, 90))
                    .setNext(new DriveToAction("driving to Zone C", new NavigationPose(ZONE_C_X, ZONE_C_Y, 0.5, .6,1, -90, .5)))
                    .setNext(nextAction);
        }

    }
}