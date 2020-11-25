package org.firstinspires.ftc.teamcode.ftc16072.opModes;


import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.ftc16072.actions.DriveToAction;
import org.firstinspires.ftc.teamcode.ftc16072.actions.ShootAction;
import org.firstinspires.ftc.teamcode.ftc16072.utils.NavigationPose;

@Autonomous()
public class QQ_ShootAndParkAuto extends QQ_ParkAuto {
    double SHOOT_X = 36;
    double SHOOT_Y = PARK_Y - 18;

    @Override
    public void init() {
        super.init();
        currentAction = new DriveToAction("drives to shooting spot",new NavigationPose(SHOOT_X, SHOOT_Y)).
                setNext(new ShootAction("shoot 1")).
                setNext(new ShootAction("shoot 2")).
                setNext(new ShootAction("shoot 3")).
                setNext(new DriveToAction("drives to park",new NavigationPose(SHOOT_X, PARK_Y)));

    }


}
