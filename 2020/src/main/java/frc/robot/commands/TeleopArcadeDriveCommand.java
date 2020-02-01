package frc.robot.commands;

import static frc.robot.Constants.*;
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
        m_drive.tankDrive(convertToTank(m_speedSupplier.getAsDouble(), m_rotationSupplier.getAsDouble()));
    }

    private static double[] convertToTank(double speed, double rot) {
        double left, right, maxSpeed;

        if (Math.abs(speed) > Math.abs(rot)) maxSpeed = speed;
        else maxSpeed = speed > 0 ? Math.abs(rot) : Math.abs(rot) * -1;

        if (speed > 0) {
            if (rot > 0) {
                left = maxSpeed;
                right = speed - rot;
            }
            else {
                left = speed + rot;
                right = maxSpeed;
            }
        }
        else {
            if (rot > 0) {
                left = speed + rot;
                right = maxSpeed;
            }
            else {
                left = maxSpeed;
                right = speed - rot;
            }
        }
        return new double[] {left, right};
    }

}
