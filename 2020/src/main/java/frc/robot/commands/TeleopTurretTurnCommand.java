package frc.robot.commands;

import frc.robot.subsystems.TurretSubsystem;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj2.command.CommandBase;

public class TeleopTurretTurnCommand extends CommandBase {

    private final TurretSubsystem m_turret;
    private DoubleSupplier m_speedSupplier;

    public TeleopTurretTurnCommand(TurretSubsystem turret) {
        m_turret = turret;
        addRequirements(turret);
    }

    public void setSupplier(DoubleSupplier speed) {
        m_speedSupplier = speed;
    }

    @Override
    public void execute() {
        m_turret.setSpeed(m_speedSupplier.getAsDouble());
    }
}
