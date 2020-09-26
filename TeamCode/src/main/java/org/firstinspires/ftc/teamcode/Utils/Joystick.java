package org.firstinspires.ftc.teamcode.Utils;

import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;

class Joystick {
    double x;
    double y;
    boolean pressed;
    Polar polar;

    Joystick(double x, double y, boolean pressed){
        this.x = x;
        this.y = y;
        this.pressed = pressed;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    void setPolar(){
        if(polar != null){
            polar = new Polar(x, y);
        }
    }

    public Polar getPolar() {
        setPolar();
        return polar;
    }

    public double getR() {
        setPolar();
        return polar.getR();
    }

    public double getTheta(AngleUnit angleUnit){
        return polar.getTheta(angleUnit);
    }

    public boolean isPressed() {
        return pressed;
    }
}
