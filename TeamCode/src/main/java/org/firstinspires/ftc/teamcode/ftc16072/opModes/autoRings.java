package org.firstinspires.ftc.teamcode.ftc16072.opModes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.teamcode.ftc16072.actions.DriveToAction;
import org.firstinspires.ftc.teamcode.ftc16072.actions.DriveToWobblyGoalZone;
import org.firstinspires.ftc.teamcode.ftc16072.actions.DropIntake;
import org.firstinspires.ftc.teamcode.ftc16072.actions.DropWobblyGoal;
import org.firstinspires.ftc.teamcode.ftc16072.actions.LowerWobblyGoal;
import org.firstinspires.ftc.teamcode.ftc16072.actions.RaiseWobblyGoal;
import org.firstinspires.ftc.teamcode.ftc16072.actions.DelayAction;
import org.firstinspires.ftc.teamcode.ftc16072.actions.ShootRings;
import org.firstinspires.ftc.teamcode.ftc16072.actions.TurnOffShooter;
import org.firstinspires.ftc.teamcode.ftc16072.actions.WarmUpShooter;
import org.firstinspires.ftc.teamcode.ftc16072.utils.NavigationPose;
import org.firstinspires.ftc.teamcode.ftc16072.utils.RobotPose;
import org.firstinspires.ftc.teamcode.ftc16072.utils.StackPipeline;


@Autonomous()
public class autoRings extends QQ_Opmode {
    double GOAL_X = 48;
    double GOAL_Y = 12;
    StackPipeline stackPipeline = new StackPipeline();


    @Override
    public void init() {
        usesCamera = true;
        super.init();
        webcam.setPipeline(stackPipeline);
        robot.nav.setCurrentPosition(new RobotPose(34,9, DistanceUnit.INCH));
        currentAction = new DriveToWobblyGoalZone()
                                .setNext(new LowerWobblyGoal())
                                .setNext(new DelayAction(.5))
                                .setNext(new DropWobblyGoal())
                                .setNext(new DelayAction(1))
                                .setNext(new RaiseWobblyGoal())
                                .setNext(new WarmUpShooter())
                                .setNext(new DriveToAction("aim", new NavigationPose(40, 65, 0)))
                                .setNext(new DelayAction(1))
                                .setNext(new ShootRings())
                                .setNext(new TurnOffShooter())
                                .setNext(new DriveToAction("park", new NavigationPose(40, 80)))
                                .setNext(new DropIntake());

        robot.intake.hold();
        robot.wobblyGoal.closeGrabber();

    }

    @Override
    public void init_loop() {
        super.init_loop();
        telemetry.addData("analysis", StackPipeline.analysis);
        numberRingsSeen = StackPipeline.analysis;
    }
}
