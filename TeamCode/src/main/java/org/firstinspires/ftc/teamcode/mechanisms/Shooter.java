package org.firstinspires.ftc.teamcode.mechanisms;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.mechanisms.tests.QQ_Test;
import org.firstinspires.ftc.teamcode.mechanisms.tests.QQ_TestMotor;
import org.firstinspires.ftc.teamcode.mechanisms.tests.QQ_TestServo;

import java.util.Arrays;
import java.util.List;

class Shooter implements QQ_Mechanism {
    enum AimLocation {
        LowGoal,
        MidGoal,
        HighGoal,
        PowerShot1,
        PowerShot2,
        PowerShot3
    }


    private DcMotor shooterBack;
    private DcMotor shooterFront;
    private Servo shooterPivot;
    private Servo shooterImport;

    final double PIVOT_DOWN = 0.25;  // TODO: find right value
    final double PIVOT_UP = 0.75; // TODO: Find correct value
    final double INSERT = 0.25; // TODO: Find Value
    final double RESET = 0.75; // TODO: Find value

    /**
     * initializes Shooter
     *
     * @param hwMap Harwdware map from config
     */
    @Override
    public void init(HardwareMap hwMap) {
        shooterBack = hwMap.get(DcMotor.class, "shooter_back_motor");
        shooterFront = hwMap.get(DcMotor.class, "shooter_front_motor");
        shooterPivot = hwMap.get(Servo.class, "servo_pivot_shooter");
        shooterImport = hwMap.get(Servo.class, "servo_import_shooter");
    }

    /**
     * gets tests
     *
     * @return a list of QQ_Tests
     */
    @Override
    public List<QQ_Test> getTests() {
        return Arrays.asList(new QQ_TestMotor("Front Motor", 0.25, shooterFront),
                new QQ_TestMotor("Back Motor", 0.25, shooterBack),
                new QQ_TestServo("Pivot", PIVOT_UP, PIVOT_DOWN, shooterPivot),
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
