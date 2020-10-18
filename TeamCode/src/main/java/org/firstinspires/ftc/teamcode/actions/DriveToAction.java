package org.firstinspires.ftc.teamcode.actions;


import org.firstinspires.ftc.teamcode.opModes.QQ_Opmode;
import org.firstinspires.ftc.teamcode.utils.NavigationPose;

public class DriveToAction extends QQ_Action {
    NavigationPose desiredPose;

    /**
     * constructor
     * @param desiredPose desired pose to get to
     * @param nextAction action to run when this is done
     * @param description description of the action
     */
    public DriveToAction(NavigationPose desiredPose, QQ_Action nextAction, String description){
        super(description, nextAction);
        this.desiredPose = desiredPose;
    }

    /**
     * runs the action
     * @param opmode this gives the action access to our robot, gamepads, time left etc
     * @return returns the next action to run when it is done
     */
    @Override
    public QQ_Action run(QQ_Opmode opmode) {
        opmode.robot.nav.driveTo(desiredPose);

        return this;
    }
}
