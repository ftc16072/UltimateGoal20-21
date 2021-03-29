package org.firstinspires.ftc.teamcode.ftc16072.utils;

import com.qualcomm.robotcore.hardware.Gamepad;

public class QQ_BothGamepad {
    Gamepad gamepad1;
    Gamepad gamepad2;

    /**
     * Constructor for the QQ gamepad
     * @param gamepad1 gamepad to use for the smart features
     * @param gamepad2 gamepad to use for the smart features
     */
    public QQ_BothGamepad(Gamepad gamepad1, Gamepad gamepad2){
        this.gamepad1 = gamepad1;
        this.gamepad2 = gamepad2;
    }


    /**
     * button a
     * @return the state of button a
     */
    public boolean a(){
        return gamepad1.a || gamepad2.a;
    }


    /**
     * button b
     * @return the state of button b
     */
    public boolean b(){
        return gamepad1.b || gamepad2.b;
    }

    /**
     * button x
     * @return the state of button x
     */
    public boolean x(){
        return gamepad1.x || gamepad2.x;
    }
    /**
     * button y
     * @return the state of button y
     */
    public boolean y(){
        return gamepad1.y || gamepad2.y;
    }
    /**
     * Dpad up button
     * @return the state of the dpad up button
     */
    public boolean dpadUp(){
        return gamepad1.dpad_up || gamepad2.dpad_up;
    }

    /**
     * Dpad down button
     * @return the state of the dpad down button
     */
    public boolean dpadDown(){
        return gamepad1.dpad_down || gamepad2.dpad_down;
    }
    /**
     * Dpad left button
     * @return the state of the dpad right button
     */
    public boolean dpadLeft(){
        return gamepad1.dpad_left || gamepad2.dpad_left;
    }
    /**
     * Dpad right button
     * @return the state of the dpad right button
     */
    public boolean dpadRight(){
        return gamepad1.dpad_right || gamepad2.dpad_right;
    }

}
