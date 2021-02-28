package frc.robot.commands;

import static frc.robot.Constants.*;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DrivetrainSubsystem;

public class LimelightAimDrivetrainCommand extends CommandBase {

    private final DrivetrainSubsystem m_drivetrain;

    public LimelightAimDrivetrainCommand(DrivetrainSubsystem drivetrain) {
        m_drivetrain = drivetrain;
        addRequirements(m_drivetrain);
    }

    @Override
    public void execute() {
        //TODO
    }

    @Override
    public void end(boolean bool) {
        //TODO
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}
