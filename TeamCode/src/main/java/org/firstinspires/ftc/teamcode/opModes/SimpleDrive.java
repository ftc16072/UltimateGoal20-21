package org.firstinspires.ftc.teamcode.opModes;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name="Simple Drive")
public class SimpleDrive extends QQ_Opmode {
    @Override
    public void init(){
        super.init();
        usesGamepads = true;
    }

    @Override
    public void loop() {
        super.loop();
        robot.mecanumDrive.driveMecanum(qq_gamepad1.leftStick.getY(),
                                        qq_gamepad1.leftStick.getX(),
                                        qq_gamepad1.rightStick.getX());
    }
}
