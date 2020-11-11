package org.firstinspires.ftc.teamcode.actions;

import org.firstinspires.ftc.teamcode.opModes.QQ_Opmode;

public class TeleopDriveAction extends QQ_Action {
    double pivotAngle;
    boolean wasUp;
    boolean wasDown;

    /**
     * handles everything we need for teleop
     *
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

    void manipulatorControls(QQ_Opmode opmode) {
//spinning wheels
        if (opmode.qq_gamepad1.rightBumper()) {
            opmode.robot.shooter.spinWheels(0.8, 0.5);
        } else {
            opmode.robot.shooter.spinWheels(0, 0);
        }
//servo to push rings
        if (opmode.qq_gamepad1.rightTrigger() > 0.1) {
            opmode.robot.shooter.flick(true);

        } else {
            opmode.robot.shooter.flick(false);
        }
//up and down of pivot
        if (opmode.qq_gamepad1.dpadUp() && !wasUp) {
            pivotAngle = Math.max(1.0, pivotAngle + 0.1);
        }
        wasUp = opmode.qq_gamepad1.dpadUp();

        if (opmode.qq_gamepad1.dpadDown() && !wasDown) {
            pivotAngle = Math.min(0, pivotAngle - 0.1);
        }
        wasDown = opmode.qq_gamepad1.dpadDown();
opmode.robot.shooter.setPivotAngle(pivotAngle);

    }

}

