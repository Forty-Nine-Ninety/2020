package frc.robot.commands;

import frc.robot.subsystems.HatchBeakSubsystem;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class ExtendHatchBeakCommand extends CommandBase {
    
    private final HatchBeakSubsystem m_hatch;

    public ExtendHatchBeakCommand(HatchBeakSubsystem hatch) {
        m_hatch = hatch;
        addRequirements(hatch);
    }

    @Override
    public void initialize() {
        m_hatch.setClosed(true);
    }
}
