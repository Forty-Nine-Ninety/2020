package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.Direction;
import frc.robot.subsystems.StorageSubsystem;

public class UnjamStorageCommand extends SequentialCommandGroup{
    
    private final StorageSubsystem m_storage;

    public UnjamStorageCommand(StorageSubsystem storage) {
        m_storage = storage;

        addCommands(
            new RunStorageCommand(m_storage, Direction.Reverse),
            new WaitCommand(0.5)
        );
    }
    
}
