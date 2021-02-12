package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.HopperSubsystem;
import frc.robot.subsystems.ShooterSubsystem;
import frc.robot.subsystems.StorageSubsystem;

public class BallMotorsCommand extends CommandBase{

    private final ShooterSubsystem m_shooter;
    private final StorageSubsystem m_storage;
    private final HopperSubsystem m_hopper;

    private final double SHOOTER_FIRE_PERCENTAGE = 0.8;

    public BallMotorsCommand(ShooterSubsystem shooter, StorageSubsystem storage, HopperSubsystem hopper){
        addRequirements(shooter);
        addRequirements(storage);
        addRequirements(hopper);

        m_shooter = shooter;
        m_storage = storage;
        m_hopper = hopper;
    }

    @Override
    public void execute() {
        while (m_shooter.getVelocity() != SHOOTER_FIRE_PERCENTAGE) {
            m_shooter.setFireSpeed(SHOOTER_FIRE_PERCENTAGE);
        }

        m_storage.setEnabled(true);
        m_storage.manualSpeed(-1);
        m_hopper.set(true);
        Timer.delay(0.5);

        m_shooter.runInserter(SHOOTER_FIRE_PERCENTAGE);
        m_storage.manualSpeed(1);
        m_hopper.set(true);


    }


}
