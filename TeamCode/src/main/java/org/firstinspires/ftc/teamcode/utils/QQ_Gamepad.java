package org.firstinspires.ftc.teamcode.utils;

import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.teamcode.utils.Joystick;

class QQ_Gamepad {
    Gamepad gamepad;
    public Joystick rightStick;
    public Joystick leftStick;

    QQ_Gamepad(Gamepad gamepad){
        this.gamepad = gamepad;
        rightStick  = new Joystick(gamepad.right_stick_x, gamepad.right_stick_y, gamepad.right_stick_button);
        leftStick = new Joystick(gamepad.left_stick_x, gamepad.left_stick_y, gamepad.left_stick_button);
    }


    public boolean leftBumper(){
        return gamepad.left_bumper;
    }
    public boolean rightBumper(){
        return gamepad.right_bumper;
    }
    public boolean a(){
        return gamepad.a;
    }
    public boolean b(){
        return gamepad.b;
    }
    public boolean x(){
        return gamepad.x;
    }
    public boolean y(){
        return gamepad.y;
    }
    public boolean dpadUp(){
        return gamepad.dpad_up;
    }
    public boolean dpadDown(){
        return gamepad.dpad_down;
    }
    public boolean dpadLeft(){
        return gamepad.dpad_left;
    }
    public boolean dpadRight(){
        return gamepad.dpad_right;
    }
    public double leftTrigger(){
        return gamepad.left_trigger;
    }
    public double rightTrigger(){
        return gamepad.right_trigger;
    }







}
