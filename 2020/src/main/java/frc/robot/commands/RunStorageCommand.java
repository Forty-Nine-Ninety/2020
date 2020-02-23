package frc.robot.commands;

import frc.robot.subsystems.HopperSubsystem;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.StorageSubsystem;
import edu.wpi.first.wpilibj2.command.CommandBase;
import static frc.robot.Constants.*;

public class RunStorageCommand extends CommandBase {

    private final StorageSubsystem m_storage;
    private final HopperSubsystem m_hopper;
    private boolean lastHopperState, lastStorageState;

    public RunStorageCommand(StorageSubsystem storage, HopperSubsystem hopper) {
        addRequirements(storage);
        addRequirements(hopper);
        m_storage = storage;
        m_hopper = hopper;

        lastStorageState = m_storage.hasBall();
        lastHopperState = m_hopper.hasBall();
    }
    
    @Override
    public void execute() {

        if (! m_hopper.hasBall() && lastHopperState) m_storage.insertBall();
        if (lastStorageState && ! m_storage.hasBall()) m_storage.removeBall();

        if (m_hopper.hasBall() && m_storage.getBallCount() == 1 && m_storage.getEmptyDistance() >= STORAGE_MINIMUM_BALL_SPACING_ENCODER_UNITS) m_storage.run(false);
        else if (m_storage.getBallCount() == 0 || m_storage.hasBall()) m_storage.run(false);
        else m_storage.run(true);

        if (! m_hopper.hasBall()) m_hopper.set(false);
        else if (m_storage.getEmptyDistance() >= STORAGE_MINIMUM_BALL_SPACING_ENCODER_UNITS) m_hopper.set(true);
        else m_hopper.set(false);


        lastHopperState = m_hopper.hasBall();
        lastStorageState = m_storage.hasBall();
    }

}
