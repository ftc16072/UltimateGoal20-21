package org.firstinspires.ftc.teamcode.opModes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.actions.QQ_Action;
import org.firstinspires.ftc.teamcode.mechanisms.Robot;
import org.firstinspires.ftc.teamcode.utils.QQ_Gamepad;

public abstract class QQ_Opmode extends OpMode {
    public Robot robot = new Robot();
    boolean usesGamepads;
    public QQ_Gamepad qq_gamepad1;
    public QQ_Gamepad qq_gamepad2;
    protected QQ_Action currentAction;

    /**
     * initializing robot
     */
    @Override
    public void init() {
        robot.init(hardwareMap);
    }

    /**
     * loop method --- runs over and over
     * Updates the gamepad
     * runs the action method
     */
    @Override
    public void loop() {
        if(usesGamepads){
            qq_gamepad1 = new QQ_Gamepad(gamepad1);
            qq_gamepad2 = new QQ_Gamepad(gamepad2);
        }
        currentAction = currentAction.run(this);
    }
}
