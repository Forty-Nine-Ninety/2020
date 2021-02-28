package frc.robot.commands;

import frc.robot.subsystems.DrivetrainSubsystem;
import java.util.function.DoubleSupplier;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class TeleopTankDriveCommand extends CommandBase {

    private final DrivetrainSubsystem m_drivetrain;
    private DoubleSupplier m_leftSpeedSupplier, m_rightSpeedSupplier;

    public TeleopTankDriveCommand(DrivetrainSubsystem drivetrain) {
        m_drivetrain = drivetrain;
        addRequirements(drivetrain);
    }

    public void setSuppliers(DoubleSupplier left, DoubleSupplier right) {
        m_leftSpeedSupplier = left;
        m_rightSpeedSupplier = right;
    }

    @Override
    public void execute() {
        m_drivetrain.tankDrive(m_leftSpeedSupplier.getAsDouble(), m_rightSpeedSupplier.getAsDouble());
    }

}
