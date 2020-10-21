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
        m_climb.setLock(false);
        m_climb.runClimb(m_climb.getTopSensors() ? 0 : CLIMB_MOTOR_SPEED);
        if (m_climb.getTopSensors()) this.cancel();
    }

    @Override
    public void end(boolean bool) {
        System.out.println("Stopping climb.");
        m_climb.setLock(true);
        m_climb.runClimb(0);
    }

    @Override
    public boolean isFinished() {
        return m_climb.getTopSensors();
    }

}