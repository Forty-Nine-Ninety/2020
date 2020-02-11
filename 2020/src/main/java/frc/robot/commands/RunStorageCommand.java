package frc.robot.commands;

import frc.robot.subsystems.StorageSubsystem;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class RunStorageCommand extends CommandBase {

    private final StorageSubsystem m_storage;

    public RunStorageCommand(StorageSubsystem storage) {
        addRequirements(storage);
        m_storage = storage;
    }
    
    @Override
    public void execute() {
        
    }

}
