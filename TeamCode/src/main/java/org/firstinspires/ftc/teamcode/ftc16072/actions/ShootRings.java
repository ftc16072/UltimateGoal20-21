package org.firstinspires.ftc.teamcode.ftc16072.actions;

import org.firstinspires.ftc.teamcode.ftc16072.mechanisms.Transfer;
import org.firstinspires.ftc.teamcode.ftc16072.opModes.QQ_Opmode;

import java.io.Console;

public class ShootRings extends QQ_Action {
    QQ_Action main;
    double waitTime;

    /**
     * shoot rings
     */
    public ShootRings(){
        /*main = new ShootAction("ring1");;
        main.setNext(new DelayAction(delayTime));
        main.setNext(new ShootAction("ring2"));
        main.setNext(new DelayAction(delayTime));
        main.setNext(new ShootAction("ring3"));

         */


    }

    /**
     * shoots rings
     * @param opmode this gives the action access to our robot, gamepads, time left etc
     * @return nextAction
     */
    @Override
    public QQ_Action run(QQ_Opmode opmode) {

        opmode.robot.shooter.autoShoot(opmode.time);
        opmode.robot.transfer.setState(Transfer.elevatorState.UP);

        if (waitTime == 0.0){
            waitTime = opmode.time + 4;
        }

        if ((opmode.robot.shooter.getRingsShot(false) >= 3) || (opmode.time >= waitTime)){
            if (opmode.time >= waitTime){
                opmode.robot.shooter.flick(true);
            }
            return nextAction;
        }
        return this;
        //return main.setNext(nextAction);
    }
}
