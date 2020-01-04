package frc.robot.commands;

import frc.robot.subsystems.ClimbSubsystem;
import edu.wpi.first.wpilibj2.command.InstantCommand;

public class ExtendClimbCommand extends InstantCommand {

    private final ClimbSubsystem m_climb;

    public ExtendClimbCommand(ClimbSubsystem climb) {
        m_climb = climb;
        addRequirements(climb);
    }

    @Override
    public void initialize() {
        m_climb.setExtended(false);
    }
}
