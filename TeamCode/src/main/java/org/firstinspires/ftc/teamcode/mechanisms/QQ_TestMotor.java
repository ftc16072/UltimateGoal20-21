package org.firstinspires.ftc.teamcode.mechanisms;

import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class QQ_TestMotor extends QQ_Test{
    private DcMotor dcMotor;
    private double speed;


    QQ_TestMotor(String desciption, double speed, DcMotor motor){

        super(desciption);
        this.speed = speed;
        dcMotor = motor;

    }

    @Override
    void run(boolean on, Telemetry telemetry) {
        if(on){

            dcMotor.setPower(speed);
        } else{

            dcMotor.setPower(0.0);
        }
        telemetry.addData("Encoder", dcMotor.getCurrentPosition());
    }
}
