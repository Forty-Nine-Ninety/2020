package frc.robot.commands;

import frc.robot.subsystems.ClimbSubsystem;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.InstantCommand;

import java.util.function.BooleanSupplier;
import java.util.function.DoubleSupplier;

public class ElevatorTestCommand extends CommandBase {

    private final ClimbSubsystem m_climb;
    private DoubleSupplier m_climbSpeedSupplier, m_balanceSpeedSupplier;

    public ElevatorTestCommand(ClimbSubsystem climb) {
        addRequirements(climb);
        m_climb = climb;
    }

    @Override
    public void execute() {
        m_climb.runClimb(m_climbSpeedSupplier.getAsDouble());
        m_climb.runBalance(m_balanceSpeedSupplier.getAsDouble());
    }

    public InstantCommand lockSupplier() {
        return new InstantCommand(() -> m_climb.setLock(! m_climb.isLocked()), m_climb);
    }

    public void setSuppliers(DoubleSupplier climb, DoubleSupplier balance) {
        m_climbSpeedSupplier = climb;
        m_balanceSpeedSupplier = balance;
    }

    @Override
    public void end(boolean bool){
        m_climb.setLock(true);
        m_climb.runClimb(0);
    }

}