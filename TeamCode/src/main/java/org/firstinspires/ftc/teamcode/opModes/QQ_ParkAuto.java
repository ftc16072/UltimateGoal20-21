package org.firstinspires.ftc.teamcode.opModes;


import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.teamcode.actions.DriveToAction;
import org.firstinspires.ftc.teamcode.utils.NavigationPose;
import org.firstinspires.ftc.teamcode.utils.RobotPose;

@Autonomous()
public class QQ_ParkAuto extends QQ_Opmode {
    double START_Y = 9;
    double START_X = 48;
    double PARK_X = START_X;
    double PARK_Y = 80;

    @Override
    public void init() {
        super.init();
        robot.nav.setCurrentPosition(new RobotPose(START_X, START_Y, DistanceUnit.INCH, 0, AngleUnit.DEGREES));
        fitIn18();
        currentAction = new DriveToAction("parks", new NavigationPose(PARK_X, PARK_Y));
    }

    protected void fitIn18() {
    /*hold shooter up
        wobbly goal arm in correct position
        hold intake up
     */
    }

}
