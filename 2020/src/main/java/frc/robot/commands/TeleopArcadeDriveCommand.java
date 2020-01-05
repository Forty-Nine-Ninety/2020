package frc.robot.commands;

import frc.robot.subsystems.DrivetrainSubsystem;
import frc.robot.subsystems.DrivetrainSubsystem.DriveMode;
import java.util.function.DoubleSupplier;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class TeleopArcadeDriveCommand extends CommandBase {

    private final DrivetrainSubsystem m_drive;
    private DoubleSupplier m_leftSpeedSupplier, m_rightSpeedSupplier;

    public TeleopArcadeDriveCommand(DrivetrainSubsystem drive) {
        addRequirements(drive);
        m_drive = drive;
        m_drive.setDriveMode(DriveMode.Arcade);
    }

    public void setSuppliers(DoubleSupplier left, DoubleSupplier right) {
        m_leftSpeedSupplier = left;
        m_rightSpeedSupplier = right;
    }

    @Override
    public void execute() {
        m_drive.drive(m_leftSpeedSupplier.getAsDouble(), m_rightSpeedSupplier.getAsDouble());
    }

}
