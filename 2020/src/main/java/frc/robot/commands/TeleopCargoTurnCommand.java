package frc.robot.commands;

import frc.robot.subsystems.CargoSubsystem;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj2.command.CommandBase;

public class TeleopCargoTurnCommand extends CommandBase {

    private final CargoSubsystem m_cargo;
    private DoubleSupplier m_speedSupplier;

    public TeleopCargoTurnCommand(CargoSubsystem cargo) {
        m_cargo = cargo;
        addRequirements(cargo);
    }

    public void setSupplier(DoubleSupplier speed) {
        m_speedSupplier = speed;
    }

    @Override
    public void execute() {
        m_cargo.setSpeed(m_speedSupplier.getAsDouble());
    }
}
