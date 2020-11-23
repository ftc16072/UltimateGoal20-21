package org.firstinspires.ftc.teamcode.opModes;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.teamcode.mechanisms.MecanumDrive;

@TeleOp
public class Drive_Test extends QQ_Opmode {
    @Override
    public void loop() {
        robot.mecanumDrive.driveMecanum(-gamepad1.left_stick_y, 0, 0);
        MecanumDrive.MoveDeltas moveDeltas = robot.mecanumDrive.getDistance(false);
        telemetry.addData("forward", moveDeltas.getForward(DistanceUnit.INCH));
    }
}
