package frc.robot.commands;

import frc.robot.subsystems.ClimbSubsystem;
import edu.wpi.first.wpilibj2.command.CommandBase;
import static frc.robot.Constants.*;

public class RetractClimbCommand extends CommandBase {

    private final ClimbSubsystem m_climb;

    public RetractClimbCommand(ClimbSubsystem climb) {
        addRequirements(climb);
        m_climb = climb;
    }

    @Override
    public void execute() {
        m_climb.runClimb(m_climb.getBottomSensors() ? 0 : -1 * CLIMB_MOTOR_SPEED);
        if (m_climb.getBottomSensors()) this.cancel();
    }

    @Override
    public void end(boolean bool) {
        System.out.println("Stopping climb.");
        m_climb.setLock(true);
        m_climb.runClimb(0);
    }

    @Override
    public boolean isFinished() {
        return m_climb.getBottomSensors();
    }

}