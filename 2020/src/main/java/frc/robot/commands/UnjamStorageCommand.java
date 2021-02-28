package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import frc.robot.subsystems.StorageSubsystem;

public class UnjamStorageCommand extends ParallelCommandGroup {

    public UnjamStorageCommand(StorageSubsystem storage) {
        
        
        addCommands(
            //TODO unjam storage
        );
    }
}
