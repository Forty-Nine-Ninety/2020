package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.Direction;
import frc.robot.subsystems.DrivetrainSubsystem;
import frc.robot.subsystems.InserterSubsystem;
import frc.robot.subsystems.ShooterSubsystem;
import frc.robot.subsystems.StorageSubsystem;

public class LimelightAimShootCommand extends ParallelCommandGroup {

    public LimelightAimShootCommand(ShooterSubsystem shooter, DrivetrainSubsystem drivetrain, InserterSubsystem inserter, StorageSubsystem storage) {
        
        //No requirements needed, because the commands themselves require the subsystem.
        //addRequirements(m_shooter, m_drivetrain);

        
        addCommands(
            //Make sure the drivetrain and shooter are aimed correctly and spinning
            new LimelightAimCommand(shooter, drivetrain),

            //Unjam storage, then start shooting
            //TODO This sequential command group needs to run 
            new SequentialCommandGroup(
                new UnjamStorageCommand(storage),
                new LimelightWaitForAimCommand(drivetrain, shooter),
                new ParallelCommandGroup(//TODO Change command type to parallelrace so it ends when inserter ends?
                    new CheckRunInserterCommand(inserter, Direction.Forward, () -> shooter.isReady()),
                    new RunStorageCommand(storage, Direction.Forward)
                )
            )
        );
    }
}
