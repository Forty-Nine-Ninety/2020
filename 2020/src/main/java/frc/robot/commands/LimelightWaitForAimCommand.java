package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DrivetrainSubsystem;
import frc.robot.subsystems.ShooterSubsystem;

public class LimelightWaitForAimCommand extends CommandBase {

    private final DrivetrainSubsystem m_drivetrain;
    private final ShooterSubsystem m_shooter;

    public LimelightWaitForAimCommand(DrivetrainSubsystem drivetrain, ShooterSubsystem shooter) {
        m_drivetrain = drivetrain;
        m_shooter = shooter;
    }

    @Override
    public void execute() {
    }

    @Override
    public void end(boolean bool) {
    }

    @Override
    public boolean isFinished() {
        return m_drivetrain.isReady() && m_shooter.isReady();
    }
}
