package org.firstinspires.ftc.teamcode.actions;

import org.firstinspires.ftc.teamcode.opModes.QQ_Opmode;

public class TeleopDriveAction extends QQ_Action {

    /**
     * handles everything we need for teleop
     * @param opmode this gives the action access to our robot, gamepads, time left etc
     * @return next action
     */
    public QQ_Action run(QQ_Opmode opmode) {
        opmode.robot.mecanumDrive.driveMecanum(opmode.qq_gamepad1.leftStick.getY(), opmode.qq_gamepad1.leftStick.getX(), opmode.qq_gamepad1.rightStick.getX());
        return this;
    }
}
