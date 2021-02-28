package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import frc.robot.subsystems.DrivetrainSubsystem;
import frc.robot.subsystems.ShooterSubsystem;
import frc.robot.vision.VisionShooterSpeedController;

public class AutoAimCommand extends ParallelCommandGroup {

    public AutoAimCommand(ShooterSubsystem shooter, DrivetrainSubsystem drivetrain) {
        
        //No requirements needed, because the commands themselves require the subsystem.
        //addRequirements(m_shooter, m_drivetrain);

        //Neither command ever finishes, so this one doesn't either.
        addCommands(
            new RunShooterCommand(shooter, () -> VisionShooterSpeedController.getSpeedFromLimelight()),
            new LimelightAimDrivetrainCommand(drivetrain)
        );
    }
}
