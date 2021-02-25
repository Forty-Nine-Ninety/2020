package frc.robot.commands.tests;

import frc.robot.subsystems.ClimbSubsystem;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.InstantCommand;

import java.util.function.DoubleSupplier;
import static frc.robot.Constants.*;

public class TestElevatorCommand extends CommandBase {

    private final ClimbSubsystem m_climb;
    private DoubleSupplier m_climbSpeedSupplier, m_balanceSpeedSupplier;

    public TestElevatorCommand(ClimbSubsystem climb) {
        addRequirements(climb);
        m_climb = climb;
    }

    @Override
    public void execute() {
        double climbSpeed = m_climbSpeedSupplier.getAsDouble() * CLIMB_MOTOR_SPEED;
        double balanceSpeed = m_balanceSpeedSupplier.getAsDouble();

        m_climb.runClimb(climbSpeed);
        m_climb.runBalance(balanceSpeed);
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