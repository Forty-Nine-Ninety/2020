package frc.robot.commands;

import frc.robot.subsystems.ClimbSubsystem;
import frc.robot.subsystems.DrivetrainSubsystem;
import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj2.command.CommandBase;
import static frc.robot.Constants.*;

public class BalanceCommand extends CommandBase {

    private final DrivetrainSubsystem m_drive;
    private final PIDController m_pid;
    private final ClimbSubsystem m_climb;

    public BalanceCommand(DrivetrainSubsystem drive, ClimbSubsystem climb) {
        addRequirements(climb);
        m_drive = drive;
        m_climb = climb;
        m_pid = new PIDController(BALANCE_KP, BALANCE_KI, BALANCE_KD);
    }

    @Override
    public void execute() {
        double tilt = m_drive.getGyroTilt();
        double val = m_pid.calculate(tilt, 0.0);
        m_climb.runBalance(val);
    }

}
