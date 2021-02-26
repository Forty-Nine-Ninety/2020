package frc.robot.commands;

import frc.robot.Direction;
import frc.robot.subsystems.StorageSubsystem;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class RunStorageCommand extends CommandBase {

    private final StorageSubsystem m_storage;
    private final Direction direction;

    public RunStorageCommand(StorageSubsystem storage, Direction direction) {
        addRequirements(storage);
        m_storage = storage;

        this.direction = direction;
    }
    
    @Override
    public void execute() {
        m_storage.run(direction);
    
    }

    @Override
    public void end(boolean interrupted) {
        m_storage.run(Direction.Stop);
    }

}
