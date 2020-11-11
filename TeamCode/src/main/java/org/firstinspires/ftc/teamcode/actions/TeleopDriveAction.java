package org.firstinspires.ftc.teamcode.actions;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.teamcode.opModes.QQ_Opmode;

public class TeleopDriveAction extends QQ_Action {
    final static double MAX_SPEED = 1;
    final static double NORMAL_SPEED = 0.6;
    final static double SLOW_SPEED = 0.3;
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
        driverControls(opmode);
        //opmode.robot.nav.driveFieldRel(opmode.qq_gamepad1.leftStick, opmode.qq_gamepad1.rightStick.getX());

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


    double rotateFromTrigger(double trigger){
        return trigger / 2;
    }

    public void driverControls(QQ_Opmode opmode) {
        opmode.qq_gamepad1.leftStick.setSquared(true);

        if(opmode.qq_gamepad1.leftBumper()){
            opmode.robot.mecanumDrive.setMaxSpeed(SLOW_SPEED);
        } else if(opmode.qq_gamepad1.rightBumper()) {
            opmode.robot.mecanumDrive.setMaxSpeed(MAX_SPEED);
        }else {
            opmode.robot.mecanumDrive.setMaxSpeed(NORMAL_SPEED);
        }

        if(opmode.qq_gamepad1.leftTrigger() >= 0.05){
            opmode.robot.nav.driveRotate(-rotateFromTrigger(opmode.qq_gamepad1.leftTrigger()));
        }else if(opmode.qq_gamepad1.rightTrigger() >= 0.05){
            opmode.robot.nav.driveRotate(rotateFromTrigger(opmode.qq_gamepad1.rightTrigger()));
        }else if(opmode.qq_gamepad1.rightStick.getR() >= 0.8){
            opmode.robot.nav.driveFieldRelativeAngle(
                    opmode.qq_gamepad1.leftStick.getPolar(),
                    opmode.qq_gamepad1.rightStick.getTheta(AngleUnit.RADIANS),
                    AngleUnit.RADIANS);
        }else{
            opmode.robot.nav.driveFieldRel(
                    opmode.qq_gamepad1.leftStick, 0.0);
        }





    }


}
