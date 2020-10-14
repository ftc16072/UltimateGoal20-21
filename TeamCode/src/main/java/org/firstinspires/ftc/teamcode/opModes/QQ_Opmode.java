package org.firstinspires.ftc.teamcode.opModes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.mechanisms.Robot;
import org.firstinspires.ftc.teamcode.utils.QQ_Gamepad;

abstract class QQ_Opmode extends OpMode {
    Robot robot = new Robot();
    boolean usesGamepads;
    QQ_Gamepad qq_gamepad1;
    QQ_Gamepad qq_gamepad2;

    @Override
    public void init() {
        robot.init(hardwareMap);
    }

    @Override
    public void loop() {
        if(usesGamepads){
            qq_gamepad1 = new QQ_Gamepad(gamepad1);
            qq_gamepad2 = new QQ_Gamepad(gamepad2);
        }
    }
}
