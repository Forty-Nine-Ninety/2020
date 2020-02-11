package frc.robot.commands;

import frc.robot.subsystems.HopperSubsystem;
import frc.robot.subsystems.StorageSubsystem;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class RunHopperCommand extends CommandBase {

    private final HopperSubsystem m_hopper;
    private final StorageSubsystem m_storage;
    private boolean lastState, override;

    public RunHopperCommand(HopperSubsystem hopper, StorageSubsystem storage) {
        addRequirements(hopper);
        m_hopper = hopper;
        m_storage = storage;

        lastState = m_hopper.hasBall();
    }

    @Override
    public void execute() {
        boolean state = m_hopper.hasBall();

        if (state == false && lastState == true) m_storage.insertBall();

        m_hopper.set(m_hopper.hasBall() || override);
        lastState = m_hopper.hasBall();
    }


    public void setOverride(boolean b) {
        override = b;
    }
}
