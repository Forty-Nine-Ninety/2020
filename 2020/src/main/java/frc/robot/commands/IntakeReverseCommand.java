package frc.robot.commands;

import frc.robot.subsystems.IntakeSubsystem;
import edu.wpi.first.wpilibj2.command.InstantCommand;

public class IntakeReverseCommand extends InstantCommand {

    private final IntakeSubsystem m_intake;

    public IntakeReverseCommand(IntakeSubsystem intake) {
        m_intake = intake;
    }

    @Override
    public void initialize() {
        m_intake.setReversed(! m_intake.isReversed());
    }

}