package frc.robot.commands;

import frc.robot.subsystems.ClimbSubsystem;
import frc.robot.subsystems.DrivetrainSubsystem;
import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj2.command.CommandBase;
import static frc.robot.Constants.*;

public class BalanceClimbCommand extends CommandBase {

    private final DrivetrainSubsystem m_drivetrain;
    private final PIDController m_pid;
    private final ClimbSubsystem m_climb;

    public BalanceClimbCommand(ClimbSubsystem climb, DrivetrainSubsystem drivetrain) {
        addRequirements(climb);
        m_drivetrain = drivetrain;
        m_climb = climb;
        m_pid = new PIDController(MotionControl.CLIMB_BALANCE_KP, MotionControl.CLIMB_BALANCE_KI, MotionControl.CLIMB_BALANCE_KD);
    }

    @Override
    public void execute() {
        double tilt = m_drivetrain.getGyroTilt();
        double val = m_pid.calculate(tilt, 0.0);
        m_climb.runBalance(val);
    }

}
