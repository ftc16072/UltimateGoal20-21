package org.firstinspires.ftc.teamcode.opModes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.teamcode.actions.DriveToAction;
import org.firstinspires.ftc.teamcode.actions.TeleopDriveAction;
import org.firstinspires.ftc.teamcode.utils.NavigationPose;

@Autonomous
public class TestAuto extends QQ_Opmode{
    @Override
    public void init(){
        super.init();
        //robot will move the direction the user commands it to
        usesGamepads = false;
        currentAction = new DriveToAction(
                new NavigationPose(-24, 2, 0, 2, DistanceUnit.INCH, 0, AngleUnit.DEGREES),
                 "Drive to (-24, 0)", null);
    }


}
