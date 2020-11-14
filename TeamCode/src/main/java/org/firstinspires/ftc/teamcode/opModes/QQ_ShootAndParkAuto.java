package org.firstinspires.ftc.teamcode.opModes;


import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.actions.DriveToAction;
import org.firstinspires.ftc.teamcode.actions.ShootAction;
import org.firstinspires.ftc.teamcode.utils.NavigationPose;

@Autonomous()
public class QQ_ShootAndParkAuto extends QQ_ParkAuto {
    double SHOOT_X = 36;
    double SHOOT_Y = PARK_Y - 18;

    @Override
    public void init() {
        super.init();
        currentAction = new DriveToAction(new NavigationPose(SHOOT_X, SHOOT_Y),
                "drives to shooting spot",
                new ShootAction("shoot 1",
                        new ShootAction("shoot 2",
                                new ShootAction("shoot 3",
                                    new DriveToAction(new NavigationPose(SHOOT_X, PARK_Y),
                                            "drives to park",
                                            null)))));

    }


}
