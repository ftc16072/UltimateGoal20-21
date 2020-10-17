package org.firstinspires.ftc.teamcode.actions;


import org.firstinspires.ftc.teamcode.opModes.QQ_Opmode;
import org.firstinspires.ftc.teamcode.utils.NavigationPose;

public class DriveToAction extends QQ_Action {
    NavigationPose desiredPose;

    public DriveToAction(NavigationPose desiredPose, QQ_Action nextAction, String description){
        super(description, nextAction);
        this.desiredPose = desiredPose;
    }

    @Override
    public QQ_Action run(QQ_Opmode opmode) {
        opmode.robot.nav.driveTo(desiredPose);

        return this;
    }
}
