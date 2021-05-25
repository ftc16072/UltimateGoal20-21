package org.firstinspires.ftc.teamcode.ftc16072.opModes;

import com.acmerobotics.dashboard.FtcDashboard;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.teamcode.ftc16072.actions.QQ_Action;
import org.firstinspires.ftc.teamcode.ftc16072.mechanisms.Robot;
import org.firstinspires.ftc.teamcode.ftc16072.utils.QQ_Gamepad;
import org.firstinspires.ftc.teamcode.ftc16072.utils.RobotPose;
import org.firstinspires.ftc.teamcode.ftc16072.utils.StackPipeline;
import org.openftc.easyopencv.OpenCvCamera;
import org.openftc.easyopencv.OpenCvCameraFactory;
import org.openftc.easyopencv.OpenCvCameraRotation;

public abstract class QQ_Opmode extends OpMode {
    public Robot robot = new Robot();
    OpenCvCamera webcam;
    boolean usesGamepads;
    boolean usesCamera;
    public QQ_Gamepad qq_gamepad1;
    public QQ_Gamepad qq_gamepad2;
    protected QQ_Action currentAction;
    public StackPipeline.ringNumber numberRingsSeen;
    FtcDashboard dashboard;
    public Telemetry dashboardTelem;

    /**
     * initializing robot
     */
    @Override
    public void init() {
        dashboard = FtcDashboard.getInstance();
        dashboardTelem = dashboard.getTelemetry();
        robot.init(hardwareMap);
        robot.nav.setCurrentPosition(new RobotPose(0,0, DistanceUnit.INCH));
        if(usesCamera){
            int cameraMonitorViewId = hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());
            webcam = OpenCvCameraFactory.getInstance().createWebcam(hardwareMap.get(WebcamName.class, "Webcam 1"), cameraMonitorViewId);

            webcam.openCameraDeviceAsync(new OpenCvCamera.AsyncCameraOpenListener()
            {
                @Override
                public void onOpened()
                {
                    webcam.startStreaming(320, 240, OpenCvCameraRotation.UPRIGHT);
                }
            });
        }

        telemetry.addData("Pid", robot.shooter.shooterMotor.getPIDFCoefficients(DcMotor.RunMode.RUN_USING_ENCODER));

    }

    /**
     * loop method --- runs over and over
     * Updates the gamepad
     * runs the action method
     */
    @Override
    public void loop() {
        telemetry.addData("analysis", numberRingsSeen);
        robot.nav.updatePose();
        updateRingCount();
        if(usesGamepads){
            qq_gamepad1 = new QQ_Gamepad(gamepad1);
            qq_gamepad2 = new QQ_Gamepad(gamepad2);
        }

        if(currentAction != null){
            telemetry.addData("State", currentAction.getDescription());
            currentAction = currentAction.run(this);
        } else {
            telemetry.addData("State", "done");
        }


    }

    /**
     * add ring count for ringsShot and ringsSeen
     */
    void updateRingCount(){
        telemetry.addData("Ring Count", Math.round(robot.ringCount));
        robot.ringCount -= robot.shooter.getRingsShot(true);
        robot.ringCount += robot.transfer.getRingsSeen(true);

        if (robot.ringCount < 0){
            robot.ringCount = 0;
        }
    }



}
