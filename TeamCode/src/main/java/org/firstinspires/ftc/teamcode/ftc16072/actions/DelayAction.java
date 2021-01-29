package org.firstinspires.ftc.teamcode.ftc16072.actions;

import org.firstinspires.ftc.teamcode.ftc16072.opModes.QQ_Opmode;

public class DelayAction extends QQ_Action {
    double delayTime;
    double delayTill;

    public DelayAction(double seconds){
        delayTime = seconds;
    }

    @Override
    public QQ_Action run(QQ_Opmode opmode) {
        if(delayTill == 0){
            delayTill = opmode.time + delayTime;
        }
        if(opmode.time >= delayTill){
            return nextAction;
        }else {
            return this;
        }
    }
}
