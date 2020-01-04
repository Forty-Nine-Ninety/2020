package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class HatchSubsystem extends SubsystemBase {

    private final Solenoid solenoid;
    private boolean isExtended;

    public HatchSubsystem() {
        solenoid = new Solenoid(Constants.CAN_PCM, Constants.PORT_PCM_HATCH);
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
}
