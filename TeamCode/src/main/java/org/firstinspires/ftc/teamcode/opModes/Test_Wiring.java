package org.firstinspires.ftc.teamcode.opModes;

import org.firstinspires.ftc.teamcode.mechanisms.QQ_Mechanism;
import org.firstinspires.ftc.teamcode.mechanisms.QQ_Test;

import java.util.List;

class Test_Wiring extends QQ_Opmode {
    int currentMechanism = 0;
    int currentTest = 0;
    boolean wasRight;
    boolean wasLeft;
    boolean wasUp;
    boolean wasDown;

    @Override
    public void loop() {
        List<QQ_Test> tests = robot.mechanisms.get(currentMechanism).getTests();

        if(qq_gamepad1.dpadRight() && !wasRight){
            currentMechanism ++;
            if (currentMechanism >= robot.mechanisms.size()){
                currentMechanism = 0;
            }
            currentTest = 0;
        }
        wasRight = qq_gamepad1.dpadRight();

        if(qq_gamepad1.dpadLeft() && !wasLeft){
            currentMechanism --;
            if (currentMechanism <= 0){
                currentMechanism = robot.mechanisms.size() - 1;
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

    }
}
