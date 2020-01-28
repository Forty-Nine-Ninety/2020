package frc.robot.commands;

import frc.robot.subsystems.SpinnerSubsystem;
import java.util.function.DoubleSupplier;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class SpinnerCommand extends CommandBase {

    private final SpinnerSubsystem m_spin;
    private DoubleSupplier m_speedSupplier;

    public SpinnerCommand(SpinnerSubsystem spin) {
        addRequirements(spin);
        m_spin = spin;
    }

    public void setSuppliers(DoubleSupplier speed) {
        m_speedSupplier = speed;
    }

    @Override
    public void execute() {
        m_spin.setSpeed(m_speedSupplier.getAsDouble());
    }

}
