package frc.robot.commands;

import frc.robot.subsystems.IntakeSubsystem;

import edu.wpi.first.wpilibj2.command.CommandBase;

public class IntakeCommand extends CommandBase {

    private final IntakeSubsystem m_intake;

    public IntakeCommand(IntakeSubsystem intake) {
        addRequirements(intake);
        m_intake = intake;
    }

    @Override
    public void execute() {
        m_intake.run();
    }

}
