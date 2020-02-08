package frc.robot.commands;

import frc.robot.subsystems.HopperSubsystem;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class RunHopperCommand extends CommandBase {

    private final HopperSubsystem m_hopper;

    public RunHopperCommand(HopperSubsystem hopper) {
        addRequirements(hopper);
        m_hopper = hopper;
    }
    @Override
    public void execute() {
        m_hopper.set(m_hopper.hasBall());
    }

}
