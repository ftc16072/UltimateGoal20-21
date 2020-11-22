package org.firstinspires.ftc.teamcode.opModes;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.teamcode.mechanisms.MecanumDrive;

@TeleOp
public class Drive_TestNav extends QQ_Opmode {
    @Override
    public void loop() {
        robot.nav.updatePose();
        robot.mecanumDrive.driveMecanum(-gamepad1.left_stick_y, 0, 0);
        telemetry.addData("forward",robot.nav.getCurrentPosition().getY(DistanceUnit.INCH));
    }
}
