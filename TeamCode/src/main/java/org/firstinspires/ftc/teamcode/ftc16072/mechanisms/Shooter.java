package org.firstinspires.ftc.teamcode.ftc16072.mechanisms;

import com.qualcomm.robotcore.hardware.AnalogInput;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.teamcode.ftc16072.mechanisms.tests.QQ_Test;
import org.firstinspires.ftc.teamcode.ftc16072.mechanisms.tests.QQ_TestMotor;
import org.firstinspires.ftc.teamcode.ftc16072.mechanisms.tests.QQ_TestServo;

import java.util.Arrays;
import java.util.List;

public class Shooter implements QQ_Mechanism {
    enum AimLocation {
        LowGoal,
        MidGoal,
        HighGoal,
        PowerShot1,
        PowerShot2,
        PowerShot3
    }


    private DcMotor shooterMotor;
    private Servo shooterImport;

    final double INSERT = 0; // TODO: Find Value
    final double RESET = 0.18; // TODO: Find value

    /**
     * initializes Shooter
     *
     * @param hwMap Harwdware map from config
     */
    @Override
    public void init(HardwareMap hwMap) {
        shooterMotor = hwMap.get(DcMotor.class, "shooter_motor");
        shooterImport = hwMap.get(Servo.class, "servo_import_shooter");
    }

    /**
     * gets tests
     *
     * @return a list of QQ_Tests
     */
    @Override
    public List<QQ_Test> getTests() {
        return Arrays.asList(new QQ_TestMotor("Motor-.5", -0.7, shooterMotor),
            new QQ_TestMotor("Motor - Full Speed", -0.8, shooterMotor),
                new QQ_TestServo("Import", INSERT, RESET, shooterImport));
    }

    //create methods to set angle up and down, left, right

    /**
     * aim the shooter at the target
     *
     * @param aimLocation location to aim the shooter to
     * @return true when done
     */
    public boolean aim(AimLocation aimLocation) {
        return false;
    }


    public void spinWheels(double frontSpeed, double backSpeed) {
        shooterMotor.setPower(backSpeed);
    }

    public void flick(boolean shouldFlick){
        if (shouldFlick) {
            shooterImport.setPosition(INSERT);
        } else {
            shooterImport.setPosition(RESET);
        }
    }



    /**
     * get name
     *
     * @return name
     */
    @Override
    public String getName() {
        return "Shooter";
    }
}
