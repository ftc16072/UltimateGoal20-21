package org.firstinspires.ftc.teamcode.ftc16072.actions;

import org.firstinspires.ftc.teamcode.ftc16072.opModes.QQ_Opmode;

public class DropWobblyGoal extends QQ_Action{

    @Override
    public QQ_Action run(QQ_Opmode opmode) {
        opmode.robot.wobblyGoal.openGrabber();
        return nextAction;
}
}
