package frc.robot.commands;

import frc.robot.subsystems.ShooterSubsystem;
import frc.robot.subsystems.StorageSubsystem;

//TODO figure out which kind of command this should be
public class LimelightShootBallCommand extends ShootBallCommand {


    public LimelightShootBallCommand(ShooterSubsystem shooter, StorageSubsystem storage) {
        super(shooter, storage);
    }
}
