package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class ClimbSubsystem extends SubsystemBase {

    private final Solenoid solenoid;
    private boolean isExtended;

    public ClimbSubsystem(Side location) {
        solenoid = new Solenoid(Constants.CAN_PCM, (location == Side.Front) ? Constants.PORT_PCM_CLIMB_FRONT : Constants.PORT_PCM_CLIMB_REAR);
        isExtended = false;
        solenoid.set(isExtended);
    }

    public boolean getExtended() {
        return isExtended;
    }

    public void setExtended(boolean extended) {
        if (getExtended() != extended) solenoid.set(extended);
        isExtended = extended;
    }

    @Override
    public void periodic() {}

    public enum Side { Front, Rear }
}
