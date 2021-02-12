package org.firstinspires.ftc.teamcode.ftc16072.opModes;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.ftc16072.actions.TeleopDriveAction;

@TeleOp(name="Teleop")
public class Teleop extends QQ_Opmode {
    /**
     * updates the init of the opmode to drive via teleopDrive
     */
    @Override
    public void init(){
        super.init();
        //robot will move the direction the user commands it to
        usesGamepads = true;
        currentAction = new TeleopDriveAction();
        robot.intake.hold();
    }
}
