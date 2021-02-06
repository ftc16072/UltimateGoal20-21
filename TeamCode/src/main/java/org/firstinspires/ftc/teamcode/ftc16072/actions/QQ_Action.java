package org.firstinspires.ftc.teamcode.ftc16072.actions;

import org.firstinspires.ftc.teamcode.ftc16072.opModes.QQ_Opmode;

abstract public class QQ_Action {
    QQ_Action nextAction;
    String description;

    /**
     * Constructor with next action and description
     * @param description description of the action
     */
    public QQ_Action(String description){
        this.description = description;
    }


    /**
     * constructor with nothing
     * next action is null
     */
    public QQ_Action(){
        this(Class.class.getSimpleName());
    }

    /**
     * this forces all QQ_actions to have a run
     * @param opmode this gives the action access to our robot, gamepads, time left etc
     * @return returns the next action to run when done
     */
    public abstract QQ_Action run(QQ_Opmode opmode);

    /**
     *
     * @param nextAction if NextAction is 0, continue to the nextAction
     * @return nextAction
     */
    public QQ_Action setNext(QQ_Action nextAction){
        if (this.nextAction == null){
            this.nextAction = nextAction;
        } else {
            this.nextAction.setNext(nextAction);
        }
        return this;
    }

    /**
     *
     * @return description
     */
    public String getDescription(){
        return description;
    }

}
