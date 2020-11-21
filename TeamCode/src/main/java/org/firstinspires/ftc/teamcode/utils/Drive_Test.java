package org.firstinspires.ftc.teamcode.utils;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.teamcode.mechanisms.MecanumDrive;
import org.firstinspires.ftc.teamcode.mechanisms.Robot;
import org.firstinspires.ftc.teamcode.opModes.QQ_Opmode;

class Drive_Test extends QQ_Opmode {
    @Override
    public void loop() {
robot.mecanumDrive.driveMecanum(-gamepad1.left_stick_y,0,0);
        MecanumDrive.MoveDeltas moveDeltas = robot.mecanumDrive.getDistance();
        telemetry.addData("forward", moveDeltas.getForward(DistanceUnit.INCH));

    }
}
