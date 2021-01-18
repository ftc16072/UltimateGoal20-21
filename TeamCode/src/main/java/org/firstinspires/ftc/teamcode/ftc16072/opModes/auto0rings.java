package org.firstinspires.ftc.teamcode.ftc16072.opModes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.ftc16072.actions.DriveToAction;
import org.firstinspires.ftc.teamcode.ftc16072.actions.DropWobblyGoal;
import org.firstinspires.ftc.teamcode.ftc16072.actions.PickUpWobblyGoal;
import org.firstinspires.ftc.teamcode.ftc16072.utils.NavigationPose;


@Autonomous()
public class auto0rings extends QQ_Opmode {
    double ZONE_X = 10;
    double ZONE_Y = 80;
    double GOAL_X = 24;
    double GOAL_Y = 12;

    @Override
    public void init() {
        super.init();
        currentAction = new DriveToAction("drives to wobbly goal spot", new NavigationPose(ZONE_X, ZONE_Y)).
                        setNext(new DropWobblyGoal().
                        setNext(new DriveToAction("drives to pick up the second wobbly goal", new NavigationPose(GOAL_X, GOAL_Y)).
                        setNext(new PickUpWobblyGoal().
                        setNext(new DriveToAction("drives to drop point", new NavigationPose(ZONE_X, ZONE_Y))))))

        ;


    }


}
