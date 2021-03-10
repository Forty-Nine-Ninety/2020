package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Direction;
import frc.robot.subsystems.InserterSubsystem;

import static frc.robot.Constants.*;

import java.util.function.BooleanSupplier;

public class CheckRunInserterCommand extends CommandBase {

    private final InserterSubsystem m_inserter;
    private final Direction m_direction;
    private final BooleanSupplier m_checkSupplier;


    public CheckRunInserterCommand(InserterSubsystem inserter, Direction direction, BooleanSupplier checkSupplier) {
        addRequirements(inserter);
        m_inserter = inserter;
        m_direction = direction;
        m_checkSupplier = checkSupplier;
    }

    @Override
    public void execute() {
        m_inserter.run(m_checkSupplier.getAsBoolean() ? m_direction : Direction.Stop);
    }

    @Override
    public void end(boolean interrupted) {
        m_inserter.run(Direction.Stop);
    }
}
