package org.firstinspires.ftc.teamcode.ftc16072.actions;

import org.firstinspires.ftc.teamcode.ftc16072.opModes.QQ_Opmode;

public class DelayAction extends QQ_Action {
    double delayTime;
    double delayTill;

    public DelayAction(double seconds){
        delayTime = seconds;
    }

    /**
     *
     * @param opmode this gives the action access to our robot, gamepads, time left etc
     * @return next action to run once finished
     */

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
