package org.firstinspires.ftc.teamcode.opModes;


import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.teamcode.actions.DriveToAction;
import org.firstinspires.ftc.teamcode.utils.NavigationPose;

public abstract class QQ_Auto extends QQ_Opmode {
    double START_X = 48;
    double TOLERANCE = 0.5;
    double START_Y = 9;
    double PARK_Y = 80;
    @Override
    public void init() {
        super.init();
        fitIn18();
        currentAction = new DriveToAction(new NavigationPose(START_X,
                TOLERANCE,
                PARK_Y,
                TOLERANCE,
                DistanceUnit.INCH,
                0,
                AngleUnit.DEGREES), null, "parks");
    }

    private void fitIn18() {
    /*hold shooter up
        wobbly goal arm in correct position
        hold intake up
     */
    }

}
