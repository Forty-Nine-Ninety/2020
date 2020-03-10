package frc.robot.commands;

import edu.wpi.first.wpilibj.trajectory.Trajectory;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.subsystems.DrivetrainSubsystem;

public class MoveOnlyCommand extends SequentialCommandGroup {

    public MoveOnlyCommand(DrivetrainSubsystem drive, Trajectory trajectory){
        addCommands(
            new AutoDriveCommand(drive, trajectory)
        );
    }
}