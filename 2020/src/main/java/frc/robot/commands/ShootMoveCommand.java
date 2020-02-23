package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.subsystems.ShooterSubsystem;
import frc.robot.subsystems.StorageSubsystem;
import frc.robot.subsystems.DrivetrainSubsystem;

public class ShootMoveCommand extends SequentialCommandGroup{

    public ShootMoveCommand(ShooterSubsystem shooter, StorageSubsystem storage, DrivetrainSubsystem drive){
        addCommands(
            new LimelightShootBallCommand(shooter, storage, drive),
            new AutoDriveCommand(drive)
        );
    }

    @Override
    public void execute(){
    }
}