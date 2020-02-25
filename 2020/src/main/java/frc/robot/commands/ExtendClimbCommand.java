package frc.robot.commands;

import frc.robot.subsystems.ClimbSubsystem;
import edu.wpi.first.wpilibj2.command.CommandBase;
import static frc.robot.Constants.*;

public class ExtendClimbCommand extends CommandBase {

    private final ClimbSubsystem m_climb;

    public ExtendClimbCommand(ClimbSubsystem climb) {
        addRequirements(climb);
        m_climb = climb;
    }

    @Override
    public void execute() {
        m_climb.runClimb(m_climb.getTopSensors() ? 0 : -1 * CLIMB_MOTOR_SPEED);
    }

    @Override
    public void end(boolean bool) {
        m_climb.setLock(true);
        m_climb.runClimb(0);
    }

}