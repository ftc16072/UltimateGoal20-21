package org.firstinspires.ftc.teamcode.ftc16072.actions;

import org.firstinspires.ftc.teamcode.ftc16072.opModes.QQ_Opmode;

public class ShootAction extends QQ_Action{
int step;
double waitTime;
double AUTO_SHOOT_ANGLE = 0.5; //change value to correct
    public ShootAction(String description){
        super(description);
    }

    @Override
    public QQ_Action run(QQ_Opmode opmode) {
        switch (step){
            case 0:
                opmode.robot.shooter.spinWheels(0.8, 0.5);
                //opmode.robot.shooter.setPivotAngle(AUTO_SHOOT_ANGLE);
                waitTime = opmode.time + 0.5;
                step = 1;
                break;
            case 1:
                if(opmode.time > waitTime){
                    opmode.robot.shooter.flick(true);
                    step = 2;
                }
                break;
            case 2:
                if(opmode.time > waitTime){
                    waitTime = opmode.time + 0.5;
                    opmode.robot.shooter.flick(false);
                    step = 3;
                }
                break;
            case 3:
                if(opmode.time > waitTime){
                    step = 4;
                }
                break;
            case 4:
                return nextAction;

        }
        return this;
    }
}
