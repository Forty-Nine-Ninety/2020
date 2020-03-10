package frc.robot.commands;

import edu.wpi.first.wpilibj.trajectory.Trajectory;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.subsystems.DrivetrainSubsystem;
import frc.robot.subsystems.ShooterSubsystem;
import frc.robot.subsystems.StorageSubsystem;

public class ShootShieldGenCommand extends SequentialCommandGroup {

        
    public ShootShieldGenCommand(ShooterSubsystem shooter, StorageSubsystem storage, DrivetrainSubsystem drive, Trajectory trajectory){
        addCommands(
            new LimelightShootBallCommand(shooter, storage, drive),
            new AutoDriveCommand(drive, trajectory)
        );
    }
}