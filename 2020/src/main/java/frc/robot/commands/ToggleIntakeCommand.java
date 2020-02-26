package frc.robot.commands;

import frc.robot.subsystems.IntakeSubsystem;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj2.command.InstantCommand;

public class ToggleIntakeCommand extends InstantCommand {

    private final IntakeSubsystem m_intake;

    public ToggleIntakeCommand(IntakeSubsystem intake) {
        m_intake = intake;
    }

    @Override
    public void initialize() {
        m_intake.set(m_intake.get() != Value.kForward);
    }

}
