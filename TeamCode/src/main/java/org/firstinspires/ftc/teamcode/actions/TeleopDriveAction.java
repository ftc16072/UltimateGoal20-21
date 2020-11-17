package org.firstinspires.ftc.teamcode.actions;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.teamcode.mechanisms.Intake;
import org.firstinspires.ftc.teamcode.mechanisms.MecanumDrive;
import org.firstinspires.ftc.teamcode.mechanisms.Transfer;
import org.firstinspires.ftc.teamcode.opModes.QQ_Opmode;
import org.firstinspires.ftc.teamcode.utils.RobotPose;

public class TeleopDriveAction extends QQ_Action {
    double pivotAngle = 0;
    boolean wasUp;
    boolean wasDown;
    final static double MAX_SPEED = 1;
    final static double NORMAL_SPEED = 0.6;
    final static double SLOW_SPEED = 0.3;


    /**
     * handles everything we need for teleop
     *
     * @param opmode this gives the action access to our robot, gamepads, time left etc
     * @return next action
     */
    public QQ_Action run(QQ_Opmode opmode) {
        RobotPose pose = opmode.robot.nav.currentPosition;
        opmode.telemetry.addData("forward", pose.getY(DistanceUnit.INCH));
        opmode.telemetry.addData("strafe", pose.getX(DistanceUnit.INCH));
        driverControls(opmode);
        manipulatorControls(opmode);
        return this;
    }

    void manipulatorControls(QQ_Opmode opmode) {
        //spinning shooter wheels
        if (opmode.qq_gamepad2.rightBumper()) {
            opmode.robot.shooter.spinWheels(-1, -0.8);
        } else {
            opmode.robot.shooter.spinWheels(0, 0);
        }

        //shooter servo to push rings
        if (opmode.qq_gamepad2.rightTrigger() > 0.1) {
            opmode.robot.shooter.flick(true);

        } else {
            opmode.robot.shooter.flick(false);
        }
/*
        //up and down of pivot shooter
        if (opmode.qq_gamepad2.dpadUp() && !wasUp) {
            pivotAngle = Math.min(15, pivotAngle + 3);
        }
        wasUp = opmode.qq_gamepad2.dpadUp();

        if (opmode.qq_gamepad2.dpadDown() && !wasDown) {
            pivotAngle = Math.max(0, pivotAngle - 3);
        }
        wasDown = opmode.qq_gamepad2.dpadDown();
        opmode.telemetry.addData("angle", pivotAngle);
        opmode.robot.shooter.goToAngle(pivotAngle);

        //up and down of wobbly goal
        if (opmode.qq_gamepad2.leftStick.getY() > 0.2) {
            opmode.robot.wobblyGoal.raiseRotator();
        } else if (opmode.qq_gamepad2.leftStick.getY() < -0.2) {
            opmode.robot.wobblyGoal.lowerRotator();
        }

        //wobbly goal open and close grabber
        if (opmode.qq_gamepad2.leftBumper()) {
            opmode.robot.wobblyGoal.openGrabber();
        } else {
            opmode.robot.wobblyGoal.closeGrabber();
        }
*/
        //intake and transfer in and out
        if (opmode.qq_gamepad2.rightStick.getY() > 0.2) {
            opmode.robot.intake.changeState(Intake.intakeState.Start);
            opmode.robot.transfer.changeTransfer(Transfer.transferState.Start);
        } else if (opmode.qq_gamepad2.rightStick.getY() < -0.2) {
            opmode.robot.intake.changeState(Intake.intakeState.Reverse);
            opmode.robot.transfer.changeTransfer(Transfer.transferState.Reverse);
        } else {
            opmode.robot.intake.changeState(Intake.intakeState.Stop);
            opmode.robot.transfer.changeTransfer(Transfer.transferState.Stop);
        }
    }


    double rotateFromTrigger(double trigger) {
        return trigger / 2;
    }

    public void driverControls(QQ_Opmode opmode) {
        opmode.qq_gamepad1.leftStick.setSquared(true);




        if (opmode.qq_gamepad1.dpadUp()){
            opmode.robot.nav.setImuOffset(-(opmode.robot.nav.getHeading(AngleUnit.RADIANS) + (Math.PI) ), AngleUnit.RADIANS);
        }



        if (opmode.qq_gamepad1.leftBumper()) {
            opmode.robot.mecanumDrive.setMaxSpeed(SLOW_SPEED);
        } else if (opmode.qq_gamepad1.rightBumper()) {
            opmode.robot.mecanumDrive.setMaxSpeed(MAX_SPEED);
        } else {
            opmode.robot.mecanumDrive.setMaxSpeed(NORMAL_SPEED);
        }

        if (opmode.qq_gamepad1.leftTrigger() >= 0.05) {
            opmode.robot.nav.driveRotate(-rotateFromTrigger(opmode.qq_gamepad1.leftTrigger()));
        } else if (opmode.qq_gamepad1.rightTrigger() >= 0.05) {
            opmode.robot.nav.driveRotate(rotateFromTrigger(opmode.qq_gamepad1.rightTrigger()));
        } else if (opmode.qq_gamepad1.rightStick.getR() >= 0.8) {
            opmode.robot.nav.driveFieldRelativeAngle(
                    opmode.qq_gamepad1.leftStick.getPolar(),
                    opmode.qq_gamepad1.rightStick.getTheta(AngleUnit.RADIANS),
                    AngleUnit.RADIANS);
        } else {
            opmode.robot.nav.driveFieldRel(
                    opmode.qq_gamepad1.leftStick, 0.0);
        }
    }
}

