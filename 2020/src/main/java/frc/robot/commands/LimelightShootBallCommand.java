package frc.robot.commands;

import edu.wpi.first.wpilibj.controller.PIDController;
import frc.robot.subsystems.DrivetrainSubsystem;
import frc.robot.subsystems.ShooterSubsystem;
import frc.robot.subsystems.StorageSubsystem;
import frc.robot.vision.Limelight;
import frc.robot.vision.TargetFinder;

import static frc.robot.Constants.*;

public class LimelightShootBallCommand extends ShootBallCommand {

    private final DrivetrainSubsystem m_drive;
    private PIDController m_pid;

    public LimelightShootBallCommand(ShooterSubsystem shooter, StorageSubsystem storage, DrivetrainSubsystem drive) {
        super(shooter, storage);
        m_drive = drive;
        addRequirements(drive);

        super.setSupplier(() -> TargetFinder.estimateShooterVelocityToTarget());
        m_pid = new PIDController(LIMELIGHT_DRIVETRAIN_KP, LIMELIGHT_DRIVETRAIN_KI, LIMELIGHT_DRIVETRAIN_KD);
    }

    @Override
    public void execute() {
        super.execute();

        //Don't shoot if angle is off as well
        if (Math.abs(Limelight.getCrosshairHorizontalOffset()) > SHOOTER_MAXIMUM_ALLOWED_ANGULAR_ERROR_DEGREES) disableStorage();
        m_drive.arcadeDrive(0, m_pid.calculate(Limelight.getCrosshairHorizontalOffset(), 0));
    }
}
