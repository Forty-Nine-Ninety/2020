package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.InserterSubsystem;

import static frc.robot.Constants.*;

import java.util.function.BooleanSupplier;

public class RunInserterCommand extends CommandBase {

    private final InserterSubsystem m_inserter;
    private BooleanSupplier m_supplier;

    public RunInserterCommand(InserterSubsystem shooter) {
        m_inserter = shooter;
    }

    @Override
    public void execute() {
    }

    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public void end(boolean interrupted) {
        m_inserter.run(false);
    }
}
