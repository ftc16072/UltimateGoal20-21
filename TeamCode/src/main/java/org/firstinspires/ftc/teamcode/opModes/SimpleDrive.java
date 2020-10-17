package org.firstinspires.ftc.teamcode.opModes;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.actions.TeleopDriveAction;

@TeleOp(name="Simple Drive")
public class SimpleDrive extends QQ_Opmode {
    @Override
    public void init(){
        super.init();
        usesGamepads = true;
        currentAction = new TeleopDriveAction();
    }
}
