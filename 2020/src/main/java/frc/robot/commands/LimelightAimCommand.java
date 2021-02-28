package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import frc.robot.subsystems.DrivetrainSubsystem;
import frc.robot.subsystems.ShooterSubsystem;
import frc.robot.vision.VisionController;

public class LimelightAimCommand extends ParallelCommandGroup {

    public LimelightAimCommand(ShooterSubsystem shooter, DrivetrainSubsystem drivetrain) {
        
        //No requirements needed, because the commands themselves require the subsystem.
        //addRequirements(m_shooter, m_drivetrain);

        //Neither command ever finishes, so this one doesn't either.
        addCommands(
            new RunShooterCommand(shooter, () -> VisionController.getShooterSpeedFromLimelight()),
            new LimelightAimDrivetrainCommand(drivetrain)
        );
    }
}
