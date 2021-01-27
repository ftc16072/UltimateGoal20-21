package org.firstinspires.ftc.teamcode.ftc16072.actions;

import org.firstinspires.ftc.teamcode.ftc16072.opModes.QQ_Opmode;

public class WarmUpShooter extends QQ_Action {

    @Override
    public QQ_Action run(QQ_Opmode opmode) {
        opmode.robot.shooter.spinWheels(-0.7, -0.8);
        return nextAction;
    }
}
