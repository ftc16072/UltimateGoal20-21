package org.firstinspires.ftc.teamcode.ftc16072.actions;


import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.teamcode.ftc16072.mechanisms.Navigation;
import org.firstinspires.ftc.teamcode.ftc16072.mechanisms.Robot;
import org.firstinspires.ftc.teamcode.ftc16072.opModes.QQ_Opmode;
import org.firstinspires.ftc.teamcode.ftc16072.utils.NavigationPose;
import org.firstinspires.ftc.teamcode.ftc16072.utils.RobotPose;

public class DriveToAction extends QQ_Action {
    NavigationPose desiredPose;

    /**
     * constructor
     * @param desiredPose desired pose to get to
     * @param description description of the action
     */
    public DriveToAction(String description, NavigationPose desiredPose){
        super(description);
        this.desiredPose = desiredPose;
    }

    /**
     * runs the action
     * @param opmode this gives the action access to our robot, gamepads, time left etc
     * @return returns the next action to run when it is done
     */
    @Override
    public QQ_Action run(QQ_Opmode opmode) {

        RobotPose pose = Navigation.currentPosition;
        opmode.telemetry.addData("forward", pose.getY(DistanceUnit.INCH));
        opmode.telemetry.addData("strafe", pose.getX(DistanceUnit.INCH));
        opmode.telemetry.addData("imu", pose.getAngle(AngleUnit.DEGREES));

        boolean done = opmode.robot.nav.driveTo(desiredPose);

        return done ? nextAction : this;
    }
}
