package org.firstinspires.ftc.teamcode.ftc16072.actions;

import org.firstinspires.ftc.teamcode.ftc16072.opModes.QQ_Opmode;
import org.firstinspires.ftc.teamcode.ftc16072.utils.NavigationPose;
import org.firstinspires.ftc.teamcode.ftc16072.utils.StackPipeline;

public class DriveToSecondWobble extends QQ_Action{
    double x;
    double y;
    double angle;


    /**
     * checks rings seen and drives to specific zone
     * @param opmode this gives the action access to our robot, gamepads, time left etc
     * @return drives to second ring with delta based on ring
     */
    @Override
    public QQ_Action run(QQ_Opmode opmode) {
        if (opmode.numberRingsSeen == StackPipeline.ringNumber.ZERO){
            x = 53;
            y = 35;
            angle = 3;
         } else if (opmode.numberRingsSeen == StackPipeline.ringNumber.ONE) {
            x = 57;
            y = 39;
            angle = 3;
        } else {
            x = 54;
            y = 31;
            angle = 0;
        }

        return new DriveToAction("clear rings", new NavigationPose(x, 80, 7, .9, 1, 0, 2))
                .setNext(new DriveToAction("Grab Wobble", new NavigationPose(x, y, .5, .1, .4, angle, 1))).setNext(nextAction);


    }
}
