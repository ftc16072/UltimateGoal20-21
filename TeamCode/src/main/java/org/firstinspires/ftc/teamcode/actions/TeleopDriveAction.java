package org.firstinspires.ftc.teamcode.actions;

import org.firstinspires.ftc.teamcode.opModes.QQ_Opmode;

public class TeleopDriveAction extends QQ_Action {

    @Override
    public QQ_Action run(QQ_Opmode opmode) {
        opmode.robot.mecanumDrive.driveMecanum(opmode.qq_gamepad1.leftStick.getY(), opmode.qq_gamepad1.leftStick.getX(), opmode.qq_gamepad1.rightStick.getX());
        return this;
    }
}
