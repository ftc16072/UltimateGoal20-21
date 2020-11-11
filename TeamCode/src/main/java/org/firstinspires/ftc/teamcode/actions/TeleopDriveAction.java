package org.firstinspires.ftc.teamcode.actions;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.teamcode.opModes.QQ_Opmode;

public class TeleopDriveAction extends QQ_Action {
    final static double MAX_SPEED = 1;
    final static double NORMAL_SPEED = 0.6;
    final static double SLOW_SPEED = 0.3;
    /**
     * handles everything we need for teleop
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
