package frc.robot.commands;

import frc.robot.subsystems.BalanceClimbSubsystem;
import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj2.command.CommandBase;

public class BalanceClimbCommand extends CommandBase {

    private final BalanceClimbSubsystem m_balance;
    private DoubleSupplier m_speedSupplier;

    public BalanceClimbCommand(BalanceClimbSubsystem balance) {
        addRequirements(balance);
        m_balance = balance;
    }

    public void setSuppliers(DoubleSupplier speed) {
        m_speedSupplier = speed;
    }

    @Override
    public void execute() {
        m_balance.setSpeed(m_speedSupplier.getAsDouble());
    }

}
