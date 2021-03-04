package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import frc.robot.Direction;
import frc.robot.subsystems.HopperSubsystem;
import frc.robot.subsystems.StorageSubsystem;

public class StorageButtonCommand extends ParallelCommandGroup{

    private final HopperSubsystem m_hopper;
    private final StorageSubsystem m_storage;

    public StorageButtonCommand(HopperSubsystem hopper, StorageSubsystem storage) {
        m_hopper = hopper;
        m_storage = storage;
        
        addCommands(
           new RunHopperCommand(m_hopper, Direction.Forward),
           new RunStorageCommand(m_storage, Direction.Forward)
        );
    }
    
}
