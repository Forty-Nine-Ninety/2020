package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class HatchBeakSubsystem extends SubsystemBase {

    private final Solenoid solenoid;
    private boolean isExtended;

    public HatchBeakSubsystem() {
        solenoid = new Solenoid(Constants.CAN_PCM, Constants.PORT_PCM_HATCH_BEAK);
        isExtended = false;
        solenoid.set(isExtended);
    }

    public boolean getClosed() {
        return isExtended;
    }

    public void setClosed(boolean extended) {
        if (getClosed() != extended) solenoid.set(extended);
        isExtended = extended;
    }

    @Override
    public void periodic() {}
}
