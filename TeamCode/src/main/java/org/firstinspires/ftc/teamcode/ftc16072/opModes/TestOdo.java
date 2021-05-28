package org.firstinspires.ftc.teamcode.ftc16072.opModes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.ftc16072.mechanisms.OdoOnlyRobot;
import org.firstinspires.ftc.teamcode.ftc16072.utils.QQ_Gamepad;

class TestOdo extends OpMode {
    public OdoOnlyRobot robot = new OdoOnlyRobot();
    QQ_Gamepad qq_gamepad1;
    boolean gamepad1BPressed;

    @Override
    public void init() {
        robot.init(hardwareMap);
    }

    @Override
    public void loop() {
        qq_gamepad1 = new QQ_Gamepad(gamepad1);
        robot.nav.driveFieldRelativeAngle(
                qq_gamepad1.leftStick,
                qq_gamepad1.rightStick);

        if (qq_gamepad1.b() & !gamepad1BPressed){
            robot.nav.flipDriving = !robot.nav.flipDriving;
        }
        telemetry.addData("Pose", robot.nav.getCurrentPosition());
    }
}
