package org.firstinspires.ftc.teamcode.ftc16072.opModes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.teamcode.ftc16072.actions.DriveToAction;
import org.firstinspires.ftc.teamcode.ftc16072.actions.DriveToSecondWobble;
import org.firstinspires.ftc.teamcode.ftc16072.actions.DriveToWobblyGoalZone;
import org.firstinspires.ftc.teamcode.ftc16072.actions.DriveToWobblyGoalZoneSecond;
import org.firstinspires.ftc.teamcode.ftc16072.actions.DropIntake;
import org.firstinspires.ftc.teamcode.ftc16072.actions.DropWobblyGoal;
import org.firstinspires.ftc.teamcode.ftc16072.actions.LowerWobblyGoal;
import org.firstinspires.ftc.teamcode.ftc16072.actions.PickUpWobblyGoal;
import org.firstinspires.ftc.teamcode.ftc16072.actions.RaiseWobblyGoal;
import org.firstinspires.ftc.teamcode.ftc16072.actions.DelayAction;
import org.firstinspires.ftc.teamcode.ftc16072.actions.ShootRings;
import org.firstinspires.ftc.teamcode.ftc16072.actions.TurnOffShooter;
import org.firstinspires.ftc.teamcode.ftc16072.actions.WarmUpShooter;
import org.firstinspires.ftc.teamcode.ftc16072.mechanisms.Transfer;
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
        usesGamepads = true;
        super.init();
        webcam.setPipeline(stackPipeline);
        robot.nav.setCurrentPosition(new RobotPose(34,9, DistanceUnit.INCH));
        //Auto Action lineup
        currentAction = new PickUpWobblyGoal()
                .setNext(new DriveToAction("navigate", new NavigationPose(12, 65, 7, .4, 1, 0, 360)))
                .setNext(new WarmUpShooter())
                .setNext(new DriveToAction("Aim", new NavigationPose(43, 67, .5, .1, 1,0, 1)))
                .setNext(new ShootRings())
                .setNext(new TurnOffShooter())
                .setNext(new PickUpWobblyGoal())
                .setNext(new DriveToWobblyGoalZone())
                .setNext(new LowerWobblyGoal())
                .setNext(new DelayAction(.15))
                .setNext(new DropWobblyGoal())
                .setNext(new DriveToSecondWobble())
                .setNext(new DelayAction(.25)) /// ^ was x = 59
                .setNext(new PickUpWobblyGoal())
                .setNext(new DelayAction(.5))
                .setNext(new DriveToWobblyGoalZoneSecond())
                .setNext(new DelayAction(.15))
                .setNext(new DropWobblyGoal())
                .setNext(new DropIntake())
                .setNext(new DriveToAction("Park", new NavigationPose(60, 90, 1, .4, 1, 0, 40)))
                .setNext(new DriveToAction("Face the right way", new NavigationPose(60,90,1,0,.1)));
        //Fit in 18
        robot.intake.hold();
        robot.wobblyGoal.closeGrabber();
        robot.transfer.setState(Transfer.elevatorState.UP);
    }

    @Override
    public void init_loop() {
        super.init_loop();
        telemetry.addData("analysis", stackPipeline.analysis);
        robot.transfer.setState(Transfer.elevatorState.UP);
        numberRingsSeen = stackPipeline.analysis;
    }

    @Override
    public void loop() {
        super.loop();
        robot.transfer.setState(Transfer.elevatorState.UP);
    }
}
