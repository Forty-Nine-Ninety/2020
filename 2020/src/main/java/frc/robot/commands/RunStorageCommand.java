package frc.robot.commands;

import frc.robot.subsystems.StorageSubsystem;
import edu.wpi.first.wpilibj2.command.CommandBase;
import static frc.robot.Constants.*;

public class RunStorageCommand extends CommandBase {

    private final StorageSubsystem m_storage;

    public RunStorageCommand(StorageSubsystem storage) {
        addRequirements(storage);
        m_storage = storage;
    }
    
    @Override
    public void execute() {
        if (m_storage.getBallsLow() == 0) m_storage.runLow(false);
        else if (m_storage.getLastEntered() > STORAGE_MINIMUM_BALL_SPACING_ENCODER_UNITS || m_storage.hasBallLow()) m_storage.runLow(true);
        else m_storage.runLow(false);
    }

}
