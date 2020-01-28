package frc.robot.commands;

import frc.robot.subsystems.IntakeSubsystem;
import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj2.command.CommandBase;

public class IntakeCommand extends CommandBase {

    private final IntakeSubsystem m_intake;
    private DoubleSupplier m_speedSupplier;

    public IntakeCommand(IntakeSubsystem intake) {
        addRequirements(intake);
        m_intake = intake;
    }

    public void setSuppliers(DoubleSupplier speed) {
        m_speedSupplier = speed;
    }

    @Override
    public void execute() {
        m_intake.setSpeed(m_speedSupplier.getAsDouble());
    }

}
