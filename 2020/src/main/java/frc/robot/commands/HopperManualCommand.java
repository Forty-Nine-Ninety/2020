package frc.robot.commands;

import frc.robot.subsystems.HopperSubsystem;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class HopperManualCommand extends CommandBase{

    private final HopperSubsystem m_hopper;

    public HopperManualCommand(HopperSubsystem hopper) {
        addRequirements(hopper);
        m_hopper = hopper;
    }

    @Override
    public void execute() {
        m_hopper.set(true);
    }

    @Override
    public void end(boolean interrupted) {
        m_hopper.set(false);
    }
}