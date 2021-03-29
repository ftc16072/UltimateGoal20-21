package org.firstinspires.ftc.teamcode.ftc16072.actions;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.teamcode.ftc16072.mechanisms.Intake;
import org.firstinspires.ftc.teamcode.ftc16072.mechanisms.Transfer;
import org.firstinspires.ftc.teamcode.ftc16072.opModes.QQ_Opmode;
import org.firstinspires.ftc.teamcode.ftc16072.utils.RobotPose;

public class DummyTeleopAction extends QQ_Action {


    double pivotAngle = 0;
    boolean wasUp;
    boolean wasDown;

    double timeOffset;

    private boolean gamepad2APressed;
    private boolean gamepad1BPressed;
    private boolean gamepad2XPressed;
    private boolean closeGrabber;
    final double MAX_SPEED = 1;
    final double NORMAL_SPEED = 0.6;
    final double SLOW_SPEED = 0.3;

    public double powerShot1Angle = 91.0;
    public double powerShot2Angle = 88.0;
    public double powerShot3Angle = 84.6;




    /**
     * handles everything we need for teleop
     *
     * @param opmode this gives the action access to our robot, gamepads, time left etc
     * @return next action
     */
    public QQ_Action run(QQ_Opmode opmode) {
        if(timeOffset == 0.0){
            timeOffset = opmode.time;
        }

        driverControls(opmode);
        manipulatorControls(opmode);

        if(opmode.robot.shooter.inAcceptableVelo()){
            opmode.robot.lights.blue();
        } else if(opmode.time - timeOffset >= 90){
            opmode.robot.lights.endGame();
        } else {
            opmode.robot.lights.normal();
        }

        opmode.telemetry.addData("Mode:", "Outreach");

        return this;
    }

    /**
     *
     * @param opmode if statement for manipulator controls
     */
    void manipulatorControls(QQ_Opmode opmode) {
        //spinning shooter wheels
        if (opmode.qq_gamepad2.rightTrigger() >= 0.2){
                opmode.robot.shooter.autoShoot(opmode.time);
                opmode.robot.transfer.setState(Transfer.elevatorState.UP);

        } else {
            opmode.robot.shooter.doneShooting();
            if (opmode.qq_gamepad2.rightBumper()) {
                opmode.robot.shooter.spinWheels(true);
                opmode.robot.transfer.setState(Transfer.elevatorState.UP);
            } else {
                opmode.robot.shooter.spinWheels(false);
                opmode.robot.shooter.updatePidF();
            }
            //shooter servo to push rings
            if (opmode.qq_gamepad2.b()) {
                opmode.robot.shooter.flick(true);
            } else {
                opmode.robot.shooter.flick(false);
            }
        }

        //intake and transfer in and out
        if (opmode.qq_gamepad2.leftTrigger() > 0.2){
                opmode.robot.transfer.setState(Transfer.elevatorState.DOWN);
                opmode.robot.intake.changeState(Intake.intakeState.Start);
                opmode.robot.transfer.changeTransfer(Transfer.transferState.START);
        } else if (opmode.qq_gamepad2.rightStick.getY() < -1.0) {
            opmode.robot.intake.changeState(Intake.intakeState.Reverse);
            opmode.robot.transfer.changeTransfer(Transfer.transferState.REVERSE);
        } else {
            opmode.robot.intake.changeState(Intake.intakeState.Stop);
            opmode.robot.transfer.changeTransfer(Transfer.transferState.STOP);
        }
    }

    /**
     *
     * @param trigger rotate from trigger
     * @return trigger divided by 2
     */
    double rotateFromTrigger(double trigger) {
        return trigger / 2;
    }

    /**
     *
     * @param opmode check gamepad controls and angle degrees
     */
    public void driverControls(QQ_Opmode opmode) {
        opmode.qq_gamepad1.leftStick.setSquared(true);

        opmode.robot.nav.driveFieldRelativeAngle(
                opmode.qq_gamepad1.leftStick,
                opmode.qq_gamepad1.rightStick);

    }
}


