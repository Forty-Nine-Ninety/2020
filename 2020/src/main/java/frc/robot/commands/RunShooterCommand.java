package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ShooterSubsystem;

import static frc.robot.Constants.*;

import java.util.function.DoubleSupplier;

public class RunShooterCommand extends CommandBase {

    private final ShooterSubsystem m_shooter;
    private DoubleSupplier m_supplier;

    public RunShooterCommand(ShooterSubsystem shooter, DoubleSupplier supplier) {
        m_shooter = shooter;
        m_supplier = supplier;
        addRequirements(shooter);
    }

    @Override
    public void execute() {
        m_shooter.setRotationPercentage(m_supplier.getAsDouble());
    }

    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public void end(boolean interrupted) {
        m_shooter.setRotationPercentage(0);
    }
}
