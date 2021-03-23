package frc.robot.vision;

import static frc.robot.Constants.*;


public class TunerState {
    public static TunerState INSTANCE = new TunerState();

    private double setpoint = 2000;

    public double getSetpoint() {
        return setpoint;
    }

    public void changeSetpoint(double delta) {
        setpoint += delta;
    }
}
