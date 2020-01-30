package frc.robot.commands;

import frc.robot.subsystems.CargoSubsystem;
import java.util.function.DoubleSupplier;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class TeleopRunCargoCommand extends CommandBase {

    private final CargoSubsystem m_cargo;
    private DoubleSupplier m_in, m_out;

    public TeleopRunCargoCommand(CargoSubsystem cargo) {
        addRequirements(cargo);
        m_cargo = cargo;
    }

    public void setSuppliers(DoubleSupplier t1, DoubleSupplier t2) {
        m_in = t1;
        m_out = t2;
    }

    @Override
    public void execute() {
        if (m_in.getAsDouble() > 0.05 && m_out.getAsDouble() > 0.05) m_cargo.run(0);
        else if (m_in.getAsDouble() > 0.05) m_cargo.run(m_in.getAsDouble());
        else if (m_out.getAsDouble() > 0.05) m_cargo.run(m_out.getAsDouble());
        else m_cargo.run(0);
    }

}
