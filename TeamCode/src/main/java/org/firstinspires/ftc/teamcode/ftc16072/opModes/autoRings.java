package org.firstinspires.ftc.teamcode.ftc16072.opModes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.ftc16072.actions.DriveToAction;
import org.firstinspires.ftc.teamcode.ftc16072.actions.DriveToWobblyGoalZone;
import org.firstinspires.ftc.teamcode.ftc16072.actions.DropWobblyGoal;
import org.firstinspires.ftc.teamcode.ftc16072.actions.PickUpWobblyGoal;
import org.firstinspires.ftc.teamcode.ftc16072.utils.NavigationPose;


@Autonomous()
public class autoRings extends QQ_ParkAuto {
    double GOAL_X = 24;
    double GOAL_Y = 12;

    @Override
    public void init() {
        super.init();
        numberRingsSeen = 4;
        currentAction = new DriveToWobblyGoalZone().
                        setNext(new DropWobblyGoal().
                        setNext(new DriveToAction("drives to pick up the second wobbly goal", new NavigationPose(GOAL_X, GOAL_Y)).
                        setNext(new PickUpWobblyGoal().
                        setNext(new DriveToWobblyGoalZone()))));


    }


}
