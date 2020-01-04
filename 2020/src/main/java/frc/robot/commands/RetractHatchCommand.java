package frc.robot.commands;

import frc.robot.subsystems.HatchSubsystem;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class RetractHatchCommand extends CommandBase {

    private final HatchSubsystem m_hatch;

    public RetractHatchCommand(HatchSubsystem hatch) {
        m_hatch = hatch;
        addRequirements(hatch);
    }

    @Override
    public void initialize() {
        m_hatch.setExtended(false);
    }
}
