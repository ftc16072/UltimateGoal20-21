package org.firstinspires.ftc.teamcode.actions;

import org.firstinspires.ftc.teamcode.opModes.QQ_Opmode;

public class TeleopDriveAction extends QQ_Action {

    /**
     * handles everything we need for teleop
     * @param opmode this gives the action access to our robot, gamepads, time left etc
     * @return next action
     */
    public QQ_Action run(QQ_Opmode opmode) {
        opmode.robot.nav.driveFieldRel(opmode.qq_gamepad1.leftStick, opmode.qq_gamepad1.rightStick.getX());

        // Gamepad commands:
        // Intake and tranfer on while holding, when released turn intake off and (amount of time) later turn transfer off.
        // Fire command
        // Pivot command
        // Wobbly goal junk
        return this;
    }
}
