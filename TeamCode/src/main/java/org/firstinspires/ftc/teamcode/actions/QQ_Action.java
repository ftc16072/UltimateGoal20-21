package org.firstinspires.ftc.teamcode.actions;

import org.firstinspires.ftc.teamcode.mechanisms.Robot;
import org.firstinspires.ftc.teamcode.opModes.QQ_Opmode;

abstract public class QQ_Action {
    QQ_Action nextAction;
    String description;

    public QQ_Action(String description, QQ_Action nextAction){
        this.description = description;
        this.nextAction = nextAction;
    }
    public QQ_Action(QQ_Action nextAction){
        this(Class.class.getSimpleName(), nextAction);
    }
    public QQ_Action(){
        this(null);
    }

    public abstract QQ_Action run(QQ_Opmode opmode);

}
