package frc.robot.commands;

import frc.robot.subsystems.DrivetrainSubsystem;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj.kinematics.DifferentialDriveWheelSpeeds;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class TeleopTankDriveCommand extends CommandBase {

    private final DrivetrainSubsystem m_drive;
    private DoubleSupplier m_leftSpeedSupplier, m_rightSpeedSupplier;

    public TeleopTankDriveCommand(DrivetrainSubsystem drive) {
        addRequirements(drive);
        m_drive = drive;
    }

    public void setSuppliers(DoubleSupplier left, DoubleSupplier right) {
        m_leftSpeedSupplier = left;
        m_rightSpeedSupplier = right;
    }

    @Override
    public void execute() {
        m_drive.setSpeeds(new DifferentialDriveWheelSpeeds(m_leftSpeedSupplier.getAsDouble(), m_rightSpeedSupplier.getAsDouble()));
    }

}
