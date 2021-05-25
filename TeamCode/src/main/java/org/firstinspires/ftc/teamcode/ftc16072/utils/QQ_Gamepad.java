package org.firstinspires.ftc.teamcode.ftc16072.utils;

import com.qualcomm.robotcore.hardware.Gamepad;

public class QQ_Gamepad {
    Gamepad gamepad;
    public Joystick rightStick;
    public Joystick leftStick;


    /**
     * Constructor for the QQ gamepad
     * @param gamepad gamepad to use for the smart features
     */
    public QQ_Gamepad(Gamepad gamepad){
        this.gamepad = gamepad;
        rightStick  = new Joystick(gamepad.right_stick_x, gamepad.right_stick_y, gamepad.right_stick_button);
        leftStick = new Joystick(gamepad.left_stick_x, gamepad.left_stick_y, gamepad.left_stick_button);
    }

    /**
     * left bumper
     * @return the state of left bumper
     */
    public boolean leftBumper(){
        return gamepad.left_bumper;
    }

    /**
     * right bumper
     * @return the state of right bumper
     */
    public boolean rightBumper(){
        return gamepad.right_bumper;
    }


    /**
     * button a
     * @return the state of button a
     */
    public boolean a(){
        return gamepad.a;
    }


    /**
     * button b
     * @return the state of button b
     */
    public boolean b(){
        return gamepad.b;
    }

    /**
     * button x
     * @return the state of button x
     */
    public boolean x(){
        return gamepad.x;
    }
    /**
     * button y
     * @return the state of button y
     */
    public boolean y(){
        return gamepad.y;
    }
    /**
     * Dpad up button
     * @return the state of the dpad up button
     */
    public boolean dpadUp(){
        return gamepad.dpad_up;
    }

    /**
     * Dpad down button
     * @return the state of the dpad down button
     */
    public boolean dpadDown(){
        return gamepad.dpad_down;
    }
    /**
     * Dpad left button
     * @return the state of the dpad right button
     */
    public boolean dpadLeft(){
        return gamepad.dpad_left;
    }
    /**
     * Dpad right button
     * @return the state of the dpad right button
     */
    public boolean dpadRight(){
        return gamepad.dpad_right;
    }
    /**
     * left trigger
     * @return the state of the left trigger
     */
    public double leftTrigger(){
        return gamepad.left_trigger;
    }

    /**
     * right trigger
     * @return the state of the right trigger
     */
    public double rightTrigger(){
        return gamepad.right_trigger;
    }







}
