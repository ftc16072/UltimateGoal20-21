package org.firstinspires.ftc.teamcode.ftc16072.opModes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.teamcode.ftc16072.actions.QQ_Action;
import org.firstinspires.ftc.teamcode.ftc16072.mechanisms.Robot;
import org.firstinspires.ftc.teamcode.ftc16072.utils.QQ_Gamepad;
import org.firstinspires.ftc.teamcode.ftc16072.utils.RobotPose;

public abstract class QQ_Opmode extends OpMode {
    public Robot robot = new Robot();
    boolean usesGamepads;
    public QQ_Gamepad qq_gamepad1;
    public QQ_Gamepad qq_gamepad2;
    protected QQ_Action currentAction;
    public int numberRingsSeen;

    /**
     * initializing robot
     */
    @Override
    public void init() {
        robot.init(hardwareMap);
        robot.nav.setCurrentPosition(new RobotPose(0,0, DistanceUnit.INCH));
    }

    /**
     * loop method --- runs over and over
     * Updates the gamepad
     * runs the action method
     */
    @Override
    public void loop() {
        robot.nav.updatePose();
        if(usesGamepads){
            qq_gamepad1 = new QQ_Gamepad(gamepad1);
            qq_gamepad2 = new QQ_Gamepad(gamepad2);
        }

        if(currentAction != null){
            telemetry.addData("State", currentAction.getDescription());
            currentAction = currentAction.run(this);
        } else {
            telemetry.addData("State", "done");
        }


    }
}
