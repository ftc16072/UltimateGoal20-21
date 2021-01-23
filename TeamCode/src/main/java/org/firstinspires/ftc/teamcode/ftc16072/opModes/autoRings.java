package org.firstinspires.ftc.teamcode.ftc16072.opModes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.teamcode.ftc16072.actions.DriveToAction;
import org.firstinspires.ftc.teamcode.ftc16072.actions.DriveToWobblyGoalZone;
import org.firstinspires.ftc.teamcode.ftc16072.actions.DropWobblyGoal;
import org.firstinspires.ftc.teamcode.ftc16072.actions.PickUpWobblyGoal;
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
        numberRingsSeen = StackPipeline.analysis;
        currentAction = new DriveToWobblyGoalZone().
                        setNext(new DropWobblyGoal().
                        setNext(new DriveToAction("drives to pick up the second wobbly goal", new NavigationPose(GOAL_X, GOAL_Y)).
                        setNext(new PickUpWobblyGoal().
                        setNext(new DriveToWobblyGoalZone()))));


    }

    @Override
    public void init_loop() {
        super.init_loop();
        telemetry.addData("analysis", StackPipeline.analysis);
    }
}
