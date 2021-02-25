package frc.robot.commands;

import frc.robot.subsystems.HopperSubsystem;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class RunHopperCommand extends CommandBase {

    private final HopperSubsystem m_hopper;
    private final boolean m_reverse;

    public RunHopperCommand(HopperSubsystem hopper, boolean reverse) {
        addRequirements(hopper);
        m_hopper = hopper;
        m_reverse = reverse;
    }

    @Override
    public void execute() {
        if (m_reverse) {
            m_hopper.setReverse(true);
        } else {
            m_hopper.set(true);
        }
    }

    @Override
    public void end(boolean interrupted) {
        if (m_reverse) {
            m_hopper.setReverse(false);
        } else {
            m_hopper.set(false);
        }
    }
}