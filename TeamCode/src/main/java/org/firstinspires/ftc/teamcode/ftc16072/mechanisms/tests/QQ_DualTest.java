package org.firstinspires.ftc.teamcode.ftc16072.mechanisms.tests;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class QQ_DualTest extends QQ_Test {
    QQ_Test qq_test_1;
    QQ_Test qq_test_2;

    /**
     *
     * @param qq_test_1 get description
     * @param qq_test_2 get description
     */
    public QQ_DualTest(QQ_Test qq_test_1, QQ_Test qq_test_2){
        super(qq_test_1.getDescription() + qq_test_2.getDescription());
        this.qq_test_1 = qq_test_1;
        this.qq_test_2 = qq_test_2;

    }

    @Override
    public void run(boolean on, Telemetry telemetry) {
        qq_test_1.run(on, telemetry);
        qq_test_2.run(on, telemetry);
    }
}
