package org.firstinspires.ftc.teamcode.opModes;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.mechanisms.QQ_Mechanism;
import org.firstinspires.ftc.teamcode.mechanisms.tests.QQ_Test;
import org.firstinspires.ftc.teamcode.utils.QQ_Gamepad;

import java.util.List;

@TeleOp()
public class TestWiring extends QQ_Opmode {
    int currentMechanism = 0;
    int currentTestNum = 0;
    boolean wasRight;
    boolean wasLeft;
    boolean wasUp;
    boolean wasDown;
    boolean wasB;
    List<QQ_Mechanism> mechanisms = robot.getMechanisms();
    String testDescription;
    QQ_Test currentTest;
    /**
     * Main code for test wiring
     */
    @Override
    public void loop() {
        qq_gamepad1 = new QQ_Gamepad(gamepad1);
        if (qq_gamepad1.dpadRight() && !wasRight) {
            currentMechanism++;
            if (currentMechanism >= mechanisms.size()) {
                currentMechanism = 0;
            }
            currentTestNum = 0;
        }
        wasRight = qq_gamepad1.dpadRight();

        if (qq_gamepad1.dpadLeft() && !wasLeft) {
            currentMechanism--;
            if (currentMechanism <= 0) {
                currentMechanism = mechanisms.size() - 1;
            }
            currentTestNum = 0;
        }
        wasLeft = qq_gamepad1.dpadLeft();

        List<QQ_Test> tests = mechanisms.get(currentMechanism).getTests();

        if(tests != null) {
            if (qq_gamepad1.dpadDown() && !wasDown) {
                currentTestNum++;
                if (currentTestNum >= tests.size()) {
                    currentTestNum = 0;
                }
            }
            wasDown = qq_gamepad1.dpadDown();

            if (qq_gamepad1.dpadUp() && !wasUp) {
                currentTestNum--;
                if (currentTestNum < 0) {
                    currentTestNum = tests.size() - 1;
                }
            }
            wasUp = qq_gamepad1.dpadUp();

            currentTest = tests.get(currentTestNum);
            testDescription = currentTest.getDescription();
            currentTest.run(qq_gamepad1.b(), telemetry);
        } else {
            testDescription = "No Tests For this Mechanism";
        }
        telemetry.addData("instructions", "Right and Left to switch mechanisms, Up and Down to arrow through tests in the mechanism");
        telemetry.addData("Mechanism", mechanisms.get(currentMechanism).getName());
        telemetry.addData("Test", testDescription);
    }
}
