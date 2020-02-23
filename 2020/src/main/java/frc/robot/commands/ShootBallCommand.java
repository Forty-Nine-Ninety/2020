package frc.robot.commands;

import java.util.function.BooleanSupplier;
import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ShooterSubsystem;
import frc.robot.subsystems.StorageSubsystem;
import static frc.robot.Constants.*;

public class ShootBallCommand extends CommandBase {

    private DoubleSupplier m_speed;
    private final ShooterSubsystem m_shooter;
    private final StorageSubsystem m_storage;

    public ShootBallCommand(ShooterSubsystem shooter, StorageSubsystem storage) {
        addRequirements(shooter);
        //Doesn't require storage subsystem
        m_shooter = shooter;
        m_storage = storage;
    }

    @Override
    public void execute() {
        if (m_storage.getBallCount() == 0) m_shooter.setFireSpeed(0);
        else m_shooter.setFireSpeed(m_speed.getAsDouble());
        
        m_storage.setEnabled(getStorageState());
    }

    @Override
    public boolean isFinished() {
        return m_storage.getBallCount() == 0;
    }

    @Override
    public void end(boolean interrupted) {
        m_shooter.setFireSpeed(0);
    }

    public boolean getStorageState() {
        //Make sure ball can be shot
        return Math.abs(m_shooter.getVelocityError()) < SHOOTER_MAXIMUM_ALLOWED_VELOCITY_ERROR;
    }

    public void setSupplier(DoubleSupplier sp) {
        m_speed = sp;
    }

}
