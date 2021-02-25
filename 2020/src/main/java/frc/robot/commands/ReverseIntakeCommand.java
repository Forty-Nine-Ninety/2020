package frc.robot.commands;

import frc.robot.subsystems.IntakeSubsystem;
import edu.wpi.first.wpilibj2.command.InstantCommand;

public class ReverseIntakeCommand extends InstantCommand {

    private final IntakeSubsystem m_intake;

    public ReverseIntakeCommand(IntakeSubsystem intake) {
        m_intake = intake;
    }

    @Override
    public void initialize() {
        m_intake.setReversed(! m_intake.isReversed());
    }

}