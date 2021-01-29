package org.firstinspires.ftc.teamcode.ftc16072.actions;

import org.firstinspires.ftc.teamcode.ftc16072.opModes.QQ_Opmode;

public class ShootRings extends QQ_Action {
    double delayTime = .75;
    QQ_Action main;

    public ShootRings(){
        main = new ShootAction("ring1");;
        main.setNext(new DelayAction(delayTime));
        main.setNext(new ShootAction("ring2"));
        main.setNext(new DelayAction(delayTime));
        main.setNext(new ShootAction("ring3"));
    }

    @Override
    public QQ_Action run(QQ_Opmode opmode) {
        return main.setNext(nextAction);
    }
}
