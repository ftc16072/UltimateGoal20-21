package org.firstinspires.ftc.teamcode.utils;

import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

public class Joystick {
    double x;
    double y;
    boolean pressed;
    Polar polar;

    /**
     * Construct a Joystick with X, Y, pressed
     * @param x x of joystick
     * @param y y of joystick(not Flipped)
     * @param pressed is the stick pressed?
     */
    Joystick(double x, double y, boolean pressed){
        this.x = x;
        this.y = -y;
        this.pressed = pressed;
    }

    /**
     * Get the x value of the joystick
     * @return x value of joystick
     */
    public double getX() {
        return x;
    }

    /**
     * Get the normalized y value of the joystick
     * @return get the flipped y value
     */
    public double getY() {
        return y;
    }

    /**
     * does the math for the polar joystick
     */
    void setPolar(){
        if(polar != null){
            polar = new Polar(x, y, DistanceUnit.INCH);
        }
    }

    /**
     * gets the polar of the joystick
     * @return polar of joystick
     */
    public Polar getPolar() {
        setPolar();
        return polar;
    }

    /**
     * get the r component of the polar
     * @return r portion of polar
     */
    public double getR() {
        setPolar();
        return polar.getR(DistanceUnit.MM);
    }

    /***
     * get the theta of the polar
     * @param angleUnit angle unit you want the theta in
     * @return theta in desired angle unit
     */
    public double getTheta(AngleUnit angleUnit){
        return polar.getTheta(angleUnit);
    }

    /**
     * is the stick pressed?
     * @return if the stick is pressed
     */
    public boolean isPressed() {
        return pressed;
    }
}
