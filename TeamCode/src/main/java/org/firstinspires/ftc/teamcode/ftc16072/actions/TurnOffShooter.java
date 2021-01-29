package org.firstinspires.ftc.teamcode.ftc16072.actions;

import org.firstinspires.ftc.teamcode.ftc16072.opModes.QQ_Opmode;

public class TurnOffShooter extends QQ_Action {
    @Override
    public QQ_Action run(QQ_Opmode opmode) {
        opmode.robot.shooter.spinWheels(0, 0);
        return nextAction;
    }
}
