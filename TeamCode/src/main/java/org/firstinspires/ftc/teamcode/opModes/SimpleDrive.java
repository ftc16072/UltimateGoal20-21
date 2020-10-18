package org.firstinspires.ftc.teamcode.opModes;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.actions.TeleopDriveAction;

@TeleOp(name="Simple Drive")
public class SimpleDrive extends QQ_Opmode {
    /**
     * updates the init of the opmode to drive via teleopDrive
     */
    @Override
    public void init(){
        super.init();
        //robot will move the direction the user commands it to
        usesGamepads = true;
        currentAction = new TeleopDriveAction();
    }
}
