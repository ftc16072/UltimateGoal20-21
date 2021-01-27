package org.firstinspires.ftc.teamcode.ftc16072.actions;

import org.firstinspires.ftc.teamcode.ftc16072.opModes.QQ_Opmode;

public class DropIntake extends QQ_Action {

    @Override
    public QQ_Action run(QQ_Opmode opmode) {
        opmode.robot.intake.release();
        return nextAction;
    }
}
