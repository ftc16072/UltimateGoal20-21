package org.firstinspires.ftc.teamcode.utils;

import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

public class Joystick {
    double x;
    double y;
    boolean pressed;
    boolean squared;
    Polar polar;

    private double squareWithSign(double input){
        return input * input * Math.signum(input);
    }

    /**
     * Construct a Joystick with X, Y, pressed
     * @param x x of joystick
     * @param y y of joystick(not Flipped)
     * @param pressed is the stick pressed?
     */
    Joystick(double x, double y, boolean pressed){
        y = y * -1;
        this.x = squared ? squareWithSign(x) : x;
        this.y = squared ? squareWithSign(y) : y;
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
     * gets the polar of the joystick
     * @return polar of joystick
     */
    public Polar getPolar() {
        if(polar == null){
            polar = new Polar(x, y, DistanceUnit.CM);
        }
        return polar;
    }

    /**
     * get the r component of the polar
     * @return r portion of polar
     */
    public double getR() {
        return getPolar().getR(DistanceUnit.CM);
    }

    /***
     * get the theta of the polar
     * @param angleUnit angle unit you want the theta in
     * @return theta in desired angle unit
     */
    public double getTheta(AngleUnit angleUnit){
        return getPolar().getTheta(angleUnit);
    }

    /**
     * is the stick pressed?
     * @return if the stick is pressed
     */
    public boolean isPressed() {
        return pressed;
    }

    public void setSquared(boolean squared){
        this.squared = squared;
    }
}
