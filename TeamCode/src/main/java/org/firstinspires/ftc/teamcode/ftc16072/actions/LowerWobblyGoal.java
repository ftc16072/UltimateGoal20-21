package org.firstinspires.ftc.teamcode.ftc16072.actions;

import org.firstinspires.ftc.teamcode.ftc16072.opModes.QQ_Opmode;

public class LowerWobblyGoal extends QQ_Action {
    double delayTill;


    public LowerWobblyGoal(){
        super("Lower Wobbly Goal");
    }

    @Override
    public QQ_Action run(QQ_Opmode opmode) {
        if(delayTill == 0){
            delayTill = opmode.time + 2;
        }
        opmode.robot.wobblyGoal.lowerRotator();

        if((opmode.time >= delayTill) || !opmode.robot.wobblyGoal.isRotatorBusy()){
            return nextAction;
        }else {
            return this;
        }
    }
}
