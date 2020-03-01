package frc.robot.commands;

import static frc.robot.Constants.*;

import frc.robot.Util;
import frc.robot.subsystems.DrivetrainSubsystem;
import java.util.function.DoubleSupplier;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class TeleopArcadeDriveCommand extends CommandBase {

    private final DrivetrainSubsystem m_drive;
    private DoubleSupplier m_speedSupplier, m_rotationSupplier;

    public TeleopArcadeDriveCommand(DrivetrainSubsystem drive) {
        addRequirements(drive);
        m_drive = drive;
    }

    public void setSuppliers(DoubleSupplier left, DoubleSupplier right) {
        m_speedSupplier = left;
        m_rotationSupplier = right;
    }

    @Override
    public void execute() {
        double[] speeds = Util.arcadeToTankDrive(m_speedSupplier.getAsDouble() * ARCADE_SPEED_MULTIPLIER, m_rotationSupplier.getAsDouble() * ARCADE_ROTATION_MULTIPLIER);
        //Convert speeds to target speeds in meters per second, and then divide by hypothetical maximum movement speed
        speeds[0] = speeds[0] * DRIVETRAIN_MAXIMUM_CRUISE_SPEED_METERS_PER_SECOND / DRIVETRAIN_MAXIMUM_MOVEMENT_SPEED_METERS_PER_SECOND;
        speeds[1] = speeds[1] * DRIVETRAIN_MAXIMUM_CRUISE_SPEED_METERS_PER_SECOND / DRIVETRAIN_MAXIMUM_MOVEMENT_SPEED_METERS_PER_SECOND;
        if (m_drive.isReversed()) speeds = new double[] {speeds[1] * -1, speeds[0 * -1]};
        
        m_drive.tankDrive(speeds);
    }

}
