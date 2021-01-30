package org.firstinspires.ftc.teamcode.ftc16072.actions;

import org.firstinspires.ftc.teamcode.ftc16072.opModes.QQ_Opmode;

public class RaiseWobblyGoal extends QQ_Action {
    double delayTill;

    public RaiseWobblyGoal(){
        super("Raise Wobbly Goal");
    }

    /**
     * raises rotator for wobbly goal
     * @param opmode this gives the action access to our robot, gamepads, time left etc
     * @return nextAction
     */
    @Override
    public QQ_Action run(QQ_Opmode opmode) {
        if(delayTill == 0){
            delayTill = opmode.time + 2;
        }
        opmode.robot.wobblyGoal.raiseRotator();

        if((opmode.time >= delayTill) || !opmode.robot.wobblyGoal.isRotatorBusy()){
            return nextAction;
        }else {
            return this;
        }
    }
}
