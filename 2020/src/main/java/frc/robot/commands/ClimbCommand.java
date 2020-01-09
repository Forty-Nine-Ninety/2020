package frc.robot.commands;

import frc.robot.subsystems.ClimbSubsystem;
import java.util.function.DoubleSupplier;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class ClimbCommand extends CommandBase {

    private final ClimbSubsystem m_climb;
    private DoubleSupplier m_speedSupplier;

    public ClimbCommand(ClimbSubsystem climb) {
        addRequirements(climb);
        m_climb = climb;
    }

    public void setSuppliers(DoubleSupplier speed) {
        m_speedSupplier = speed;
    }

    @Override
    public void execute() {
        m_climb.setSpeed(m_speedSupplier.getAsDouble());
    }

}
