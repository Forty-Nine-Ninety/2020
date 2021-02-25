package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ShooterSubsystem;

import static frc.robot.Constants.*;

import java.util.function.DoubleSupplier;

public class RunShooterCommand extends CommandBase {

    private final ShooterSubsystem m_shooter;
    private DoubleSupplier m_supplier;

    public RunShooterCommand(ShooterSubsystem shooter) {
        m_shooter = shooter;
        addRequirements(shooter);
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
        m_shooter.setFireSpeed(0);
    }

    public void setSupplier(DoubleSupplier s) {
        m_supplier = s;
    }
}
