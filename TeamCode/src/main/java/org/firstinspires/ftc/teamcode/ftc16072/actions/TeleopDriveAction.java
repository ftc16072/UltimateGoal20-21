package org.firstinspires.ftc.teamcode.ftc16072.actions;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.teamcode.ftc16072.mechanisms.Intake;
import org.firstinspires.ftc.teamcode.ftc16072.mechanisms.Transfer;
import org.firstinspires.ftc.teamcode.ftc16072.opModes.QQ_Opmode;
import org.firstinspires.ftc.teamcode.ftc16072.utils.RobotPose;

public class TeleopDriveAction extends QQ_Action {


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

        RobotPose pose = opmode.robot.nav.currentPosition;
        opmode.telemetry.addData("Y", pose.getY(DistanceUnit.INCH));
        opmode.telemetry.addData("X", pose.getX(DistanceUnit.INCH));
        opmode.telemetry.addData("imu", opmode.robot.nav.getHeading(AngleUnit.DEGREES));
        opmode.telemetry.addData("theta", pose.getAngle(AngleUnit.DEGREES));
        driverControls(opmode);
        manipulatorControls(opmode);

        opmode.telemetry.addData("Vel", opmode.robot.shooter.shooterMotor.getVelocity());

        opmode.dashboardTelem.addData("Velocity", -opmode.robot.shooter.shooterMotor.getVelocity());
        opmode.dashboardTelem.addData("goal", -opmode.robot.shooter.SHOOTER_VELO);
        opmode.dashboardTelem.addData("low", -opmode.robot.shooter.SHOOTER_VELO + opmode.robot.shooter.SHOOTER_RANGE);
        opmode.dashboardTelem.addData("high", -opmode.robot.shooter.SHOOTER_VELO - opmode.robot.shooter.SHOOTER_RANGE);

        opmode.dashboardTelem.update();

        if(opmode.robot.shooter.inAcceptableVelo()){
            opmode.robot.lights.blue();
        } else if(opmode.time - timeOffset >= 90){
            opmode.robot.lights.endGame();
        } else {
            opmode.robot.lights.normal();
        }



        if(nextAction != null){
            return nextAction;
        }
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

        if(opmode.qq_gamepad2.x() && !gamepad2XPressed){
            if(opmode.robot.transfer.currentState() == Transfer.elevatorState.UP || opmode.qq_gamepad2.y()){
                opmode.robot.transfer.setState(Transfer.elevatorState.DOWN);
            } else {
                opmode.robot.transfer.setState(Transfer.elevatorState.UP);
            }
        }
        gamepad2XPressed = opmode.qq_gamepad2.x();



        //up and down of wobbly goal
        if (opmode.qq_gamepad2.dpadUp()) {
            opmode.robot.wobblyGoal.raiseRotator();
        } else if (opmode.qq_gamepad2.dpadDown()) {
            opmode.robot.wobblyGoal.lowerRotator();
        }

        //wobbly goal open and close grabber
        if (opmode.qq_gamepad2.a() && !gamepad2APressed) {
            closeGrabber = !closeGrabber;
        }
        gamepad2APressed = opmode.qq_gamepad2.a();

        if (closeGrabber){
            opmode.robot.wobblyGoal.closeGrabber();
        } else {
            opmode.robot.wobblyGoal.openGrabber();
        }

        //intake and transfer in and out
        if ( (opmode.qq_gamepad2.leftBumper() || opmode.qq_gamepad2.rightStick.getY() > 0.2 || opmode.qq_gamepad2.leftTrigger() > 0.2) ){
                opmode.robot.transfer.setState(Transfer.elevatorState.DOWN);
                opmode.robot.intake.changeState(Intake.intakeState.Start);
                opmode.robot.transfer.changeTransfer(Transfer.transferState.START);
        } else if (opmode.qq_gamepad2.rightStick.getY() < -0.2) {
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

        if (opmode.qq_gamepad1.dpadUp()) {
            opmode.robot.nav.resetIMU(0, AngleUnit.DEGREES);
        } else if (opmode.qq_gamepad1.dpadLeft()) {
            opmode.robot.nav.resetIMU(-90, AngleUnit.DEGREES);
        } else if (opmode.qq_gamepad1.dpadDown()) {
            opmode.robot.nav.resetIMU(180, AngleUnit.DEGREES);
        } else if (opmode.qq_gamepad1.dpadRight()) {
            opmode.robot.nav.resetIMU(90, AngleUnit.DEGREES);
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
        } else {
            opmode.robot.nav.driveFieldRelativeAngle(
                    opmode.qq_gamepad1.leftStick,
                    opmode.qq_gamepad1.rightStick);

        }

        if (opmode.qq_gamepad1.x()) {
            nextAction = new DummyTeleopAction();
        }

        if (opmode.qq_gamepad1.b() & !gamepad1BPressed){
            opmode.robot.nav.flipDriving = !opmode.robot.nav.flipDriving;
        }
        gamepad1BPressed = opmode.qq_gamepad1.b();

        if (opmode.gamepad1.a){
            opmode.robot.intake.release();
        }

    }
}

