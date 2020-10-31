package org.firstinspires.ftc.teamcode.mechanisms;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.mechanisms.tests.QQ_Test;

import java.util.List;

class Shooter implements QQ_Mechanism {
    class manualAim{
        /**
         * Manualy move the shooter up
         * @param degrees degrees to move the shooter up
         * @return true when done
         */
        public boolean ShooterUp(double degrees) {
            return false;
        }

        /**
         * Manualy move the shooter Left
         * @param degrees degrees to move the shooter left
         * @return true when done
         */
        public boolean ShooterLeft(double degrees) {
            return false;
        }

        /**
         * Manualy move the shooter right
         * @param degrees degrees to move the shooter right
         * @return true when done
         */
        public boolean ShooterRight(double degrees) {
            return false;
        }
        /**
         * Manualy move the shooter down
         * @param degrees degrees to move the shooter down
         * @return true when done
         */
        public boolean ShooterDown(double degrees) {
            return false;
        }
    }
    enum AimLocation {
        LowGoal,
        MidGoal,
        HighGoal,
        PowerShot1,
        PowerShot2,
        PowerShot3
    }


    private DcMotor ShooterLeft;
    private DcMotor ShooterRight;

    /**
     * initializes Shooter
     * @param hwMap Harwdware map from config
     */
    @Override
    public void init(HardwareMap hwMap) {
        //ShooterLeft = hwMap.get(DcMotor.class, "LeftMotor");
        //ShooterRight = hwMap.get(DcMotor.class, "RightMotor");
    }

    /**
     * gets tests
     * @return a list of QQ_Tests
     */
    @Override
    public List<QQ_Test> getTests() {
        return null;
    }

    //create methods to set angle up and down, left, right

    /**
     * aim the shooter at the target
     * @param aimLocation location to aim the shooter to
     * @return true when done
     */
    public boolean aim(AimLocation aimLocation){
        return false;
    }

    /**
     * get name
     * @return name
     */
    @Override
    public String getName() {
        return "Shooter";
    }
}
