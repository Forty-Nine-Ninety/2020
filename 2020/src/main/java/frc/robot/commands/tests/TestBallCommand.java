package frc.robot.commands.tests;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.HopperSubsystem;
import frc.robot.subsystems.ShooterSubsystem;

public class TestBallCommand extends CommandBase{

    private final ShooterSubsystem m_shooter;
    private final HopperSubsystem m_hopper;

    private final double SHOOTER_FIRE_PERCENTAGE = 0.8;

    public TestBallCommand(ShooterSubsystem shooter, HopperSubsystem hopper){
        addRequirements(shooter);
        addRequirements(hopper);

        m_shooter = shooter;
        m_hopper = hopper;
    }

    @Override
    public void execute() {
        m_shooter.setFireSpeed(SHOOTER_FIRE_PERCENTAGE);

        m_hopper.setReverse(true);
        Timer.delay(0.5);
        m_hopper.setReverse(false);

        m_shooter.runInserter(SHOOTER_FIRE_PERCENTAGE);
        m_hopper.set(true);


    }


}
