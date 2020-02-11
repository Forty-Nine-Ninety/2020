package frc.robot.commands;

import frc.robot.subsystems.DrivetrainSubsystem;
import frc.robot.subsystems.ShooterSubsystem;
import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.vision.Limelight;
import frc.robot.vision.TargetFinder;
import static frc.robot.Constants.*;

public class LimelightTargetCommand extends CommandBase {

    private final DrivetrainSubsystem m_drive;
    private final ShooterSubsystem m_shooter;
    private PIDController m_pidS, m_pidD;

    public LimelightTargetCommand(DrivetrainSubsystem drive, ShooterSubsystem shooter) {
        addRequirements(drive);
        addRequirements(shooter);

        m_drive = drive;
        m_shooter = shooter;
        //Because PID needs to rotate base

        m_pidS = new PIDController(LIMELIGHT_SHOOTER_KP, LIMELIGHT_SHOOTER_KI, LIMELIGHT_SHOOTER_KD);
        m_pidD = new PIDController(LIMELIGHT_DRIVETRAIN_KP, LIMELIGHT_DRIVETRAIN_KI, LIMELIGHT_DRIVETRAIN_KD);
    }

    @Override
    public void execute() {
        m_drive.arcadeDrive(0, m_pidD.calculate(Limelight.getCrosshairHorizontalOffset(), 0));
        m_shooter.fire(m_pidS.calculate(m_shooter.getVelocity(), TargetFinder.estimateShooterVelocityToTarget()));
    }

}
