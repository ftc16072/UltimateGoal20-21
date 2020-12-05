package org.firstinspires.ftc.teamcode.ftc16072.actions;

import android.sax.StartElementListener;

import org.firstinspires.ftc.teamcode.ftc16072.opModes.QQ_Opmode;

public class ShootAction extends QQ_Action{
int step;
double waitTime;
double AUTO_SHOOT_ANGLE = 0.5; //change value to correct

    /**
     *
     * @param description add description of shootAction
     */
    public ShootAction(String description){
        super(description);
    }

    /**
     * tests if the cases are true or false
     * @param opmode this gives the action access to our robot, gamepads, time left etc
     * @return if true, return case number; if false, return nextAction
     */
    @Override
    public QQ_Action run(QQ_Opmode opmode) {
        /*switch (step){
            case 0:
                opmode.robot.shooter.flick(true);
                step = 1;
                break;
            case 1:
                waitTime = opmode.time + 0.25;
                step = 2;
                break;
            case 2:
                if(opmode.time >= waitTime){
                    opmode.robot.shooter.flick(false);
                    waitTime = opmode.time + 0.25;
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

         */
        return this;
    }
}
