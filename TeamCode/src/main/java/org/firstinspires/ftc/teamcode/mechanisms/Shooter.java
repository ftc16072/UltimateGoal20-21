package org.firstinspires.ftc.teamcode.mechanisms;

import com.qualcomm.robotcore.hardware.AnalogInput;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.teamcode.mechanisms.tests.QQ_DualTest;
import org.firstinspires.ftc.teamcode.mechanisms.tests.QQ_Test;
import org.firstinspires.ftc.teamcode.mechanisms.tests.QQ_TestAnalogSensor;
import org.firstinspires.ftc.teamcode.mechanisms.tests.QQ_TestMotor;
import org.firstinspires.ftc.teamcode.mechanisms.tests.QQ_TestServo;

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

    public double shooterAngle = 0;


    private DcMotor shooterBack;
    private DcMotor shooterFront;
    private Servo shooterPivot;
    private Servo shooterImport;
    private AnalogInput pot;

    final double PIVOT_DOWN = 0.25;  // TODO: find right value
    final double PIVOT_UP = 0.75; // TODO: Find correct value

    final double ANGLE_UP = 15.0;
    final double ANGLE_DOWN = 0.0;

    final double VOLT_UP = 0.17;
    final double VOLT_DOWN = 0.27;

    final double VOLTAGE_TOLERANCE = 0.03;
    final double VOLTAGE_KP_UP = 1.6;
    final double VOLTAGE_KP_DOWN = 0.8;




    final double INSERT = 0; // TODO: Find Value
    final double RESET = 0.18; // TODO: Find value

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
        pot = hwMap.get(AnalogInput.class, "lift_pot");

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
                new QQ_DualTest(
                        new QQ_TestServo("Pivot", PIVOT_UP, PIVOT_DOWN, shooterPivot),
                        new QQ_TestAnalogSensor("pot", pot)),
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


    public boolean goToAngle(double angle){
        return goToVoltage(Range.scale(angle, VOLT_DOWN, VOLT_UP, ANGLE_DOWN, ANGLE_UP));
    }

    private boolean goToVoltage(double desiredVoltage){
        double voltageDiff = pot.getVoltage() - desiredVoltage;
        if (Math.abs(voltageDiff) <= VOLTAGE_TOLERANCE){
            shooterPivot.setPosition(0.0);
            return true;
        }

        if(voltageDiff > 0){
            double speed = Math.max( voltageDiff * VOLTAGE_KP_UP, 0.75);
            shooterPivot.setPosition(speed);
        } else {
            double speed =  Math.max( voltageDiff * VOLTAGE_KP_DOWN, 0.25);
            shooterPivot.setPosition(speed);
        }
        return false;
    }


    public void spinWheels(double frontSpeed, double backSpeed) {
        shooterBack.setPower(frontSpeed);
        shooterFront.setPower(backSpeed);
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
