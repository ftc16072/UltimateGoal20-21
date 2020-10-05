package org.firstinspires.ftc.teamcode.mechanisms.tests;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public abstract class QQ_Test {
    private String description;

    /**
     * Constructor method
     * @param description a human readable description of the test
     */
    QQ_Test(String description) {
        this.description = description;
    }

    /**
     * gets the description of the test
     * @return returns the description of the test
     */
    public String getDescription() {
        return description;
    }

    /**
     * forces the test to have a run method
     * @param on on flips the test state
     * @param telemetry telemetry for the test to send what its doing
     */
    public abstract void run(boolean on, Telemetry telemetry);
}
