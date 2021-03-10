package frc.robot.commands;

import frc.robot.Direction;
import frc.robot.subsystems.IntakeSubsystem;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class RunIntakeCommand extends CommandBase {

    private final IntakeSubsystem m_intake;
    private final Direction m_direction;

    public RunIntakeCommand(IntakeSubsystem intake, Direction direction) {
        addRequirements(intake);
        m_intake = intake;

        m_direction = direction;
    }

    @Override
    public void execute() {
        m_intake.run(m_direction);
    }

    @Override
    public void end(boolean interrupted) {
        m_intake.run(Direction.Stop);
    }


}
