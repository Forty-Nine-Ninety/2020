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
        m_climb.climb(CLIMB_TARGET_ENCODER_TICKS);
        
        if (CLIMB_LOCK_MAXIMUM_ALLOWED_ERROR > Math.abs(CLIMB_TARGET_ENCODER_TICKS - m_climb.getEncoderTicks())){
            m_climb.setLock(true);
        }
    }

    @Override
    public void end(boolean bool){
        m_climb.climb(0);
    }

}
