package org.firstinspires.ftc.teamcode.ftc16072.opModes;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.ftc16072.actions.TeleopDriveAction;
import org.firstinspires.ftc.teamcode.ftc16072.actions.TeleopDriveActionUpdate;

@TeleOp(name="Fixed Controls")
public class FixedControls extends QQ_Opmode {
    /**
     * updates the init of the opmode to drive via teleopDrive
     */
    @Override
    public void init(){
        super.init();
        //robot will move the direction the user commands it to
        usesGamepads = true;
        currentAction = new TeleopDriveActionUpdate();
        robot.intake.hold();

    }
}
