package frc.robot.commands;

import frc.robot.subsystems.ClimbSubsystem;
import edu.wpi.first.wpilibj2.command.InstantCommand;

public class RetractClimbCommand extends InstantCommand {

    private final ClimbSubsystem m_climb;

    public RetractClimbCommand(ClimbSubsystem climb) {
        m_climb = climb;
        addRequirements(climb);
    }

    @Override
    public void initialize() {
        m_climb.setExtended(false);
    }
}
