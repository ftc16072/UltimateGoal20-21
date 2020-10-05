package org.firstinspires.ftc.teamcode.mechanisms.tests;

import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class QQ_TestServo extends QQ_Test {
    private double offlocation;
    private double onlocation;
    private Servo servo;

    /**
     *
     * @param desciption
     * @param onlocation
     * @param offlocation
     * @param servo
     */
    QQ_TestServo(String desciption, double onlocation,double offlocation, Servo servo){

    super(desciption);
    this.servo = servo;
    this.offlocation = offlocation;
    this.onlocation = onlocation;


}

    @Override
    public void run(boolean on, Telemetry telemetry) {
        if(on){

            servo.setPosition(onlocation);
        } else{

           servo.setPosition(offlocation);
        }
        telemetry.addData("Position", servo.getPosition());
    }

}
