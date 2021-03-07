package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.ParallelDeadlineGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.Direction;
import frc.robot.subsystems.StorageSubsystem;

import static frc.robot.Constants.*;

public class UnjamStorageCommand extends SequentialCommandGroup {
    
    private final StorageSubsystem m_storage;

    public UnjamStorageCommand(StorageSubsystem storage) {
        m_storage = storage;

        addCommands(
            new ParallelDeadlineGroup(new WaitCommand(SubsystemConfig.STORAGE_UNJAM_REVERSE_TIME), new RunStorageCommand(m_storage, Direction.Reverse)),
            new WaitCommand(SubsystemConfig.STORAGE_UNJAM_WAIT_TIME)
        );
    }
    
}
