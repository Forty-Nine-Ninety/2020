package frc.robot.commands;

import frc.robot.Direction;
import frc.robot.subsystems.StorageSubsystem;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class RunStorageCommand extends CommandBase {

    private final StorageSubsystem m_storage;
    private final Direction direction;
    private final long startTime;

    public RunStorageCommand(StorageSubsystem storage, Direction direction) {
        addRequirements(storage);
        m_storage = storage;

        this.direction = direction;
        
        startTime = System.currentTimeMillis();
    }
    
    @Override
    public void execute() {
        m_storage.run(direction);
    
    }

    @Override
    public boolean isFinished() {
        if (direction.compareTo(Direction.Reverse) == 0){
            if (System.currentTimeMillis() - startTime >= 100) {
                return true;
            }
        }

        return false;
    }

    @Override
    public void end(boolean interrupted) {
        m_storage.run(Direction.Stop);
    }

}
