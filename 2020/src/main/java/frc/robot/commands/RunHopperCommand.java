package frc.robot.commands;

import frc.robot.Direction;
import frc.robot.subsystems.HopperSubsystem;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class RunHopperCommand extends CommandBase {

    private final HopperSubsystem m_hopper;
    private final Direction direction;

    public RunHopperCommand(HopperSubsystem hopper, Direction direction) {
        addRequirements(hopper);
        m_hopper = hopper;
        
        this.direction = direction;
    }

    @Override
    public void execute() {
        m_hopper.run(direction);
    }

    @Override
    public void end(boolean interrupted) {
        m_hopper.run(Direction.Stop);
    }
}