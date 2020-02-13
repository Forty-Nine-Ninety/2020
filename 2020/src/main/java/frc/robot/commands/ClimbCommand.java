package frc.robot.commands;

import frc.robot.subsystems.ClimbSubsystem;
import edu.wpi.first.wpilibj2.command.CommandBase;
import static frc.robot.Constants.*;

public class ClimbCommand extends CommandBase {

    private final ClimbSubsystem m_climb;

    public ClimbCommand(ClimbSubsystem climb) {
        addRequirements(climb);
        m_climb = climb;
    }

    @Override
    public void execute() {
        m_climb.climb(CLIMB_ENCODER_TICKS);
    }

}
