package org.firstinspires.ftc.teamcode.opModes;

import org.firstinspires.ftc.teamcode.mechanisms.QQ_Mechanism;
import org.firstinspires.ftc.teamcode.mechanisms.QQ_Test;
import org.firstinspires.ftc.teamcode.utils.QQ_Gamepad;

import java.util.List;

class Test_Wiring extends QQ_Opmode {
    int currentMechanism = 0;
    int currentTest = 0;
    boolean wasRight;
    boolean wasLeft;
    boolean wasUp;
    boolean wasDown;
    boolean wasB;
    List<QQ_Mechanism> mechanisms = robot.getMechanisms();

    @Override
    public void loop() {

        List<QQ_Test> tests = mechanisms.get(currentMechanism).getTests();


        if(qq_gamepad1.dpadRight() && !wasRight){
            currentMechanism ++;
            if (currentMechanism >= mechanisms.size()){
                currentMechanism = 0;
            }
            currentTest = 0;
        }
        wasRight = qq_gamepad1.dpadRight();

        if(qq_gamepad1.dpadLeft() && !wasLeft){
            currentMechanism --;
            if (currentMechanism <= 0){
                currentMechanism = mechanisms.size() - 1;
            }
            currentTest = 0;
        }
        wasLeft = qq_gamepad1.dpadLeft();

        if(qq_gamepad1.dpadDown() && !wasDown){
            currentTest --;
            if(currentTest <= 0){
                currentTest = 0;
            }
        }
        wasDown = qq_gamepad1.dpadDown();

        if(qq_gamepad1.dpadUp() && !wasUp){
            currentTest ++;
            if(currentTest >= tests.size()){
                currentTest = tests.size() - 1;
            }
        }

        telemetry.addData("instructions", "Right and Left to switch mechanisms, Up and Down to arrow through tests in the mechanism");
        telemetry.addData("Mechanism", mechanisms.get(currentMechanism).getName());
        telemetry.addData("Test",tests.get(currentTest).getDescription());


        tests.get(currentTest).run(qq_gamepad1.b(), telemetry);



    }
}
