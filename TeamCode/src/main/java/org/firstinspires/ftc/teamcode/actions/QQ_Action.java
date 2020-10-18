package org.firstinspires.ftc.teamcode.actions;

import org.firstinspires.ftc.teamcode.mechanisms.Robot;
import org.firstinspires.ftc.teamcode.opModes.QQ_Opmode;

abstract public class QQ_Action {
    QQ_Action nextAction;
    String description;

    /**
     * Constructor with next action and description
     * @param description description of the action
     * @param nextAction the action to take when this is done
     */
    public QQ_Action(String description, QQ_Action nextAction){
        this.description = description;
        this.nextAction = nextAction;
    }

    /**
     * Constructor with next action
     * description is pulled from the simple name of the class
     * @param nextAction the action to take when this is done
     */
    public QQ_Action(QQ_Action nextAction){
        this(Class.class.getSimpleName(), nextAction);
    }

    /**
     * constructor with nothing
     * next action is null
     */
    public QQ_Action(){
        this(null);
    }

    /**
     * this forces all QQ_actions to have a run
     * @param opmode this gives the action access to our robot, gamepads, time left etc
     * @return returns the next action to run when done
     */
    public abstract QQ_Action run(QQ_Opmode opmode);

}
