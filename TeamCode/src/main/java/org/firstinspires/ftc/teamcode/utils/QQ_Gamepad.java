package org.firstinspires.ftc.teamcode.utils;

import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.teamcode.utils.Joystick;

public class QQ_Gamepad {
    Gamepad gamepad;
    public Joystick rightStick;
    public Joystick leftStick;

    public QQ_Gamepad(Gamepad gamepad){
        this.gamepad = gamepad;
        rightStick  = new Joystick(gamepad.right_stick_x, gamepad.right_stick_y, gamepad.right_stick_button);
        leftStick = new Joystick(gamepad.left_stick_x, gamepad.left_stick_y, gamepad.left_stick_button);
    }

    /**
     * returns the state of left bumper
     * @return the state of the left bumper
     */
    public boolean leftBumper(){
        return gamepad.left_bumper;
    }

    /**
     * returns the state of right bumper
     * @return the state of the right bumper
     */
    public boolean rightBumper(){
        return gamepad.right_bumper;
    }

    /**
     * returns the state of button a
     * @return the state of button a
     */
    public boolean a(){
        return gamepad.a;
    }

    /**
     * returns the state of button b
     * @return the stae of button b
     */
    public boolean b(){
        return gamepad.b;
    }

    /**
     * returns the state of button x
     * @return the state of buttton x
     */
    public boolean x(){
        return gamepad.x;
    }

    /**
     * returns the state of button y
     * @return the state of button y
     */
    public boolean y(){
        return gamepad.y;
    }

    /**
     * returns the state of upper dpad
     * @return the state of upper dpad
     */
    public boolean dpadUp(){
        return gamepad.dpad_up;
    }

    /**
     * returns the state of lower dpad
     * @return the state of lower dpad
     */
    public boolean dpadDown(){
        return gamepad.dpad_down;
    }

    /**
     * returns the state of left dpad
     * @return the state of left dpad
     */
    public boolean dpadLeft(){
        return gamepad.dpad_left;
    }

    /**
     * returns the state of right dpad
     * @return the state of right dpad
     */
    public boolean dpadRight(){
        return gamepad.dpad_right;
    }

    /**
     * returns the state of left trigger
     * @return the state of left trigger
     */
    public double leftTrigger(){
        return gamepad.left_trigger;
    }

    /**
     * returns the state of right trigger
     * @return the state of right trigger
     */
    public double rightTrigger(){
        return gamepad.right_trigger;
    }







}
