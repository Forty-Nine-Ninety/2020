package frc.robot.commands;

import frc.robot.subsystems.HatchBeakSubsystem;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class RetractHatchBeakCommand extends CommandBase {

    private final HatchBeakSubsystem m_hatch;

    public RetractHatchBeakCommand(HatchBeakSubsystem hatch) {
        m_hatch = hatch;
        addRequirements(hatch);
    }

    @Override
    public void initialize() {
        m_hatch.setClosed(false);
    }
}
