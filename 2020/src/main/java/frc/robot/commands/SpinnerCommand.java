package frc.robot.commands;

import frc.robot.subsystems.SpinnerSubsystem;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class SpinnerCommand extends CommandBase {

    private final SpinnerSubsystem m_spin;

    public SpinnerCommand(SpinnerSubsystem spin) {
        addRequirements(spin);
        m_spin = spin;
    }

    @Override
    public void execute() {
        m_spin.setSpeed();
    }

}
