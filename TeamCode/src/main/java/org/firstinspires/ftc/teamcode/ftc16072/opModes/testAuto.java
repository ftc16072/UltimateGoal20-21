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
public class testAuto extends QQ_Opmode {
    public static double GOAL_X = 0;
    public static double GOAL_Y = 24;
    StackPipeline stackPipeline = new StackPipeline();


    @Override
    public void init() {
        super.init();
        robot.nav.setCurrentPosition(new RobotPose(0,0, DistanceUnit.INCH));
        currentAction = new DriveToAction("testing", new NavigationPose(GOAL_X, GOAL_Y));


    }

    @Override
    public void init_loop() {
        super.init_loop();
        telemetry.addData("analysis", StackPipeline.analysis);
    }
}
